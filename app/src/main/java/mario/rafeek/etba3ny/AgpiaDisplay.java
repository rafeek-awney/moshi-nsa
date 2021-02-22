package mario.rafeek.etba3ny;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import mario.rafeek.etba3ny.modules.textFile;

public class AgpiaDisplay extends AppCompatActivity {
    int prayer;
    int chapter;
    // agpia  display activity
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context mContext =this;
        setTheme(SettingData.getTheme(mContext));
        setContentView(R.layout.agpia_display);
        ///////////////////////
        prayer=getIntent().getIntExtra("agpia_prayer",0);
        chapter=getIntent().getIntExtra("agpia_chapter",0);
        final TextView display_text_ar =  findViewById(R.id.txtAgDispAr);
        final TextView display_text_cop =  findViewById(R.id.txtAgDispCop);
        display_text_ar.setTextSize(SettingData.getTxetSize());
        display_text_cop.setTextSize(SettingData.getTxetSize());
        /////////////////////////
        final ScrollView scrollView = findViewById(R.id.Agscroll);
        /////////////swipe functions ///////////
        scrollView.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeLeft() {
                if (chapter == 0)
                    Toast.makeText(getApplicationContext(), "لا توجد صفحات سابقه", Toast.LENGTH_SHORT).show();
                else {
                    chapter--;
                    ch_disp();
                    scrollView.scrollTo(0, 0);
                }

            }

            public void onSwipeRight() {
                if (chapter == AgpiaData.agpiaPrayerList.get(prayer).file_names.size() - 1) {
                    Toast.makeText(getApplicationContext(), "لا يوجد صفحات تاليه", Toast.LENGTH_SHORT).show();
                } else {
                    ++chapter;
                    ch_disp();
                    scrollView.scrollTo(0, 0);
                }

            }

            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }

        });

        /////////////////////////////////////
        ch_disp();
        ///////////next button code ////////////////
        ImageButton btnNext =  findViewById(R.id.btNext);
        btnNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // set the animation type of textSwitcher
                ++chapter;
                ch_disp();
                scrollView.scrollTo(0, 0);
            }

        });
        ////////////back button code ////////////
        ImageButton btnBack =  findViewById(R.id.btBack);

        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                chapter--;
                ch_disp();
                scrollView.scrollTo(0, 0);
            }

        });

        final CheckBox arabicCheckBox=findViewById(R.id.check_box_arabic);
        final CheckBox copticCheckBox=findViewById(R.id.check_box_coptic);

        arabicCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!copticCheckBox.isChecked()){
                    AlertDialog.Builder dlgAlert=new AlertDialog.Builder(mContext);
                    dlgAlert.setMessage("يجب أختيار لغه واحده علي الاقل .");
                    dlgAlert.setPositiveButton("ok",null);
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                    arabicCheckBox.setChecked(true);
                }else if(!arabicCheckBox.isChecked()){
                    display_text_ar.setVisibility(View.GONE);
                }else {
                    display_text_ar.setVisibility(View.VISIBLE);
                }
            }
        });

        copticCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!arabicCheckBox.isChecked()){
                    AlertDialog.Builder dlgAlert=new AlertDialog.Builder(mContext);
                    dlgAlert.setMessage("يجب أختيار لغه واحده علي الاقل .");
                    dlgAlert.setPositiveButton("ok",null);
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                    copticCheckBox.setChecked(true);
                }else if(!copticCheckBox.isChecked()){
                    display_text_cop.setVisibility(View.GONE);
                }else {
                    display_text_cop.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    /////////////display the  chosen chapter ++ code for next and back fix////////////////////////
    protected void ch_disp() {
        final TextView title_text = findViewById(R.id.txtTitle);
        title_text.setText(AgpiaData.agpiaPrayerList.get(prayer).getTitle());
        //RadioButton radioArabic=findViewById(R.id.radio_arabic);
        textFile fileAr = new textFile();
        textFile fileCop = new textFile();
        //////////////////
        final TextView display_text_ar = findViewById(R.id.txtAgDispAr);
        final TextView display_text_cop = findViewById(R.id.txtAgDispCop);
        String fileNameAr;String fileNameCop;
        fileNameAr = "agpia/ar/" + AgpiaData.agpiaPrayerList.get(prayer).name+"/"+ AgpiaData.agpiaPrayerList.get(prayer).file_names.get(chapter) + ".txt";
        fileNameCop = "agpia/cop/" + AgpiaData.agpiaPrayerList.get(prayer).name+"/"+ AgpiaData.agpiaPrayerList.get(prayer).file_names.get(chapter) + ".txt";

        fileAr.readAsset(getApplicationContext(), fileNameAr);
        fileCop.readAsset(getApplicationContext(), fileNameCop);
        display_text_ar.setText(Html.fromHtml(fileAr.getString('\n')));
        display_text_cop.setText(Html.fromHtml(fileCop.getString('\n')));
        //////////////////////////////////////////
        ImageButton btnNext =  findViewById(R.id.btNext);
        if (chapter== AgpiaData.agpiaPrayerList.get(prayer).file_names.size() - 1) {
            btnNext.setEnabled(false);
        } else if (!btnNext.isEnabled())
            btnNext.setEnabled(true);
        //////////////////////////////////////////
        ImageButton btnBack =  findViewById(R.id.btBack);
        if (chapter == 0) {
            btnBack.setEnabled(false);
        } else if (!btnBack.isEnabled())
            btnBack.setEnabled(true);
    }
}
