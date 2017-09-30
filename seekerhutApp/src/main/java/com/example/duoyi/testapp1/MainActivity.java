package com.example.duoyi.testapp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button)findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            try {
                URL url = new URL("http://www.seekerhut.com/homeapi/login");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setDoInput(true);
                con.setDoOutput(true);
                Map<String, String> loginMap = new HashMap<>();
                loginMap.put("username", "duwei");
                loginMap.put("password", "123456");
                byte[] postInfo = PostParamConverter(loginMap);
                byte[] gotInfo = new byte[1000];
                con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                con.setRequestProperty("Content-Length", String.valueOf(postInfo.length));
                OutputStream os = con.getOutputStream();
                os.write(postInfo);
                InputStream is = con.getInputStream();
                is.read(gotInfo);
                System.out.print(gotInfo);

            }
            catch (Exception e) {
                System.out.println(e);
            }
            }
        });
    }
    private byte[] PostParamConverter(Map<String, String> params) throws Exception {
        byte[] encodeBytes = null;
        StringBuilder sb = new StringBuilder();
        if (params == null) {
            return encodeBytes;
        }
        for (Map.Entry<String, String> ent : params.entrySet()) {
            sb.append(URLEncoder.encode(ent.getKey(), "UTF-8"));
            if (!TextUtils.isEmpty(ent.getValue())) {
                sb.append(URLEncoder.encode(ent.getValue(), "UTF-8"));
            }
            else {
                sb.append(URLEncoder.encode("", "UTF-8"));
            }
            sb.append("&");
        }
        encodeBytes = sb.toString().getBytes();
        return encodeBytes;
    }
}
