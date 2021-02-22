package mario.rafeek.etba3ny.helpers;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

import org.xmlpull.v1.XmlPullParser;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import mario.rafeek.etba3ny.modules.tranimModule;

public class TranimSqlHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "etba3ny_tranim.db";
    public static final int DB_VERSION = 1;
    public static String DB_Location;
    String TAG = "still_alive";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public TranimSqlHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        if (((double) Build.VERSION.SDK_INT) >= 4.2d) {
            DB_Location = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_Location = "/data/data/" + context.getPackageName() + "/databases/";
        }
        this.mContext = context;
    }

    public String createDataBase() throws IOException {
        String error = XmlPullParser.NO_NAMESPACE;
        if (!checkDataBase()) {
            getReadableDatabase();
            close();
            try {
                copyDataBase();
            } catch (IOException mIOException) {
                return error + mIOException.getStackTrace().toString();
            }
        }
        return error;
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(DB_Location + DB_NAME, null, SQLiteDatabase.OPEN_READONLY);
            Log.d(TAG, "checkDataBase: database opened succesfully");
        } catch (Exception e) {
            Log.d(TAG, "no database found");
        }
        if (checkDB != null) {
            try {
                Log.d(TAG, "checkDataBase: try to check version");
                Cursor cursor = null;
                cursor = checkDB.rawQuery("select value from info where name = \'version\'", null);
                cursor.moveToFirst();
                String version = cursor.getString(cursor.getColumnIndex("value"));
                Log.d(TAG, "checkDataBase: database opened succesfully");
                if (!version.equals("1.3.3")) {
                    checkDB.close();
                    checkDB = null;
                    Log.d(TAG, "checkDataBase: version missmatch");
                }
            }catch (Exception e){
                checkDB.close();
                checkDB = null;
                Log.d(TAG, "checkDataBase: version exception");
            }

        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = this.mContext.getAssets().open(DB_NAME);
        OutputStream myOutput = new FileOutputStream(DB_Location + DB_NAME);
        byte[] buffer = new byte[AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT];
        while (true) {
            int length = myInput.read(buffer);
            if (length > 0) {
                myOutput.write(buffer, 0, length);
            } else {
                myOutput.flush();
                myOutput.close();
                myInput.close();
                return;
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean openDataBase() throws SQLException {
        this.mDatabase = SQLiteDatabase.openDatabase(DB_Location + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
        return this.mDatabase != null;
    }

    public synchronized void close() {
        if (this.mDatabase != null) {
            this.mDatabase.close();
        }
        super.close();
    }

    public List<tranimModule> getPath(String folder, boolean ArrangeByName) {
        tranimModule TranimModule = null;
        List<tranimModule> list = new ArrayList<>();
        openDataBase();
        Cursor cursor = null;
        if (ArrangeByName) {
            cursor = mDatabase.rawQuery("select * from tranim where folder = '" + folder + "' ORDER BY name", null);
        } else {
            cursor = mDatabase.rawQuery("select * from tranim where folder = '" + folder + "' ORDER BY id", null);
        }
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            TranimModule = new tranimModule(cursor.getString(cursor.getColumnIndex("id")), cursor.getString(cursor.getColumnIndex("name")), cursor.getString(cursor.getColumnIndex("folder")));
            list.add(TranimModule);
            cursor.moveToNext();
        }
        cursor.close();
        close();
        return list;
    }

    public List<tranimModule> getName(String Name) {
        tranimModule TranimModule = null;
        List<tranimModule> list = new ArrayList<>();
        openDataBase();
        Cursor cursor = null;
        cursor = mDatabase.rawQuery("select * from tranim where name Like '%" + Name + "%' ORDER BY name", null);
        //cursor=mDatabase.rawQuery("select * from tranim where instr(name, '" + Name + "') > 0 ORDER BY name",null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            TranimModule = new tranimModule(cursor.getString(cursor.getColumnIndex("id")), cursor.getString(cursor.getColumnIndex("name")), cursor.getString(cursor.getColumnIndex("folder")));
            list.add(TranimModule);
            cursor.moveToNext();
        }
        cursor.close();
        close();
        return list;
    }

    public Cursor getRow(String id) {
        openDataBase();
        Cursor res = mDatabase.rawQuery("select * from tranim where id='" + id + "'", null);
        return res;
    }
}
