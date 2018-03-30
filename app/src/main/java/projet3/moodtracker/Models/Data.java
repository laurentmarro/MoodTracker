package projet3.moodtracker.Models;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import projet3.moodtracker.Controllers.Activities.HistoricalActivity;
import projet3.moodtracker.R;

public class Data extends AppCompatActivity {

    public static String DATE;
    public static String DATA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temporaire);
        recovery();
        Intent myIntent = new Intent(this, HistoricalActivity.class);
        startActivity(myIntent);
    }

    private void recovery() {

        // of the last week and today

        for (int i = -7; i <= 0 ; i++) {

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, i);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            DATE = simpleDateFormat.format(calendar.getTime());
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            DATA = preferences.getString(DATE, "NO");

            // feed if it isn't done !

            if (DATA.equals("NO")) {
                DATA = DATE + "-" + "happy" + "-" + "null";
            }

            putPref(DATE, DATA, this);
        }
    }

    public static void putPref(String key, String value, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }
}