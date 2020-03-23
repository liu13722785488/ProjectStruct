package com.happyjob.wagesteward.bean;


import java.io.Serializable;

/**
 * Filename: job.java <br>
 * 
 * Description: 卡号信息<br>
 * 
 * @author: HLJ <br>
 * @version: 1.0 <br>
 * @Createtime: 2015-5-27 <br>
 * 
 * @Copyright: Copyright (c)2015 by HLJ <br>
 * 
 */

public class Job implements Serializable {

//			"company_name": "河北乐聘网络科技有限公司",
//			"jobs_name": "测试职位请勿报名",
//			"category": "实习生",
//			"deadline": "2017-09-23",
//			"starttime": "2017-09-21",
	private String company_name;
	private String jobs_name;
	private String category;
	private String deadline;
	private String starttime;
	private String address;
	private String wage;
	private String wage_cn;
	private String addtime;

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getWage() {
		return wage;
	}

	public void setWage(String wage) {
		this.wage = wage;
	}

	public String getWage_cn() {
		return wage_cn;
	}

	public void setWage_cn(String wage_cn) {
		this.wage_cn = wage_cn;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Job() {
	}


	public String getJobs_name() {
		return jobs_name;
	}

	public void setJobs_name(String jobs_name) {
		this.jobs_name = jobs_name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
}
