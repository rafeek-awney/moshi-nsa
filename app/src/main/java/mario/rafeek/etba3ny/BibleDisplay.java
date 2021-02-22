package mario.rafeek.etba3ny;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import mario.rafeek.etba3ny.modules.textFile;

public class BibleDisplay extends AppCompatActivity {
    textFile file = new textFile();
    AppCompatActivity activity = this;
    int listPosition;
    Context mContext=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SettingData.getTheme(mContext));
        setContentView(R.layout.bible_display);
        final ListView listDisp = findViewById(R.id.listDisp);
        /////////////////////////////////////
        ch_display();
        ///////////next button for bible///////////////////
        ImageButton btnNext = findViewById(R.id.btNext);

        btnNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                ++BibleData.Bc;
                if (BibleData.Bc == BibleData.bible_chapters[BibleData.Bp][BibleData.Ba]) {
                    BibleData.Bc = 0;
                    ++BibleData.Ba;
                    if (BibleData.Ba == 46) {
                        BibleData.Ba = 0;
                        ++BibleData.Bp;
                    }

                }
                ch_display();
            }

        });
        ///////////////back button for bible/////////////////////////
        final ImageButton btnBack = findViewById(R.id.btBack);

        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (BibleData.Bc == 0) {
                    if (BibleData.Ba == 0) {
                        BibleData.Bp--;
                        BibleData.Ba = 46;
                    }
                    BibleData.Ba--;
                    BibleData.Bc = BibleData.bible_chapters[BibleData.Bp][BibleData.Ba] - 1;
                } else
                    BibleData.Bc--;
                ch_display();
                //scrollView.scrollTo(0,0);
            }

        });
        ///////////////////////
        AdapterView.OnItemLongClickListener aa = new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                Log.d("long1", "in onLongClick");
                listPosition = position;
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), view);
                popupMenu.getMenuInflater().inflate(R.menu.bible_popup, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int ids = menuItem.getItemId();
                        if (ids == R.id.copyItem) {
                            SettingData.copyText(activity, file.list.get(listPosition) + "\n(" + BibleData.bible_titles[BibleData.Bp][BibleData.Ba] + "-" + (BibleData.Bc + 1) + ")");

                        }

                        if (ids == R.id.shareItem) {
                            SettingData.shareText(activity, "", file.list.get(listPosition) + "\n(" + BibleData.bible_titles[BibleData.Bp][BibleData.Ba] + "-" + (BibleData.Bc + 1) + ")");

                        }

                        return true;
                    }
                });
                popupMenu.show();

                Log.d("long1", "ionLongClick finished");
                return true;
            }
        };
        listDisp.setOnItemLongClickListener(aa);

        final ImageButton BtnREgularRead = findViewById(R.id.bt_regular_read);
        BtnREgularRead.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                new AlertDialog.Builder(BibleDisplay.this)
                        .setTitle("القراءه المنتظمه")
                        .setMessage("هل وصلت قراءتك المنتظمه لهذا الاصحاح ؟")
                        .setNegativeButton("لا", null) // dismisses by default
                        .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SettingData.setBookmarkedTestament(BibleData.Bp);
                                SettingData.setBookmarkedChapter(BibleData.Ba);
                                SettingData.setBookmarkedVerse(BibleData.Bc);
                                BtnREgularRead.setImageResource(R.drawable.ic_action_bookmark);
                            }
                        })
                        .create()
                        .show();
            }

        });
    }


    /////////display chapter and fix back, next button/////////////
    protected void ch_display() {
        ///////////////////////////////////////////
        String fileName = "bible/" + BibleData.bible_names[BibleData.Bp][BibleData.Ba] + "/b" + BibleData.bible_names[BibleData.Bp][BibleData.Ba] + (BibleData.Bc + 1);
        final ListView listDisp = findViewById(R.id.listDisp);
        final TextView txtTitle = findViewById(R.id.txtTitle);
        file.readAsset(listDisp.getContext(), fileName);
        //////////////////////////////////////////////

        bibleDisplayItemAdapter adapter = new bibleDisplayItemAdapter(this, file.list, SettingData.getTxetSize());
        listDisp.setAdapter(adapter);

        // to change regular read icon;
        final ImageButton BtnREgularRead = findViewById(R.id.bt_regular_read);
        txtTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (BibleData.Bp == SettingData.getBookmarkedTestament() && BibleData.Ba == SettingData.getBookmarkedChapter() && BibleData.Bc == SettingData.getBookmarkedVerse())
                    BtnREgularRead.setImageResource(R.drawable.ic_action_bookmark);
                else
                    BtnREgularRead.setImageResource(R.drawable.ic_action_star);
            }
        });
        //////////////////////////////////////////
        txtTitle.setText(BibleData.bible_titles[BibleData.Bp][BibleData.Ba] + "\n\r" + "اصحاح " + (BibleData.Bc + 1));
        //////////////////////////////////////////
        ImageButton btnNext = findViewById(R.id.btNext);
        if (BibleData.Bp == 1 && BibleData.Ba == 26 && (BibleData.Bc == BibleData.bible_chapters[1][26] - 1)) {
            btnNext.setEnabled(false);
        } else if (!btnNext.isEnabled())
            btnNext.setEnabled(true);
        //////////////////////////////////////////
        ImageButton btnBack = findViewById(R.id.btBack);
        if (BibleData.Bp == 0 && BibleData.Ba == 0 && BibleData.Bc == 0) {
            btnBack.setEnabled(false);
        } else if (!btnBack.isEnabled())
            btnBack.setEnabled(true);
    }
}
////////////////////////////////////////////////////////////////
