package edu.pku.canoe.yuta;;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.pku.canoe.yuta.util.ButtonAnimation;


public class MainActivity extends AppCompatActivity {

    //声明kongjian
    private Button loginButton;
    private Button registerButton;
    private Button passengerButton;
    private Button testButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找到控件
        loginButton = findViewById(R.id.btn_Login);
        registerButton = findViewById(R.id.btn_Register);
        passengerButton = findViewById(R.id.btn_passenger_Login);
        testButton = findViewById(R.id.test_button);
        ButtonAnimation.initButton(loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        passengerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                startActivity(intent);
            }
        });
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });
    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
