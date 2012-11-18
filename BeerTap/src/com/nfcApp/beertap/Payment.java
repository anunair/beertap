package com.nfcApp.beertap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class Payment extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_payment, menu);
        return true;
    }
    
    public void onCLickConfirm(View view)
    {
    	
    	
    	
    }
}
