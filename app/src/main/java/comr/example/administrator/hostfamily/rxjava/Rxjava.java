package comr.example.administrator.hostfamily.rxjava;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

import comr.example.administrator.hostfamily.R;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.subjects.PublishSubject;
public class Rxjava extends AppCompatActivity implements View.OnClickListener{
    private Button creat_observable;
    private Button observable_from;
    private Button obs_just;
    private Button publish_subject;
    private Button obs_distinct;
    private Button obs_map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        initView();
    }

    private void initView() {
        creat_observable = (Button)findViewById(R.id.creat_observable);
        observable_from = (Button) findViewById(R.id.observable_from);
        obs_just = (Button)findViewById(R.id.obs_just);
        publish_subject = (Button)findViewById(R.id.publish_subject);
        obs_map = (Button)findViewById(R.id.obs_map);
        creat_observable.setOnClickListener(this);
        observable_from.setOnClickListener(this);
        obs_just.setOnClickListener(this);
        publish_subject.setOnClickListener(this);
        obs_map.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.creat_observable:
                creat_observable();
                break;
            case R.id.observable_from:
                observable_from();
                break;
            case R.id.obs_just:
                obs_just();
                break;
            case R.id.publish_subject:
                publish_subject();
                break;
            case R.id.obs_map:
                obs_map();
                break;
        }
    }

    private void obs_map() {
        startActivity(new Intent(this,Observable_Map.class));
    }


    private void publish_subject() {
        final PublishSubject<Boolean> pbs=PublishSubject.create();
        pbs.subscribe(new Observer<Boolean>() {
            @Override
            public void onCompleted() {
                Log.d("publish_subject--","publish_subject---------------------next");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                Log.d("publish_subject--","publish_subject---------------------next");

            }
        });
    Observable.create(new Observable.OnSubscribe<Integer>() {
        @Override
        public void call(Subscriber<? super Integer> subscriber) {
            for (int i=0;i<5;i++){
                subscriber.onNext(i);
        }
        subscriber.onCompleted();
    }}).doOnCompleted(new Action0() {
        @Override
        public void call() {
            pbs.onNext(true);
        }
    }).subscribe();
    }

    private void obs_just() {
        //利用observable.just将一个传统函数转变为observable函数进行输出
        Observable<String> obsjust=Observable.just(helloWord());
        Subscription subjust=obsjust.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d("obs_just","observable_just  has completed!");

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.d("obs_just","observable------------just"+s);

            }
        });

    }
    public String helloWord(){
        return "hello ! this is observable just!";
    }

    private void observable_from() {
        //通过observable.from遍历一个数组
        List<Integer> list=new ArrayList<>();
        list.add(111);
        list.add(222);
        list.add(333);
        list.add(444);
        Observable<Integer> observable=Observable.from(list)
            .take(2)//只要前2个数据
            .repeat(3)//重复三次
            .distinct();//去除重复选项
        Subscription sub=observable.subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d("boservale_creat","observable----------------from"+integer);
            }
        });
    }

    public void creat_observable(){
        //创建一个observable，observable.creat()
        final Observable<Integer> observable=Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i=0;i<5;i++){
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();//完成
            }
        });
        Subscription subscription=observable.subscribe(new Observer<Integer>() {
            //订阅了Observable，返回一个Subscription
            @Override
            public void onCompleted() {
                Log.d("completed","observable completed!");

            }

            @Override
            public void onError(Throwable e) {
                Log.d("error","oh! has some error happened!");

            }

            @Override
            public void onNext(Integer integer) {
                Log.d("onNext","item is ----------------"+integer);
            }
        });
    }


}
