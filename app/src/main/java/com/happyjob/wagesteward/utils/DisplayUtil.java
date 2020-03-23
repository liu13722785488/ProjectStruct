package com.happyjob.wagesteward.utils;

import android.content.Context;

/**
 * Filename: DisplayUtil.java <br>
 * 
 * Description: <br>
 * 
 * @author: HLJ <br>
 * @version: 1.0 <br>
 * @Createtime: 2016-1-5 <br>
 * 
 * @Copyright: Copyright (c)2016 by HLJ <br>
 * 
 */

public class DisplayUtil {
	public static int dip2px(Context context, float f) {
		return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
	}
}
