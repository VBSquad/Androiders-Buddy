package e.vatsalkesarwani.intentsndservices.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import e.vatsalkesarwani.intentsndservices.R;

public class SCICheckBox extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom);

        final CheckBox checkBox=findViewById(R.id.checkBox);
        Button submit=findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    Toast.makeText(SCICheckBox.this, "Jai Hind ☺", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(SCICheckBox.this, "Sad for your response 😔", Toast.LENGTH_SHORT).show();
            }
        });
    }
}