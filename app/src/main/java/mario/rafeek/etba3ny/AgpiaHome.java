package mario.rafeek.etba3ny;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AgpiaHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context = this;
        setTheme(SettingData.getTheme(context));
        setContentView(R.layout.agpia_home);
        ///////////////////////////////////////////////
        final Spinner PartSpinner =  findViewById(R.id.AgPartSpinner);
        final ListView ChList =  findViewById(R.id.AgChList);
        ///////////// get agbia data from json /////////////
        AgpiaData.agpiaPrayerList= AgpiaData.getAgpiaDataFromJSON(context);
        ///////////////////////////////////////////////////
        List<String> agpiaPrayerNames= new ArrayList<>();
        for(int i = 0; i< AgpiaData.agpiaPrayerList.size(); i++) agpiaPrayerNames.add(AgpiaData.agpiaPrayerList.get(i).getTitle());
        ArrayAdapter<String> partAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, agpiaPrayerNames);
        partAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        PartSpinner.setAdapter(partAdapter);
        //////////agbia chapters adaptors////////
        final ArrayAdapter<String> ChAdapter =new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, AgpiaData.agpiaPrayerList.get(0).getTitles());
        ChList.setAdapter(ChAdapter);
        ////////////////change chapter adabtor due to chosen part//////////////
        PartSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final ArrayAdapter<String> ChAdapter =new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, android.R.id.text1, AgpiaData.agpiaPrayerList.get(position).getTitles());
                ChList.setAdapter(ChAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /////////////////////////////////
        AdapterView.OnItemClickListener ChapterListener = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(context, AgpiaDisplay.class);
                intent.putExtra("agpia_prayer",PartSpinner.getSelectedItemPosition());
                intent.putExtra("agpia_chapter",position);
                startActivity(intent);
            }
        };
        ChList.setOnItemClickListener(ChapterListener);


    }

}