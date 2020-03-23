package com.happyjob.wagesteward.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.happyjob.wagesteward.R;
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

public class StringAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private Context context;
	private List<String> jobslist;
	private LayoutInflater inflater;
	private int firstType=-1;//就是第一条记录的type是多少。

	public StringAdapter(Context context, List<String> list) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.jobslist = list;
	}



	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = inflater.inflate(R.layout.item_for_text_list, parent, false);

		return new HolderView(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
		HolderView itemViewHolder = (HolderView) holder;
		itemViewHolder.type_tv.setText(jobslist.get(position));
	}

	@Override
	public long getItemId(int pos) {
		return pos;
	}

	@Override
	public int getItemCount() {
		return jobslist.size();
	}



	private class HolderView extends RecyclerView.ViewHolder{

		TextView type_tv;//银行卡分类，是银行卡还是支付宝

		public HolderView(@NonNull View itemView) {
			super(itemView);
			type_tv = itemView.findViewById(R.id.tv_type);
		}
	}

}
