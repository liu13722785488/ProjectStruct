package com.happyjob.wagesteward.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ant.liao.GifView;
import com.happyjob.wagesteward.MainActivity;
import com.happyjob.wagesteward.R;

import java.util.Timer;
import java.util.TimerTask;


/**  
 * Filename: MyToastUtil.java  <br>
 *
 * Description:  toast工具 <br>
 * 
 * @author: HLJ <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2015-5-19 <br>
 *
 * @Copyright: Copyright (c)2015 by HLJ <br>
 *  
 */

public class MyToastUtil {
	/**
	 * 唯一的toast
	 */
	private static Toast mToast = null;
	public static void toastMsg(Context context, String text){
		//		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
		if (mToast != null) {
			//mToast.cancel();
		} else {
			mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		}
		mToast.setText(text);
		mToast.show();
	}

	private static final long WaitTime = 1500;
	private static boolean exitFlag = false;

	/**
	 * @param exitFlag the exitFlag to set
	 */
	public synchronized static void setExitFlag(boolean exitFlag) {
		MyToastUtil.exitFlag = exitFlag;
	}
	public synchronized static boolean getExitFlag(){
		return exitFlag;
	}
	/**
	 * @Title: msgToastWithBg
	 * @Description: 只显示信息的toast
	 * @param context
	 * @param showinfo
	 *            void
	 * @Exception
	 * @since Ver 1.0
	 */
	public static void msgToastWithBg(Activity context, String showinfo) {
		if (context == null) {
			return;
		}

		final Dialog d = getDialogInstance(context);
		d.getWindow().setContentView(R.layout.msgtoast);
		TextView text = (TextView) d.findViewById(R.id.msgtoast_msg);
		text.setText(showinfo);
		// 比较重要
		d.setCancelable(true);
		if (!getExitFlag() && !context.isFinishing()) {
			d.show();
			final Timer t = new Timer();
			t.schedule(new TimerTask() {
				@Override
				public void run() {
					d.dismiss(); // when the task active then close the dialog
					t.cancel(); // also just top the timer thread, otherwise,
					// you
					// may receive a crash report
				}
			}, WaitTime); // after 1 second (or 2000 miliseconds), the task will
			// be
			// active.
		}
	}

	/**
	 * @Title: toastWithImageShowWithBg
	 * @Description: 显示带背景的toast,消息消失后执行设定的任务
	 * @param context
	 * @param imageid
	 * @param showinfo
	 * @param task
	 *            void
	 * @Exception
	 * @since Ver 1.0
	 */
	private static Dialog toastWithImageShowWithBg(Activity context, int imageid, String showinfo, TimerTask task,int textColor) {
		if (context == null) {
			return null;
		}

		final Dialog d = dialogWithImage(context, imageid, showinfo,textColor);
		final TimerTask tt = task;
		if (!getExitFlag() && !context.isFinishing()) {
			d.show();
			final Timer t = new Timer();
			t.schedule(new TimerTask() {
				@Override
				public void run() {
					d.dismiss(); // when the task active then close the dialog
					t.cancel(); // also just top the timer thread, otherwise,
					// you
					// may receive a crash report
					if (tt != null) {
						tt.run();
					}
				}
			}, WaitTime); // after 1 second (or 2000 miliseconds), the task will
			// be
			// active.
		}
		return d;
	}

	/**
	 * @Title: dialogWithImage
	 * @Description: 自定义dialog,显示信息和图片
	 * @param context
	 * @param imageid
	 * @param showinfo
	 * @return Dialog
	 * @Exception
	 * @since Ver 1.0
	 */
	private static Dialog dialogWithImage(Context context, int imageid, String showinfo, boolean showProgressbar,int textColor) {
		Dialog dialog = getDialogInstance(context);
		dialog.getWindow().setContentView(R.layout.mytoast);
		// R.layout.myversiondialog 布局文件也可参考
		ImageView image = (ImageView) dialog.findViewById(R.id.showimage);
		ProgressBar pb = (ProgressBar) dialog.findViewById(R.id.probar);
		if (showProgressbar == false) {
			image.setImageResource(imageid);
			image.setVisibility(View.VISIBLE);
			pb.setVisibility(View.GONE);
		} else {
			image.setVisibility(View.GONE);
			pb.setVisibility(View.VISIBLE);
		}
		TextView text = (TextView) dialog.findViewById(R.id.showinfo);
		text.setText(showinfo);
		if(textColor >0){			
			text.setTextColor(context.getResources().getColor(textColor));
		}
		// 比较重要
		dialog.setCancelable(true);
		return dialog;
	}

	/**
	 * @Title: toastWithImageShowWithBg
	 * @Description: 显示带背景的toast
	 * @param context
	 * @param imageid
	 * @param showinfo
	 *            void
	 * @Exception
	 * @since Ver 1.0
	 */
	public static Dialog toastWithImageShowWithBg(Activity context, int imageid, String showinfo,int textColor) {
		return toastWithImageShowWithBg(context, imageid, showinfo, null,textColor);
	}

	/**
	 * @Title: getDialogInstance
	 * @Description:返回一个dialog
	 * @param context
	 * @return Dialog
	 * @Exception
	 * @since Ver 1.0
	 */
	public static Dialog getDialogInstance(Context context) {
		Dialog d = new Dialog(context, R.style.MyDialog);
		d.getWindow().setWindowAnimations(R.style.dialogAnimationStyle);
		return d;
	}

	/**
	 * @Title: dialogWithImage
	 * @Description:显示图片
	 * @param context
	 * @param imageid
	 * @param showinfo
	 * @return Dialog
	 * @Exception
	 * @since Ver 1.0
	 */
	public static Dialog dialogWithImage(Context context, int imageid, String showinfo,int textColor) {
		return dialogWithImage(context, imageid, showinfo, false,textColor);
	}

