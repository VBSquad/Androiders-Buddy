package example.javatpoint.com.androidnotification;  
  
import android.app.NotificationManager;  
import android.app.PendingIntent;  
import android.content.Context;  
import android.content.Intent;  
import android.support.v4.app.NotificationCompat;  
import android.support.v7.app.AppCompatActivity;  
import android.os.Bundle;  
import android.view.View;  
import android.widget.Button;  
  
public class MainActivity extends AppCompatActivity {  
    Button button;  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);  
        button = findViewById(R.id.button);  
        button.setOnClickListener(new View.OnClickListener() {  
            @Override  
            public void onClick(View v) {  
                addNotification();  
            }  
        });  
    }  
  
    private void addNotification() {  
        NotificationCompat.Builder builder =  
                new NotificationCompat.Builder(this)  
                        .setSmallIcon(R.drawable.messageicon) //set icon for notification  
                        .setContentTitle("Notifications Example") //set title of notification  
                        .setContentText("This is a notification message")//this is notification message  
                        .setAutoCancel(true) // makes auto cancel of notification  
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT); //set priority of notification  
  
  
        Intent notificationIntent = new Intent(this, NotificationView.class);  
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
        //notification message will get at NotificationView  
        notificationIntent.putExtra("message", "This is a notification message");  
  
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,  
                PendingIntent.FLAG_UPDATE_CURRENT);  
        builder.setContentIntent(pendingIntent);  
  
        // Add as notification  
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);  
        manager.notify(0, builder.build());  
    }  
}  