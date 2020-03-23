package com.happyjob.wagesteward.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.happyjob.wagesteward.R;


/**
 * 应用程序Activity的基类
 *
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-9-18
 */
public abstract class BaseActivity extends FragmentActivity {

	// 是否允许全屏
	private boolean allowFullScreen = true;

	// 是否允许销毁
	private boolean allowDestroy = true;

	private View view;
	//	public static final int RightButtonId = R.id.rightButton;
//	public static final int RightHotAreaId = R.id.right_rl;
//	public static final int BackHotAreaId = R.id.reback_rl;
	// 返回热区
	public RelativeLayout reback_rl;
	// 返回图片
	private ImageView reback;
	// 标题
	private TextView common_title;
	// 右键热区
	private RelativeLayout right_rl;
	// 右面图片
	private ImageView tomonth;
	// 右面按钮
	public Button rightButton;
	// 上下文环境
	private Activity context;

//	public ImageLoader imageLoader = ImageLoader.getInstance();
//	public DisplayImageOptions options = new DisplayImageOptions.Builder()
//			.showStubImage(R.drawable.morentouxiang) // 设置图片下载期间显示的图片
//			.showImageForEmptyUri(R.drawable.morentouxiang) // 设置图片Uri为空或是错误的时候显示的图片
//			.showImageOnFail(R.drawable.morentouxiang) // 设置图片加载或解码过程中发生错误显示的图片
//			.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
//			.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
//			.displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
//			.build(); // 创建配置过得DisplayImageOption对象

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		allowFullScreen = true;
		// 添加Activity到堆栈
		// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		if (allowFullScreen) {
			requestWindowFeature(Window.FEATURE_NO_TITLE); // 取消标题
		}
		AppManager.getAppManager().addActivity(this);
		context=this;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		// 结束Activity&从堆栈中移除
		AppManager.getAppManager().finishActivity(this);
	}

	public boolean isAllowFullScreen() {
		return allowFullScreen;
	}

	/**
	 * 设置是否可以全屏
	 *
	 * @param allowFullScreen
	 */
	public void setAllowFullScreen(boolean allowFullScreen) {
		this.allowFullScreen = allowFullScreen;
	}

	public void setAllowDestroy(boolean allowDestroy) {
		this.allowDestroy = allowDestroy;
	}

	public void setAllowDestroy(boolean allowDestroy, View view) {
		this.allowDestroy = allowDestroy;
		this.view = view;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && view != null) {
			view.onKeyDown(keyCode, event);
			if (!allowDestroy) {
				return false;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	protected abstract void initView();

	protected abstract void initData();

	protected abstract void setListener();

	/**
	 * 初始化
	 *
	 * @param context
	 * @param title
	 *            标题栏标题 如： 分类导航
	 * @author: HLJ
	 * @Createtime: 2015-5-11
	 */
	public void defaultInit(Activity context, String title) {
		defaultInit(context);
		setTitle(title);
	}

	/**
	 * 设置标题和右侧图片
	 *
	 * @param context
	 *            容器
	 * @param title
	 *            标题
	 * @param imageId
	 *            图片ID
	 * @author: HLJ
	 * @Createtime: 2015-5-11
	 */
	public void defaultInit(Activity context, String title, int imageId) {
		defaultInit(context);
		setTitle(title);
		setRightImage(imageId);
	}

	/**
	 * 设置标题和右端按钮
	 *
	 * @param context
	 *            容器
	 * @param title
	 *            标题
	 * @param text
	 *            按钮文本
	 * @author: HLJ
	 * @Createtime: 2015-5-11
	 */
	public void defaultInit(Activity context, String title, String text) {
		defaultInit(context);
		setTitle(title);
		setRigthButtonText(text);
	}

	/**
	 * 初始化
	 *
	 * @param context
	 * @author: HLJ
	 * @Createtime: 2015-5-11
	 */
	private void defaultInit(Activity context) {
		this.context = context;
		findView();
		reback_rl.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	/**
	 * 查找标题栏控件
	 *
	 * @author: HLJ
	 * @Createtime: 2015-5-11
	 */
	private void findView() {
		reback_rl = (RelativeLayout) context.findViewById(R.id.reback_rl);
		reback = (ImageView) context.findViewById(R.id.reback);
		common_title = (TextView) context.findViewById(R.id.common_title);
		right_rl = (RelativeLayout) context.findViewById(R.id.right_rl);
		tomonth = (ImageView) context.findViewById(R.id.tomonth);
		rightButton = (Button) context.findViewById(R.id.rightButton);
	}

	/**
	 * 隐藏右侧热区
	 *
	 * @author: HLJ
	 * @Createtime: 2015-5-11
	 */
	public void hideRightHotArea() {
		right_rl.setVisibility(View.GONE);
	}

	/**
	 * 显示右侧热区
	 *
	 * @author: HLJ
	 * @Createtime: 2015-5-11
	 */
	public void showRightHotArea() {
		right_rl.setVisibility(View.VISIBLE);
	}

	/**
	 * 去除返回的监听事件
	 *
	 * @author: HLJ
	 * @Createtime: 2015-5-11
	 */
	public void removeBackListener() {
		reback_rl.setOnClickListener(null);
	}

	/**
	 * 添加其他的返回的监听事件
	 *
	 * @param listener
	 * @author: HLJ
	 * @Createtime: 2015-5-11
	 */
	public void addNewBackListener(View.OnClickListener listener) {
		reback_rl.setOnClickListener(listener);
	}

	/**
	 * 设置标题
	 *
	 * @param title
	 * @author: HLJ
	 * @Createtime: 2015-5-11
	 */
	public void setTitle(String title) {
		common_title.setText(title);
	}

	/**
	 * 设置右面图片
	 *
	 * @param imageId
	 * @author: HLJ
	 * @Createtime: 2015-5-11
	 */
	public void setRightImage(int imageId) {
		// tomonth.setImageResource(imageId);
		tomonth.setBackgroundResource(imageId);
		right_rl.setVisibility(View.VISIBLE);
		tomonth.setVisibility(View.VISIBLE);
		rightButton.setVisibility(View.GONE);
		right_rl.setOnClickListener((OnClickListener) context);
	}

	/**
	 * 返回右面图片对象
	 *
	 * @return
	 * @author: HLJ
	 * @Createtime: 2015-5-11
	 */
	public ImageView getRightImage() {
		return tomonth;
	}

	/**
	 * 设置右面按钮名称
	 *
	 * @param text
	 * @author: HLJ
	 * @Createtime: 2015-5-11
	 */
	public void setRigthButtonText(String text) {
		rightButton.setText(text);
		rightButton.setVisibility(View.VISIBLE);
		tomonth.setVisibility(View.GONE);
		rightButton.setOnClickListener((OnClickListener) context);
	}

	/**
	 * 返回右侧标题
	 *
	 * @return
	 * @author: HLJ
	 * @Createtime: 2015-5-11
	 */
	public Button getRightButton() {
		return rightButton;
	}
	/**
	 * 跳转到...
	 */
	public void redirectTo(Class toclass){
		Intent intent = new Intent(this, toclass);
		startActivity(intent);
	}

	//此方法只是关闭软键盘
	public void hintKbTwo() {
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		if(imm.isActive()&&getCurrentFocus()!=null){
			if (getCurrentFocus().getWindowToken()!=null) {
				imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			}
		}
	}


}
