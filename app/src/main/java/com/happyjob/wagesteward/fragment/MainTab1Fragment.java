package com.happyjob.wagesteward.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.happyjob.wagesteward.R;
import com.happyjob.wagesteward.adapter.StringAdapter;
import com.happyjob.wagesteward.base.BaseFragment;
import com.happyjob.wagesteward.databinding.FragmentMaintab1LayoutBinding;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;


public class MainTab1Fragment extends BaseFragment implements OnClickListener {
    private Context context;
    private RefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private StringAdapter mStringAdapter;
    List<String> list;
    private FragmentMaintab1LayoutBinding mBinding;

    private int i=0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflater的时候最好是用3个参数的。
		mBinding=FragmentMaintab1LayoutBinding.inflate(inflater);
        //这个里面没办法传mBingding.getRoot()
        View messageLayout = inflater.inflate(R.layout.fragment_maintab1_layout, container, false);
        return messageLayout;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getActivity();
        initView(view);
        initData();
    }

    public MainTab1Fragment() {
        super();

    }

    public static MainTab1Fragment getMessageFragment() {
        MainTab1Fragment messageFragment = new MainTab1Fragment();
        return messageFragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }

    @Override
    public void initView(View view) {
        mRefreshLayout = view.findViewById(R.id.refreshLayout);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRefreshLayout.setDragRate(0.5f);//显示下拉高度/手指真实下拉高度=阻尼效果
        mRefreshLayout.setReboundDuration(300);//回弹动画时长（毫秒）

        mRefreshLayout.setHeaderHeight(100);//Header标准高度（显示下拉高度

//        //设置 Header 为 Material风格
//        mRefreshLayout.setRefreshHeader(new MaterialHeader(context).setShowBezierWave(true));
//        //设置 Footer 为 球脉冲
//        mRefreshLayout.setRefreshFooter(new BallPulseFooter(context).setSpinnerStyle(SpinnerStyle.Scale));
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() { //下拉刷新
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
//                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
//                refreshlayout.finishRefresh(true);
                System.out.println("刷新了");
                clearData(refreshlayout);
            }
        });

        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() { //上拉加载更多
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
//                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
//                refreshlayout.finishLoadMore(true);
                System.out.println("加载了");
                AddData(refreshlayout);
                //这个是如果加载了次了。就取消加载更多功能。模拟的。
                i++;
                if(i==3){
                    mRefreshLayout.setEnableLoadMore(false);//设置不能加载更多了。传false
                }
            }
        });
    }


    @Override
    public void initData() {
        // 初始化
         list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("这是第" + (i + 1) + "个item");
        }
        mStringAdapter = new StringAdapter(context, list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
//      layoutManager.setOrientation(LinearLayoutManager.VERTICAL);  //设置方向
        mRecyclerView.setLayoutManager(layoutManager);//recyclerView必须设置LayoutManager,否则recyclerView显示不出来
        mRecyclerView.setAdapter(mStringAdapter);
    }

    private void clearData( RefreshLayout refreshLayout){
        list.clear();
        for (int i = 0; i < 15; i++) {
            list.add("这是刷新后的" + (i + 1) + "个item");
        }
        mStringAdapter.notifyDataSetChanged();
        refreshLayout.finishRefresh(true);//刷新成功。
    }
    private void AddData( RefreshLayout refreshLayout){
        for (int i = 0; i <10; i++) {
            list.add("这是加载后的" + (i + 1) + "个item");
        }
        mStringAdapter.notifyDataSetChanged();
        refreshLayout.finishLoadMore(true);//加载成功
    }

    @Override
    public void setListener() {


    }
}
