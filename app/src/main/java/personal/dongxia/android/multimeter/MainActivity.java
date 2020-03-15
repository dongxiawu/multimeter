package personal.dongxia.android.multimeter;

import java.util.Arrays;
import java.util.List;
import java.util.Observer;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import android.net.Uri;
import android.util.Log;
import android.view.View.OnClickListener;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import personal.dongxia.android.business.Ip.IpService;
import personal.dongxia.android.business.Ip.model.Ip;
import personal.dongxia.android.framework.bundle.BundlePlatform;

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
        findViewById(R.id.test).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFiles(Arrays.asList("1111","22222","33333","44444","555555"));
            }
        });
    }


    private void uploadFiles(List<String> uriList) {
        Disposable disposable = Observable.fromIterable(uriList).concatMap(
            new Function<String, ObservableSource<String>>() {
                @Override
                public ObservableSource<String> apply(String s) throws Exception {
                    return Observable.just(s).flatMap(new Function<String, ObservableSource<String>>() {
                        @Override
                        public ObservableSource<String> apply(String s) throws Exception {
                            return Observable.create(emitter -> {
                                new Thread(() -> {
                                    emitter.onNext(s);
                                    emitter.onComplete();
                                }).start();
                            });
                        }
                    });
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(result -> {
            Log.d("1111", result);
        }, throwable -> {

        }, () -> {
                Log.d("111", "complite");
        });
    }
}
