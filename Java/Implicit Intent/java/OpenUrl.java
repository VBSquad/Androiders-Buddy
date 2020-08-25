package e.vatsalkesarwani.intentsndservices.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import e.vatsalkesarwani.intentsndservices.R;

public class OpenUrl extends AppCompatActivity {

    EditText url;
    Button openUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_url);

        openUrl = findViewById(R.id.bt_url);
        url = findViewById(R.id.url);

        openUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Url = url.getText().toString();
                Uri webpage=Uri.parse(Url);
                Intent intent=new Intent(Intent.ACTION_VIEW,webpage);
                if (intent.resolveActivity(getPackageManager())!=null)
                {
                    startActivity(intent);
                }
            }
        });
    }
}