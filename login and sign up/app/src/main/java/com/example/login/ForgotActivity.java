package com.example.login;

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

public class ForgotActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.layout_confirm)
    View layout_confirm;
    @BindView(R.id.layout)
    View layout;
    @BindView(R.id.editText_confirm)
    EditText editText_confirm;//邮箱验证
    @BindView(R.id.editText2)
    EditText editText2;//邮箱
    @BindView(R.id.editText)
    EditText editText;//用户名
    @BindView(R.id.editText3)
    EditText editText3;//密码
    @BindView(R.id.editText11)
    EditText editText11;//密码确认

    private LoginDataService loginDataService;
    private UserBean bean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(Color.parseColor("#f0f0f0"));
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setTitle(R.string.f);
        loginDataService = new LoginDataService(this);
    }

    //验证邮箱
    @OnClick(R.id.button_confirm)
    public void verifyEmail(View view) {
        String str = editText_confirm.getText().toString().trim();
        if (!TextUtils.isEmpty(str)) {
            bean = loginDataService.queryUserByEmail(str);
            if (bean == null) {
                Toast.makeText(this, getString(R.string.email_unused), Toast.LENGTH_SHORT).show();
            } else {
                layout_confirm.setVisibility(View.GONE);
                layout.setVisibility(View.VISIBLE);
                editText2.setText(str);
                editText.setText(bean.getUser_name());
                editText.setEnabled(false);
                editText2.setEnabled(false);
            }
        } else {
            Toast.makeText(this, getString(R.string.email), Toast.LENGTH_SHORT).show();
        }

    }

    //更新信息
    @OnClick(R.id.button)
    public void update(View view) {
        String passwordStr = editText3.getText().toString().trim();
        String passwordAffirmStr = editText11.getText().toString().trim();
//        String emailStr = editText2.getText().toString().trim();

        if (TextUtils.isEmpty(passwordStr)) {
            Toast.makeText(this, getString(R.string.eyp), Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(passwordAffirmStr)) {
            Toast.makeText(this, getString(R.string.affirm_pwd), Toast.LENGTH_SHORT).show();
            return;
        }
//        if (TextUtils.isEmpty(emailStr)) {
//            Toast.makeText(this, getString(R.string.email), Toast.LENGTH_SHORT).show();
//            return;
//        }
        if (!passwordStr.equals(passwordAffirmStr)) {
            Toast.makeText(this, getString(R.string.affirm_pwd_error), Toast.LENGTH_SHORT).show();
            return;
        }


//        //判断当前输入的邮箱是否存在
//        UserBean userBean = loginDataService.queryUserByUserName(emailStr);
//        if (userBean != null && !bean.getUser_email().equals(emailStr)) {
//            Toast.makeText(this, getString(R.string.email_used), Toast.LENGTH_SHORT).show();
//            return;
//        }
//        bean.setUser_email(emailStr);
        bean.setUser_password(passwordStr);
        loginDataService.update(bean);
        Toast.makeText(this, getString(R.string.update_successfully), Toast.LENGTH_SHORT).show();
        finish();
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
