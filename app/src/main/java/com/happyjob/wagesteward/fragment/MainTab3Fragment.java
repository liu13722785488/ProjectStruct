package com.happyjob.wagesteward.fragment;

/*
 _ooOoo_
 o8888888o
 88" . "88
 (| -_- |)
 O\  =  /O
 ____/`---'\____
 .'  \\|     |//  `.
 /  \\|||  :  |||//  \
 /  _||||| -:- |||||-  \
 |   | \\\  -  /// |   |
 | \_|  ''\---/''  |   |
 \  .-\__  `-`  ___/-. /
 ___`. .'  /--.--\  `. . __
 ."" '<  `.___\_<|>_/___.'  >'"".
 | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 \  \ `-.   \_ __\ /__ _/   .-` /  /
 ======`-.____`-.___\_____/___.-`____.-'======

 ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 佛祖保佑       永无BUG
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.happyjob.wagesteward.R;
import com.happyjob.wagesteward.base.BaseFragment;

/**
 * 通讯录
 * 
 * @author HLJ
 * 
 */
public class MainTab3Fragment extends BaseFragment implements View.OnClickListener
{
	private LayoutInflater inflater;
	private Context context;
	//
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		context = getActivity();
		View contactsLayout = inflater.inflate(
				R.layout.fragment_maintab3_layout, container, false);
		initView(contactsLayout);
		setListener();
		return contactsLayout;
	}


	public MainTab3Fragment() {
		super();
	}

	public static MainTab3Fragment getClientFragment() {

		MainTab3Fragment clientFragment = new MainTab3Fragment();
		return clientFragment;
	}

	@SuppressLint("NewApi")
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}


	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}


	@Override
	public void initView(View view) {
	}
	@Override
	public void initData() {
		// 初始化
	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		// 搜索按钮
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}



}
