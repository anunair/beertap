package com.nfcApp.beertap;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class BeerTapMenu extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }
    
    public void onClickPlaceOrder(View view)
    {
		Intent intent_summary = new Intent(this, Summary.class);
		
		/*
		 *  Put Current order details in the "putExtra" given below
		 */
		//intent_DisplayToDo.putExtra("CurrentUserObject", user);
		
		// Call next screen to display User ToDO list
		startActivity(intent_summary);
    	
    }
}
