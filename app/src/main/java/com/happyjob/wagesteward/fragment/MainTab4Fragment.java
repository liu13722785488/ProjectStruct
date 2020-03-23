package com.happyjob.wagesteward.fragment;

/*
 _ooOoo_
 o8888888o
 88" . "88
 (| -_- |)
 O\  =  /O
 ____/`---'\____
 .'  \\|     |//  `.
 /  \\|||  :  |||//  \
 /  _||||| -:- |||||-  \
 |   | \\\  -  /// |   |
 | \_|  ''\---/''  |   |
 \  .-\__  `-`  ___/-. /
 ___`. .'  /--.--\  `. . __
 ."" '<  `.___\_<|>_/___.'  >'"".
 | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 \  \ `-.   \_ __\ /__ _/   .-` /  /
 ======`-.____`-.___\_____/___.-`____.-'======

 ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 佛祖保佑       永无BUG
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.happyjob.wagesteward.R;
import com.happyjob.wagesteward.base.BaseFragment;
import com.happyjob.wagesteward.utils.IconUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 通讯录
 *
 * @author HLJ
 */
public class MainTab4Fragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_left1)
    TextView tvLeft1;
    @BindView(R.id.tv_right1)
    TextView tvRight1;
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;

    private LayoutInflater inflater;
    private Context context;
    private Unbinder mUnbinder;
    int i=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();
        View contactsLayout = inflater.inflate(
                R.layout.fragment_maintab4_layout, container, false);
        mUnbinder = ButterKnife.bind(this, contactsLayout);
        initView(contactsLayout);
        initData();
        setListener();
        return contactsLayout;
    }


    public MainTab4Fragment() {
        super();
    }

    public static MainTab4Fragment getClientFragment() {

        MainTab4Fragment clientFragment = new MainTab4Fragment();
        return clientFragment;
    }

    @SuppressLint("NewApi")
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void initView(View view) {
    }

    @Override
    public void initData() {
        // 初始化

    }

    @Override
    public void setListener() {
        // TODO Auto-generated method stub
        // 搜索按钮
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
		switch (v.getId()){
            case R.id.button1:
                //这个是测试动态改变app的图标
                IconUtil.setIcon(context,"com.happyjob.wagesteward.RoundActivity");
                break;
            case R.id.button2:
                //这个是测试动态改变app的图标
                IconUtil.setIcon(context,"com.happyjob.wagesteward.MainActivity");
                break;
            default:
                break;

		}

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
