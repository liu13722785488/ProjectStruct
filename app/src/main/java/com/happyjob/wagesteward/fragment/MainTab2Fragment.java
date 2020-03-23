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

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.guozheng.okhttputils.okhttp.CallBackUtil;
import com.guozheng.okhttputils.okhttp.OkhttpUtil;
import com.happyjob.wagesteward.R;
import com.happyjob.wagesteward.adapter.JobListAdapter;
import com.happyjob.wagesteward.base.BaseFragment;
import com.happyjob.wagesteward.bean.Job;
import com.happyjob.wagesteward.bean.SucGetJob;
import com.happyjob.wagesteward.databinding.FragmentMaintab1LayoutBinding;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;


/**
 * 通讯录
 *
 * @author HLJ
 */
public class MainTab2Fragment extends BaseFragment implements View.OnClickListener {
    private Context context;
    private RefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private JobListAdapter mJobListAdapter;
    private List<Job> mJobList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();
        View contactsLayout = inflater.inflate(
                R.layout.fragment_maintab2_layout, container, false);
        initView(contactsLayout);
        initData();
        setListener();
        return contactsLayout;
    }


    public MainTab2Fragment() {
        super();
    }

    public static MainTab2Fragment getClientFragment() {

        MainTab2Fragment clientFragment = new MainTab2Fragment();
        return clientFragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }



    @Override
    public void initView(View view) {
        mRefreshLayout=view.findViewById(R.id.refreshLayout);
        mRecyclerView=view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);  //设置方向
        mRecyclerView.setLayoutManager(layoutManager);
        //这里是设置每个item之间的高度和颜色
        DividerItemDecoration decor = new DividerItemDecoration(context, layoutManager.getOrientation());
        decor.setDrawable(ContextCompat.getDrawable(context,R.drawable.divide_hight)); //这里在就是
        mRecyclerView.addItemDecoration(decor);

        mJobList=new ArrayList<Job>();
        mJobListAdapter=new JobListAdapter(context,mJobList);
        mRecyclerView.setAdapter(mJobListAdapter);
        //下面是设置下拉刷新和底部加载的一些样式和监听
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
                refreshlayout.finishRefresh(true);
            }
        });

        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() { //上拉加载更多
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
//                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                refreshlayout.finishLoadMore(true);
                System.out.println("加载了");
            }
        });
    }

    @Override
    public void initData() {
        // 初始化
        String url="http://123.56.129.29/lepin/mobile/person.php?act=my_do_job_list&audit=1&page_num=1&page_size=20&timestamp=1584408111&uid=cd3b5bf2f58bc3b890d613de28bb2cfe&sign=5EB1BA8727141C8EE989B888E13F49BE";
        getData(url);

    }

    @Override
    public void setListener() {
        // TODO Auto-generated method stub
        // 搜索按钮
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    private void getData(String url) {
        OkhttpUtil.okHttpGet(url, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                Log.d("kwwl", response);
                if(response!=null)
                {

                    SucGetJob sucGetJob = new Gson().fromJson(response, SucGetJob.class);
                    if(sucGetJob!=null)
                    {
//                        System.out.println(sucGetJob.getData().size());
                        if(sucGetJob.getData()!=null){

                            mJobList.addAll(sucGetJob.getData());
                            mJobListAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }


    private void getImageData(String url){
        //获取图片要用okHttpGetBitmap
        OkhttpUtil.okHttpGetBitmap(url, new CallBackUtil.CallBackBitmap() {
            @Override
            public void onFailure(Call call, Exception e) {
                Log.d("call","e");
            }

            @Override
            public void onResponse(Bitmap response) {
                //TODO
                Log.d("call","success");
//                mMView.imageView.setImageBitmap(response);

            }
        });

    }
    private void postData(String url) {

        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("audit", "1");
        paramsMap.put("page_num", "1");
        paramsMap.put("page_size", "20");
        paramsMap.put("timestamp", "1584408111");
        paramsMap.put("uid", "cd3b5bf2f58bc3b890d613de28bb2cfe");
        paramsMap.put("sign", "5EB1BA8727141C8EE989B888E13F49BE");
        OkhttpUtil.okHttpPost(url, paramsMap, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
            }

            @Override
            public void onResponse(String response) {
//                mMView.textview.setText(response);
            }
        });

    }



}