	/**
	 * @Title: dialogWithImage
	 * @Description: 显示加载进度
	 * @param context
	 * @param showinfo
	 * @return Dialog
	 * @Exception
	 * @since Ver 1.0
	 */
	public static Dialog dialogWithImage(Context context, String showinfo,int textColor) {
		return dialogWithImage(context, 0, showinfo, true,textColor);
	}

	/**
	 * @Title: showNetworkErrToast
	 * @Description: 网络异常
	 * @param context
	 *            void
	 * @Exception
	 * @since Ver 1.0
	 */
	public static void showNetworkErrToast(Activity context) {
		toastWithImageShowWithBg(context, R.mipmap.icon_warning, context.getString(R.string.toast_network_error),R.color.user_font_networkerror);
	}

	/**
	 * @Title: showNoFoundErrToast
	 * @Description: 没有此用户
	 * @param context
	 *            void
	 * @Exception
	 * @since Ver 1.0
	 */
	public static void showNoFoundErrToast(Activity context) {
		toastWithImageShowWithBg(context, R.mipmap.icon_warning, context.getString(R.string.toast_no_found_error),R.color.user_font_networkerror);
	}


	/**
	 * @Title: loadingDialog
	 * @Description: 加载等待,自定义消息内容
	 * @param context
	 * @param msg
	 * @return Dialog
	 * @Exception
	 * @since Ver 1.0
	 */
	public static Dialog loadingDialog(Context context, String msg) {
		return dialogWithImage(context, msg,-1);
	}

	/**
	 * @Title: loadingDialog
	 * @Description: 加载等待
	 * @param context
	 * @return Dialog
	 * @Exception
	 * @since Ver 1.0
	 */
	public static Dialog loadingDialog(Context context) {
		return loadingDialog(context, context.getString(R.string.toast_loading));
	}

	/**
	 * @Title: submitWaitingDialog
	 * @Description: 提交等待
	 * @param context
	 * @return Dialog
	 * @Exception
	 * @since Ver 1.0
	 */
	public static Dialog submitWaitingDialog(Context context) {
		return loadingDialog(context, context.getString(R.string.toast_submiting));
	}
	/**
	 * @Title: submitWaitingDialog
	 * @Description: 提交等待
	 * @param context
	 * @return Dialog
	 * @Exception
	 * @since Ver 1.0
	 */
	public static Dialog submitWaitingDialog(Context context,String str) {
		return loadingDialog(context, str);
	}

	/**
	 * @Title: showErrMsgToast
	 * @Description: 显示错误信息
	 * @param context
	 * @param msg
	 *            void
	 * @Exception
	 * @since Ver 1.0
	 */
	public static Dialog showErrMsgToast(Activity context, String msg) {
		return toastWithImageShowWithBg(context, R.mipmap.icon_warning, msg,-1);
	}



	/**
	 * @Title: showErrMsgToast
	 * @Description: 显示错误信息
	 * @param context
	 * @param msg
	 * @param tt
	 *            void
	 * @Exception
	 * @since Ver 1.0
	 */
	public static void showErrMsgToast(Activity context, String msg, TimerTask tt) {
		toastWithImageShowWithBg(context, R.mipmap.icon_warning, msg, tt,-1);
	}

	/**
	 * @Title: submitSuccessful
	 * @Description: 提交成功
	 * @param context
	 *            void
	 * @Exception
	 * @since Ver 1.0
	 */
	public static void submitSuccessful(Activity context) {
		submitSuccessful(context, null);
	}

	/**
	 * @Title: submitSuccessful
	 * @Description: 提交成功,在提示消失后自动执行该任务
	 * @param context
	 * @param tt
	 *            void
	 * @Exception
	 * @since Ver 1.0
	 */
	public static void submitSuccessful(Activity context, TimerTask tt) {
		toastWithImageShowWithBg(context, R.mipmap.icon_check, context.getString(R.string.toast_subming_ok), tt,R.color.user_font_submitsuccessful);
	}

	/**
	 * @Title: showSuccessful
	 * @Description: 自定义成功信息
	 * @param context
	 * @param msg
	 *            void
	 * @Exception
	 * @since Ver 1.0
	 */
	public static void showSuccessfulMsg(Activity context, String msg) {
		toastWithImageShowWithBg(context, R.mipmap.icon_check, msg, null,-1);
	}
	/**
	 * @Title: showSuccessfulMsg 
	 * @Description: 自定义成功信息
	 * @param context
	 * @param msg
	 * @param tt
	 * void
	 * @Exception 
	 * @since Ver 1.0
	 */
	public static void showSuccessfulMsg(Activity context, String msg,TimerTask tt) {
		toastWithImageShowWithBg(context, R.mipmap.icon_check, msg, tt,-1);
	}

	/**
	 * 
	 * @param context
	 * @author: HLJ  
	 * @Createtime: 2015-6-14
	 */
	public static Dialog showDialog(Context context) {
		if (context == null) {
			return null;
		}

		final Dialog d = new Dialog(context, R.style.MyDialog);
		//		d.getWindow().setWindowAnimations(R.style.dialogAnimationStyle);
		d.getWindow().setContentView(R.layout.mytoast2);
		// 从xml中得到GifView的句柄
		GifView gf1 = (GifView) d.findViewById(R.id.gif1);
		// 设置Gif图片源
		gf1.setGifImage(R.drawable.loading);
		// 设置显示的大小，拉伸或者压缩
		// gf1.setShowDimension(300, 300);
		// 设置加载方式：先加载后显示、边加载边显示、只显示第一帧再显示
		gf1.setGifImageType(GifView.GifImageType.COVER);
		// 比较重要
		d.setCancelable(true);
		d.show();
		return d;
	}
}
