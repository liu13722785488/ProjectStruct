package com.happyjob.wagesteward.utils;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.happyjob.wagesteward.R;


public class IssLoadingDialog extends Dialog {
	private Context mActivity;
	private TextView loadingText;

	public IssLoadingDialog(Context context) {
		super(context, R.style.dialog_normal);
		mActivity = context;
		setContentView(R.layout.issloading);
		setProperty();
		setCancelable(false);
		loadingText = (TextView) findViewById(R.id.loading_text);
	}

	public void setLoadingMessage(String message){
		if(!TextUtils.isEmpty(message)){
			loadingText.setText(message);
		}
	}

	public void show(String message) {
		if(!TextUtils.isEmpty(message)){
			loadingText.setText(message);
		}
		try{
			super.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void close() {
		if(null == mActivity){
			return;
		}
		if (!((Activity)mActivity).isFinishing()) {
			((Activity)mActivity).runOnUiThread(new Runnable() {
				@Override
				public void run() {
					if (isShowing()) {
						dismiss();
					}
				}
			});
		}
	}

	private void setProperty() {
		Window window = getWindow();
		WindowManager.LayoutParams p = window.getAttributes();
		Display d = getWindow().getWindowManager().getDefaultDisplay();

		p.height = (int) (d.getHeight() * 1);
		p.width = (int) (d.getWidth() * 1);
		window.setAttributes(p);
	}

}
