package com.nfcApp.beertap;

import java.util.ArrayList;
import java.util.StringTokenizer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Summary extends Activity {
	ArrayList<String> selectedItems =new ArrayList<String>();
	ArrayAdapter<String> adapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        String selItems= getIntent().getStringExtra("selected");
        Toast.makeText(getApplicationContext(), selItems, Toast.LENGTH_LONG).show();
        parseSelectedItems(selItems);
    }

    private void parseSelectedItems(String selItems) {
		// TODO Auto-generated method stub
    	
    	StringTokenizer tok = new StringTokenizer(selItems, "|");
    	while(tok.hasMoreTokens()) {
    		String item = tok.nextToken();
    		selectedItems.add(item);
    	}
    	String total = "total\t" + getIntent().getDoubleExtra("total", 0.0);
    	selectedItems.add(total);
    	adapter=new ArrayAdapter<String>(this,
    			android.R.layout.simple_list_item_1,selectedItems);
    	ListView menuList = (ListView)findViewById(R.id.listView1);
    	menuList.setAdapter(adapter);
    	menuList.setItemsCanFocus(false);
    	menuList.setChoiceMode(ListView.CHOICE_MODE_NONE);
		
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_summary, menu);
        return true;
    }
    
    public void onClickEdit(View view)
    {
		Intent intent_returnToMenu = new Intent(this, BeerTapMenu.class);
		
		/*
		 *  Put Current order details in the "putExtra" given below
		 */
		//intent_DisplayToDo.putExtra("CurrentUserObject", user);
		
		// Call next screen to display User ToDO list
		startActivity(intent_returnToMenu);
    	
    }
    public void onClickPay(View view)
    {
    	Intent intent_payment = new Intent(this, Payment.class);
    	startActivity(intent_payment);
    }
    
}
