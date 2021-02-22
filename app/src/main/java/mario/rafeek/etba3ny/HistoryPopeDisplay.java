package mario.rafeek.etba3ny;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import mario.rafeek.etba3ny.modules.textFile;

public class HistoryPopeDisplay extends AppCompatActivity {
    // agpia  display activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context mContext=this;
        setTheme(SettingData.getTheme(mContext));
        setContentView(R.layout.history_pope_display);
        //SettingData.setActionBar(this, R.id.actionbar, "بطاركه الاسكندريه", false);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout);
        final TextView txtDisp = (TextView) findViewById(R.id.txtPopeDisp);
        txtDisp.setTextSize(SettingData.getTxetSize());
        /////////////////////////////////////
        ch_disp();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    /////////////display the  chosen chapter ++ code for next and back fix////////////////////////
    protected void ch_disp() {
        //////////////////
        String fileName = "popes/" + SettingData.currentPopeFileName;
        textFile file = new textFile();
        final TextView txt1 = (TextView) findViewById(R.id.txtPopeDisp);
        file.readAsset(txt1.getContext(), fileName);
        txt1.setText(file.getString('\n'));
    }
}
////////////////////////////////////////////////////////////////
