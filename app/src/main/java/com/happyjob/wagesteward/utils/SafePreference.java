package com.happyjob.wagesteward.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;

import com.happyjob.wagesteward.base.AppConfig;


/**
 * 对SharePreference操作 存 取
 */
public class SafePreference {

	/**
	 * 往SharePreference中存数据
	 * 
	 * @param contex
	 * @param key
	 * @param value
	 *            ,不仅可以保持字符串还可以保持boolean
	 */
	public static void save(Context context, String key, Object value) {
		// 拿到SharePreference
		SharedPreferences sp = context
				.getSharedPreferences(AppConfig.CONFIG, Context.MODE_PRIVATE);
		if (value != null) {
			// 判断要存的数据是什么数据
			if (value instanceof String) {
				sp.edit().putString(key, (String) value).commit();
			} else if (value instanceof Boolean) {
				sp.edit().putBoolean(key, (Boolean) value).commit();
			} else if (value instanceof Integer) {
				sp.edit().putInt(key, (Integer) value).commit();
			}
		} else {
			sp.edit().putString(key, "").commit();
		}
	}

	/**
	 * 从SharePreference中取数据
	 * 
	 * @param contex
	 * @param key
	 * @param value
	 */
	public static String getStr(Context context, String key) {
		SharedPreferences sp = context
				.getSharedPreferences(AppConfig.CONFIG, 0);
		return sp.getString(key, "");
	}

	/**
	 * 从SharePreference中取数据
	 * 
	 * @param contex
	 * @param key
	 * @param value
	 */
	public static int getStr1(Context context, String key) {
		SharedPreferences sp = context
				.getSharedPreferences(AppConfig.CONFIG, 0);
		return sp.getInt(key, 0);
	}

	public static Boolean getBoolean(Context context, String key) {
		SharedPreferences sp = context
				.getSharedPreferences(AppConfig.CONFIG, 0);
		return sp.getBoolean(key, false);
	}


	/**
	 * 传一个默认的int值，这个是为了 性别哪里使用的
	 * @param context
	 * @param key
	 * @return
	 * @author: HLJ  
	 * @Createtime: 2015-4-20
	 */
	public static Integer getInteger(Context context, String key,int defValue){
		SharedPreferences sp = context
				.getSharedPreferences(AppConfig.CONFIG, 0);
		return sp.getInt(key, defValue);
	}
	/**
	 * 
	 * @param context
	 * @param key
	 * @return
	 * @author: HLJ  
	 * @Createtime: 2015-4-20
	 */
	public static Integer getInteger(Context context, String key){
		SharedPreferences sp = context
				.getSharedPreferences(AppConfig.CONFIG, 0);
		return sp.getInt(key, 0);
	}

	public static void saveUserName(Context context, String username) {
		SafePreference.save(context, AppConfig.LOGINEDUSERNAME, username);
	}

	public static String getUserName(Context context) {
		return SafePreference.getStr(context, AppConfig.LOGINEDUSERNAME);
	}

	public static void saveComName(Context context, String username) {
		SafePreference.save(context, AppConfig.COMLOGINEDUSERNAME, username);
	}

	public static String getComName(Context context) {
		return SafePreference.getStr(context, AppConfig.COMLOGINEDUSERNAME);
	}

	public static void saveNickName(Context context, String username) {//姓名
		SafePreference.save(context, AppConfig.NICKNAME, username);
	}

	public static String getNickName(Context context) {
		return SafePreference.getStr(context, AppConfig.NICKNAME);
	}
	public static void saveDeptName(Context context, String username) {//姓名
		SafePreference.save(context, AppConfig.DEPTNAME, username);
	}

	public static String getDeptName(Context context) {
		return SafePreference.getStr(context, AppConfig.DEPTNAME);
	}
	/**
	 * 所在省存取
	 * 
	 * @param context
	 * @return
	 * @author: HLJ
	 * @Createtime: 2015-3-24
	 */
	public static String getProvince(Context context) {
		return SafePreference.getStr(context, AppConfig.PROVINCE);
	}

	public static void saveProvince(Context context, String province) {
		SafePreference.save(context, AppConfig.PROVINCE, province);
	}

	/**
	 * 所在市存取
	 * 
	 * @param context
	 * @return
	 * @author: HLJ
	 * @Createtime: 2015-3-24
	 */
	public static String getCity(Context context) {
		return SafePreference.getStr(context, AppConfig.CITY);
	}

	public static void saveCity(Context context, String city) {
		SafePreference.save(context, AppConfig.CITY, city);
	}

