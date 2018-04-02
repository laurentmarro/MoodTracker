package projet3.moodtracker.Models;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import projet3.moodtracker.Controllers.Activities.HistoricalActivity;
import projet3.moodtracker.R;

public class Data extends AppCompatActivity {

    public static String DATE;
    public static String DATA;
    public static String TO_SAVE;
    public static String PARAMETERS="PARAMETERS";
    public static String PARAMETERS_TO_SAVE;
    public List<String> dates = new ArrayList<>();
    public List<String> moods = new ArrayList<>();
    public List<String> comments = new ArrayList<>();
    public List<String> datas = new ArrayList<>();
    public static final boolean OK = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temporaire);
        recovery();
        delete();
        parameters();
        concatenate_backup();
        Intent myIntent = new Intent(this, HistoricalActivity.class);
        startActivity(myIntent);
    }

    private void recovery() {

        // recovery of the last week and today in ArrayList

        dates = new ArrayList<>();
        moods = new ArrayList<>();
        comments = new ArrayList<>();
        datas = new ArrayList<>();

        for (int i = -7; i <= 0; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, i);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            DATE = simpleDateFormat.format(calendar.getTime());
            DATA = getPref(DATE, this);

            // Looking if the DATE is saved in the SharedPreferences

            boolean OK = DATA.contains(DATE);
            if (!OK) {
                dates.add(DATE);
                moods.add("happy");
                comments.add("0");
            }

            if (OK) { // do the job
                String[] separated = DATA.split("-");
                dates.add(separated[0]); // dates
                moods.add(separated[1]); // moods
                comments.add(separated[2]); // comments
                OK = false;
            }
        }
    }

    public static void putPref(String key, String value, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getPref(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    private void concatenate_backup() {
        for (int i = 0; i <= 7; i++) {
            TO_SAVE = dates.get(i) + "-" + moods.get(i) + "-" + comments.get(i);
            DATE = dates.get(i);
            putPref(DATE, TO_SAVE, this);
        }
    }

    private void delete() {
        SharedPreferences settings = this.getSharedPreferences("PreferencesName", Context.MODE_PRIVATE);
        settings.edit().clear().apply();
    }

    private void parameters() {

        // Convert var from px to dpi

        final float scale = this.getResources().getDisplayMetrics().density;

            // width of each Block

        int layout_width_sad = (int) (110 * scale + 0.5f);
        int layout_width_disappointed = (int) (165 * scale + 0.5f);
        int layout_width_normal = (int) (220 * scale + 0.5f);
        int layout_width_happy = (int) (275 * scale + 0.5f);
        int layout_width_super_happy = (int) (550 * scale + 0.5f);

            // margin_start from each Button

        int layout_marginStart_sad = (int) (80 * scale + 0.5f);
        int layout_marginStart_disappointed = (int) (135 * scale + 0.5f);
        int layout_marginStart_normal = (int) (190 * scale + 0.5f);
        int layout_marginStart_happy = (int) (245 * scale + 0.5f);
        int layout_marginStart_super_happy = (int) (500 * scale + 0.5f);

            // color of each Block

        String color_sad = "#ffde3c50";
        String color_disappointed = "#ff9b9b9b";
        String color_normal = "a5468ad9";
        String color_happy = "#ffb8e986";
        String color_super_happy = "#fff9ec4f";

            // concatenate all var in one

        for (int i = 0; i <=7 ; i++) {
            if (moods.get(i).equals("happy")) { datas.add(layout_width_happy + "-" + color_happy + "-" + layout_marginStart_happy);}
            if (moods.get(i).equals("super_happy")) { datas.add(layout_width_super_happy + "-" + color_super_happy + "-" + layout_marginStart_super_happy);}
            if (moods.get(i).equals("normal")) { datas.add(layout_width_normal + "-" + color_normal + "-" + layout_marginStart_normal);}
            if (moods.get(i).equals("disappointed")) { datas.add(layout_width_disappointed + "-" + color_disappointed + "-" + layout_marginStart_disappointed);}
            if (moods.get(i).equals("happy")) { datas.add(layout_width_sad + "-" + color_sad + "-" + layout_marginStart_sad);}

        }

            // parameters to save

        PARAMETERS_TO_SAVE = (datas.get(0)+"-"+datas.get(1)+"-"+datas.get(2)+"-"+datas.get(3)+"-"+datas.get(4)+"-"+datas.get(5)+"-"+datas.get(6));

            // put in SharedPreferences

        putPref(PARAMETERS, PARAMETERS_TO_SAVE, this);
    }
}