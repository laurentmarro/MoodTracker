package com.example.android.moodtracker.Controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.android.moodtracker.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HistoricalActivity extends AppCompatActivity {

    // Var
    private static String DATE = "";
    private static String DATA="";
    private static String PARAMETERS="PARAMETERS";
    private int position;
    private List<String> moods = new ArrayList<>();
    private List<String> comments = new ArrayList<>();
    private List<String> dates = new ArrayList<>();
    ArrayList<String> colors;
    ArrayList<String> moods_origines;

    // Widgets
    private ConstraintLayout Block1;
    private ConstraintLayout Block2;
    private ConstraintLayout Block3;
    private ConstraintLayout Block4;
    private ConstraintLayout Block5;
    private ConstraintLayout Block6;
    private ConstraintLayout Block7;
    private ImageView view_comment_button1;
    private ImageView view_comment_button2;
    private ImageView view_comment_button3;
    private ImageView view_comment_button4;
    private ImageView view_comment_button5;
    private ImageView view_comment_button6;
    private ImageView view_comment_button7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical);

        // 7 blocks
        Block1 = (ConstraintLayout) findViewById(R.id.Block1);
        Block2 = (ConstraintLayout) findViewById(R.id.Block2);
        Block3 = (ConstraintLayout) findViewById(R.id.Block3);
        Block4 = (ConstraintLayout) findViewById(R.id.Block4);
        Block5 = (ConstraintLayout) findViewById(R.id.Block5);
        Block6 = (ConstraintLayout) findViewById(R.id.Block6);
        Block7 = (ConstraintLayout) findViewById(R.id.Block7);

        // 7 Buttons
        view_comment_button1 = (ImageView) findViewById(R.id.view_comment_button1);
        view_comment_button2 = (ImageView) findViewById(R.id.view_comment_button2);
        view_comment_button3 = (ImageView) findViewById(R.id.view_comment_button3);
        view_comment_button4 = (ImageView) findViewById(R.id.view_comment_button4);
        view_comment_button5 = (ImageView) findViewById(R.id.view_comment_button5);
        view_comment_button6 = (ImageView) findViewById(R.id.view_comment_button6);
        view_comment_button7 = (ImageView) findViewById(R.id.view_comment_button7);

        recovery();
        datas();
        display_buttons();
        blocks_width_and_set_color();

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

            if (DATA == null) {DATA = DATE+"-"+"happy"+"-"+"0";}
            String [] separated = DATA.split("-");

            dates.add(separated[0]); // dates
            moods.add(separated[1]); // moods
            comments.add(separated[2]); // comments
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

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private static String getPref(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    private void blocks_width_and_set_color() { // To set the width of each block

        // Colors definition
        colors = new ArrayList<>();
        colors.add("#ffde3c50");
        colors.add("#ff9b9b9b");
        colors.add("#a5468ad9");
        colors.add("#ffb8e986");
        colors.add("#fff9ec4f");

        // Moods_origines definition
        moods_origines = new ArrayList<>();
        moods_origines.add("sad");
        moods_origines.add("disappointed");
        moods_origines.add("normal");
        moods_origines.add("happy");
        moods_origines.add("super_happy");

        // Width
        int screenWidth = getResources().getDisplayMetrics().widthPixels;

        // Block 1
        for (int i = 0; i <5 ; i++) {
            if (moods.get(0).equals(moods_origines.get(i))) {position = i;}
            Block1.setBackgroundColor(Color.parseColor(colors.get(position)));
            ViewGroup.LayoutParams params=Block1.getLayoutParams();
            params.width=screenWidth*(position+1)/5;
        }

        // Block 2
        for (int i = 0; i <5 ; i++) {
            if (moods.get(1).equals(moods_origines.get(i))) {position = i;}
            Block2.setBackgroundColor(Color.parseColor(colors.get(position)));
            ViewGroup.LayoutParams params=Block2.getLayoutParams();
            params.width=screenWidth*(position+1)/5;
        }

        // Block 3
        for (int i = 0; i <5 ; i++) {
            if (moods.get(2).equals(moods_origines.get(i))) {position = i;}
            Block3.setBackgroundColor(Color.parseColor(colors.get(position)));
            ViewGroup.LayoutParams params=Block3.getLayoutParams();
            params.width=screenWidth*(position+1)/5;
        }

        // Block 4
        for (int i = 0; i <5 ; i++) {
            if (moods.get(3).equals(moods_origines.get(i))) {position = i;}
            Block4.setBackgroundColor(Color.parseColor(colors.get(position)));
            ViewGroup.LayoutParams params=Block4.getLayoutParams();
            params.width=screenWidth*(position+1)/5;
        }

        // Block 5
        for (int i = 0; i <5 ; i++) {
            if (moods.get(4).equals(moods_origines.get(i))) {position = i;}
            Block5.setBackgroundColor(Color.parseColor(colors.get(position)));
            ViewGroup.LayoutParams params=Block5.getLayoutParams();
            params.width=screenWidth*(position+1)/5;
        }

        // Block 6
        for (int i = 0; i <5 ; i++) {
            if (moods.get(5).equals(moods_origines.get(i))) {position = i;}
            Block6.setBackgroundColor(Color.parseColor(colors.get(position)));
            ViewGroup.LayoutParams params=Block6.getLayoutParams();
            params.width=screenWidth*(position+1)/5;
        }

        // Block 7
        for (int i = 0; i <5 ; i++) {
            if (moods.get(6).equals(moods_origines.get(i))) {position = i;}
            Block7.setBackgroundColor(Color.parseColor(colors.get(position)));
            ViewGroup.LayoutParams params=Block7.getLayoutParams();
            params.width=screenWidth*(position+1)/5;
        }
    }
}