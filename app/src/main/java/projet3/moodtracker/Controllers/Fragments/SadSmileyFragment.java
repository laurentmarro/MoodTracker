package projet3.moodtracker.Controllers.Fragments;

import android.app.AlertDialog;
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
import projet3.moodtracker.Controllers.Activities.HistoricalActivity;
import projet3.moodtracker.R;

public class SadSmileyFragment extends Fragment {

    public static String MOOD_OF_TODAY;
    private Button historical_button;
    private Button comment_button;
    private ImageView smiley_iv;
    private SharedPreferences preferences;

    public static SadSmileyFragment newInstance() {
        return (new SadSmileyFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sad, container, false);
        historical_button = (Button) view.findViewById(R.id.historical_button);
        comment_button = (Button) view.findViewById(R.id.comment_button);
        smiley_iv = (ImageView) view.findViewById(R.id.smiley_iv);

        historical_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent historicalActivity = new Intent(getActivity(), HistoricalActivity.class);
                startActivity(historicalActivity);
            }
        });

        comment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Commentaire");
                final EditText input = new EditText(getActivity());
                int maxLength = 30;
                InputFilter[] FilterArray = new InputFilter[1];
                FilterArray[0] = new InputFilter.LengthFilter(maxLength);
                input.setFilters(FilterArray);
                builder.setView(input);
                builder.setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {dialog.cancel();
                    }
                });

                builder.setPositiveButton("VALIDER", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        String COMMENT_OF_TODAY = input.getText().toString();
                        preferences.edit().putString("Commentaire", COMMENT_OF_TODAY).apply();

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
                final MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.sad);
                mp.start();
                // Save
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(MOOD_OF_TODAY, "sad");
                editor.commit();
            }
        });

        return view;
    }
}