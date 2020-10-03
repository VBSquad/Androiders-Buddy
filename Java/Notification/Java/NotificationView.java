package example.javatpoint.com.androidnotification;  
  
import android.support.v7.app.AppCompatActivity;  
import android.os.Bundle;  
import android.widget.TextView;  
import android.widget.Toast;  
  
public class NotificationView extends AppCompatActivity {  
    TextView textView;  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_notification_view);  
        textView = findViewById(R.id.textView);  
        //getting the notification message  
        String message=getIntent().getStringExtra("message");  
        textView.setText(message);  
    }  
}  