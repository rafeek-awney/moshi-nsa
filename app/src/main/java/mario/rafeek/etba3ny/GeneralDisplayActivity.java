package mario.rafeek.etba3ny;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by rifo on 9/26/16.
 * popup windows for fast
 */
public class GeneralDisplayActivity extends AppCompatActivity {
    public final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context mContext=this;
        setTheme(SettingData.getTheme(mContext));
        setContentView(R.layout.general_display_activity);

        String titleString= getIntent().getExtras().getString("title");
        String bodyString=getIntent().getExtras().getString("body");

        final TextView title= findViewById(R.id.general_title);
        final TextView body= findViewById(R.id.general_body);
        title.setText(titleString);
        //body.setText(bodyString);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            body.setText(Html.fromHtml(bodyString, Html.FROM_HTML_MODE_COMPACT));
        } else {
            body.setText(Html.fromHtml(bodyString));
            Log.e("message",bodyString);
        }
    }
}
