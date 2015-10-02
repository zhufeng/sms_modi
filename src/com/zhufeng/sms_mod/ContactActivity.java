package com.zhufeng.sms_mod;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.PhoneLookup;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ContactActivity extends Activity {

	private ListView listView;
	private ListView listView2;
	private Button button;
	/*
	 * 1.静态常量的定义 1.1 各个参数用于选择的目标，如果在数组中没有某个参数，则游标不会定义（即找到那个参数） 1.2
	 * 从定义可以看出,联系人的所有信息不是定义在一张表之中 1.3 为了操作的方便和可行，分开定义查找的目标
	 */
	public static final String[] COLUMNS = { Contacts._ID,
			Contacts.DISPLAY_NAME };
	private static final String[] COLUMNS2 = { Contacts.DISPLAY_NAME,
			Contacts._ID };
	private static final String[] COLUMNS3 = { Phone.NUMBER };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);

		// TextView tv = new TextView(this);
		// tv.setText(getContacts());

		listView = (ListView) findViewById(R.id.listview);
		listView2 = (ListView) findViewById(R.id.listview2);
		button = (Button) findViewById(R.id.button);
		button.setText("点击获取联系人姓名和手机号码");
		button.setOnClickListener(new MyButton());
		@SuppressWarnings("deprecation")
		Cursor cursor = managedQuery(Contacts.CONTENT_URI, COLUMNS, null, null,
				null);
		// 获取ID所对应的索引值---列索引
		int idIndex = cursor.getColumnIndex(COLUMNS[0]);
		// 获取NAME所对应的索引值---列索引
		int displayNameIndex = cursor.getColumnIndex(COLUMNS[1]);
		List<String> items = new ArrayList<String>();
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			int id = cursor.getInt(idIndex);
			String name = cursor.getString(displayNameIndex);
			items.add("id=" + id + "\t 姓名= " + name);
		}
		/* ArrayAdapter<String> adapter = new ArrayAdapter<String>(
		 *         ContactActivity.this, R.layout.list_item, items);
		 * listView.setAdapter(adapter); */

		Toast.makeText(this, this.getString(R.string.toast_end),
				Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact, menu);
		return true;
	}

	public class MyButton implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch (arg0.getId()) {
			case R.id.button:
				getNameAndPhone();
				break;

			default:
				break;
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void getNameAndPhone() {
		Cursor cursor = managedQuery(Contacts.CONTENT_URI, COLUMNS2, null,
				null, null);
		int displayNameIndex = cursor.getColumnIndex(COLUMNS2[0]);
		List<String> items = new ArrayList<String>();
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			String name = cursor.getString(displayNameIndex);
			int id = cursor.getInt(cursor.getColumnIndex(COLUMNS2[1]));
			Cursor phone = managedQuery(Phone.CONTENT_URI, null,
					Phone.CONTACT_ID + " = " + id, null, null);
			while (phone.moveToNext()) {
				String phoneNumber = phone.getString(phone
						.getColumnIndex(COLUMNS3[0]));
				items.add("姓名：" + name + "\t 手机：" + phoneNumber);
			}
		}
		/* ArrayAdapter<String> adapter = new ArrayAdapter<String>(
		 *         ContactActivity.this, R.layout.list_item, items);
		 * listView2.setAdapter(adapter); */
	}

	// 读取联系人
	public String getContacts() {

		ContentResolver cr = getContentResolver();
		Uri URI = ContactsContract.Contacts.CONTENT_URI;
		String[] columns = new String[] { ContactsContract.Contacts._ID,
				PhoneLookup.DISPLAY_NAME };

		// 查询联系人ID和联系人名称两列
		Cursor cursor = cr.query(URI, columns, PhoneLookup.HAS_PHONE_NUMBER
				+ "=1", null, null);

		// 限定只返回有号码的联系人
		while (cursor.moveToNext()) {
			String phoneNum = "";
			Cursor cursor2 = cr
					.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
							new String[] { ContactsContract.CommonDataKinds.Phone.NUMBER },
							ContactsContract.CommonDataKinds.Phone.CONTACT_ID
									+ "=" + cursor.getLong(0), null, null);

			// 因为号码与联系人不存在一个表中,一个联系人可能存在多个号码,
			// 所以根据联系人ID查找号码,存在phoneNum中
			while (cursor2.moveToNext()) {
				phoneNum += cursor2.getString(0) + " ";
				// 循环把该联系的所属的号码加进phoneNum
			}
			cursor2.close();
			System.out.println(cursor.getLong(0) + ":" + cursor.getString(1)
					+ phoneNum);
			// 循环输出ID,名称,号码
		}
		cursor.close();
		return "Query Contacts Successfully!";
	}

}
