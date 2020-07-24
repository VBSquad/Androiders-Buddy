package e.vatsalkesarwani.intentsndservices.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;

import e.vatsalkesarwani.intentsndservices.R;

public class ViewStubActivity extends AppCompatActivity {

    ViewStub viewStub;
    Button show,hide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stub);

        show=(Button)findViewById(R.id.show);
        hide=(Button)findViewById(R.id.hide);
        viewStub=(ViewStub)findViewById(R.id.viewStub);
        viewStub.inflate();

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewStub.setVisibility(View.VISIBLE);
            }
        });
        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewStub.setVisibility(View.GONE);
            }
        });
    }
}