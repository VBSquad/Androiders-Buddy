package com.test.loginsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.net.URI;

public class registerActivity extends AppCompatActivity {


    private EditText UserName,UserPassword, UserEmail;
    private Button RegBtn;
    private TextView UserLogin;
    private FirebaseAuth firebaseAuth;
    private ImageView ImageProfile;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    private static int PICK_IMAGE = 123;
    Uri imagePAth;

    String name, email, pass;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if ((requestCode == PICK_IMAGE) && (resultCode == RESULT_OK) && (data.getData()!=null)){
            imagePAth = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePAth);
                ImageProfile.setImageBitmap(bitmap);
            } catch (IOException e){
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        UIdeclaration();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        storageReference = firebaseStorage.getReference();

        ImageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select an image"), PICK_IMAGE);
            }
        });



        RegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()) {
                    String user_email = UserEmail.getText().toString().trim();
                    String user_pass= UserPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()) {
                                //Toast.makeText(registerActivity.this, "Registration Done", Toast.LENGTH_SHORT).show();
                                //startActivity(new Intent(registerActivity.this, MainActivity.class));
                                sendemailVerification();
                            }else {
                                Toast.makeText(registerActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }
            }
        });

        UserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(registerActivity.this, MainActivity.class));
            }
        });
    }
    private void UIdeclaration()
    {
        UserName = (EditText)findViewById(R.id.etUserName);
        UserPassword = (EditText)findViewById(R.id.etPass);
        UserEmail = (EditText)findViewById(R.id.etEmail);
        RegBtn = (Button)findViewById(R.id.Regbtn);
        UserLogin = (TextView)findViewById(R.id.tvLogin);
        ImageProfile = (ImageView)findViewById(R.id.iProfile);
    }
    private  Boolean validate(){
        Boolean result = false ;

        name = UserName.getText().toString();
        pass = UserPassword.getText().toString();
         email = UserEmail.getText().toString();

        if(name.isEmpty() || pass.isEmpty() || email.isEmpty() || imagePAth == null) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
        }else {
            result = true;
        }
        return result;
    }

    private void sendemailVerification(){
        final FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        sendUserData();
                    Toast.makeText(registerActivity.this, "Successfully Registered and email verification sent!", Toast.LENGTH_SHORT).show();
                    firebaseAuth.signOut();
                    finish();
                    startActivity(new Intent( registerActivity.this, MainActivity.class));
                    }

                    else {
                        Toast.makeText(registerActivity.this, "Could not send Verification email...please try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref= firebaseDatabase.getReference(firebaseAuth.getUid());
        StorageReference imgref = storageReference.child(firebaseAuth.getUid()).child("Images").child("Profile Images");
        UploadTask uploadTask = imgref.putFile(imagePAth);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(registerActivity.this, "Could not upload image..please try again", Toast.LENGTH_SHORT).show();


            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(registerActivity.this, "Image uploaded", Toast.LENGTH_SHORT).show();

            }
        });
                UserProfile userProfile = new UserProfile(name, email);
        ref.setValue(userProfile);

    }
}
