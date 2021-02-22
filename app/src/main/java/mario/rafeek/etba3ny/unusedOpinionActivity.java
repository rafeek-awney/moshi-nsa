package mario.rafeek.etba3ny;

import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class unusedOpinionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opinion_activity);
        ////////web view to display about.htm/////////
        WebView aboutDisp = (WebView) findViewById(R.id.webView);
        aboutDisp.setWebViewClient(new WebViewClient());
        aboutDisp.getSettings().setJavaScriptEnabled(true);
        aboutDisp.loadUrl("http://etba3ny.byethost17.com/messages.php");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }
}