	public static void savePassword(Context context, String pwd) {
		save(context, AppConfig.PASSWORD, pwd);
	}

	public static String getPassword(Context context) {
		return getStr(context, AppConfig.PASSWORD);
	}

	public static void setRememberPWD(Context context, boolean flag) {
		save(context, AppConfig.ISREMREMBERPASSWORD, flag);
	}

	public static boolean getRememberPWD(Context context) {
		return getBoolean(context, AppConfig.ISREMREMBERPASSWORD);
	}


	public static void saveComPassword(Context context, String pwd) {
		save(context, AppConfig.PASSWORD, pwd);
	}

	public static String getComPassword(Context context) {
		return getStr(context, AppConfig.PASSWORD);
	}

	public static void setRememberComPWD(Context context, boolean flag) {
		save(context, AppConfig.ISREMREMBERPASSWORD, flag);
	}

	public static boolean getRememberComPWD(Context context) {
		return getBoolean(context, AppConfig.ISREMREMBERPASSWORD);
	}


	public static PackageInfo getPackageInfo(Context context) {
		PackageManager manager = context.getPackageManager();
		try {
			return manager.getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
		}
		return null;
	}

	/*
	 * 修改密码时判断密码是否一致
	 */
	public static boolean isEqualsOldPassword(Context context, String password) {
		String oldPassword = SafePreference.getStr(context, AppConfig.PASSWORD);
		if (TextUtils.isEmpty(oldPassword)) {
			return false;
		}
		// String md5pwd = MD5.pwdWithSalt(password,
		// SafePreference.getSalt(context));
		return oldPassword.equals(password);
	}

