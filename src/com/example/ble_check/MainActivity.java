package com.example.ble_check;

import java.util.Locale;

import android.os.Bundle;
import android.os.Debug;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity implements  OnInitListener{
	
	boolean haveble = false;
	public int gotit = 0;
	TextToSpeech talker;
	  
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		talker = new TextToSpeech(this, this);
		
	    if (!getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
	      haveble = false;
	    }
	    else
	    {
	       haveble = true;
	    }
	    
	    TextView textView1 = (TextView) findViewById(R.id.textView1);
	    if(haveble == true)
	    {
	    	gotit = 1;
	    	textView1.setText("Wohoo! All the Bluetooth 4.0's!");
	    	//tts.speak("Woot all the Bluetooths!", TextToSpeech.QUEUE_FLUSH, null);
	    	say("You've got Bluetooth 4.0!");
	    }
	    else {
	    	gotit = 0;
	    	textView1.setText("Oh oh sphagettio! You've got no Bluetooth 4.0's! Yikes!");
	    	//tts.speak("No bluetooth's for you. Sad face", TextToSpeech.QUEUE_FLUSH, null);
	    	say("Shoot! Your phone does not support Bluetooth 4.0.");

	    }
	    	
	 } 
	 
	 public void say(String text2say){
		 talker.speak(text2say,  TextToSpeech.QUEUE_FLUSH, null);
		 //System.out.println("spoke");
	 }
	 
	 public void onInit(int code) {
		 if(gotit == 1)
		 {
		   say("You've got Bluetooth 4.0!");     
		 }
		 else
		 {
			 say("Darn. Your phone does not support Bluetooth 4.0.");
		 }
	}
	 
	 protected void onDestroy() {
		 if(talker != null) {
			 talker.stop();
			 talker.shutdown();
		 }
		 super.onDestroy();
	      
	}
	 
	 
	 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	

}
