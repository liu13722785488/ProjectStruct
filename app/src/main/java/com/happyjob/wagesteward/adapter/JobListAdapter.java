package com.happyjob.wagesteward.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.happyjob.wagesteward.R;
import com.happyjob.wagesteward.bean.Job;

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

public class JobListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private Context context;
	private List<Job> jobslist;
	private LayoutInflater inflater;

	public JobListAdapter(Context context, List<Job> list) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.jobslist = list;
	}



	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = inflater.inflate(R.layout.item_for_job_list, parent, false);

		return new HolderView(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
		HolderView itemViewHolder = (HolderView) holder;
		Job job=jobslist.get(position);
		itemViewHolder.tv_jobname.setText(job.getJobs_name());
		itemViewHolder.tv_work_time.setText(job.getStarttime()+"-"+job.getDeadline());
		itemViewHolder.tv_place.setText(job.getAddress());
		itemViewHolder.tv_wage.setText(job.getWage());
		itemViewHolder.tv_wage_cn.setText(job.getWage_cn());
		itemViewHolder.tv_fabu_company.setText(job.getCompany_name());
		itemViewHolder.tv_fabu_time.setText(job.getAddtime());
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

		TextView tv_jobname;//
		TextView tv_work_time;//
		TextView tv_place;//
		TextView tv_wage;//
		TextView tv_wage_cn;//
		TextView tv_fabu_company;//
		TextView tv_fabu_time;//

		public HolderView(@NonNull View itemView) {
			super(itemView);
			tv_jobname = itemView.findViewById(R.id.tv_jobname);
			tv_work_time = itemView.findViewById(R.id.tv_work_time);
			tv_place = itemView.findViewById(R.id.tv_place);
			tv_wage = itemView.findViewById(R.id.tv_wage);
			tv_wage_cn = itemView.findViewById(R.id.tv_wage_cn);
			tv_fabu_company = itemView.findViewById(R.id.tv_fabu_company);
			tv_fabu_time = itemView.findViewById(R.id.tv_fabu_time);
		}
	}

}
