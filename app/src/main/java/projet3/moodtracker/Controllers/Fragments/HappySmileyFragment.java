package projet3.moodtracker.Controllers.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import projet3.moodtracker.Models.Data;
import projet3.moodtracker.R;

public class HappySmileyFragment extends Fragment {

    public static String MOOD = null;
    public static String COMMENT = null;
    public static String DATE = null;
    public static String TO_SAVE = null;
    private Button historical_button;
    private Button comment_button;
    private ImageView smiley_iv;
    int maxLength = 30;

    public static HappySmileyFragment newInstance() {
        return (new HappySmileyFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.happy, container, false);

        // Widgets

        historical_button = (Button) view.findViewById(R.id.historical_button);
        comment_button = (Button) view.findViewById(R.id.comment_button);
        smiley_iv = (ImageView) view.findViewById(R.id.smiley_iv);

        // Listeners

        historical_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getActivity(), Data.class);
                startActivity(myIntent);
            }
        });

        comment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Commentaire");
                final EditText input = new EditText(getActivity());

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
            }
        });

        smiley_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Play Music
                final MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.happy);
                mp.start();

                //Update of MOOD_OF_TODAY
                MOOD = "happy";
                toast("Mood : " + MOOD + " is today's mood.");
                backup();
            }
        });

        backup();
        return view;
    }

    private void comment_update() {
        // if mood_of_today doesn't exist

        if (COMMENT == null) {
            COMMENT = "0";
        }
    }

    private void mood_update() {
        // if mood_of_today doesn't exist

        if (MOOD == null) {
            MOOD = "happy";
        }
    }

    private void date() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DATE = simpleDateFormat.format(calendar.getTime());
    }

    public static void putPref(String key, String value, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    private void backup_keys() {
        putPref(DATE, TO_SAVE, getActivity());
    }

    private void toast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    private void backup() {
        date();
        mood_update();
        comment_update();
        TO_SAVE = DATE+"-"+MOOD+"-"+COMMENT;
        backup_keys();
    }
}