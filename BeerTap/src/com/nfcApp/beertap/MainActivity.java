package com.nfcApp.beertap;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void onClickDummyRead(View view)
    {
		Intent intent_gotoMenu = new Intent(this, BeerTapMenu.class);
		
		/*
		 *  Put Current order details in the "putExtra" given below
		 */
		//intent_DisplayToDo.putExtra("CurrentUserObject", user);
		
		// Call next screen to display User ToDO list
		startActivity(intent_gotoMenu);
    	
    }
}
