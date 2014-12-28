package com.zhufeng.sms_mod;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class Sms_readActivity extends Activity {
	/* Called when the activity is first created. */
	private static final String LOG_TAG = "Sms Query";

	final String ADDRESS = "address"; // 收件人发件人号码
	final String DATE = "date";
	final String READ = "read";
	final String STATUS = "status";
	final String BODY = "body"; // 信息内容
	final String TYPE = "type"; // 1是接收到的，2是发出的
	final String PERSON = "person"; // 通讯录中对应的人名序号
	final String THREAD_ID = "thread_id";
	int MESSAGE_TYPE_INBOX = 1;
	int MESSAGE_TYPE_SENT = 2;

	// 入口是onCreate
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sms_read);

		TextView tv = new TextView(this);
		tv.setText("Hello, Android! \n\n");
		tv.append(getSmsByContent(7025) + "\n\n");
		// tv.append(getSmsByThreadID(1));

		// ScrollView 只能有一个child view
		ScrollView sv = new ScrollView(this);
		sv.addView(tv);
		setContentView(sv);

		// Toast.makeText(this, this.getString(R.string.toast_end),
		// Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	// 按thread_id查询短信
	@SuppressWarnings("deprecation")
	public String getSmsByThreadID(int thread_id) {
		StringBuilder str = new StringBuilder();
		try {
			Cursor cs = managedQuery(Uri.parse("content://sms"), new String[] {
					ADDRESS, DATE, TYPE, BODY }, "thread_id = " + thread_id,
					null, "date desc");
			str.append("按thread_id=" + thread_id + "查询短信结果如下：\n\n");
			str.append("共有符合要求的短信数量：" + cs.getCount() + "\n\n");
			str.append(processResults(cs, true));
			str.append("getSmsByThreadID() has executed!");
			// System.out.println("getSmsByThreadID() has executed!");
		} catch (SQLiteException ex) {
			Log.d(LOG_TAG, ex.getMessage());
		}

		Toast.makeText(this, this.getString(R.string.toast_getSmsByThreadID),
				Toast.LENGTH_LONG).show();

		return str.toString();

	}

	// 按内容查询短信
	@SuppressWarnings("deprecation")
	public String getSmsByContent(int number) {
		StringBuilder str = new StringBuilder();
		try {

			Cursor myCursor = managedQuery(Uri.parse("content://sms"),
					new String[] { ADDRESS, BODY, DATE, TYPE },
					"address like ?", new String[] { "%" + number + "%" },
					"date desc");

			str.append("按号码关键字" + number + "查询短信结果如下：\n\n");
			str.append("共有符合要求的短信数量：" + myCursor.getCount() + "\n\n");
			str.append(processResults(myCursor, true));
			str.append("getSmsByContent() has executed!");
			// System.out.println("getSmsByContent has executed!");

		} catch (SQLiteException ex) {
			Log.d(LOG_TAG, ex.getMessage());
		}

		Toast.makeText(this, this.getString(R.string.toast_getSmsByContent),
				Toast.LENGTH_LONG).show();
		return str.toString();
	}

	/*
	 * 处理短信结果
	 * 
	 * @param cur
	 * 
	 * @param all 用来判断是读一条还是全部读。后来没有用all，可以无视
	 */
	private StringBuilder processResults(Cursor cur, boolean all) {
		StringBuilder str = new StringBuilder();
		if (cur.moveToFirst()) {

			// String name;
			String phoneNumber;
			String sms;
			String date;
			String type;

			// int nameColumn = cur.getColumnIndex(PERSON);
			int addressColumn = cur.getColumnIndex(ADDRESS);
			int bodyColumn = cur.getColumnIndex(BODY);
			int dateColumn = cur.getColumnIndex(DATE);
			int typeColumn = cur.getColumnIndex(TYPE);

			do {
				// Get the field values
				// name = cur.getString(nameColumn);
				phoneNumber = cur.getString(addressColumn);
				sms = cur.getString(bodyColumn);
				date = DateFormatConv.convertDateFormat(cur
						.getString(dateColumn));
				type = cur.getString(typeColumn);
				if (type.equals("1"))
					type = "接收";
				if (type.equals("2"))
					type = "发送";

				str.append("{");
				// str.append(name + ",");
				str.append(phoneNumber + " , ");
				str.append(date + " , ");
				str.append(type + " , ");
				str.append(sms);
				str.append("}");
				str.append("\n\n");

				if (null == sms)
					sms = "";

			} while (cur.moveToNext());
		} else {
			str.append("no result!");
			System.out.println("no result!");
		}

		// Toast.makeText(this, this.getString(R.string.toast_querySMS),
		// Toast.LENGTH_SHORT).show();

		return str;
	}// processResults

}
