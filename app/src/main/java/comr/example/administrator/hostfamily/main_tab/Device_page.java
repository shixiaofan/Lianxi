package comr.example.administrator.hostfamily.main_tab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import comr.example.administrator.hostfamily.R;

public class Device_page extends AppCompatActivity implements View.OnClickListener{
    private int gameType;
    private SurfaceView surface;
    private Button one,two,three,four;
    private int w,h;
    private int gameSpan;//游戏区域高度
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        startstartmenuView();
    }

    private void startstartmenuView() {
        setContentView(R.layout.lay_device_page);//游戏完后重新加载游戏

    }

    private void initView() {
         one=(Button)findViewById(R.id.one);
         two=(Button)findViewById(R.id.two);
         three=(Button)findViewById(R.id.three);
         four=(Button)findViewById(R.id.four);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.one:
                gameType=2;
                break;
            case R.id.two:
                gameType=3;
                break;
            case R.id.three:
                break;
            case R.id.four:
                break;
            default:
        }
startGameView();
    }

    private void startGameView() {
    }

}
