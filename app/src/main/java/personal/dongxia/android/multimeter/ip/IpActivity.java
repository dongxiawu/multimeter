package personal.dongxia.android.multimeter.ip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import personal.dongxia.android.business.Ip.model.Ip;
import personal.dongxia.android.multimeter.R;

public class IpActivity extends AppCompatActivity implements IpContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip);
    }

    @Override
    public void showQueryResult(Ip ip) {

    }

    @Override
    public void setPresenter(IpContract.Presenter presenter) {
        
    }
}
