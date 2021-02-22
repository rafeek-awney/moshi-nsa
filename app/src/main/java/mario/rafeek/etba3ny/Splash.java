package mario.rafeek.etba3ny;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Locale;

import mario.rafeek.etba3ny.helpers.TranimSqlHelper;

/**
 * splash screen
 * Created by Rafeek Awney Anwar on 07/10/2015.
 */


public class Splash extends AppCompatActivity {
    public final Context context = this;
    private TranimSqlHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(SettingData.getTheme(context));
        super.onCreate(savedInstanceState);
        ///////////////// set language /////////////////
        Locale locale;
        locale=new Locale("ar");
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());

        ///////////////////////////////////////////////
        setContentView(R.layout.splash);

        /* ***** Create Thread that will sleep for 3 seconds ************ */
        Thread background = new Thread() {
            public void run() {
                Intent i = null;

                mDbHelper = new TranimSqlHelper(context);
                try {
                    mDbHelper.createDataBase();
                } catch (IOException mIOException) {
                    throw new Error("UnableToCreateDatabase");
                }

                try {
                    // Thread will sleep for 3 seconds
                    sleep(2000);

                    // After 2 seconds redirect to another intent
                    i = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(i);

                    //Remove activity
                    finish();

                } catch (Exception e) {
                    startActivity(i);
                }
            }
        };

        // start thread
        background.start();
    }
}

