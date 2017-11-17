package comr.example.administrator.hostfamily.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;

import comr.example.administrator.hostfamily.R;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Observable_Map extends AppCompatActivity implements View.OnClickListener{

    private TextView show_ip;
    private TextView click_operator;
    private TextView tvLogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observable__map);
        initView();
    }

    private void initView() {
        click_operator = (TextView)findViewById(R.id.btn_operator);
        show_ip = (TextView)findViewById(R.id.btn_operator_map_ips);
        tvLogs = (TextView) findViewById(R.id.tv_logs);
        tvLogs.setText("Click Button to test 'map operator'");
        click_operator.setOnClickListener(this);
        show_ip.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
             case R.id.btn_operator_map_ips:
                tvLogs.setText("");
                 observable_mapIps();
                break;
            case R.id.btn_operator:
                tvLogs.setText("");
                map_bigwrite();
                break;
        }

    }



private String getIpfromUrls(String str)throws MalformedURLException,UnknownHostException{
    URL url=new URL(str);
    String host=url.getHost();//获得主机
    String address= InetAddress.getByName(host).toString();//获得主机地址
    int b=address.indexOf("/");
    return address.substring(b+1);
}
private Observable<String> processUrlsIpByMap(){
    return Observable.just( "http://www.baidu.com/",//你要获取地址的URL
                                    "http://www.google.com/",
                                     "https://www.bing.com/")
            .map(new Func1<String, String>() {
                @Override
                public String call(String s) {
                    try {
                        return s+":"+getIpfromUrls(s);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
}

private void observable_mapIps(){
    processUrlsIpByMap().subscribe(new Action1<String>() {
        @Override
        public void call(String s) {
            Log.d("tvLogs","--Consume data--"+s);
        }
    });

}

private void map_bigwrite(){
    Observable.from(new String[]{"java","android","ios" })
          .map(new Func1<String, String>() {
              @Override
              public String call(String s) {
                  Log.d("tvLogs","--big  writer  数字挨个输出--"+s);
                  return s.toUpperCase();//全部字符变为大写，返回新字符串。
              }
          }).toList()
            .map(new Func1<List<String>, List<String>>() {
                @Override
                public List<String> call(List<String> strings) {
                    Log.d("tvLogs","--big  writer----大写--"+strings.toString());
                    Collections.reverse(strings);//按原顺序的反向顺序返回list
                    return strings;
                }
            }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1<List<String>>() {
                @Override
                public void call(List<String> strings) {
                    Log.d("tvLogs","--big  writer----反过来大写--"+strings.toString());
                }
            });
}

}
