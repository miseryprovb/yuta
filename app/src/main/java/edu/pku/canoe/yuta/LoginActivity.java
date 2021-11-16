package edu.pku.canoe.yuta;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.pku.canoe.yuta.util.ToastUtil;


public class LoginActivity extends AppCompatActivity{
    private EditText username;
    private EditText userpassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.email);
        userpassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.button_login);
        Drawable email = getResources().getDrawable(R.drawable.email);
        Drawable password = getResources().getDrawable(R.drawable.mima);


        email.setBounds(-15, 0, 35, 50);
        password.setBounds(-15, 0, 35, 50);
        username.setCompoundDrawables(email, null, null, null);
        userpassword.setCompoundDrawables(password, null, null, null);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameS = username.getText().toString();
                String passwordS = userpassword.getText().toString();
                String success = "登录成功";
                String fail = "密码或账号有误，请重新登录！";
                Intent intent = null;
                ///假设正确的密码是gzy,123456
                if(usernameS.equals("gzy") && passwordS.equals("123456")) {
//            //toast普通版
//            Toast.makeText(getApplicationContext(), success, Toast.LENGTH_SHORT).show();

                    ToastUtil.showMsg(getApplicationContext(), success);
                    //正确跳转
                    intent = new Intent(LoginActivity.this, ShowActivity.class);
                    startActivity(intent);
                }else if(usernameS.equals("")){
                    ToastUtil.showMsg(getApplicationContext(), "请输入email！");
                }else if(passwordS.equals("")){
                    ToastUtil.showMsg(getApplicationContext(), "请输入密码！");
                }
                else {
                    //不正确弹出toast
                    //提升版，居中显示的话
                    Toast toastCenter = Toast.makeText(getApplicationContext(), fail, Toast.LENGTH_SHORT);
                    toastCenter.show();
                }
            }
        });
    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}
