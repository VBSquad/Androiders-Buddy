package e.vatsalkesarwani.intentsndservices.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import e.vatsalkesarwani.intentsndservices.R;

public class ResultActivity extends AppCompatActivity {

    EditText message;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        message = findViewById(R.id.message1);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("MESSAGE",message.getText().toString());
                setResult(2,intent);
                finish();
            }
        });
    }
}