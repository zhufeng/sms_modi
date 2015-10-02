package com.zhufeng.sms_mod;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ViewGroupImpl extends ViewGroup {

	public ViewGroupImpl(Context context, AttributeSet attrs) {
		super(context, attrs);
		Context mContext = context;
		myAddView();
	}

	public ViewGroupImpl(Context context) {
		super(context);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		View v = getChildAt(0);
		v.layout(l, t, r, b);
	}

	public void myAddView() {
		ImageView mIcon = new ImageView(getContext());
		mIcon.setImageResource(R.drawable.ic_launcher);
		addView(mIcon);
	}

}
