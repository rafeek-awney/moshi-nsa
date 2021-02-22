package mario.rafeek.etba3ny;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import mario.rafeek.etba3ny.helpers.TranimSqlHelper;

public class TranimList extends AppCompatActivity {
    // tranim list class
    String TAG = "still_alive";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context = this;
        setTheme(SettingData.getTheme(context));
        setContentView(R.layout.tranim_list);
        //SettingData.setActionBar(this, R.id.actionbar, "", false);
        ///////////////////////////////////////////////
        //////////////////////////////////////////////
        final Spinner lettersSpinner = (Spinner) findViewById(R.id.spinner2);
        final ListView dispList = (ListView) findViewById(R.id.listDisp);
        ArrayAdapter<String> lettersAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, TranimData.letters);
        lettersSpinner.setAdapter(lettersAdapter);
        //TranimData.MainPosition=0;
        Log.d("tranim pos", "" + TranimData.MainPosition);
        lettersSpinner.setSelection(TranimData.MainPosition);
        TranimData.tranimModules = new TranimSqlHelper(context).getPath(TranimData.lettersFolders[TranimData.MainPosition], false);
        TranimListAdapter tranimListAdapter = new TranimListAdapter(context, TranimData.tranimModules);
        dispList.setAdapter(tranimListAdapter);
        dispList.requestFocus();
        //////////////////////////////////////////////////////
        AdapterView.OnItemSelectedListener LettersListner = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TranimData.MainPosition = position;
                TranimData.tranimModules = new TranimSqlHelper(context).getPath(TranimData.lettersFolders[TranimData.MainPosition], false);
                TranimListAdapter tranimListAdapter2 = new TranimListAdapter(context, TranimData.tranimModules);
                dispList.setAdapter(tranimListAdapter2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        Log.d(TAG, "onCreate: 3");

        lettersSpinner.setOnItemSelectedListener(LettersListner);
        /////////////////////////////////////////////////////////////////////////////
        AdapterView.OnItemClickListener tranimListener = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                TranimData.currentID = TranimData.tranimModules.get(position).getId();
                Intent intent = new Intent(context, TranimDisplay.class);
                startActivity(intent);
            }
        };
        dispList.setOnItemClickListener(tranimListener);
        ///////////////////////////////////////////////////
        ToggleButton BtArrange = (ToggleButton) findViewById(R.id.btArrangeTranim);
        BtArrange.setVisibility(View.VISIBLE);
        BtArrange.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TranimData.tranimModules = new TranimSqlHelper(context).getPath(TranimData.lettersFolders[TranimData.MainPosition], isChecked);
                TranimListAdapter tranimListAdapter2 = new TranimListAdapter(context, TranimData.tranimModules);
                dispList.setAdapter(tranimListAdapter2);

            }
        });
        Log.d(TAG, "onCreate: 4");
        ///////////////////// tranim code go to ///////////////////
        ImageButton btGotoT = (ImageButton) findViewById(R.id.btGoto_Tranim);
        final EditText etGotoT = (EditText) findViewById(R.id.etGoto_Tranim);
        btGotoT.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String temp = etGotoT.getText().toString();
                Cursor row = new TranimSqlHelper(context).getRow(temp);
                if (row.getCount() > 0) {
                    TranimData.currentID = temp;
                    Intent intent = new Intent(context, TranimDisplay.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "الكود غير موجود", Toast.LENGTH_LONG).show();
                }

            }

        });

        ///////////////////// tranim code go to ///////////////////
        ImageButton btSearchT = (ImageButton) findViewById(R.id.bt_tranim_search);
        btSearchT.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, TranimSearch.class);
                startActivity(intent);
            }

        });
    }


    @Override
    public void onRestart() {
        super.onRestart();
        Intent i = getIntent();
        finish();
        startActivity(i);
    }
}
