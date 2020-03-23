package com.happyjob.wagesteward.utils;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.happyjob.wagesteward.base.AppConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Filename: JsonUtil.java <br>
 *
 * Description: JSON字符串工具类 <br>
 *
 * @author: HLJ <br>
 * @version: 1.0 <br>
 * @Createtime: 2015-5-11 <br>
 *
 * @Copyright: Copyright (c)2015 by HLJ <br>
 *
 */

public class JsonUtil<T extends Serializable> {

	/**
	 * 1.只需返回是否成功 没有其他数据返回
	 *
	 * @param json
	 *            {"code": "1","msg": "收藏成功！"}
	 * @param handler
	 *            负责网络和UI线程交互
	 * @author: HLJ
	 * @Createtime: 2015-5-16
	 */
	public static void jsonReturnNoData(String json, Handler handler) {
		Message msg = new Message();
		Bundle bundle = new Bundle();
		try {
			JSONObject object = new JSONObject(json);
			if ("0".equals(object.getString("result_code"))) {// 失败
				msg.what = AppConfig.NETWORK_RETURE_NO_DATA_ERROR;
			} else {// 成功
				msg.what = AppConfig.NETWORK_RETURE_NO_DATA_SUCCESS;
			}
			bundle.putString("msg", object.getString("result_msg"));
		} catch (Exception e) {// 发生错误 默认失败
			e.printStackTrace();
			msg.what = AppConfig.NETWORK_RETURE_NO_DATA_ERROR;
			bundle.putString("msg", "操作失败，请稍后再试");
		}
		msg.setData(bundle);
		handler.sendMessage(msg);
	}

	/**
	 * 2.json转化成单个的object
	 * @param json
	 * @param handler
	 * @param context
	 * @param clazz
	 * @author: HLJ
	 * @Createtime: 2015-5-19
	 */
	public void jsonToObject(String json, Handler handler,Context context, Class<T> clazz){
		Message msg = new Message();
		Bundle bundle = new Bundle();
		try {
			JSONObject object = new JSONObject(json);
			// result_code
			if ("0".equals(object.getString("result_code"))) {// 失败
				msg.what = AppConfig.NETWORK_RETURE_DATA_ERROR;
			} else {// 成功
				if("com.jianzhile.app.bean.VersionBean".equals(clazz.getName())){
					//				bundle.putSerializable("entity", new Gson().fromJson(json, clazz));
					JSONArray array = object.getJSONArray("data");
					bundle.putSerializable("entity", new Gson().fromJson(array.getString(0), clazz));
				}else{
					bundle.putSerializable("entity", new Gson().fromJson(object.getJSONObject("data").toString(), clazz));
				}
				msg.what = AppConfig.NETWORK_RETURE_DATA_SUCCESS;
			}
			bundle.putString("msg", object.getString("result_msg"));
		} catch (Exception e) {// 发生错误 默认失败
			e.printStackTrace();
			msg.what = AppConfig.NETWORK_RETURE_DATA_ERROR;
			bundle.putString("msg", "操作失败，请稍后再试");
		}
		msg.setData(bundle);
		handler.sendMessage(msg);
	}

	/**
	 * 3.json转化List列表数据
	 * @param json 服务器端返回的JSon
	 * @param handler ui和网络操作线程交互
	 * @param context
	 * @param clazz 要转化成的类
	 * @param pagenum 当前第几页
	 * @author: HLJ
	 * @Createtime: 2015-5-19
	 */
	public void jsonToList(String json, Handler handler,Context context, Class<T> clazz,int pagenum) {
		Message msg = new Message();
		Bundle bundle = new Bundle();
		try {
			JSONObject object = new JSONObject(json);
			// result_code
			if ("0".equals(object.getString("result_code"))) {// 失败
				msg.what = AppConfig.NETWORK_RETURE_LIST_ERROR;
				if(pagenum==1 && !"City".equals(clazz.getSimpleName())){
				}
			} else {// 成功

				JSONArray array = object.getJSONArray("data");
				if(array.length()>0 && pagenum==1){
				}
				for (int i = 0; i < array.length(); i++) {
					JSONObject entity = array.getJSONObject(i);
				}
				if(json.contains("page_size")){
					bundle.putInt("page_size", object.getInt("page_size"));
				}
				if(json.contains("page_num")){
					bundle.putInt("page_num", object.getInt("page_num"));
				}
				if(json.contains("flag")){
					bundle.putInt("flag", object.getInt("flag"));
				}
				if(json.contains("edit_time")){
					bundle.putString("edit_time", object.getString("edit_time"));
				}
				msg.what = AppConfig.NETWORK_RETURE_LIST_SUCCESS;
			}
			bundle.putString("msg", object.getString("result_msg"));

		} catch (Exception e) {// 发生错误 默认失败
			e.printStackTrace();
			msg.what = AppConfig.NETWORK_RETURE_LIST_ERROR;
			bundle.putString("msg", "操作失败，请稍后再试");
		}
		msg.setData(bundle);
		handler.sendMessage(msg);
	}

