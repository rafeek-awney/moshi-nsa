package mario.rafeek.etba3ny;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HistoryPopeList extends AppCompatActivity {
    // search display class
    String[] popePath;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        popePath = null;
        List<String> popesNames;
        //////////////////////////////
        setTheme(SettingData.getTheme(context));
        setContentView(R.layout.history_pope_list);

        final ListView dispList = (ListView) findViewById(R.id.popeList);
        ///////////////////////////////////////////////
        AssetManager am = getResources().getAssets();
        try {
            popePath = am.list("popes");
        } catch (IOException e) {
            e.printStackTrace();
        }
        popesNames = readPathTitle(popePath);

        /////////////////////////////////////
        List<String> popesCodes = new ArrayList<>();
        for (int j = 0; j < popePath.length; j++) {
            popesCodes.add((j + 1) + " -" + popesNames.get(j));
        }

        ////////////////////////////////////////
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, popesCodes);
        ///////////////////////////////////////////////
        dispList.setAdapter(adapter);
        //////////go to selected item(chapter) from search resalts//////////////
        AdapterView.OnItemClickListener aa = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                SettingData.currentPopeFileName = popePath[position];
                Intent intent = new Intent(context, HistoryPopeDisplay.class);
                startActivity(intent);
            }
        };
        dispList.setOnItemClickListener(aa);
        dispList.requestFocus();

    }


    ////////////////read raw  file to list  using filename ////////////////////
    List<String> readPathTitle(String[] FileNames) {
        List<String> temp = new ArrayList<>();

        for (String FileName : FileNames) {
            try {
                InputStream inputStream = getAssets().open("popes/" + FileName);
                InputStreamReader inputreader = new InputStreamReader(inputStream);
                BufferedReader bufferedreader = new BufferedReader(inputreader);
                String line = bufferedreader.readLine();
                temp.add(line);
                inputStream.close();
            } catch (IOException e) {
                return null;
            }
        }

        return temp;
    }
}
/////////////////////////////////////////
