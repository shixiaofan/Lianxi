package comr.example.administrator.hostfamily.main_tab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import comr.example.administrator.hostfamily.R;
import comr.example.administrator.hostfamily.Rxjava;

public class Home_page extends AppCompatActivity implements View.OnClickListener{

    private Button rxjava;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_home_page);
        initView();
    }

    private void initView() {
        rxjava = (Button)findViewById(R.id.rxjava);
        rxjava.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rxjava:
                Intent intentRx=new Intent(Home_page.this,Rxjava.class);
                startActivity(intentRx);
        }
    }
}
