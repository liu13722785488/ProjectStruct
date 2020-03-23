package com.happyjob.wagesteward.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;


import com.happyjob.wagesteward.R;
import com.happyjob.wagesteward.base.AppConfig;
import com.happyjob.wagesteward.utils.MyToastUtil;
import com.happyjob.wagesteward.utils.SafePreference;
import com.happyjob.wagesteward.utils.WebUrlUtil;

import java.util.LinkedHashMap;

public class YaoQingMaDialog {
	// 对话框对象
	private Dialog wordDialog;
	private Context context;
	private View view;
	private TextView confirm;
	private EditText et_tuijianma;//推荐码
	private String tuijianma="";
	private Handler handler;
	private String id="";
	/**
	 * @param context
	 * (in) 上下文
	 */
	public YaoQingMaDialog(Context context, Handler handler, String id) {
		this.context = context;
		this.id=id;
		this.handler=handler;
	}
	/**
	 * 弹出自动关闭的模态对话框对象
	 */
	public void init() {
		// 动态加载布局,将布局文件实例化成一个views
		view = LayoutInflater.from(context).inflate(R.layout.dialog_yaoqingma,
				null);
		if (wordDialog != null && wordDialog.isShowing()) {
			wordDialog.dismiss();
		}
		wordDialog=new Dialog(context,R.style.dialog_normal);

		// 参数true指出此对话框类似于非模态对话框
		wordDialog.setCancelable(true);
		wordDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置对话框对象所使用的布局


		confirm=(TextView)view.findViewById(R.id.confirm);
		et_tuijianma=(EditText)view.findViewById(R.id.et_tuijianma);
		confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				//调用接口验证邀请码是否正确
				tuijianma=et_tuijianma.getText().toString();
				if(!TextUtils.isEmpty(tuijianma)&&tuijianma.length()!=4){
					//判断是不是四位
						MyToastUtil.toastMsg(context, "推荐码为4位，请确认位数是否正确。");
				}else{
					//调用接口。
					LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

					params.put("uid", SafePreference.getUid(context));
					params.put("id", id);
					params.put("recommend_code", tuijianma);
//					new HttpUtil<SucGetOrderNumber>().postAccessServer(context, WebUrlUtil
//							.urlString(AppConfig.PERSON_PHP, AppConfig.exchange_vip, params),
//							handler, SucGetOrderNumber.class, AppConfig.GET_RESULT_NO_DATE, 1);
					shutAutoDialog();
				}
			}
		});
		try {
			wordDialog.show();
			setProperty();
			wordDialog.getWindow().setContentView(view);

		} catch (Exception e) {
		}
	}
	/**
	 * 此方法用于关闭自动对话框,不论是人为的关闭还是自动关闭都请调用此方法
	 */
	private void shutAutoDialog() {
		if (wordDialog != null) {
			wordDialog.dismiss();
		}
	}
	private void setProperty() {
		WindowManager m =((Activity)context).getWindowManager();
		Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
		// 以下代码设置对话框 的布局参数
		WindowManager.LayoutParams params = wordDialog.getWindow()
				.getAttributes();
		params.gravity = Gravity.CENTER;
		// 设置dialog的宽和高
		params.width = (int) (d.getWidth() * 0.9); // 宽度设置为屏幕的0.8
		params.height =params.WRAP_CONTENT; // 宽度设置为屏幕的0.8
		// 设置对话框的布局参数为居中
		wordDialog.getWindow().setAttributes(params);
	}

}
