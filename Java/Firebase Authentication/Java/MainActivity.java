package com.test.loginsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText password;
    private Button login;
    private TextView info;
    private TextView REG;
    private int c=5;
    private FirebaseAuth firebaseAuth ;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText)findViewById(R.id.etname);
        password = (EditText)findViewById(R.id.etP);
        info = (TextView)findViewById((R.id.tvn));
        login = (Button)findViewById(R.id.LoginBtn);
        REG = (TextView)findViewById(R.id.tvReg);


        REG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, registerActivity.class));
            }
        });

        info.setText("No. of attempts remaining : 5");

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            finish();
            startActivity(new Intent(MainActivity.this, secondactivity.class));
        }

        login.setOnClickListener(new View.OnClickListener()
                                 {
                                     @Override
                                     public void onClick(View view){
                                         validate(name.getText().toString(), password.getText().toString());
                                     }
                                 }
        );

    }
    private void validate(String userName, String userPassword) {
        progressDialog.setMessage("It is checking please wait!");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
           if (task.isSuccessful()){
               progressDialog.dismiss();
              // startActivity(new Intent(MainActivity.this, secondactivity.class));
               // Toast.makeText(MainActivity.this,"Login Successful!!",Toast.LENGTH_SHORT).show();
               checkEmailVerification();
           }
           else {
               progressDialog.dismiss();
               Toast.makeText(MainActivity.this,"Login Failed!",Toast.LENGTH_SHORT).show();
               c--;
               info.setText("No. of attempts remaining :"+c);
               if(c==0){
                   login.setEnabled(false);
               }
           }
            }
        });
    }
    private void checkEmailVerification(){
        FirebaseUser firebaseUser= firebaseAuth.getInstance().getCurrentUser();
        Boolean emailFlag= firebaseUser.isEmailVerified();

        if (emailFlag){
            finish();
            startActivity(new Intent(MainActivity.this, secondactivity.class));
        }else {
            Toast.makeText(this, "Verify your email first please", Toast.LENGTH_LONG).show();
            firebaseAuth.signOut();
        }
    }
}
