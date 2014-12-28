package com.zhufeng.sms_mod;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

	private Button btnReadSMS, btnInsertSMS, btnListView, btnVG, btnContact;

	@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);

			btnReadSMS = (Button) findViewById(R.id.btnReadSMS);
			btnInsertSMS = (Button) findViewById(R.id.btnInsertSMS);
			btnListView = (Button) findViewById(R.id.btnListView);
			btnVG = (Button) findViewById(R.id.btnVG);
			btnContact = (Button) findViewById(R.id.btnContact);

			btnReadSMS.setOnClickListener(this);
			btnInsertSMS.setOnClickListener(this);
			btnListView.setOnClickListener(this);
			btnVG.setOnClickListener(this);
			btnContact.setOnClickListener(this);
		}

	@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}

	public void onClick(View v) {
		int id = v.getId();
		switch(id) {
			case R.id.btnReadSMS:
				Intent intent_read = new Intent(MainActivity.this, 
						Sms_readActivity.class);
				MainActivity.this.startActivity(intent_read);
				break;
			case R.id.btnInsertSMS:
				Intent intent_inset = new Intent(MainActivity.this,
						Sms_insertActivity.class);
				MainActivity.this.startActivity(intent_inset);
				break;
			case R.id.btnListView:
				Intent intetn_listview = new Intent(MainActivity.this,
						ListViewDemo.class);
				break;
			case R.id.btnVG:
				Intent intent_vg = new Intent(MainActivity.this,
						ViewGroupActivity.class);
				MainActivity.this.startActivity(intent_vg);
				break;
			case R.id.btnContact:
				Intent intent_contact = new Intent(MainActivity.this,
						ContactActivity.class);
				MainActivity.this.startActivity(intent_contact);
				break;
		}
	}


}
