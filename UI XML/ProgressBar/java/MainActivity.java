package com.iitms.progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ProgressBar pgsBar;
    TextView txtView;
    int i = 0;
    Handler hdlr = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pgsBar = findViewById(R.id.pBar);
        txtView = findViewById(R.id.tView);
        Button btn = findViewById(R.id.btnShow);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                i=pgsBar.getProgress();
                //Handler is simply used for posting a message to the thread to which it is attached (where its is created). It does not create a thread on its own.
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (i < 100){
                            i+=1;
                            // Update the progress bar and display the current value in text view
                            hdlr.post(new Runnable() {
                                @Override
                                public void run() {
                                    pgsBar.setProgress(i);
                                    txtView.setText(i+"/"+pgsBar.getMax());

                                }
                            });
                            try{
                                // Sleep for 100 milliseconds to show the progress slowly.
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();

            }
        });
    }
}