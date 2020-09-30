package com.test.loginsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

public class secondactivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
private Button Logout;
private Button Profile;
private Button Share;
private Button Create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondactivity);

        firebaseAuth = FirebaseAuth.getInstance();

        Logout =(Button)findViewById(R.id.LogoutBtn);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(secondactivity.this, MainActivity.class));
            }
        });

        Profile = findViewById(R.id.ProfilrBtn);
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(secondactivity.this, ProfileActivity.class));

            }
        });

        Create = findViewById(R.id.Crt_btn);
        Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createlink();
            }
        });

        Share = findViewById(R.id.shr_btn);
        Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("TEXT");
                String shareBody= "https://www.youtube.com/watch?v=3LyMLAHtRZk";
                String shareSubject = "Subejct";

                    sharingIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                    sharingIntent.putExtra(Intent.EXTRA_SUBJECT,shareSubject);

                    startActivity(Intent.createChooser(sharingIntent, "Share by using -"));
            }
        });
    }
public void createlink()
{
    Log.e("main", "Create link");
    DynamicLink dynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink()
            .setLink(Uri.parse("https://www.youtube.com/"))
            .setDomainUriPrefix("loginsystem.page.link")
            // Open links with this app on Android
            .setAndroidParameters(new DynamicLink.AndroidParameters.Builder().build())
            // Open links with com.example.ios on iOS
            .setIosParameters(new DynamicLink.IosParameters.Builder("com.example.ios").build())
            .buildDynamicLink();

    Uri dynamicLinkUri = dynamicLink.getUri();
    Log.e("main" , "LOng relink"+ dynamicLink.getUri() );

    Task<ShortDynamicLink> shortLinkTask = FirebaseDynamicLinks.getInstance().createDynamicLink()
            .setLongLink(Uri.parse("https://loginsystem.page.link?apn=com.test.loginsystem&ibi=com.example.ios&link=https%3A%2F%2Fwww.youtube.com%2F"))
            .buildShortDynamicLink()
            .addOnCompleteListener(this, new OnCompleteListener<ShortDynamicLink>() {
                @Override
                public void onComplete(@NonNull Task<ShortDynamicLink> task) {
                    if (task.isSuccessful()) {
                        // Short link created
                        Uri shortLink = task.getResult().getShortLink();
                        Uri flowchartLink = task.getResult().getPreviewLink();
                        Log.e("main", "short link"+shortLink);

                        Intent  intent = new Intent();
                        intent.setAction(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_TEXT, shortLink.toString());
                        intent.setType("text/plain");
                        startActivity(intent);
                    } else {
                        // Error
                        // ...
                        Log.e("main", "err"+task.getException());
                    }
                }
            });
}

}
