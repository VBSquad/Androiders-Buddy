package e.vatsalkesarwani.intentsndservices.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import e.vatsalkesarwani.intentsndservices.R;

public class ExplicitIntent extends AppCompatActivity {

    EditText message;
    Button activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit_intent);

        message = findViewById(R.id.intentMessage);
        activity = findViewById(R.id.bt_activity);

        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                intent.putExtra("message",message.getText().toString());
                startActivity(intent);
            }
        });
    }
}