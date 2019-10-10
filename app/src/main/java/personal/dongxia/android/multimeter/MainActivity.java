package personal.dongxia.android.multimeter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
                try {
                    URL url = new URL("");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    try {
//                        urlConnection.setDoOutput(true);
//                        urlConnection.setChunkedStreamingMode(0);

//                        OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
//                        writeStream(out);

                        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                        readStream(in);
                    } finally {
                        urlConnection.disconnect();
                    }
                } catch (MalformedURLException e) {

                } catch (IOException e) {

                }
            }
        });
    }

    private void readStream(InputStream in) {
    }
}