	/*
	 * 判断密码长度是否符合要求
	 */
	public static boolean psdIsRequire(String str) {
		if (str.length() >= 6 && str.length() <= 30) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是否登录
	 * @param context
	 * @return
	 * @author: HLJ  
	 * @Createtime: 2015-3-27
	 */
	public static boolean isLogin(Context context) {
		return SafePreference.getBoolean(context, "islogin");
	}

	/**
	 * 保存是否登录
	 * @param context
	 * @param islogin
	 * @author: HLJ  
	 * @Createtime: 2015-3-27
	 */
	public static void setIsLogin(Context context,boolean islogin) {
		SafePreference.save(context, "islogin", islogin);
	}

	/**
	 * 企业是否登录
	 * @param context
	 * @return
	 * @author: HLJ  
	 * @Createtime: 2015-3-27
	 */
	public static boolean isComLogin(Context context) {
		return SafePreference.getBoolean(context, "isComlogin");
	}

	/**
	 * 保存企业是否登录
	 * @param context
	 * @param islogin
	 * @author: HLJ  
	 * @Createtime: 2015-3-27
	 */
	public static void setIsComLogin(Context context,boolean islogin) {
		SafePreference.save(context, "isComlogin", islogin);
	}

	/**
	 * 是否有求职者注册
	 * @param context
	 * @return
	 * @author: HLJ  
	 * @Createtime: 2015-3-27
	 */
	public static boolean isUserReg(Context context) {
		return SafePreference.getBoolean(context, "userisreg");
	}

	/**
	 * 保存是否有求职者注册
	 * @param context
	 * @param islogin
	 * @author: HLJ  
	 * @Createtime: 2015-3-27
	 */
	public static void setIsUserReg(Context context,boolean islogin) {
		SafePreference.save(context, "userisreg", islogin);
	}

	/**
	 * 是否有企业注册
	 * @param context
	 * @return
	 * @author: HLJ  
	 * @Createtime: 2015-3-27
	 */
	public static boolean isComReg(Context context) {
		return SafePreference.getBoolean(context, "comisreg");
	}

	/**
	 * 保存是否有企业注册
	 * @param context
	 * @param islogin
	 * @author: HLJ  
	 * @Createtime: 2015-3-27
	 */
	public static void setIsComReg(Context context,boolean islogin) {
		SafePreference.save(context, "comisreg", islogin);
	}

	/**
	 * 是否有简历
	 * @param context
	 * @return
	 * @author: HLJ  
	 * @Createtime: 2015-3-27
	 */
	public static boolean isResume(Context context) {
		return SafePreference.getBoolean(context, "isresume");
	}

	/**
	 * 保存是否有简历
	 * @param context
	 * @param islogin
	 * @author: HLJ  
	 * @Createtime: 2015-3-27
	 */
	public static void setIsResume(Context context,boolean hasresume) {
		SafePreference.save(context, "isresume", hasresume);
	}

	/**
	 * 是否第一次进入程序
	 * @param context
	 * @return
	 * @author: HLJ  
	 * @Createtime: 2015-3-27
	 */
	public static boolean isFirst(Context context) {
		return SafePreference.getBoolean(context, "isfirst");
	}

	/**
	 * 保存是否第一次进入程序
	 * @param context
	 * @param islogin
	 * @author: HLJ  
	 * @Createtime: 2015-3-27
	 */
	public static void setIsFirst(Context context,boolean isfirst) {
		SafePreference.save(context, "isfirst", isfirst);
	}

	/**
	 * 0==企业  1===个人 -1===注销
	 * @param context
	 * @return
	 * @author: HLJ  
	 * @Createtime: 2015-4-20
	 */
	public static int isPersonOrCompany(Context context) {
		return SafePreference.getInteger(context, "personorcompany");
	}

	public static void savePersonOrCompany(Context context,int personorcomy) {
		SafePreference.save(context, "personorcompany", personorcomy);
	}

	// 1-职位性质分类（首页标签）2-	职位类型（派单等）3-智能排序   4-学校信息
	public static void saveState(Context context, String nature,String state) {
		if("1".equals(state)){
			save(context, "nature", nature);
		}else if("2".equals(state)){
			save(context, "catege_cn", nature);
		}else if("3".equals(state)){
			save(context, "order", nature);
		}else if("4".equals(state)){
			save(context, "school", nature);
		}else{
			save(context, "xueli", nature);
		}
	}

	public static String getState(Context context,String state) {
		if("1".equals(state)){
			return getStr(context, "nature");
		}else if("2".equals(state)){
			return getStr(context, "catege_cn");
		}else if("3".equals(state)){
			return getStr(context, "order");
		}else if("4".equals(state)){
			return getStr(context, "school");
		}else{
			return getStr(context, "xueli");
		}
	}

	/**
	 * 保存/得到余额
	 * @param context
	 * @return
	 * @author: HLJ  
	 * @Createtime: 2015-6-4
	 */
	public static String getPoints(Context context) {
		return getStr(context, "person_points");
	}

	public static void savePoints(Context context,String points) {
		SafePreference.save(context, "person_points", points);
	}

	/**
	 * 保存/得到用户id
	 * @param context
	 * @return
	 * @author: HLJ  
	 * @Createtime: 2015-6-4
	 */
	public static String getUid(Context context) {
		return SafePreference.getStr(context, "personuid");
	}
	public static void saveUid(Context context,String personuid) {
		SafePreference.save(context, "personuid", personuid);
	}
	/**
	 * 保存/得到融云用户id
	 * @param context
	 * @return
	 * @author: HLJ
	 * @Createtime: 2015-6-4
	 */
	public static String getRongCloudUid(Context context) {
		return SafePreference.getStr(context, "rongCloudId");
	}
	public static void saveRongCloudUid(Context context,String personuid) {
		SafePreference.save(context, "rongCloudId", personuid);
	}

	/**
	 * 保存/得到用户名称
	 * @param context
	 * @return
	 * @author: HLJ  
	 * @Createtime: 2015-6-4
	 */
	public static String getFullname(Context context) {
		return SafePreference.getStr(context, "fullname");
	}
	public static void saveFullname(Context context,String fullname) {
		SafePreference.save(context, "fullname", fullname);
	}

	/**
	 * 保存/得到用户头像图标
	 * @param context
	 * @return
	 * @author: HLJ  
	 * @Createtime: 2015-6-4
	 */
	public static String getphotoImg(Context context) {
		return SafePreference.getStr(context, "photoimage");
	}
	public static void savephotoImg(Context context,String photoimage) {
		SafePreference.save(context, "photoimage", photoimage);
	}
	/**
	 * 保存/得到用户身份证id
	 * @param context
	 * @return
	 * @author: HLJ  
	 * @Createtime: 2015-6-4
	 */
	public static String getcard_id(Context context) {
		return SafePreference.getStr(context, "card_id");
	}
	public static void savecard_id(Context context,String card_id) {
		SafePreference.save(context, "card_id", card_id);
	}


	/**
	 * 保存/得到省市县更新的时间
	 * @param context
	 * @return
	 * @author: HLJ  
	 * @Createtime: 2015-6-4
	 */
	public static String getDistrictTime(Context context) {
		return SafePreference.getStr(context, "district_update_time");
	}
	public static void saveDistrictTime(Context context,String time) {
		SafePreference.save(context, "district_update_time", time);
	}


	/**
	 * 保存/得到职位类型（派单等）更新时间
	 * @param context
	 * @return
	 * @author: HLJ  
	 * @Createtime: 2015-6-4
	 */
	public static String getNatureTime(Context context) {
		return SafePreference.getStr(context, "nature_update_time");
	}
	public static void saveNatureTime(Context context,String time) {
		SafePreference.save(context, "nature_update_time", time);
	}
	/**
	 * 用来保存用户第一次修改资料的时候需要去看一下等级说明。
	 * @param context
	 * @return
	 * @author: HLJ  
	 * @Createtime: 2015-6-4
	 */
	public static String getIsSeedDengJi(Context context) {
		return SafePreference.getStr(context, "see_dengji");
	}
	public static void saveIsSeeDengJi(Context context,String seeDengJi) {//看是否需要看等级，第一次修改完资料就要去看等级,第一次的时候是空的。
		SafePreference.save(context, "see_dengji", seeDengJi);
	}

	/**
	 * 保存是否是从注册界面跳转过来的。
	 * @param context
	 * @return
	 * @author: HLJ  
	 * @Createtime: 2015-6-4
	 */
	public static String getIsFromRegister(Context context) {
		return SafePreference.getStr(context, "is_from_register");
	}
	public static void saveIsFromRegister(Context context,String isFromRegister) {
		SafePreference.save(context, "is_from_register", isFromRegister);
	}
	/**
	 * 保存定制的个人信息。 保存省 市 县  市的id  空闲时间  兼职类型 兼职区域  性别 
	 * @param context
	 * @return
	 * @author: HLJ  
	 * @Createtime: 2015-6-4
	 * county是县
	 * CityId 是市的id
	 * category = data.getStringExtra("leixing");
				freetime = data.getStringExtra("freetime");
				sex 
	 * 
	 */
	public static String getCounty(Context context) {
		return SafePreference.getStr(context, "county");
	}
	public static void saveCounty(Context context,String county) {
		SafePreference.save(context, "county", county);
	}

	public static int getCityId(Context context) {
		return SafePreference.getInteger(context, "cityid");
	}
	public static void saveCityId(Context context,int cityid) {
		SafePreference.save(context, "cityid", cityid);
	}

	/**
	 * @param context
	 * 保存兼职类型
	 * @return
	 */
	public static String getCategory(Context context) {
		return SafePreference.getStr(context, "category");
	}
	public static void saveCategory(Context context,String category) {
		SafePreference.save(context, "category", category);
	}

	/**
	 * @param context
	 * 保存空闲时间
	 * @return
	 */
	public static String getFreeTime(Context context) {
		return SafePreference.getStr(context, "freetime");
	}
	public static void saveFreeTime(Context context,String freetime) {
		SafePreference.save(context, "freetime", freetime);
	}

	/**
	 * @param context
	 * 保存性别
	 * @return
	 */
	public static int getSex(Context context) {
		return SafePreference.getInteger(context,  "sex", 3);
	}
	public static void saveSex(Context context,int sex) {
		SafePreference.save(context, "sex", sex);
	}


	/**
	 * @param context
	 * 保存现在从网络上获取的通知的id,保存通知的所有id。写成字符串的形式，用，分割。
	 * @return
	 */
	public static String getNowTongzhiId(Context context) {
		return SafePreference.getStr(context,  "tongzhiid");
	}
	public static void saveNowTongzhiId(Context context,String tongzhiid) {
		SafePreference.save(context, "tongzhiid",tongzhiid);
	}


	/**
	 * @param context
	 * 保存原来的通知的id,保存通知的所有id。写成字符串的形式，用，分割。
	 * @return
	 */
	public static String getYuanTongzhiId(Context context) {
		return SafePreference.getStr(context,  "yuantongzhiid");
	}
	public static void saveYuanTongzhiId(Context context,String tongzhiid) {
		SafePreference.save(context, "yuantongzhiid",tongzhiid);
	}


	/**
	 * @param context
	 * 保存是否定位过，如果为true则表示定位了，如果为false则表示没有定位
	 * @return
	 */
	public static Boolean getIsLocation(Context context) {
		return SafePreference.getBoolean(context,  "isLocation");
	}
	public static void saveIsLocation(Context context,boolean isLocation) {
		SafePreference.save(context, "isLocation",isLocation);
	}


	public static void saveClientid(Context context, String username) {
		SafePreference.save(context, "clientid", username);
	}

	public static String getClientid(Context context) {
		return SafePreference.getStr(context, "clientid");
	}
	//存上登陆者的身份
	public static void saveLoginType(Context context, String type) {
		SafePreference.save(context, "type", type);
	}

	public static String getLoginType(Context context) {
		return SafePreference.getStr(context, "type");
	}
	//存上登陆者的身份
	public static void saveJsign(Context context, String jsign) {
		SafePreference.save(context, "jsign", jsign);
	}

	public static String getJsign(Context context) {
		return SafePreference.getStr(context, "jsign");
	}
	//存上登陆者的城市id
	public static void saveDeptId(Context context, String deptid) {
		SafePreference.save(context, "deptid", deptid);
	}

	public static String getDeptId(Context context) {
		return SafePreference.getStr(context, "deptid");
	}
	//存着登录者的身份。用了区分，登陆者是什么身份的。
	public static void saveType(Context context, String type) {
		SafePreference.save(context, "type", type);
	}

	public static String getType(Context context) {
		return SafePreference.getStr(context, "type");
	}
	//存着登录者对应的角色名称
	//	public static void saveTypeChinese(Context context, String type) {
	//		SafePreference.save(context, "chineseType", type);
	//	}
	//
	//	public static String getTypeChinese(Context context) {
	//		return SafePreference.getStr(context, "chineseType");
	//	}
	//存着登录者的头像
	public static void saveBgUrl(Context context, String imgUrl) {
		SafePreference.save(context, "bgUrl", imgUrl);
	}

	public static String getBgUrl(Context context) {
		return SafePreference.getStr(context, "bgUrl");
	}
	//存着登录者的头像
	public static void saveImgUrl(Context context, String imgUrl) {
		SafePreference.save(context, "imgUrl", imgUrl);
	}
	
	public static String getImgUrl(Context context) {
		return SafePreference.getStr(context, "imgUrl");
	}
	//存着系统的色值
	public static void saveSkinColor(Context context, String skinColor) {
		SafePreference.save(context, "skincolor", skinColor);
	}

	public static String getSkinColor(Context context) {
		return SafePreference.getStr(context, "skincolor");
	}
	//存着系统的色值所在是的文件夹
	public static void saveFileName(Context context, String fileName) {
		SafePreference.save(context, "fileName", fileName);
	}

	public static String getFileName(Context context) {
		return SafePreference.getStr(context, "fileName");
	}
	//存着角色的岗位名称
	public static void saveJname(Context context, String jname) {
		SafePreference.save(context, "jname", jname);
	}

	public static String getJname(Context context) {
		return SafePreference.getStr(context, "jname");
	}
	//存着json字符串。皮肤对应的url
	public static void saveJsonString(Context context, String jsonString) {
		SafePreference.save(context, "jsonSkin", jsonString);
	}

	public static String getJsonString(Context context) {
		return SafePreference.getStr(context, "jsonSkin");
	}

	/**
	 * 照片打卡存放的第一个位置的图片url
	 * @param context
	 * @param imgUrl
	 */
	public static void saveImgUrl0(Context context,String imgUrl){
		SafePreference.save(context, "imgurl0", imgUrl);
	}
	public static String getImgUrl0(Context context){
		return SafePreference.getStr(context, "imgurl0");
	}

	/**
	 * 照片打卡存放的第2个位置的图片url
	 * @param context
	 * @param imgUrl
	 */
	public static void saveImgUrl1(Context context,String imgUrl){
		SafePreference.save(context, "imgurl1", imgUrl);
	}
	public static String getImgUrl1(Context context){
		return SafePreference.getStr(context, "imgurl1");
	}
	/**
	 * 照片打卡存放的第3个位置的图片url
	 * @param context
	 * @param imgUrl
	 */
	public static void saveImgUrl2(Context context,String imgUrl){
		SafePreference.save(context, "imgurl2", imgUrl);
	}
	public static String getImgUrl2(Context context){
		return SafePreference.getStr(context, "imgurl2");
	}
	/**
	 * 新增客户，可以选择的客户的类型  只能兼职，全职。还算可以选择兼职全职。
	 * @param context
	 * @param customMark
	 */
	public static void saveCustomMark(Context context,String customMark){
		SafePreference.save(context, "customMark", customMark);
	}
	public static String getCustomMark(Context context){
		return SafePreference.getStr(context, "customMark");
	}

	//融云的token
	/**
	 * @param context
	 * @param token 融云的token
	 */
	public static void saveRongCloudToken(Context context, String token) {
		SafePreference.save(context, "rongcloudtoken", token);
	}

	public static String getRongCloudToken(Context context) {
		return SafePreference.getStr(context, "rongcloudtoken");
	}
}
