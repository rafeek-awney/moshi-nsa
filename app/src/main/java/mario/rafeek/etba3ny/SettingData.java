package mario.rafeek.etba3ny;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

/**
 * data for setting and common used functions
 * Created by rifo on 10/23/15.
 */
public class SettingData {
    ////////////////////////////temporary pope data///////////////
    static String currentPopeFileName;
    //////////////////////////////////////////
    static SharedPreferences settings_preferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getAppContext());

    //////////////////// settings preferences ///////////////////

    // preference for theme
    static @StyleRes int getTheme(Context context) {
        String theme = settings_preferences.getString("themeCode", "LightTheme");
        int resID = context.getResources().getIdentifier(theme , "style", context.getPackageName());
        return resID;
    }

    // preference for text size
    static int getTxetSize() {
        return settings_preferences.getInt("text_size", 23);
    }

    ////////////////  bible preferences /////////////////////
    // Testament
    static int getBookmarkedTestament() {
        return settings_preferences.getInt("bookmarked_bible_testament", 0);
    }

    static void setBookmarkedTestament(int testament) {
        settings_preferences.edit().putInt("bookmarked_bible_testament", testament).apply();
    }

    // Chapters
    static int getBookmarkedChapter() {
        return settings_preferences.getInt("bookmarked_bible_chapter", 0);
    }

    static void setBookmarkedChapter(int chapter) {
        settings_preferences.edit().putInt("bookmarked_bible_chapter", chapter).apply();
    }

    // Verses
    static int getBookmarkedVerse() {
        return settings_preferences.getInt("bookmarked_bible_verse", 0);
    }

    static void setBookmarkedVerse(int verse) {
        settings_preferences.edit().putInt("bookmarked_bible_verse", verse).apply();
    }

    ///////////////////////share text //////////////////////
    static public void shareText(AppCompatActivity context, String title, String string) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, title);
        sharingIntent.putExtra(Intent.EXTRA_TEXT, string + "\n تمت المشاركه بواسطه تطبيق اتبعنى \n https://play.google.com/store/apps/details?id=mario.rafeek.etba3ny");
        context.startActivity(Intent.createChooser(sharingIntent, "مشاركه بواسطه"));
    }

    /////////////////// copy to clipboard //////////////////////////
    static public void copyText(AppCompatActivity activity, String string) {
        string += "\n بواسطه تطبيق اتبعنى \n https://play.google.com/store/apps/details?id=mario.rafeek.etba3ny";
        ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("etba3ny", string);
        clipboard.setPrimaryClip(clipData);
        Toast.makeText(activity, "تم النسخ بنجاح", Toast.LENGTH_LONG).show();

    }
}
