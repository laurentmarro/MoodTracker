package projet3.moodtracker.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import projet3.moodtracker.Controllers.Fragments.DisappointedFragment;
import projet3.moodtracker.Controllers.Fragments.HappySmileyFragment;
import projet3.moodtracker.Controllers.Fragments.NormalSmileyFragment;
import projet3.moodtracker.Controllers.Fragments.SadSmileyFragment;
import projet3.moodtracker.Controllers.Fragments.SuperHappySmileyFragment;

public class PageAdapter extends FragmentPagerAdapter {


    //Default Constructor
    public PageAdapter(FragmentManager mgr) {
        super(mgr);
    }

    @Override
    public int getCount() {
        return (5);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return DisappointedFragment.newInstance();
            case 1:
                return SadSmileyFragment.newInstance();
            case 2:
                return NormalSmileyFragment.newInstance();
            case 3:
                return HappySmileyFragment.newInstance();
            case 4:
                return SuperHappySmileyFragment.newInstance();
            default:
                return null;
        }
    }
}
