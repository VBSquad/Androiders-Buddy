package e.vatsalkesarwani.intentsndservices.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import e.vatsalkesarwani.intentsndservices.R;

public class SecondActivity extends AppCompatActivity {

    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        message = findViewById(R.id.mess);

        Intent intent = getIntent();

        message.setText(intent.getStringExtra("message"));
    }
}