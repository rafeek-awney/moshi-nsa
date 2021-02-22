package mario.rafeek.etba3ny;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import mario.rafeek.etba3ny.modules.copticCalender;

public class MainActivity extends AppCompatActivity {

    String message = "لا توجد رسائل حاليا ......";

    Context context = this;
    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /////////////// set app theme /////////////////
        setTheme(SettingData.getTheme(context));
        //////////////////////////////////////////////////////////
        setContentView(R.layout.main_activity);
        /////////////set action bar properties
        toolBar = findViewById(R.id.action_tool_bar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setIcon(R.mipmap.app_icon);

        //////////////coptic calender/////////////////////
        TextView copticMonthTextView = (TextView)this.findViewById(R.id.title_text);
        copticMonthTextView.setText(copticCalender.get_calender("0"));

        ////////////////////
        final TextView TV_fast_bible=findViewById(R.id.fast_luck);
        final String FastBibleString[] = context.getResources().getStringArray(R.array.fast_luck_strings);
        int bibleNumGen = (int) (copticCalender.dayOfYear() % FastBibleString.length);
        TV_fast_bible.setText(FastBibleString[bibleNumGen]);
        //////////////////////////////////////////////////////////
        RelativeLayout actionBible = findViewById(R.id.action_bible);
        actionBible.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                Intent intent = new Intent(context, BibleHome.class);
                startActivity(intent);
            }
        }));
        ///////////////////////////////////////////////////////////
        RelativeLayout actionAgp =findViewById(R.id.action_agp);
        actionAgp.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                Intent intent = new Intent(context, AgpiaHome.class);
                startActivity(intent);
            }
        }));
        //////////////////////////////////////////////////////////
        RelativeLayout actionTranim = findViewById(R.id.action_tranim);
        actionTranim.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                Intent intent = new Intent(context, TranimList.class);
                startActivity(intent);
            }
        }));
        ///////////////////////////////////////////////////
        RelativeLayout actionHistory = findViewById(R.id.action_history);
        actionHistory.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                Intent intent = new Intent(context, HistoryHome.class);
                startActivity(intent);
            }
        }));
        ////////////////////////////////////////////////
        RelativeLayout actionFeel = findViewById(R.id.action_feel);
        actionFeel.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                Intent intent = new Intent(context, FeelHome.class);
                startActivity(intent);
            }
        }));

        ////////////////////////////////////////////////
        ImageView ayat = findViewById(R.id.ayatLayout);
        ayat.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                Intent intent = new Intent(context, GeneralDisplayActivity.class);
                intent.putExtra("title","احفظها تحفظك");
                intent.putExtra("body",TV_fast_bible.getText());
                startActivity(intent);
            }
        }));
        final ImageView messageView = findViewById(R.id.messageLayout);
        final TextView messageTextView=findViewById(R.id.message_textview);
        messageView.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                Intent intent = new Intent(context, GeneralDisplayActivity.class);
                intent.putExtra("title","اخبارنا ورسايلنا");
                intent.putExtra("body",message);
                startActivity(intent);
            }
        }));

        new JsonOnlineTask().execute("https://json.extendsclass.com/bin/6baecc0eca50");
    }

    @Override
    protected void onRestart() {
        recreate();
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(context, SettingsActivity.class);
            startActivity(intent);
        }

        else if (id == R.id.menu_item_share) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "حمل تطبيق اتبعنى الان من الرابط التالى \n" + "https://play.google.com/store/apps/details?id=" +
                    getPackageName();
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "تطبيق اتبعنى");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "مشاركه بواسطه"));
            return true;
        }

        else if (id == R.id.menu_item_about) {
            Intent intent =new Intent(context, AboutActivity.class);
            startActivity(intent);
        }

        else if(id == R.id.menu_item_apps){
            Intent intent = new Intent(context, ApplicationsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private class JsonOnlineTask extends AsyncTask<String, String, String> {

        TextView messageTextView;

        protected void onPreExecute() {
            super.onPreExecute();
            messageTextView=findViewById(R.id.message_textview);
        }

        protected String doInBackground(String... params) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

                }
                JSONObject jresponse = new JSONObject(buffer.toString());
                message = jresponse.getString("message");
                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e){
                e.printStackTrace();
            }
            finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            /*if (pd.isShowing()){
                pd.dismiss();
            }*/
            //messageTextView.setText(message);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                messageTextView.setText(Html.fromHtml(message, Html.FROM_HTML_MODE_COMPACT));
            } else {
                messageTextView.setText(Html.fromHtml(message));
            }            Log.e("message",message);
        }
    }
}
