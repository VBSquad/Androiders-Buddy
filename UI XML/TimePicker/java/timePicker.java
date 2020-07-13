package e.vatsalkesarwani.intentsndservices.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

import e.vatsalkesarwani.intentsndservices.R;

public class TimePicker extends AppCompatActivity {

    private android.widget.TimePicker timePicker;
    private Calendar calendar;
    private String format = "",time="";
    private Button show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);

        timePicker = findViewById(R.id.timePicker);
        calendar = Calendar.getInstance();
        show=findViewById(R.id.show);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        showTime(hour, min);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime(timePicker);
            }
        });
    }

    public void setTime(View view) {
        int hour = timePicker.getCurrentHour();
        int min = timePicker.getCurrentMinute();
        showTime(hour, min);
    }

    public void showTime(int hour, int min) {
        if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }
        String minn=min+"";
        if(min<10){
            minn="0"+min;
        }

        time=hour+" : "+ minn +" "+format;
        Toast.makeText(this, ""+time, Toast.LENGTH_SHORT).show();
    }
}