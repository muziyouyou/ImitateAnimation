package com.shaung.ramclear;

import com.shaung.ramclear.ui.MusicItem;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MusicItemActivity extends Activity {

	private MusicItem musicItem1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_music);
		musicItem1 = (MusicItem) findViewById(R.id.musicItem1);
	}
	public void reduce(View view){
		
		musicItem1.reduceHight(5);
	}

	public void add(View view){
		musicItem1.addHight(5);
	}
}
