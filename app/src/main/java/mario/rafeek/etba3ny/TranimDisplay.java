package mario.rafeek.etba3ny;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import mario.rafeek.etba3ny.helpers.TranimSqlHelper;
import mario.rafeek.etba3ny.modules.textFile;

public class TranimDisplay extends AppCompatActivity {
    Context mContext=this;
    // tranom  display activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //////////////
        setTheme(SettingData.getTheme(mContext));
        setContentView(R.layout.tranim_display);
        TextView title= findViewById(R.id.txtTitle2);
        title.setText(TranimData.currentID);
        ///////////////////////////////////////
        final TextView txtDisp = (TextView) findViewById(R.id.txtAgDispAr);
        txtDisp.setTextSize(SettingData.getTxetSize());
        /////////////////////////////////////
        ch_disp(TranimData.currentID);
    }


    /////////////display the  chosen chapter ++ code for next and back fix////////////////////////
    protected void ch_disp(String id) {
        //////////////////
        Cursor row = new TranimSqlHelper(this).getRow(id);
        row.moveToFirst();
        String fileName = "tranim/" + row.getString(row.getColumnIndex("folder")) + "/" + row.getString(row.getColumnIndex("id"));
        textFile file = new textFile();
        final TextView txt1 = (TextView) findViewById(R.id.txtAgDispAr);
        file.readAsset(txt1.getContext(), fileName);
        txt1.setText(file.getString('\n'));
    }
}
////////////////////////////////////////////////////////////////
