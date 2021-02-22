package mario.rafeek.etba3ny;

import android.app.Application;
import android.content.Context;

import java.util.Locale;


public class MyApplication extends Application {

    private static Context context;
    //private LocalizationApplicationDelegate localizationDelegate = new LocalizationApplicationDelegate();


    @Override
    public void onCreate() {
        super.onCreate();
        MyApplication.context= getApplicationContext();
        unusedLocaleUtils.setLocale(new Locale(unusedLocaleUtils.getPrefLangCode(this), unusedLocaleUtils.getPrefCountryCode(this)));
        unusedLocaleUtils.updateConfiguration(this, getResources().getConfiguration());


    }

    public static Context getAppContext(){
        return MyApplication.context;
    }

/*    @Override
    protected void attachBaseContext(Context base) {
        localizationDelegate.setDefaultLanguage(base, Locale.ENGLISH);
        super.attachBaseContext(localizationDelegate.attachBaseContext(base));
    }



    public void changeLocale(String language){
        Log.d("pref", "changeLocale: "+Locale.getDefault().getLanguage());
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Log.d("pref", "changeLocale: "+Locale.getDefault().getLanguage());
        // Create a new configuration object
        Configuration config = new Configuration(context.getResources().getConfiguration());
        // Set the locale of the new configuration
        config.setLocale(locale);
        // Update the configuration of the Accplication context
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        context = context.createConfigurationContext(config);
    }*/

}
