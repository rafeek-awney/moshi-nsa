package mario.rafeek.etba3ny;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class FeelHome extends AppCompatActivity {

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SettingData.getTheme(context));
        setContentView(R.layout.feel_home);
        //SettingData.setActionBar(this, R.id.actionbar, "", false);
        ///////////////////////////////////////////////
        /////////////////////////////////
        final ListView feelList = (ListView) findViewById(R.id.feelList);
        ArrayAdapter<String> feelAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, BibleSearchData.feelings);
        feelList.setAdapter(feelAdapter);
        /////////////////////////////////
        AdapterView.OnItemClickListener feelListener = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                BibleData.Bp = 0;
                BibleData.Ba = BibleSearchData.feelings_director[0][position];
                BibleData.Bc = BibleSearchData.feelings_director[1][position];
                Intent intent = new Intent(context, BibleDisplay.class);
                startActivity(intent);
            }
        };
        feelList.setOnItemClickListener(feelListener);
    }

}