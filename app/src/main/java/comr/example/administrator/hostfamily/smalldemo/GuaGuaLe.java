package comr.example.administrator.hostfamily.smalldemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import comr.example.administrator.hostfamily.R;

public class GuaGuaLe extends AppCompatActivity {
    private ImageView image_before;
    private ImageView image_after;
    private Bitmap bitmap;
    private Bitmap bitmap_before;
    private Bitmap bitmap_after;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gua_gua_le);
        //初始化图片
        image_before = (ImageView)findViewById(R.id.image_before);
        image_after = (ImageView)findViewById(R.id.image_after);
        //获得图片资源
        bitmap_after= BitmapFactory.decodeResource(getResources(),R.drawable.a);
        bitmap_before= BitmapFactory.decodeResource(getResources(),R.drawable.b);
        //设置图片
        image_before.setImageBitmap(bitmap_before);
        image_after.setImageBitmap(bitmap_after);
        //设置空白的bitmap
        bitmap=Bitmap.createBitmap(bitmap_before.getWidth(),bitmap_before.getHeight(),bitmap_before.getConfig());
        Paint paint=new Paint();
        paint.setStrokeWidth(10);
        paint.setColor(Color.BLACK);
        Canvas canvas=new Canvas(bitmap);
        canvas.drawBitmap(bitmap_before,new Matrix(),paint);
        image_before.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        int newX= (int) motionEvent.getX();
                        int newY= (int) motionEvent.getY();
                        for (int i=-10;i<10;i++){
                            for (int j=-10;j<10;j++){
                                if (i+newX>=bitmap_before.getWidth()||j+newY>=bitmap_before.getHeight()||i+newX<0||j+newY<0){
                                    return false;
                                }
                                bitmap.setPixel(i+newX,j+newY,Color.TRANSPARENT);//将图片设置为透明
                            }
                        }
                        image_before.setImageBitmap(bitmap);
                        break;
                }

                return true;
            }
        });

    }
}
