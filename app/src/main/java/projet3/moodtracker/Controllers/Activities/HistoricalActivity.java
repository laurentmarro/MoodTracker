package projet3.moodtracker.Controllers.Activities;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import projet3.moodtracker.R;

public class HistoricalActivity extends AppCompatActivity {

    public static String DATE;
    public static String DATA;
    public static int mood;

    List<String> dates = new ArrayList<>();
    List<String> moods = new ArrayList<>();
    List<String> comments = new ArrayList<>();

    // Widgets

    private TextView text1;

    private LinearLayout Block1;
    private LinearLayout Block2;
    private LinearLayout Block3;
    private LinearLayout Block4;
    private LinearLayout Block5;
    private LinearLayout Block6;
    private LinearLayout Block7;

    private Button view_comment_button1;
    private Button view_comment_button2;
    private Button view_comment_button3;
    private Button view_comment_button4;
    private Button view_comment_button5;
    private Button view_comment_button6;
    private Button view_comment_button7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historical_activity);

        text1 = (TextView) findViewById(R.id.text1);

        // 7 blocks
        Block1 = (LinearLayout) findViewById(R.id.Block1);
        Block2 = (LinearLayout) findViewById(R.id.Block2);
        Block3 = (LinearLayout) findViewById(R.id.Block3);
        Block4 = (LinearLayout) findViewById(R.id.Block4);
        Block5 = (LinearLayout) findViewById(R.id.Block5);
        Block6 = (LinearLayout) findViewById(R.id.Block6);
        Block7 = (LinearLayout) findViewById(R.id.Block7);

        // 7 Buttons
        view_comment_button1 = (Button) findViewById(R.id.view_comment_button1);
        view_comment_button2 = (Button) findViewById(R.id.view_comment_button2);
        view_comment_button3 = (Button) findViewById(R.id.view_comment_button3);
        view_comment_button4 = (Button) findViewById(R.id.view_comment_button4);
        view_comment_button5 = (Button) findViewById(R.id.view_comment_button5);
        view_comment_button6 = (Button) findViewById(R.id.view_comment_button6);
        view_comment_button7 = (Button) findViewById(R.id.view_comment_button7);

        recovery();
        parameters();
    }

    private void recovery() {

        for (int i = 0; i <= 7; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, i - 7); // date a week ago
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            DATE = simpleDateFormat.format(calendar.getTime());
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            DATA = preferences.getString(DATE, null); // recovery of the data J-7
            String [] separated = DATA.split("-");
            moods.add(separated[1]); // moods
            comments.add(separated[2]); // comments
        }
    }

    private void parameters() {

        int i = 1;

        if (moods.get(i).equals("disappointed")) {
            mood = 0;
        } else if (moods.get(i).equals("sad")) {
            mood = 1;
        } else if (moods.get(i).equals("normal")) {
            mood = 2;
        } else if (moods.get(i).equals("happy")) {
            mood = 3;
        } else if (moods.get(i).equals("super_happy")) {
            mood = 4;
        }

        Log.e("Mood :", moods.get(i) + " / " + comments.get(i));
    }
}