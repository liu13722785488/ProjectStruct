package com.happyjob.wagesteward.bean;

import org.json.JSONObject;

import java.io.Serializable;


public abstract class BaseBean<T> implements Serializable {


	/**
	 * 将json对象转化为Bean实例
	 * 
	 * @param jsonObj
	 * @return
	 */
	public abstract T parseJSON(JSONObject jsonObj);

	/**
	 * 将Bean实例转化为json对象
	 * 
	 * @return
	 */
	public abstract JSONObject toJSON();



	

}
