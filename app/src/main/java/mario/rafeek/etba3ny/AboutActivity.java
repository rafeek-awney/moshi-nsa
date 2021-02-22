package mario.rafeek.etba3ny;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    ////////// Declare views ////////
    private TextView textViewTeam;
    private TextView textViewVersion;
    private TextView textViewCaution;
    private TextView textViewPlan;
    private TextView textViewShare;
    private TextView textViewContrbution;
    ////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context mContext = this;
        setTheme(SettingData.getTheme(mContext));
        setContentView(R.layout.about_activity);

        ////////initialization///////////
        textViewTeam = findViewById(R.id.about_team_text);
        textViewVersion = findViewById(R.id.about_version_text);
        textViewShare = findViewById(R.id.about_share_text);
        textViewContrbution = findViewById(R.id.about_contrbution_text);

        ///////////////////////////////

        LinearLayout facebookPage = findViewById(R.id.about_facebook_layout);
        facebookPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/%D8%A7%D8%AA%D8%A8%D8%B9%D9%86%D9%8A-1052116574822411"));//("fb://page/110853817349569"));//("https://facebook.com/theheartofkemi"));
                startActivity(browserIntent);
            }
        });

        ///////////////////////////////

        LinearLayout rateLayout = findViewById(R.id.about_rate_layout);
        rateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName() + "#rate"));
                startActivity(browse);
            }
        });
        /////////////////////////////
        LinearLayout shareApp = findViewById(R.id.about_share_layout);
        shareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "يمكنك تنزيل تطبيق \"أتبعني\" من الرابط التالي : \nYou can download \"ⲙⲟϣⲓ ⲛⲥⲁ\" APP from below link :\n" + "https://play.google.com/store/apps/details?id=" +
                        getPackageName();
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "اتبعني");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share APP"));
            }
        });

    }

}