package e.vatsalkesarwani.intentsndservices.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.Toast;

import e.vatsalkesarwani.intentsndservices.R;

public class SCIToast extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState ) ;
        setContentView( R.layout.activity_s_c_i_toast ) ;

        //Toast 
        Toast.makeText( this , " Toast Implementation on Activity startUp " , Toast.LENGTH_LONG )
        	.show() ;
    }
}