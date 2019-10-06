package com.example.worldinfoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.worldinfoapplication.util.AppStringUtils;
import com.example.worldinfoapplication.util.LogUtils;
import com.example.worldinfoapplication.util.SharedPreferenceUtil;
import com.example.worldinfoapplication.util.TextInputCondition;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private static LogUtils log= new LogUtils(LoginActivity.class.getSimpleName(),true);
    //email edit input layout and edit text
    private TextInputLayout textInputLayout_Email;
    private TextInputEditText textInputEditText_Email;

    //Password edit input layout and edit text
    private TextInputLayout textInputLayout_Pass;
    private TextInputEditText textInputEditText_Pass;

    //Progress bar
    private ProgressBar progressBar;

    //login button
    private Button button_Login;

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void networkConnected(boolean isConnected) {

    }

    @Override
    protected void getNetworkType(String networkType) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String loginUser=getStringValue(getString(R.string.pref_login_user_name_key));
        if(AppStringUtils.isNotEmpty(loginUser)){
            progressBar.setVisibility(View.VISIBLE);
            //if already login go to menu activity
            menuActivity();
        }



    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
          // go to menu activity

        textInputLayout_Email=findViewById(R.id.layoutEmail);
        textInputEditText_Email=findViewById(R.id.etEmail);
        textInputLayout_Pass=findViewById(R.id.layoutPassword);
        textInputEditText_Pass=findViewById(R.id.etPassword);
        button_Login=findViewById(R.id.btn_login);
        progressBar=findViewById(R.id.progressBar);
        //set onclick listener
        button_Login.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                if(isValidData()) {
                    if(checkAndRequestPermissions(textInputEditText_Email.getText().toString())){
                       menuActivity();
                    }
                }
                break;
        }
    }

    private boolean isValidData() {
        boolean isValid=true;
        String email=textInputEditText_Email.getText().toString();
        String pass=textInputEditText_Pass.getText().toString();

        if(AppStringUtils.isEmpty(email)){
            textInputLayout_Email.setError(getString(R.string.error_email));
            requestFocus(textInputEditText_Email);
            isValid=false;
        }
        else if(!TextInputCondition.isValidEmailInput(email)){
            textInputLayout_Email.setError(getString(R.string.invalid_email));
            requestFocus(textInputEditText_Email);
            isValid=false;
        }
        else {
            textInputLayout_Email.setErrorEnabled(false);
        }

         if(AppStringUtils.isEmpty(pass)){
             textInputEditText_Pass.setError(getString(R.string.error_pass));
             requestFocus(textInputEditText_Pass);
             isValid=false;
         }
         else if(textInputEditText_Pass.getText().toString().length()<6){
             textInputLayout_Pass.setError(getString(R.string.password_length));
             requestFocus(textInputEditText_Pass);
             isValid=false;
         }
         else{
             textInputLayout_Pass.setErrorEnabled(false);
         }

    return isValid;

    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    
      private void  menuActivity(){
          Intent intent_Users= new Intent(LoginActivity.this,MenuActivity.class);
          intent_Users.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
          startActivity(intent_Users);
          finish();
          progressBar.setVisibility(View.GONE);
    }
}
