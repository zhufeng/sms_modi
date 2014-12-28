package com.zhufeng.sms_mod;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class Sms_insertActivity extends Activity {

	final String ADDRESS = "address"; // 收件人发件人号码
	final String DATE = "date";
	final String READ = "read";
	final String STATUS = "status";
	final String BODY = "body"; // 信息内容
	final String TYPE = "type"; // 1是接收到的，2是发出的
	final String PERSON = "person"; // 通讯录中对应的人名序号
	final String THREAD_ID = "thread_id";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sms_insert);

		TextView tv = new TextView(this);
		tv.setText(insertSMS());

		Toast.makeText(this, this.getString(R.string.toast_end),
				Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sms_insert, menu);
		return true;
	}

	public String insertSMS() {
		ContentResolver cr = getContentResolver();
		ContentValues cv = new ContentValues();

		Uri uriInbox = Uri.parse("content://sms/inbox");

		cv.put(TYPE, 1);
		cv.put(ADDRESS, "10086");
		cv.put(BODY, "Insert SMS Test... MSG... zfzg1923......!");

		cr.insert(uriInbox, cv);
		// System.out.println("Insert SMS Successfully!");
		Toast.makeText(this, this.getString(R.string.toast_insert),
				Toast.LENGTH_LONG).show();
		return "Insert SMS Successfully!";

	}
}
