package e.vatsalkesarwani.intentsndservices.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import e.vatsalkesarwani.intentsndservices.R;

public class SCI_DynamicRadioButtom extends AppCompatActivity {

    RelativeLayout rel;
    RadioGroup rg;
    RadioButton rbyes,rbno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_c_i__dynamic_radio_buttom);

        rg=new RadioGroup(this);
        rbno=new RadioButton(this);
        rbyes=new RadioButton(this);

        rel=findViewById(R.id.rell);
        rbyes.setText("Yes");
        rbno.setText("No");
        rg.addView(rbyes);
        rg.addView(rbno);
        rg.setOrientation(LinearLayout.HORIZONTAL);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);


        rg.setLayoutParams(params);
        rel.addView(rg);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                Toast.makeText(getApplicationContext(),radioButton.getText(),Toast.LENGTH_LONG).show();
            }
        });
    }
}