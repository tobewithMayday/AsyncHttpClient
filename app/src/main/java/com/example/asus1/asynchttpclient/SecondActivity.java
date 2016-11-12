package com.example.asus1.asynchttpclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

public class SecondActivity extends AppCompatActivity {

    private EditText et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        et2 = (EditText)findViewById(R.id.et);

        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://v.juhe.cn/xiangji_weather/real_time_weather.php?areaid=101010100&key=dc516fba3073640ef277cc122325601b";
        client.get(getApplicationContext(),url,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                // response为返回的JSON对象
                System.out.println(response.toString());

                // 获得 response 中的 result 属性
                try {
                    JSONObject result = response.getJSONObject("result");

                    // 获得 result 中的 data 属性
                    JSONObject data = result.getJSONObject("data");

                    // 获得 data 中的详细内容
                    String output = data.getString("cw") + "\n"
                            + data.getString("w") + "\n"
                            + data.getInt("rh") + "\n"
                            + data.getString("cwd");

                    //将内容放到文本框中
                    et2.setText(output);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
