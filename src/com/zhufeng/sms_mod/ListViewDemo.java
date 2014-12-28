package com.zhufeng.sms_mod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ListViewDemo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view_demo);

		// 获取ListView对象
		ListView mListView = (ListView) findViewById(R.id.listview);
		// 下面是数据映射关系,mFrom和mTo按顺序一一对应
		String[] mFrom = new String[] { "img", "title1", "title2", "time" };
		int[] mTo = new int[] { R.id.img, R.id.title1, R.id.title2, R.id.time };
		// 获取数据,这里随便加了10条数据,实际开发中可能需要从数据库或网络读取
		List<Map<String, Object>> mList = new ArrayList<Map<String, Object>>();
		Map<String, Object> mMap = null;
		for (int i = 0; i < 10; i++) {
			mMap = new HashMap<String, Object>();
			mMap.put("img", R.drawable.ic_launcher);
			mMap.put("title1", "标题");
			mMap.put("title2", "副标题");
			mMap.put("time", "2011-08-15 09:00");
			mMap.put("checked", true);
			mList.add(mMap);
		}
		// 创建适配器
		SimpleAdapter mAdapter = new SimpleAdapter(this, mList,
				R.layout.activity_list_view_demo, mFrom, mTo) {
			@Override
			public View getView(final int position, View convertView,
					ViewGroup parent) {
				View view = super.getView(position, convertView, parent);
				@SuppressWarnings("unchecked")
				final HashMap<String, Object> map = (HashMap<String, Object>) this
						.getItem(position);
				// 获取相应View中的Checkbox对象
				CheckBox checkBox = (CheckBox) view.findViewById(R.id.checked);
				checkBox.setChecked((Boolean) map.get("checked"));
				// 添加单击事件,在map中记录状态
				checkBox.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						map.put("checked", ((CheckBox) view).isChecked());
					}
				});
				return view;
			}
		};
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				@SuppressWarnings("unchecked")
				// 获取被点击的item所对应的数据
				HashMap<String, Object> map = (HashMap<String, Object>) parent
						.getItemAtPosition(position);
				// 下面是你的其他事务逻辑
			}
		});
		System.out.println("test on Activity_List_View_Demo");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_view_demo, menu);
		return true;
	}

}
