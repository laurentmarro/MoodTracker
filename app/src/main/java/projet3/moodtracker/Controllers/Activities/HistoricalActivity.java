package projet3.moodtracker.Controllers.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import projet3.moodtracker.R;

public class HistoricalActivity extends AppCompatActivity {

    // Var

    public static String DATE = "";
    public static String DATA="";
    public static String PARAMETERS="PARAMETERS";
    public List<String> moods = new ArrayList<>();
    public List<String> comments = new ArrayList<>();
    public List<String> dates = new ArrayList<>();
    public List<String> widths  = new ArrayList<>();
    public List<String> colors  = new ArrayList<>();
    public List<String> margin_starts  = new ArrayList<>();

    // Widgets

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
        datas();
        display_buttons();
        width_setting_and_color();
        set_margin_for_buttons();

        view_comment_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            toast(comments.get(0));
            }
        });

        view_comment_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast(comments.get(1));
            }
        });

        view_comment_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast(comments.get(2));
            }
        });

        view_comment_button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast(comments.get(3));
            }
        });

        view_comment_button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast(comments.get(4));
            }
        });

        view_comment_button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast(comments.get(5));
            }
        });

        view_comment_button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast(comments.get(6));
            }
        });

    }

    private void recovery() {
        DATA= getPref(DATE , this);
        PARAMETERS = getPref("PARAMETERS", this);
    }

    private void datas() {
        dates = new ArrayList<>();
        moods = new ArrayList<>();
        comments = new ArrayList<>();

        for (int i = -7; i <= 0 ; i++) {

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, i);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

            DATE = simpleDateFormat.format(calendar.getTime());
            DATA = getPref(DATE, this);
            String [] separated = DATA.split("-");

            dates.add(separated[0]); // dates
            moods.add(separated[1]); // moods
            comments.add(separated[2]); // comments
        }

        for (int i = 0; i <=7 ; i++) {
            String[] separated = PARAMETERS.split("-");
            widths.add(separated[0]); // width
            colors.add(separated[1]); // colors
            margin_starts.add(separated[2]); // margin_start
        }
    }

    private void display_buttons() { // Show buttons
        if (comments.get(0).equals("0")) {
            view_comment_button1.setVisibility(View.GONE); }
        if (comments.get(1).equals("0")) {
            view_comment_button2.setVisibility(View.GONE); }
        if (comments.get(2).equals("0")) {
            view_comment_button3.setVisibility(View.GONE); }
        if (comments.get(3).equals("0")) {
            view_comment_button4.setVisibility(View.GONE); }
        if (comments.get(4).equals("0")) {
            view_comment_button5.setVisibility(View.GONE); }
        if (comments.get(5).equals("0")) {
            view_comment_button6.setVisibility(View.GONE); }
        if (comments.get(6).equals("0")) {
            view_comment_button7.setVisibility(View.GONE); }
    }

    private void width_setting_and_color() {
        // Block 1
        Block1.getLayoutParams().width = Integer.parseInt(widths.get(0));
        Block1.setBackgroundColor(Color.parseColor(colors.get(0)));
        Block1.requestLayout();

        // Block 2
        Block2.getLayoutParams().width = Integer.parseInt(widths.get(0));
        Block2.setBackgroundColor(Color.parseColor(colors.get(0)));
        Block2.requestLayout();

        // Block 3
        Block3.getLayoutParams().width = Integer.parseInt(widths.get(0));
        Block3.setBackgroundColor(Color.parseColor(colors.get(0)));
        Block3.requestLayout();

        // Block 4
        Block4.getLayoutParams().width = Integer.parseInt(widths.get(0));
        Block4.setBackgroundColor(Color.parseColor(colors.get(0)));
        Block4.requestLayout();

        // Block 5
        Block5.getLayoutParams().width = Integer.parseInt(widths.get(0));
        Block5.setBackgroundColor(Color.parseColor(colors.get(0)));
        Block5.requestLayout();

        // Block 6
        Block6.getLayoutParams().width = Integer.parseInt(widths.get(0));
        Block6.setBackgroundColor(Color.parseColor(colors.get(0)));
        Block6.requestLayout();

        // Block 7
        Block7.getLayoutParams().width = Integer.parseInt(widths.get(0));
        Block7.setBackgroundColor(Color.parseColor(colors.get(0)));
        Block7.requestLayout();
    }

    private void set_margin_for_buttons(){

        final float scale = this.getResources().getDisplayMetrics().density;

        int top = (int) (5 * scale);
        int right = (int) (10 * scale);
        int bottom = (int) (45 * scale);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        params.setMargins(Integer.parseInt(margin_starts.get(0)), top, right, bottom);
        view_comment_button1.setLayoutParams(params);
        params.setMargins(Integer.parseInt(margin_starts.get(1)), top, right, bottom);
        view_comment_button2.setLayoutParams(params);
        params.setMargins(Integer.parseInt(margin_starts.get(2)), top, right, bottom);
        view_comment_button3.setLayoutParams(params);
        params.setMargins(Integer.parseInt(margin_starts.get(3)), top, right, bottom);
        view_comment_button4.setLayoutParams(params);
        params.setMargins(Integer.parseInt(margin_starts.get(4)), top, right, bottom);
        view_comment_button5.setLayoutParams(params);
        params.setMargins(Integer.parseInt(margin_starts.get(5)), top, right, bottom);
        view_comment_button6.setLayoutParams(params);
        params.setMargins(Integer.parseInt(margin_starts.get(6)), top, right, bottom);
        view_comment_button7.setLayoutParams(params);
    }

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public static String getPref(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }
}