package personal.dongxia.android.multimeter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import personal.dongxia.android.business.Ip.IpService;
import personal.dongxia.android.business.Ip.model.Ip;
import personal.dongxia.android.framework.bundle.basic.BundlePlatform;

public class MainActivity extends AppCompatActivity {

    private EditText edtIpAddress;
    private Button btnQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtIpAddress = findViewById(R.id.edt_ip_address);
        btnQuery = findViewById(R.id.btn_query);
        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               final String address = edtIpAddress.getText().toString();
                final IpService ipService = BundlePlatform.getServiceManager().getService(IpService.class);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Ip ip = ipService.queryIp(address);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, ip.getCountry(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }).start();
            }
        });
    }
}
