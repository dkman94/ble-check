package com.example.ble_check;

import android.os.Bundle;
import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.widget.TextView;

public class MainActivity extends Activity implements  OnInitListener{
	
	//boolean variable that determines if the phone has Bluetooth LE
	boolean haveBLE = false;
	//variable int to determine spoken message
	public int gotBLE = 0;
	//create the TextToSpeech variable
	TextToSpeech talker;
	  
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//intialize the constructor of the TextToSpeech variable
		talker = new TextToSpeech(this, this);
		
		//Android phone does not have the system feature of Bluetooth LE, then haveBLE is false
	    	if (!getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
	    			haveBLE = false;
	    	}
	    //else the Phone does have Bluetooth LE, and haveBLE is true
	    	else {
	    			haveBLE = true;
	    	  }
	    
	    //create the TextView to print the result
	    TextView textView1 = (TextView) findViewById(R.id.textView1);
	    	if(haveBLE == true)  {
	    		//set gotBLE to true(or 1) and set text view to display success
	    			gotBLE = 1;
	    			textView1.setText("Wohoo! All the Bluetooth 4.0's!");
	    	  }
	    	else {
	    		//set gotBLE to true(or 1) and set text view to failure success
	    			gotBLE = 0;
	    			textView1.setText("Uh oh sphagettio! You've got no Bluetooth 4.0's! Yikes!");
	    	  }
	     } 
	 
	 	//say method inputs a string and speaks out the result
	 	//QUEUE_FLUSH only allows 1 result to be spoken as is required in this app
	 public void say(String text2say){
		 talker.speak(text2say,  TextToSpeech.QUEUE_FLUSH, null);
	 }
	 
	 	//onInit method allows result of Bluetooth 4.0 Report to be spoken
	 public void onInit(int code) {
		 	if(gotBLE == 1)  {
		 			say("You've got Bluetooth 4.0!");     
		  }
		    else {
		    		say("Darn. Your phone does not support Bluetooth 4.0.");
		  }
	}
	 
	 	//onDestroy() makes sure to end the spoken result if the app if ended by the system
	 protected void onDestroy() {
		 	if(talker != null) {
		 		talker.stop();
		 		talker.shutdown();
		 }
		 super.onDestroy();	      
	}
	

}
