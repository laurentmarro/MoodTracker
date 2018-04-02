package projet3.moodtracker.Controllers.Activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import projet3.moodtracker.Adapters.PageAdapter;
import projet3.moodtracker.R;

public class MainActivity extends AppCompatActivity {

    public static int position = 3;

    // Getter et setter

    public static int getPosition() {
        return position;
    }

    public static void setPosition(int position) {
        MainActivity.position = position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configure ViewPager

        this.configureViewPager();
    }

    private void configureViewPager(){

        // Get ViewPager from layout
        ViewPager pager = (ViewPager)findViewById(R.id.activity_main_viewpager);
        // Set Adapter PageAdapter and glue it together
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()) {
        });
    }
}