package mario.rafeek.etba3ny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ApplicationsActivity extends AppCompatActivity {

    ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
    private SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context mContext=this;
        setTheme(SettingData.getTheme(mContext));
        setContentView(R.layout.applications_activity);


        HashMap<String,String> item;
        for(int i = 0; i< applicationsArray.length; i++){
            item = new HashMap<String,String>();
            item.put( "line1", applicationsArray[i][0]);
            item.put( "line2", applicationsArray[i][1]);
            list.add( item );
        }
        sa = new SimpleAdapter(this, list,
                R.layout.twolines,
                new String[] { "line1","line2" },
                new int[] {R.id.line_a, R.id.line_b});
        ((ListView)findViewById(R.id.applications_list)).setAdapter(sa);

        AdapterView.OnItemClickListener lettersAdapter = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(applicationsArray[position][1]));//("fb://page/110853817349569"));//("https://facebook.com/theheartofkemi"));
                startActivity(browserIntent);
            }
        };
        ((ListView)findViewById(R.id.applications_list)).setOnItemClickListener(lettersAdapter);
    }

    private String[][] applicationsArray =
            {{"  مشروع الكنوز القبطية","https://coptic-treasures.com"},
                    {"  أرثوذكسى + القطمارس","https://play.google.com/store/apps/details?id=com.app.orsozoxi"},
                    {"  تطبيق Ⲡϩⲏⲧ ⲛⲭⲏⲙⲓ","https://play.google.com/store/apps/details?id=com.timetson.theheartofegypt"},
                    {"  تطبيق قوتي","https://play.google.com/store/apps/details?id=com.petra.margergesassiut"},
                    {"  تطبيق Naqlun Coptic Dictionary","https://play.google.com/store/apps/details?id=com.naqlun.coptdict"},
                    {"  تطبيق CopticHymns","https://play.google.com/store/apps/details?id=com.subsplashconsulting.s_728JTS"},
};
}