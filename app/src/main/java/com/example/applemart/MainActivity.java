package com.example.applemart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    EditText name, number, password,passwordConfirm, email, address, emailLogin,passwordLogin, adminUname, adminPwd;
    Button register, loginBtn, adminBtn;
    ProgressBar regProgressBar, logProgressBar;
    TextView dispChnage, dispChnageTwo, user_login;
    LinearLayout loginLinear,registerLinear,adminLinear;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    ImageButton adminButton;
    ImageView userImage, adminImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adminButton = findViewById(R.id.admin_button);
        register = findViewById(R.id.register_button);
        adminBtn = findViewById(R.id.admin_login_btn);
        loginBtn = findViewById(R.id.login_btn);
        name = findViewById(R.id.name_text);
        number = findViewById(R.id.phone_number_text);
        password = findViewById(R.id.password_text);
        passwordConfirm = findViewById(R.id.password_confirm_text);
        email = findViewById(R.id.email_text);
        address = findViewById(R.id.address_text);
        adminUname = findViewById(R.id.admin_uname);
        adminPwd = findViewById(R.id.admin_pwd);
        dispChnage = findViewById(R.id.change_disp);
        dispChnageTwo = findViewById(R.id.change_disp_two);
        user_login = findViewById(R.id.user_login);
        loginLinear =  findViewById(R.id.login_linear);
        registerLinear = findViewById(R.id.register_linear);
        adminLinear = findViewById(R.id.admin_linear);
        emailLogin = findViewById(R.id.email_login_text);
        passwordLogin = findViewById(R.id.password_login_text);
        regProgressBar = findViewById(R.id.register_progress);
        logProgressBar = findViewById(R.id.login_progress);

        userImage = findViewById(R.id.imageView);
        adminImage = findViewById(R.id.imageView2);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        mAuth = FirebaseAuth.getInstance();
        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AdminActivity.class));
                /*adminLinear.setVisibility(View.VISIBLE);
                adminImage.setVisibility(View.VISIBLE);
                loginLinear.setVisibility(View.GONE);
                registerLinear.setVisibility(View.GONE);
                userImage.setVisibility(View.GONE);*/
            }
        });
        user_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminLinear.setVisibility(View.GONE);
                adminImage.setVisibility(View.GONE);
                loginLinear.setVisibility(View.VISIBLE);
                registerLinear.setVisibility(View.GONE);
                userImage.setVisibility(View.VISIBLE);
            }
        });
        adminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adminname = adminUname.getText().toString().trim();
                String adminpwd = adminPwd.getText().toString().trim();
                String uname = "admin";
                String pwd = "admin";
                if (TextUtils.isEmpty(adminname)){
                    Toast.makeText(MainActivity.this, "Enter Username...", Toast.LENGTH_SHORT).show();
                }if (TextUtils.isEmpty(adminpwd)){
                    Toast.makeText(MainActivity.this, "Enter password...", Toast.LENGTH_SHORT).show();
                }if (adminname.equals(uname) && adminpwd.equals(pwd)){
                    Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "Incorrect Username and Password...", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dispChnage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerLinear.setVisibility(View.VISIBLE);
                loginLinear.setVisibility(View.GONE);
            }
        });
        dispChnageTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerLinear.setVisibility(View.GONE);
                loginLinear.setVisibility(View.VISIBLE);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String strName = name.getText().toString().trim();
                final String strPhone = number.getText().toString().trim();
                final String strEmail = email.getText().toString().trim();
                String strPassword = password.getText().toString().trim();
                String strPasswordConfirm = passwordConfirm.getText().toString().trim();
                final String strAddress = address.getText().toString().trim();
                if (TextUtils.isEmpty(strName)){
                    Toast.makeText(MainActivity.this,"Please enter your Name.",Toast.LENGTH_LONG).show();
                    return;
                }if (TextUtils.isEmpty(strPhone)){
                    Toast.makeText(MainActivity.this,"Please enter your Phone Number....",Toast.LENGTH_LONG).show();
                    return;
                }if (TextUtils.isEmpty(strEmail)){
                    Toast.makeText(MainActivity.this,"Please enter your Email....",Toast.LENGTH_LONG).show();
                    return;
                }if (TextUtils.isEmpty(strPassword)){
                    Toast.makeText(MainActivity.this,"Please enter your password....",Toast.LENGTH_LONG).show();
                    return;
                }if (TextUtils.isEmpty(strPasswordConfirm)){
                    Toast.makeText(MainActivity.this,"Please enter your confirm password....",Toast.LENGTH_LONG).show();
                    return;
                }if (TextUtils.isEmpty(strAddress)){
                    Toast.makeText(MainActivity.this,"Please enter your address....",Toast.LENGTH_LONG).show();
                    return;
                }if (strPassword.length()<6){
                    Toast.makeText(MainActivity.this,"Minimum six letters....",Toast.LENGTH_LONG).show();
                    return;
                }if (strPasswordConfirm.length()<6){
                    Toast.makeText(MainActivity.this,"Minimum six letters....",Toast.LENGTH_LONG).show();
                    return;
                }
                regProgressBar.setVisibility(View.VISIBLE);
                if (strPassword.equals(strPasswordConfirm)){
                    mAuth.createUserWithEmailAndPassword(strEmail, strPassword)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        UsersSingUp register = new UsersSingUp(
                                                strName,
                                                strPhone,
                                                strEmail,
                                                strAddress
                                        );
                                        FirebaseDatabase.getInstance().getReference("Users").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).setValue(register).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                registerLinear.setVisibility(View.GONE);
                                                loginLinear.setVisibility(View.VISIBLE);
                                                Toast.makeText(MainActivity.this,"Registration completed, kindly Login...", Toast.LENGTH_LONG).show();
                                                regProgressBar.setVisibility(View.GONE);
                                            }
                                        });
                                    } else {
                                        Toast.makeText(MainActivity.this, "Registration failed...", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginEmail = emailLogin.getText().toString().trim();
                String loginPassword = passwordLogin.getText().toString().trim();
                logProgressBar.setVisibility(View.VISIBLE);
                if (TextUtils.isEmpty(loginEmail)){
                    Toast.makeText(MainActivity.this, "Enter Email...", Toast.LENGTH_SHORT).show();
                }if(TextUtils.isEmpty(loginPassword)){
                    Toast.makeText(MainActivity.this, "Enter password...", Toast.LENGTH_SHORT).show();
                }
                mAuth.signInWithEmailAndPassword(loginEmail, loginPassword)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this,"Successfully logged in...",Toast.LENGTH_LONG).show();
                                    logProgressBar.setVisibility(View.GONE);
                                } else {
                                    Toast.makeText(MainActivity.this,"Enter correct Email and Password...",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }
}