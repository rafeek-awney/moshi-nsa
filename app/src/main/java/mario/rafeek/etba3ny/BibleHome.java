package mario.rafeek.etba3ny;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class BibleHome extends AppCompatActivity {
    final Context context = this;

    //bible go to activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SettingData.getTheme(context));
        setContentView(R.layout.bible_home);
        ///////////////////////////////////////////////

        ///////////set part spinner continents/////////
        final Spinner PartSpinner =  findViewById(R.id.PartSpinner);
        ArrayAdapter<String> partAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, BibleData.bible_parts);
        partAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        PartSpinner.setAdapter(partAdapter);
        ////////////////set asfar spinner first open//////////////
        final Spinner AsfarSpinner =  findViewById(R.id.AsfarSpinner);
        final ArrayAdapter<String> asfarAdapter[] = new ArrayAdapter[2];
        asfarAdapter[0] = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, BibleData.bible_titles[0]);
        asfarAdapter[1] = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, BibleData.bible_titles[1]);
        asfarAdapter[0].setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        asfarAdapter[1].setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AsfarSpinner.setAdapter(asfarAdapter[0]);
        ///////////change asfar spinner due to selected part////////////
        PartSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AsfarSpinner.setAdapter(asfarAdapter[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ///////////////set chapter spinner due to sefr selected//////////
        final Spinner ChapterSpinner =  findViewById(R.id.ChapterSpinner);
        AsfarSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int chap_temp = BibleData.bible_chapters[PartSpinner.getSelectedItemPosition()][position];
                String[] temp = new String[chap_temp];
                for (int i = 0; i < chap_temp; i++)
                    temp[i] = String.valueOf(i + 1);
                ArrayAdapter<String> chapAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, temp);
                chapAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                ChapterSpinner.setAdapter(chapAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ////////////go to button code///////
        final LinearLayout btnGoto =  findViewById(R.id.btGoto);

        btnGoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                BibleData.Bp = PartSpinner.getSelectedItemPosition();
                BibleData.Ba = AsfarSpinner.getSelectedItemPosition();
                BibleData.Bc = ChapterSpinner.getSelectedItemPosition();
                Intent intent = new Intent(context, BibleDisplay.class);
                startActivity(intent);

            }

        });
        ////////////select for me button code//////////
        final LinearLayout btnGoLuck =  findViewById(R.id.btGoLuck);

        btnGoLuck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                BibleData.Bp = (int) ((System.currentTimeMillis() % 17) % 2);
                BibleData.Ba = (int) (System.currentTimeMillis() % BibleData.bible_names[BibleData.Bp].length);
                BibleData.Bc = (int) (System.currentTimeMillis() % BibleData.bible_chapters[BibleData.Bp][BibleData.Ba]);
                Intent intent = new Intent(context, BibleDisplay.class);
                startActivity(intent);

            }

        });
        ////////////////////////////
        final LinearLayout btnSearch =  findViewById(R.id.btSrchStart);

        btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, BibleSearchActivity.class);
                startActivity(intent);

            }

        });
        ////////////////////////////
        final LinearLayout btnRegularRead =  findViewById(R.id.bt_regular_read);

        btnRegularRead.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                BibleData.Bp= SettingData.getBookmarkedTestament();
                BibleData.Ba= SettingData.getBookmarkedChapter();
                BibleData.Bc= SettingData.getBookmarkedVerse();
                Intent intent = new Intent(context, BibleDisplay.class);
                startActivity(intent);

            }

        });
    }

}