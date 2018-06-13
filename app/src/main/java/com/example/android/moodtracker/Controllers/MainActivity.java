package com.example.android.moodtracker.Controllers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.android.moodtracker.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="" ;
    private ConstraintLayout rootview;
    private ImageView historicalview;
    private ImageView addview;
    private ImageView smileyview;
    private static String COMMENT = null;
    private int maxLength = 30;
    private static String MOOD = null;
    private static String DATE = null;
    private static String TO_SAVE = null;
    private int position = 3;
    ArrayList<Integer> smileys = new ArrayList<>();
    ArrayList<String> colors;
    ArrayList<String> moods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Moods definition
        moods = new ArrayList<>();
        moods.add("sad");
        moods.add("disappointed");
        moods.add("normal");
        moods.add("happy");
        moods.add("super_happy");

        // Colors definition
        colors = new ArrayList<>();
        colors.add("#ffde3c50");
        colors.add("#ff9b9b9b");
        colors.add("#a5468ad9");
        colors.add("#ffb8e986");
        colors.add("#fff9ec4f");

        // Smileys definition
        smileys.add(R.drawable.smiley_sad);
        smileys.add(R.drawable.smiley_disappointed);
        smileys.add(R.drawable.smiley_normal);
        smileys.add(R.drawable.smiley_happy);
        smileys.add(R.drawable.smiley_super_happy);

        // Widgets
        rootview = (ConstraintLayout) findViewById(R.id.rootview);
        historicalview = (ImageView) findViewById(R.id.historicalview);
        addview = (ImageView) findViewById(R.id.addview);
        smileyview = (ImageView) findViewById(R.id.smileyview);

        // set color and smiley

        color_and_smiley();

        // Class to detect the vertical swipe
        class OnSwipeTouchListener implements View.OnTouchListener {

            private final GestureDetector gestureDetector;

            public OnSwipeTouchListener (Context context){
                gestureDetector = new GestureDetector(context, new GestureListener());
            }

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event); }

            final class GestureListener extends GestureDetector.SimpleOnGestureListener {
                private static final int SWIPE_THRESHOLD = 100;
                private static final int SWIPE_VELOCITY_THRESHOLD = 100;

                @Override
                public boolean onDown(MotionEvent e) {
                    return true; }

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    boolean result = false;
                    try {
                        float diffY = e2.getY() - e1.getY();
                        if  (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffY > 0) {onSwipeBottom();} else {onSwipeTop();}
                            result = true; }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    return result;
                }
            }

            public void onSwipeTop() { }

            public void onSwipeBottom() { }
        }

        // Listeners to the touch
        rootview.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeTop() {
                position +=1;
                if (position>4) {position =4;}
                color_and_smiley();}

            public void onSwipeBottom() {
                position -=1;
                if (position<0) {position =0;}
                color_and_smiley();}
        });

        // Listeners on images
        historicalview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HistoricalActivity.class);
                startActivity(intent);
            }
        });

        addview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Commentaire");
                final EditText input = new EditText(MainActivity.this);

                // length of EditText
                InputFilter[] FilterArray = new InputFilter[1];
                FilterArray[0] = new InputFilter.LengthFilter(maxLength);
                input.setFilters(FilterArray);
                builder.setView(input);

                // Negative Button
                builder.setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                // Positive Button
                builder.setPositiveButton("VALIDER", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        COMMENT = input.getText().toString();
                        toast("Comment : " + COMMENT + " is today's comment.");
                        backup();
                    }
                });
                builder.setCancelable(true);
                AlertDialog dialog = builder.create();
                dialog.show();
                backup();
            }
        });

        smileyview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Play Music
                switch(position) {
                    case 0:
                        final MediaPlayer mp0 = MediaPlayer.create(MainActivity.this, R.raw.sad);
                        mp0.start();
                        break;
                    case 1:
                        final MediaPlayer mp1 = MediaPlayer.create(MainActivity.this, R.raw.disappointed);
                        mp1.start();
                        break;
                    case 2:
                        final MediaPlayer mp2 = MediaPlayer.create(MainActivity.this, R.raw.normal);
                        mp2.start();
                        break;
                    case 3:
                        final MediaPlayer mp3 = MediaPlayer.create(MainActivity.this, R.raw.happy);
                        mp3.start();
                        break;
                    case 4:
                        final MediaPlayer mp4 = MediaPlayer.create(MainActivity.this, R.raw.super_happy);
                        mp4.start();
                        break;
                }

                //Update of MOOD_OF_TODAY
                MOOD = moods.get(position);
                toast("Mood : " + MOOD + " is today's mood.");
                backup();
            }
        });
        backup();
    }

    // method to set color and smiley
    public void color_and_smiley() {
        rootview.setBackgroundColor(Color.parseColor(colors.get(position)));
        smileyview.setImageResource(smileys.get(position));}

    // method to up comment
    private void comment_update() {
        // if mood_of_today doesn't exist
        if (COMMENT == null) {
            COMMENT = "0";
        }
    }

    // method to update the mood
    private void mood_update() {
        // if mood_of_today doesn't exist
        if (MOOD == null) {
            MOOD = "happy";
        }
    }

    // method to get the date
    private void date() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DATE = simpleDateFormat.format(calendar.getTime());
    }

    // method to toast change in mood or comment
    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public static void putPref(String key, String value, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    // method to concatenate data for backup
    private void backup() {
        date();
        mood_update();
        comment_update();
        TO_SAVE = DATE+"-"+MOOD+"-"+COMMENT;
        backup_keys();
    }

    // Backup of data
    private void backup_keys() {
        putPref(DATE, TO_SAVE, MainActivity.this);
    }
}