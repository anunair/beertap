package com.nfcApp.beertap;


import java.util.ArrayList;
import java.util.StringTokenizer;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

class MenuItemEntry {
	double price;
	String name;
	
	 public String getName() {
		return name;
	}
	 
	 public double getPrice() {
		return price;
	}
	 
	 public MenuItemEntry(String entry) {
		// TODO Auto-generated constructor stub
		 
		 StringTokenizer tok = new StringTokenizer(entry, "\t");
		 this.name = tok.nextToken();
		 this.price = Double.parseDouble(tok.nextToken());
	}
	 
	 
}
public class BeerTapMenu extends Activity {
	
	ArrayList<String> menuItems =new ArrayList<String>();
	ArrayAdapter<String> adapter;
	ArrayList<MenuItemEntry> selectedMenuItems = new ArrayList<MenuItemEntry>();
	double totalBill = 0.0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        EditText restName = (EditText) findViewById(R.id.RestaurantName);
        String menuInfo= getIntent().getStringExtra("menu");
        Toast.makeText(getApplicationContext(), menuInfo, Toast.LENGTH_LONG).show();
        parseMenuItems(menuInfo);
        restName.setKeyListener(null); // make EditText non-editable.
        //@TODO: Instead of EditText for restaurant name, use TextView 
    }

    private void parseMenuItems(String menuInfo) {
    	StringTokenizer tok = new StringTokenizer(menuInfo, "|");
    	while(tok.hasMoreTokens()) {
    		String item = tok.nextToken();
    		item = item.replace(',', '\t');
    		menuItems.add(item);
    	}
    	
    	adapter=new ArrayAdapter<String>(this,
    			android.R.layout.simple_list_item_multiple_choice,menuItems);
    	ListView menuList = (ListView)findViewById(R.id.listView1);
    	menuList.setAdapter(adapter);
    	menuList.setItemsCanFocus(false);
    	menuList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    	
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }
    
    public void onClickPlaceOrder(View view)
    {
		Intent intent_summary = new Intent(this, Summary.class);
		ListView menuList = (ListView) findViewById(R.id.listView1);
		SparseBooleanArray itemList = menuList.getCheckedItemPositions();
		
		boolean someItemSelected = false;
		totalBill = 0.0;
		String print = "";
		for (int i = 0 ; i < itemList.size(); i++) {
			if(itemList.get(i)) {
				someItemSelected = true;
				MenuItemEntry selectedMenuItem = new MenuItemEntry(menuItems.get(i));
				totalBill += selectedMenuItem.getPrice();
				selectedMenuItems.add(new MenuItemEntry(menuItems.get(i)));
				print += menuItems.get(i) +"|";
				
			}
		}
		if(!someItemSelected) {
			Toast.makeText(getApplicationContext(), 
					"Please select an Item", Toast.LENGTH_LONG).show();
			return;
		}
		
		Toast.makeText(getApplicationContext(), print, Toast.LENGTH_LONG).show();
		/*
		 *  Put Current order details in the "putExtra" given below
		 */
		//intent_DisplayToDo.putExtra("CurrentUserObject", user);
		
		// Call next screen to display User ToDO list
		intent_summary.putExtra("selected", print);
		intent_summary.putExtra("total", totalBill);
		startActivity(intent_summary);
    	
    }
}
