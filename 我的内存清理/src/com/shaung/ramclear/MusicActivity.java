package com.shaung.ramclear;

import com.shaung.ramclear.ui.Musics;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class MusicActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_music);
		Musics musics1 = (Musics) findViewById(R.id.musics1);
	}
}
