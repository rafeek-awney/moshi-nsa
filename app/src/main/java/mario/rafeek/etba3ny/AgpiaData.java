package mario.rafeek.etba3ny;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.List;

import mario.rafeek.etba3ny.helpers.JsonLocalHelper;

/**
 * data for agbia
 * Created by RAA on 07/10/2015.
 */
public class AgpiaData {

    public static class AgpiaPrayer {
        String name;
        String title;
        List<String> file_names;
        List<String> titles;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public List<String> getTitles() {
            return titles;
        }

        @NotNull
        @Override
        public String toString() {
            return "prayer{" +
                    "name='" + name + '\'' +
                    ", title=" + title +
                    ", file_names=" + file_names +
                    ", titles=" + titles +
                    '}';
        }

    }

    public static List<AgpiaData.AgpiaPrayer> agpiaPrayerList;

    public static List<AgpiaPrayer> getAgpiaDataFromJSON(Context context){

        String jsonFileString = JsonLocalHelper.getJsonFromAssets(context, "agpia/agbia_list.json");
        Log.i("data", jsonFileString);

        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<AgpiaPrayer>>() { }.getType();

        return gson.fromJson(jsonFileString, listUserType);
    }
}