	/**
	 * 5.json转化成单个的object
	 * @param json
	 * @param handler
	 * @param context
	 * @param clazz
	 * @author: HLJ
	 * @Createtime: 2015-5-19
	 */
	public void jsonToObjectNoData(String json, Handler handler,Context context, Class<T> clazz){
		Message msg = new Message();
		Bundle bundle = new Bundle();
		try {
			//			json = json.substring(1, json.length());
			System.out.println("===newjson=="+json);
			JSONObject object = new JSONObject(json);
			// result_code
			if ("0".equals(object.getString("result_code"))) {// 失败
				msg.what = AppConfig.NETWORK_RETURE_DATA_ERROR;
			} else {// 成功
				bundle.putSerializable("entity", new Gson().fromJson(json, clazz));
				msg.what = AppConfig.NETWORK_RETURE_DATA_SUCCESS;
			}
			bundle.putString("msg", object.getString("result_msg"));
		} catch (Exception e) {// 发生错误 默认失败
			e.printStackTrace();
			msg.what = AppConfig.NETWORK_RETURE_DATA_ERROR;
			bundle.putString("msg", "操作失败，请稍后再试");
		}
		msg.setData(bundle);
		handler.sendMessage(msg);
	}
	/***************************************lyf***********************************************************/
	private void dbDictSync(final JSONArray array,final String state,final Context context){
		new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				//				for (int i = 0; i < array.length(); i++) {
				//					JSONObject entity = null;
				//					try {
				//						entity = array.getJSONObject(i);
				//					} catch (JSONException e) {
				//						// TODO Auto-generated catch block
				//						e.printStackTrace();
				//					}
				//					Dictionary dic = new Gson().fromJson(entity.toString(), Dictionary.class);
				//					dic.setState(state);
				//					new BaseDao<Dictionary>(context).save(dic);
				//				}
			}

		}.start();
	}

	private void dbCitySync(final JSONArray array,final  Class<T> clazz,final Context context){
		new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				for (int i = 0; i < array.length(); i++) {
					JSONObject entity = null;
					try {
						entity = array.getJSONObject(i);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}.start();
	}
	/**
	 *6.字典,存储改为子线程处理
	 * @param json
	 * @param handler
	 * @param context
	 * @author: HLJ
	 * @Createtime: 2015-5-29
	 */
	public void jsonToDicsSync(String json, Handler handler,Context context,String state) {
		//		Message msg = new Message();
		//		Bundle bundle = new Bundle();
		//		try {
		//			JSONObject object = new JSONObject(json);
		//			// result_code
		//			if ("0".equals(object.getString("result_code"))) {// 失败
		//				msg.what = AppConfig.NETWORK_RETURE_LIST_ERROR;
		//			} else {// 成功
		//				JSONArray array = object.getJSONArray("data");
		//
		//				System.out.println("===111==="+SafePreference.getState(context, state));
		//				System.out.println("===222==="+object.getString("edit_time"));
		//				if(array.length()>0 && !SafePreference.getState(context, state).equals(object.getString("edit_time"))){
		//					new BaseDao<Dictionary>(context).delorgcode(Dictionary.class,"state='"+state+"'");
		//					SafePreference.saveState(context, object.getString("edit_time"), state);
		//					dbDictSync(array, state, context);
		//				}
		//
		//				msg.what = AppConfig.NETWORK_RETURE_LIST_SUCCESS;
		//			}
		//			bundle.putString("msg", object.getString("result_msg"));
		//		} catch (Exception e) {// 发生错误 默认失败
		//			e.printStackTrace();
		//			msg.what = AppConfig.NETWORK_RETURE_LIST_ERROR;
		//			bundle.putString("msg", "操作失败，请稍后再试");
		//		}
		//		msg.setData(bundle);
		//		handler.sendMessage(msg);
	}


	/**
	 * 7.城市那个存储改为子线程处理。json转化List列表数据
	 * @param json 服务器端返回的JSon
	 * @param handler ui和网络操作线程交互
	 * @param context
	 * @param clazz 要转化成的类
	 * @param pagenum 当前第几页
	 * @author: HLJ
	 * @Createtime: 2015-5-19
	 */
	public void jsonToListSync(String json, Handler handler,Context context, Class<T> clazz,int pagenum) {
		Message msg = new Message();
		Bundle bundle = new Bundle();
		try {
			JSONObject object = new JSONObject(json);
			// result_code
			if ("0".equals(object.getString("result_code"))) {// 失败
				msg.what = AppConfig.NETWORK_RETURE_LIST_ERROR;
				if(pagenum==1 && !"City".equals(clazz.getSimpleName())){
				}
				//				new BaseDao<T>(context).delAll(clazz);
			} else {// 成功

				JSONArray array = object.getJSONArray("data");
				if(array.length()>0 && pagenum==1){
				}
				dbCitySync(array, clazz, context);
				//				for (int i = 0; i < array.length(); i++) {
				//					JSONObject entity = array.getJSONObject(i);
				//					new BaseDao<T>(context).save(new Gson().fromJson(entity.toString(), clazz));
				//				}
				if(json.contains("page_size")){
					bundle.putInt("page_size", object.getInt("page_size"));
				}
				if(json.contains("page_num")){
					bundle.putInt("page_num", object.getInt("page_num"));
				}
				if(json.contains("flag")){
					bundle.putInt("flag", object.getInt("flag"));
				}
				if(json.contains("edit_time")){
					bundle.putString("edit_time", object.getString("edit_time"));
				}
				msg.what = AppConfig.NETWORK_RETURE_LIST_SUCCESS;
			}
			bundle.putString("msg", object.getString("result_msg"));

		} catch (Exception e) {// 发生错误 默认失败
			e.printStackTrace();
			msg.what = AppConfig.NETWORK_RETURE_LIST_ERROR;
			bundle.putString("msg", "操作失败，请稍后再试");
		}
		msg.setData(bundle);
		handler.sendMessage(msg);
	}

	public void jsonToListNotSaveDB(String json, Handler handler,Context context, Class<T> clazz,int pagenum) {
		Message msg = new Message();
		Bundle bundle = new Bundle();
		try {
			if(!TextUtils.isEmpty(json))
			{
				JSONObject object = new JSONObject(json);
				// result_code
				if ("0".equals(object.getString("result_code"))) {// 失败
					msg.what = AppConfig.NETWORK_RETURE_LIST_ERROR;
				} else {// 成功

					if(json.contains("data")){
						JSONArray array = object.getJSONArray("data");
						ArrayList<T> list = new ArrayList<T>();
						for (int i = 0; i < array.length(); i++) {
							JSONObject jsonObject = (JSONObject) array.get(i);
							list.add(new Gson().fromJson(jsonObject.toString(), clazz));
						}
						bundle.putSerializable("entity", list);
					}
					if(json.contains("page_size")){
						bundle.putInt("page_size", object.getInt("page_size"));
					}
					if(json.contains("page_num")){
						bundle.putInt("page_num", object.getInt("page_num"));
					}
					if(json.contains("flag")){
						bundle.putInt("flag", object.getInt("flag"));
					}
					if(json.contains("totals")){
						bundle.putInt("totals", object.getInt("totals"));
					}else{
						if(json.contains("total")){
							bundle.putInt("total", object.getInt("total"));
						}
					}
					try {
						if(json.contains("edit_time")){
							bundle.putString("edit_time", object.getString("edit_time"));
						}
					}catch (Exception e){

					}

					bundle.putString("data", json);
					msg.what = AppConfig.NETWORK_RETURE_LIST_SUCCESS;
				}
				bundle.putString("msg", object.getString("result_msg"));
			}
		} catch (Exception e) {// 发生错误 默认失败
			e.printStackTrace();
			msg.what = AppConfig.NETWORK_RETURE_LIST_ERROR;
			bundle.putString("msg", "操作失败，请稍后再试");
		}
		msg.setData(bundle);
		handler.sendMessage(msg);
	}
	/**
	 * 7.json转化成单个的 得到data。data是个单独的类。不是数组
	 * @param json
	 * @param handler
	 * @param context
	 * @param clazz
	 * @author: HLJ
	 * @Createtime: 2015-5-19
	 */
	public void jsonToSingleDataObject(String json, Handler handler,Context context, Class<T> clazz){
		Message msg = new Message();
		Bundle bundle = new Bundle();
		try {
			JSONObject object = new JSONObject(json);
			// result_code
			if ("0".equals(object.getString("result_code"))) {// 失败
				msg.what = AppConfig.NETWORK_RETURE_DATA_ERROR;
			} else {// 成功
				JSONObject object2=object.getJSONObject("data");
				bundle.putSerializable("entity", new Gson().fromJson(object2.toString(), clazz));
				msg.what = AppConfig.NETWORK_RETURE_DATA_SUCCESS;
			}
			bundle.putString("msg", object.getString("result_msg"));
		} catch (Exception e) {// 发生错误 默认失败
			e.printStackTrace();
			msg.what = AppConfig.NETWORK_RETURE_DATA_ERROR;
			bundle.putString("msg", "操作失败，请稍后再试");
		}
		msg.setData(bundle);
		handler.sendMessage(msg);
	}

}
