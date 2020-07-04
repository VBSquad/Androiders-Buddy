package e.vatsalkesarwani.intentsndservices.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import e.vatsalkesarwani.intentsndservices.R;

public class SCI_CustomRadioButton extends AppCompatActivity {

    RadioGroup radioGroup;
    TextView textView;
    Button order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_radio_button);

        radioGroup=findViewById(R.id.radioGroup);
        order=findViewById(R.id.order);
        textView=findViewById(R.id.OrderState);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioCheck();
            }
        });
    }

    private void radioCheck() {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton button=findViewById(selectedId);
        if(selectedId==-1){
            textView.setText("Nothing selected");
        }
        else{
            textView.setText(button.getText());
        }
    }

}