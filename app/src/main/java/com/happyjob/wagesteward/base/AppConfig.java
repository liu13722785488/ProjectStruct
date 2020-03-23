package com.happyjob.wagesteward.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * 应用程序配置类：用于保存用户相关信息及设置
 *
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
@SuppressLint("NewApi")
public class AppConfig {

    private final static String APP_CONFIG = "config";
    public final static String SAVE_IMAGE_PATH = "save_image_path";

    @SuppressLint("NewApi")
    public final static String DEFAULT_SAVE_IMAGE_PATH = Environment
            .getExternalStorageDirectory()
            + File.separator
            + "ffw"
            + File.separator;

    private Context mContext;
    private static AppConfig appConfig;

    public static AppConfig getAppConfig(Context context) {
        if (appConfig == null) {
            appConfig = new AppConfig();
            appConfig.mContext = context;
        }
        return appConfig;
    }

    // --数据库名称
    public static final String DB_NAME = "wagesteward01.db";
    // --网络连接超时 默认30秒=1000 * 30
    public static final int NETWORK_TIMEOUT = 1000 * 30*6;
    // --网络访问无网络错误
    public static final int NO_NETWORK = 1001;
    // --网络访问 成功
    public static final int NETWORK_SUCCESS = 1002;
    // --网络访问 失败
    public static final int NETWORK_ERROR = 1003;
    // --网络访问 只需返回是否成功 没有其他数据返回 -成功{"code": "1","msg": "收藏成功！"}
    public static final int NETWORK_RETURE_NO_DATA_SUCCESS = 1004;
    // --网络访问 只需返回是否成功 没有其他数据返回 -失败{"code": "0","msg": "收藏成功！"}
    public static final int NETWORK_RETURE_NO_DATA_ERROR = 1005;
    // --网络访问 返回单个数据 -成功
    public static final int NETWORK_RETURE_DATA_SUCCESS = 1006;
    // --网络访问 返回单个数据 -失败
    public static final int NETWORK_RETURE_DATA_ERROR = 1007;
    // --网络访问 返回List数据 -成功
    public static final int NETWORK_RETURE_LIST_SUCCESS = 1008;
    // --网络访问 返回List数据 -失败
    public static final int NETWORK_RETURE_LIST_ERROR = 1009;

    // --只需返回成功与否,跟那个NETWORK_RETURE_NO_DATA_ERROR配套
    public static final int GET_RESULT = 1;
    // --得到单个数据
    public static final int GET_SIGLE_DATA = 2;//对应的是data是一个类而不是数组
    // --得到list数据
    public static final int GET_LIST_DATA = 3;
    // --得到单个数据  这个是解析整个json数据转换为class
    public static final int GET_RESULT_NO_DATE = 4;
    // --直接返回访问数据
    public static final int SIGLE_STRING = 5;
    // --直接返回data。data是一个单独的类,data不是一个数组。是一个类
    public static final int GET_SINGLE_CLASS_DATA = 6;

    // --每页多少个
    public static int PAGE_SIZE = 20;
    // 服务器IP地址
    public static final String httpXieYiString = "http://123.56.129.29/cms/mobile/user/b.html";

    //     测试本地的ip地址
//    public static final String IP="http://192.168.1.159:8090";
//    public static final String jzlIP="http://192.168.1.159";
//    //调到兼职乐企业端职位详情需要的
//    public static final String jobIP="http://192.168.1.159/lepin_pc/";
//    public static final String jzlBaseUrl=jzlIP+"/lepin_pc/mobile";

    //线上地址
    // 服务器IP地址
    public static final String CRMIP="http://crm.happyjob.com";
    public static final String CRMBaseUrl = CRMIP + "/handinfo/service.do";
    //     服务器IP地址
    public static final String IP = "http://123.56.129.29";
    public static final String BaseUrl = IP+"/lepin/mobile";
    public static final String BaseUrl_lepin=IP+"/lepin";
    public static final String PICURL = IP+"/lepin/mobile";
    public static final String EIDTPICURL = IP+"/lepin/mobile";
    public final static String DownApkUrl = BaseUrl
            + "/happyjob/HappyJobPerson.apk";
    public static final String DOWNAPKURLAPK = "http://www.happyjob.com";
    public static final String GET_INDEX_MENU = "/person.php?act=get_index_menu";
    public static final String IMAGE_DOWN_PRE = "http://123.56.129.29/lepin_pc";
    public static final String BANNERURL = IP + "/lepin/banner.php?act=new_banner";
    public static final String SUCCESSURL=IP +"/lepin/buy.php?act=success&type=app";

    // --配置文件名称
    public static final String CONFIG = "configcrm";
    // --是否记住密码
    public static final String ISREMREMBERPASSWORD = "isRememberPassword";
    // --最近一次登录的账号
    public static final String LOGINEDUSERNAME = "loginedusername";
    // --最近一次登录者的姓名
    public static final String NICKNAME = "nickname";
    // --登录者所在的城市
    public static final String DEPTNAME = "deptName";
    // --明文密码
    public static final String PASSWORD = "password";
    // --是否记住密码
    public static final String ISREMREMBERCOMPASSWORD = "isRemembercomPassword";
    // --最近一次登录的账号
    public static final String COMLOGINEDUSERNAME = "comloginedusername";
    // --明文密码
    public static final String COMPASSWORD = "compassword";
    // --所在省
    public static final String PROVINCE = "province";
    // --所在市
    public static final String CITY = "city";
    // 下面的省市县代表是用户选择的，并不是实际的。

    /** -------------------php名字-------- */
    public static final String PERSON_PHP="person.php";
    /** -------------------访问接口的方法定义-------- */
    /**
     根据打卡id获取打卡详情
     */
    public static final String person_sign_info  = "person_sign_info";
    /** i. reg用户注册 */
    public static final String _REG = "reg";
    /** ii. invitationCode检测邀请码是否可用 */

}
