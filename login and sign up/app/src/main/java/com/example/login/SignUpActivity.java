package com.example.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.login.db.LoginDataService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//注册
public class SignUpActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.editText)
    EditText editText;//用户名
    @BindView(R.id.editText8)
    EditText editText8;//密码
    @BindView(R.id.editText9)
    EditText editText9;//密码确认
    @BindView(R.id.editText10)
    EditText editText10;//邮件
    @BindView(R.id.editText_first)
    EditText editText_first;//editText_first name
    @BindView(R.id.editText_last)
    EditText editText_last;//editText_last name
    private LoginDataService loginDataService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(Color.parseColor("#f0f0f0"));
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setTitle(R.string.s);
        initData();
    }


    private void initData() {
        loginDataService = new LoginDataService(this);
    }

    @OnClick(R.id.button)
    public void onClock(View view) {
        switch (view.getId()) {
            case R.id.button:
                String userNameStr = editText.getText().toString().trim();
                String passwordStr = editText8.getText().toString().trim();
                String passwordAffirmStr = editText9.getText().toString().trim();
                String emailStr = editText10.getText().toString().trim();
                String firstStr = editText_first.getText().toString().trim();
                String laseStr = editText_last.getText().toString().trim();
                if (TextUtils.isEmpty(userNameStr)) {
                    Toast.makeText(this, getString(R.string.username), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(passwordStr)) {
                    Toast.makeText(this, getString(R.string.eyp), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(passwordAffirmStr)) {
                    Toast.makeText(this, getString(R.string.affirm_pwd), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(emailStr)) {
                    Toast.makeText(this, getString(R.string.email), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!passwordStr.equals(passwordAffirmStr)) {
                    Toast.makeText(this, getString(R.string.affirm_pwd_error), Toast.LENGTH_SHORT).show();
                    return;
                }

                //判断当前输入的用户名是否存在
                UserBean userBean = loginDataService.queryUserByUserName(userNameStr);
                if (userBean != null) {
                    Toast.makeText(this, getString(R.string.user_id_used), Toast.LENGTH_SHORT).show();
                    return;
                }
                //判断当前输入的邮箱是否存在
                userBean = loginDataService.queryUserByUserName(emailStr);
                if (userBean != null) {
                    Toast.makeText(this, getString(R.string.email_used), Toast.LENGTH_SHORT).show();
                    return;
                }
                UserBean bean = new UserBean();
                bean.setUser_name(userNameStr);
                bean.setUser_email(emailStr);
                bean.setUser_password(passwordStr);

                if (!TextUtils.isEmpty(firstStr)) {
                    bean.setFirst_name(firstStr);
                }
                if (!TextUtils.isEmpty(laseStr)) {
                    bean.setLast_name(laseStr);
                }
                loginDataService.insertUser(bean);
                Toast.makeText(this, getString(R.string.registered_successfully), Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        switch (id) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}