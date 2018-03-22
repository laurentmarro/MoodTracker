package projet3.moodtracker.Controllers.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import projet3.moodtracker.R;

public class HappySmileyFragment extends Fragment {

    public static HappySmileyFragment newInstance() {
        return (new HappySmileyFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.happy, container, false);
    }
}