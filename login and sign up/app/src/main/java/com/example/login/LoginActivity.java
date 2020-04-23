package com.example.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.db.LoginDataService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.button3)
    Button button3;//登陆按钮
    @BindView(R.id.editText4)
    EditText username;//用户名输入框
    @BindView(R.id.editText7)
    EditText password;//密码输入框
    private LoginDataService loginDataService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //清单文件中要设置主题为NoActionBar
        //在布局文件中设置的第一个view的背景图片则为当前状态栏背景图片
        StatusBarUtils.with(this).init();
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(Color.parseColor("#D0009688"));
        }
        initData();
    }

    private void initData() {
        loginDataService = new LoginDataService(this);
        //记住密码功能
        String str = PreferencesUtils.getString(this, "login", null);
        if (!TextUtils.isEmpty(str)) {
            String[] s = str.split("##//##");
            username.setText(s[0]);
            username.setSelection(s[0].length());
            password.setText(s[1]);
            password.setSelection(s[1].length());
        }
    }

    @OnClick({R.id.button3, R.id.textView4, R.id.textView7})
    public void onClock(View v) {
        switch (v.getId()) {
            case R.id.button3://登陆按钮
                String userNameStr = username.getText().toString().trim();
                String passwordStr = password.getText().toString().trim();
                if (TextUtils.isEmpty(userNameStr)) {
                    Toast.makeText(this, getString(R.string.username), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(passwordStr)) {
                    Toast.makeText(this, getString(R.string.eyp), Toast.LENGTH_SHORT).show();
                    return;
                }
                UserBean userBean = loginDataService.queryUserByUserName(userNameStr);
                if (userBean == null) {
                    Toast.makeText(this, getString(R.string.no_user), Toast.LENGTH_SHORT).show();
                } else {
                    String pwd = userBean.getUser_password();
                    if (passwordStr.equals(pwd)) {
                        //登陆成功
                        startActivity(new Intent(this, HomeActivity.class));
                        //记住密码功能
                        PreferencesUtils.putString(this, "login", userNameStr + "##//##" + passwordStr);
                        finish();
                    } else {
                        Toast.makeText(this, getString(R.string.pwd_error), Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case R.id.textView4://忘记密码
                startActivity(new Intent(this, ForgotActivity.class));

                break;
            case R.id.textView7://注册
                startActivity(new Intent(this, SignUpActivity.class));

                break;
        }
    }

}