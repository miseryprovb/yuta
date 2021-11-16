package edu.pku.canoe.yuta;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.pku.canoe.yuta.util.ToastUtil;


public class RegisterActivity extends AppCompatActivity {
    private EditText username;
    private EditText userpassword;
    private EditText userpasswordAgain;
    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.emailRg);
        userpassword = findViewById(R.id.passwordRg);
        userpasswordAgain = findViewById(R.id.passwordRgAgain);
        buttonRegister = findViewById(R.id.button_register);
        Drawable email = getResources().getDrawable(R.drawable.email);
        Drawable password = getResources().getDrawable(R.drawable.mima);


        email.setBounds(-15, 0, 35, 50);
        password.setBounds(-15, 0, 35, 50);
        username.setCompoundDrawables(email, null, null, null);
        userpassword.setCompoundDrawables(password, null, null, null);
        userpasswordAgain.setCompoundDrawables(password, null, null, null);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usenameS = username.getText().toString();
                String passwordS = userpassword.getText().toString();
                String passwordS2 = userpasswordAgain.getText().toString();
                String warning = "两次密码不一样";
                String success = "注册成功！";
                Intent intent = null;
                if(usenameS.equals("")) {
                    ToastUtil.showMsg(getApplicationContext(), "请输入email！");
                }else if(passwordS.equals("") || passwordS2.equals("")) {
                    ToastUtil.showMsg(getApplicationContext(), "请输入密码！");
                }
                else if(passwordS.equals(passwordS2)) {
//            //toast普通版
//            Toast.makeText(getApplicationContext(), success, Toast.LENGTH_SHORT).show();

                    ToastUtil.showMsg(getApplicationContext(),success);
                    //正确跳转
                    intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else {
                    //不正确弹出toast
                    //提升版，居中显示的话
                    Toast toastCenter = Toast.makeText(getApplicationContext(), warning, Toast.LENGTH_SHORT);
                    toastCenter.setGravity(Gravity.CENTER, 0, 0);
                    toastCenter.show();
                }
            }
        });
    }




}
