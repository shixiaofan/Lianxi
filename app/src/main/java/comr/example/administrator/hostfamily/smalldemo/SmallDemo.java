package comr.example.administrator.hostfamily.smalldemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import comr.example.administrator.hostfamily.R;

public class SmallDemo extends AppCompatActivity implements View.OnClickListener {

    private Button gua_gua_le,threeD_car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_small_demo);
        gua_gua_le = (Button)findViewById(R.id.gua_gua_le);
        threeD_car = (Button)findViewById(R.id.threeD_car);
        gua_gua_le.setOnClickListener(this);
        threeD_car.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.gua_gua_le:
                startActivity(new Intent(this,GuaGuaLe.class));
                break;
            case R.id.threeD_car:
                startActivity(new Intent(this,ThreeD_Car.class));
                break;
        }

    }
}
