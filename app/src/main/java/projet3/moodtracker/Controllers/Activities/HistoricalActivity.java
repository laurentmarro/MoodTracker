package projet3.moodtracker.Controllers.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import projet3.moodtracker.R;

public class HistoricalActivity extends AppCompatActivity {

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

    }

    //

}
