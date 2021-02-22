package mario.rafeek.etba3ny.modules;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafeek Awney Anwar on 12/30/2015.
 * class to handle data in text files
 */
public class textFile {
    public List<String> list = null;

    ///////////////////////////////////////////////
    public void readAsset(Context context, String filePath) {
        List<String> temp = new ArrayList<>();
        try {
            InputStream inputStream = context.getAssets().open(filePath);
            InputStreamReader inputreader = new InputStreamReader(inputStream);
            BufferedReader bufferedreader = new BufferedReader(inputreader);
            String line;
            while ((line = bufferedreader.readLine()) != null) {
                temp.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.list = temp;
    }

    //////////////////////////////////////////
    public String getString(char separator) {
        String temp = "";
        for (int j = 0; j < this.list.size(); j++) {
            temp = temp + this.list.get(j);
            temp += separator;
        }
        return temp;
    }
}
