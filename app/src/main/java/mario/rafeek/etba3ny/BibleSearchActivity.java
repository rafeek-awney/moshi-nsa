package mario.rafeek.etba3ny;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


import java.util.List;

import mario.rafeek.etba3ny.modules.textFile;

public class BibleSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context = this;
        setTheme(SettingData.getTheme(context));
        setContentView(R.layout.bible_search_activity);
        ///////////////////////////////////////////////
        ////////////get search word/////////////
        final EditText srch_word= findViewById(R.id.EditSearch);
        final ListView dispList = findViewById(R.id.search_list);
        ////////////spinner for search options/////////
        final Spinner main_spinner=(Spinner) findViewById(R.id.mainSrchSpinner);
        ArrayAdapter<String> mainAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, BibleSearchData.main_parts);
        mainAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        main_spinner.setAdapter(mainAdapter);
        /////////////prepare for select sefr spinner/////////////////
        final Spinner sec_spinner=(Spinner) findViewById(R.id.secondSrchSpinner);
        ArrayAdapter<String> secAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item);
        secAdapter.addAll(BibleData.bible_titles[0]);
        secAdapter.addAll(BibleData.bible_titles[1]);
        secAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sec_spinner.setAdapter(secAdapter);
        sec_spinner.setEnabled(false);
        ///////////////manage sec spinner dis/enable///////////////
       main_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               if (position == 3)
                  sec_spinner.setEnabled(true);
               else
                   sec_spinner.setEnabled(false);
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
        ////////////clear previous results + prepare search locations//////////////
        /////////// start search  + prepare search results  /////////////
        final ImageButton btnSearchStart = findViewById(R.id.btSearch);
        final textFile searchFile=new textFile();
        btnSearchStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String S_word = srch_word.getText().toString().replace("ى","ي");
                if (S_word.equals("") || S_word.equals(" ")) {
                    dispList.setAdapter(null);
                    Toast.makeText(getApplicationContext(),"ادخل كلمه للبحث",
                            Toast.LENGTH_LONG).show();
                } else {
                    BibleSearchData.results.clear();
                    BibleSearchData.go_result_p.clear();
                    BibleSearchData.go_result_a.clear();
                    BibleSearchData.go_result_c.clear();
                    int p, a;
                    if (main_spinner.getSelectedItemPosition() == 3) {
                        p = (sec_spinner.getSelectedItemPosition() / 46);
                        a = (sec_spinner.getSelectedItemPosition() % 46);
                        String ChName = BibleData.bible_names[p][a]+"/b"+ BibleData.bible_names[p][a];
                        for (int j = 0; j < BibleData.bible_chapters[p][a]; j++) {
                            searchFile.readAsset(context,"bible_search/" + ChName + String.valueOf(j + 1));
                            search_engine(searchFile.list, S_word, p, a, j);
                        }
                    }
                    if (main_spinner.getSelectedItemPosition() == 2) {
                        for (int i = 0; i < 27; i++) {
                            String ChName = BibleData.bible_names[1][i]+"/b" + BibleData.bible_names[1][i];
                            for (int j = 0; j < BibleData.bible_chapters[1][i]; j++) {
                                searchFile.readAsset(context,"bible_search/"+ChName + String.valueOf(j + 1));
                                        search_engine(searchFile.list, S_word, 1, i, j);
                            }
                        }
                    }
                    if (main_spinner.getSelectedItemPosition() == 1) {
                        for (int i = 0; i < 46; i++) {
                            String ChName = BibleData.bible_names[0][i]+"/b"+ BibleData.bible_names[0][i];
                            for (int j = 0; j < BibleData.bible_chapters[0][i]; j++) {
                                searchFile.readAsset(context,"bible_search/"+ChName + String.valueOf(j + 1));
                                search_engine(searchFile.list, S_word,0,i,j);
                            }
                        }
                    }
                    if (main_spinner.getSelectedItemPosition() == 0) {
                        for (int i = 0; i < 73; i++) {
                            p = i / 46;
                            a = i % 46;
                            String ChName = BibleData.bible_names[p][a]+"/b" + BibleData.bible_names[p][a];
                            for (int j = 0; j < BibleData.bible_chapters[p][a]; j++) {
                                searchFile.readAsset(context,"bible_search/"+ChName + String.valueOf(j + 1));
                                search_engine(searchFile.list, S_word,p,a,j);
                            }
                        }
                    }
                    //BibleSearchData.results.add(String.valueOf(main_spinner.getSelectedItemPosition()+" "+String.valueOf(sec_spinner.getSelectedItemPosition())));
                    if(BibleSearchData.results.size()>0){
                        // display search results in list
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, android.R.id.text1, BibleSearchData.results);
                        dispList.setAdapter(adapter);
                        //////////go to selected item(chapter) from search resalts//////////////
                        AdapterView.OnItemClickListener aa = new AdapterView.OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view,
                                                    int position, long id) {
                                BibleData.Bp = BibleSearchData.go_result_p.get(position);
                                BibleData.Ba = BibleSearchData.go_result_a.get(position);
                                BibleData.Bc = BibleSearchData.go_result_c.get(position);
                                Intent intent = new Intent(context, BibleDisplay.class);
                                startActivity(intent);
                            }
                        };
                        dispList.setOnItemClickListener(aa);
                    }
                    else {
                        dispList.setAdapter(null);
                        Toast.makeText(getApplicationContext(), "لا توجد نتائج بحث", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
        }


    /////////////////search in spesefic chapter +add to search results/////////////////////
    protected static void search_engine(List<String> input,String word,int p,int a,int c){
        for (int j=0;j<input.size();j++)
            if (input.get(j).contains(word)){
                BibleSearchData.go_result_p.add(p);
                BibleSearchData.go_result_a.add(a);
                BibleSearchData.go_result_c.add(c);
                String temp= BibleData.bible_titles[p][a] +"   "+"اصحاح " +String.valueOf(c+1)+"\n";
                BibleSearchData.results.add(temp+input.get(j));
            }
    }
}