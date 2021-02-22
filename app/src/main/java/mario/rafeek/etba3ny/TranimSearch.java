package mario.rafeek.etba3ny;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import mario.rafeek.etba3ny.helpers.TranimSqlHelper;

public class TranimSearch extends AppCompatActivity {
    // tranim list class
    String TAG = "still_alive";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context = this;
        setTheme(SettingData.getTheme(context));
        setContentView(R.layout.tranim_search);
        //SettingData.setActionBar(this, R.id.actionbar, "", false);
        ///////////////////////////////////////////////
        //////////////////////////////////////////////
        final ListView dispList = (ListView) findViewById(R.id.listDisp);
        //TranimData.MainPosition=0;
        TranimData.tranimModules = new TranimSqlHelper(context).getName("");
        TranimListAdapter tranimListAdapter = new TranimListAdapter(context, TranimData.tranimModules);
        dispList.setAdapter(tranimListAdapter);
        dispList.requestFocus();

        /////////////////////////////////////////////////////////////////////////////
        AdapterView.OnItemClickListener tranimListener = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                TranimData.currentID = TranimData.tranimModules.get(position).getId();
                Intent intent = new Intent(context, TranimDisplay.class);
                startActivity(intent);
                finish();
            }
        };
        dispList.setOnItemClickListener(tranimListener);

        ///////////////////// tranim code go to ///////////////////
        final EditText etSearchT = (EditText) findViewById(R.id.et_search_Tranim);
        etSearchT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                TranimData.tranimModules = new TranimSqlHelper(context).getName(charSequence.toString());
                TranimListAdapter tranimListAdapter2 = new TranimListAdapter(context, TranimData.tranimModules);
                dispList.setAdapter(tranimListAdapter2);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etSearchT.requestFocus();
        dispList.setFocusable(false);

            /*    setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String temp = etGotoT.getText().toString();
                Cursor row=new TranimSqlHelper(context).getRow(temp);
                if(row.getCount()>0){
                    TranimData.currentID=temp;
                    Intent intent = new Intent(context, TranimDisplay.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "الكود غير موجود", Toast.LENGTH_LONG).show();
                }

            }

        });*/
    }
}
