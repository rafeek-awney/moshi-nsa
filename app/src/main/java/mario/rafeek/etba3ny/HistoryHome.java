package mario.rafeek.etba3ny;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

/**
 * main menu
 * Created by RAA on 28/09/2015.
 */
public class HistoryHome extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context = this;
        setTheme(SettingData.getTheme(context));
        setContentView(R.layout.history_home);
        ///////////////////////////////////////////////
        ////////////////////////////
        Button btnpope =  findViewById(R.id.btpope);

        btnpope.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, HistoryPopeList.class);
                startActivity(intent);

            }

        });
    }
}
