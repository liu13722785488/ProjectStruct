package com.happyjob.wagesteward.bean;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Filename: SucGetJob.java <br>
 * <p>
 * Description: 卡号信息<br>
 *
 * @author: HLJ <br>
 * @version: 1.0 <br>
 * @Createtime: 2015-5-27 <br>
 * @Copyright: Copyright (c)2015 by HLJ <br>
 */

public class SucGetJob implements Serializable {


    private String result_code;
    private String result_msg;
    private String page_size;
    private String page_num;
    private String flag;
    private ArrayList<Job> data;

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getResult_msg() {
        return result_msg;
    }

    public void setResult_msg(String result_msg) {
        this.result_msg = result_msg;
    }

    public String getPage_size() {
        return page_size;
    }

    public void setPage_size(String page_size) {
        this.page_size = page_size;
    }

    public String getPage_num() {
        return page_num;
    }

    public void setPage_num(String page_num) {
        this.page_num = page_num;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public  ArrayList<Job> getData() {
        return data;
    }

    public void setData(ArrayList<Job> data) {
        this.data = data;
    }
}
