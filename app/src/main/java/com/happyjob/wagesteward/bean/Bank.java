package com.happyjob.wagesteward.bean;


import java.io.Serializable;

/**
 * Filename: Blance.java <br>
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

public class Bank implements Serializable {

	private int account_type;// 类型 1-银行 2-支付宝
	private int bankid;// 卡号信息id
	private String fullname;// 用户名
	private String card_namber;// 卡号/支付宝号码
	private String bank;// 可选 银行需要 开户行
	private String bank_cn;//银行卡支付宝的中文
	private String branck;// 开户分行
	private String bankimg;// 银行图标
	private String alipay;
	private String province;//省
	private String city;//市
	private String type;//1是我的银行卡  2是我的支付宝  空是不用显示
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProvince() {
		
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getBank_cn() {
		return bank_cn;
	}
	public void setBank_cn(String bank_cn) {
		this.bank_cn = bank_cn;
	}
	public int getAccount_type() {
		return account_type;
	}
	public void setAccount_type(int account_type) {
		this.account_type = account_type;
	}
	public int getBankid() {
		return bankid;
	}
	public void setBankid(int bankid) {
		this.bankid = bankid;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getCard_namber() {
		return card_namber;
	}
	public void setCard_namber(String card_namber) {
		this.card_namber = card_namber;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBranck() {
		return branck;
	}
	public void setBranck(String branck) {
		this.branck = branck;
	}
	public String getBankimg() {
		return bankimg;
	}
	public void setBankimg(String bankimg) {
		this.bankimg = bankimg;
	}
	public String getAlipay() {
		return alipay;
	}
	public void setAlipay(String alipay) {
		this.alipay = alipay;
	}

}
