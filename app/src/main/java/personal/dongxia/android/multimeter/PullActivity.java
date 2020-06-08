package personal.dongxia.android.multimeter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class PullActivity extends AppCompatActivity {
    //private TestView testView;
    private Button btnTest;

    private TaurusPanelLayout taurusPanelLayout;
    private View panel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull);
        taurusPanelLayout = findViewById(R.id.panel_layout);
        //testView = findViewById(R.id.test_view);

        View root = getWindow().getDecorView();
        if (root instanceof ViewGroup) {
            panel = LayoutInflater.from(this).inflate(R.layout.float_panel,(ViewGroup)root, false);
            ((ViewGroup)root).addView(panel);
        }
        taurusPanelLayout.setPanel(panel);

        btnTest = findViewById(R.id.btn_test);
        btnTest.setOnClickListener((v) -> {
            //panel.setVisibility(View.VISIBLE);
            //TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0 - panel.getHeight(), 0);
            //translateAnimation.setDuration(5000);
            //panel.startAnimation(translateAnimation);
        });
    }
}
