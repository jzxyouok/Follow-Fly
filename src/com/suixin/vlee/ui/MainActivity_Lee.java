package com.suixin.vlee.ui;

import com.suixin.vy.ui.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity_Lee extends Activity {
	private Button but_next;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_one);

		but_next = (Button) findViewById(R.id.but_next);

		but_next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity_Lee.this,
						ListViewLeeActivity.class);
				startActivity(intent);
			}
		});

	}
}
