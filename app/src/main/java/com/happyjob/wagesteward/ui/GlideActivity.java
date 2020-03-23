package com.happyjob.wagesteward.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;
import com.happyjob.wagesteward.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GlideActivity extends Activity {




//    private ActivityGlideBinding mBinding;//用的viewBinding
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.imageView)
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mBinding = ActivityGlideBinding.inflate(getLayoutInflater());
//        setContentView(mBinding.getRoot());
        setContentView(R.layout.activity_glide);
        ButterKnife.bind(this);
//        mBinding.button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getImage();
//            }
//        });

    }

    private void getImage() {
        String url = "http://p3.pstatp.com/origin/pgc-image/15220293999066398493c24.jpg";
        //options设置一下加载图片的属性，也可以不设置。
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.loading_0) //加载的时候占位符
                .circleCrop()//图片圆角
                .override(400, 200)//设置指定图片大小
                .error(R.mipmap.ic_launcher);//下载错误显示的图片
//        Glide.with(GlideActivity.this).load(url).apply(options)
//                .into(mBinding.imageView);

        //自己封装的方法类
//        GlideUtil.load(this,url,mImageView,options);


    }

}
