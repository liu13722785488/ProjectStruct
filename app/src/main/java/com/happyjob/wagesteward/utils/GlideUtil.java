package com.happyjob.wagesteward.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * 此类事Glide加载图片封装的的工具类
 */
public class GlideUtil {
    /**
     * glide 加载图片的方法，
     * 第一个参数是context（可以是application,activity,或fragment）,
     * 第二个参数url是下载图片的地址
     * 第三个参数imageView 展示下载图片的imageView控件，
     * 第四个参数options设置下载图片的一些属性，例如圆形，是否缓存，大小等。
     */
    public static void load(Context context,
                            String url,
                            ImageView imageView,
                            RequestOptions options) {
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

    /**
     * glide 加载图片的方法，
     * 第一个参数是context（可以是application,activity,或fragment）,
     * 第二个参数url是下载图片的地址
     * 第三个参数imageView 展示下载图片的imageView控件，
     *相比另一个方法就是少了第四个参数
     */
    public static void load(Context context,
                            String url,
                            ImageView imageView)
     {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }
}
