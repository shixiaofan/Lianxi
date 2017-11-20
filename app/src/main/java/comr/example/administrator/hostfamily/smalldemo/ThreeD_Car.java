package comr.example.administrator.hostfamily.smalldemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import comr.example.administrator.hostfamily.R;

public class ThreeD_Car extends AppCompatActivity {
    private ImageView car;
    private int srcNum;//当前的图片编号
    private int startX;//开始的位置
    private int currentX;//结束的位置
    private int maxNum=52;//图片总和
    private static Bitmap biemap;
    private int[] srcs = new int[] { R.drawable.p1, R.drawable.p2,
            R.drawable.p3, R.drawable.p4, R.drawable.p5, R.drawable.p6,
            R.drawable.p7, R.drawable.p8, R.drawable.p9, R.drawable.p10,
            R.drawable.p11, R.drawable.p12, R.drawable.p13, R.drawable.p14,
            R.drawable.p15, R.drawable.p16, R.drawable.p17, R.drawable.p18,
            R.drawable.p19, R.drawable.p20, R.drawable.p21, R.drawable.p22,
            R.drawable.p23, R.drawable.p24, R.drawable.p25, R.drawable.p26,
            R.drawable.p27, R.drawable.p28, R.drawable.p29, R.drawable.p30,
            R.drawable.p31, R.drawable.p32, R.drawable.p33, R.drawable.p34,
            R.drawable.p35, R.drawable.p36, R.drawable.p37, R.drawable.p38,
            R.drawable.p39, R.drawable.p40, R.drawable.p41, R.drawable.p42,
            R.drawable.p43, R.drawable.p44, R.drawable.p45, R.drawable.p46,
            R.drawable.p47, R.drawable.p48, R.drawable.p49, R.drawable.p50,
            R.drawable.p51, R.drawable.p52 };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_d_car);
        car = (ImageView)findViewById(R.id.car);
        srcNum=1;
        car.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX= (int) motionEvent.getX();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        currentX= (int) motionEvent.getX();
                        if (currentX-startX>0){
                            //右划
                            rightSlide();
                        }else if (currentX-startX<0){
                           //左划
                            leftSlide();
                        }
                        startX= (int) motionEvent.getX();//重置起始位置
                        break;


                }
                return true;
            }
        });

    }

    private void leftSlide() {
        if (srcNum<=0){
            srcNum=maxNum;
        }
        if (srcNum<=maxNum){
            biemap=BitmapFactory.decodeResource(getResources(),srcs[srcNum-1]);
            car.setImageBitmap(biemap);
            srcNum--;
        }
    }

    private void rightSlide() {
        if (srcNum>maxNum){
            srcNum=1;
        }
        if (srcNum>0){
            biemap= BitmapFactory.decodeResource(getResources(),srcs[srcNum-1]);
            car.setImageBitmap(biemap);
            srcNum++;
        }
    }
}
