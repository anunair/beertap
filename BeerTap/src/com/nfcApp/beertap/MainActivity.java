package com.nfcApp.beertap;

import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcAdapter.OnNdefPushCompleteCallback;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.os.Parcelable;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements CreateNdefMessageCallback,
OnNdefPushCompleteCallback{

	NfcAdapter mNfcAdapter;
	TextView mInfoText;
	Button readMenu;
	private String menuInfo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInfoText = (TextView) findViewById(R.id.tapTextView);
        readMenu = (Button) findViewById(R.id.DummyRead);
        readMenu.setVisibility(View.INVISIBLE);
        // Check for available NFC Adapter
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mNfcAdapter == null) {
            mInfoText = (TextView) findViewById(R.id.tapTextView);
            mInfoText.setText("NFC is not available on this device.");
        } else {
            // Register callback to set NDEF message
            mNfcAdapter.setNdefPushMessageCallback(this, this);
            // Register callback to listen for message-sent success
            mNfcAdapter.setOnNdefPushCompleteCallback(this, this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void onClickDummyRead(View view)
    {
		Intent intent_gotoMenu = new Intent(this, BeerTapMenu.class);
		intent_gotoMenu.putExtra("menu", menuInfo);
		/*
		 *  Put Current order details in the "putExtra" given below
		 */
		//intent_DisplayToDo.putExtra("CurrentUserObject", user);
		
		// Call next screen to display User ToDO list
		startActivity(intent_gotoMenu);
    	
    }

	public void onNdefPushComplete(NfcEvent event) {
		// TODO Auto-generated method stub
		
	}

	public NdefMessage createNdefMessage(NfcEvent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
     * Parses the NDEF Message from the intent and prints to the TextView
     */
    void processIntent(Intent intent) {
        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(
                NfcAdapter.EXTRA_NDEF_MESSAGES);
        // only one message sent during the beam
        NdefMessage msg = (NdefMessage) rawMsgs[0];
        // record 0 contains the MIME type, record 1 is the AAR, if present
        menuInfo = new String(msg.getRecords()[0].getPayload());
        
        mInfoText.setText("Click Button to Load Menu");
        readMenu.setText("Load Menu");
        readMenu.setVisibility(View.VISIBLE);
        
        //Toast.makeText(getApplicationContext(), menuInfo, Toast.LENGTH_LONG).show();
    }
    
    public void onResume() {
        super.onResume();
        // Check to see that the Activity started due to an Android Beam
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            processIntent(getIntent());
        }
    }
    
    
}
