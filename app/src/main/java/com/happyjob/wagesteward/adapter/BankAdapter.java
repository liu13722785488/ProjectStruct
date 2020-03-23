package com.happyjob.wagesteward.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;


import com.happyjob.wagesteward.R;
import com.happyjob.wagesteward.base.AppConfig;
import com.happyjob.wagesteward.bean.Bank;


import java.util.List;

/**
 * Filename: BankAdapter.java <br>
 * 
 * Description: 我的钱包 钱包设置 适配器 <br>
 * 
 * @author: HLJ <br>
 * @version: 1.0 <br>
 * @Createtime: 2015-5-19 <br>
 * 
 * @Copyright: Copyright (c)2015 by HLJ <br>
 * 
 */

public class BankAdapter extends BaseAdapter {

	private Context context;
	private List<Bank> jobslist;
	private LayoutInflater inflater;
	private int firstType=-1;//就是第一条记录的type是多少。

	public BankAdapter(Context context, List<Bank> list) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.jobslist = list;
	}

	@Override
	public int getCount() {
		return jobslist.size();
	}

	@Override
	public Object getItem(int position) {
		return jobslist.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HolderView holderView = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_for_bank_list, null);// 实例化convertView
			holderView = new HolderView();
			holderView.fullname_tv = (TextView) convertView
					.findViewById(R.id.fullname_tv);// 名称
			holderView.nums_tv = (TextView) convertView
					.findViewById(R.id.nums_tv);// 兼职次数
			holderView.type_tv = (TextView) convertView
					.findViewById(R.id.tv_type);//钱包类型，是银行卡还是支付宝
			holderView.cb = (CheckBox) convertView.findViewById(R.id.cb);//
			holderView.cb.setVisibility(View.GONE);
			convertView.setTag(holderView);

		} else {
			holderView = (HolderView) convertView.getTag();
		}

		Bank entity = jobslist.get(position);

		int cardLength=entity.getCard_namber().length();
		if(cardLength>=4&&entity.getAccount_type()==1)
		{
			String string=entity.getCard_namber().substring(cardLength-4, cardLength);
			holderView.nums_tv.setText("尾号" + string);// 银行卡号
		}else{
			holderView.nums_tv.setText(entity.getCard_namber());// 银行卡号

		}
		holderView.fullname_tv.setText(entity.getFullname() + "");// 银行名
		if(entity.getType()!=null){
			if(entity.getType().equals("1"))
			{
				holderView.type_tv.setVisibility(View.VISIBLE);
				holderView.type_tv.setText("我的银行卡");

			}else if(entity.getType().equals("2")){
				holderView.type_tv.setVisibility(View.VISIBLE);
				holderView.type_tv.setText("我的支付宝");
			}
			else{
				holderView.type_tv.setVisibility(View.GONE);
				firstType=entity.getAccount_type();
			}
		}
		return convertView;
	}

	private class HolderView {
		TextView fullname_tv;// 银行名
		TextView nums_tv;// 银行卡号
		CheckBox cb;//选择
		TextView type_tv;//银行卡分类，是银行卡还是支付宝
	}

}
