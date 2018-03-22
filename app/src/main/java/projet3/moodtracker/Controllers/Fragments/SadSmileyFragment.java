package projet3.moodtracker.Controllers.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import projet3.moodtracker.R;

public class SadSmileyFragment extends Fragment {

    public static SadSmileyFragment newInstance() {
        return (new SadSmileyFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sad, container, false);
    }
}