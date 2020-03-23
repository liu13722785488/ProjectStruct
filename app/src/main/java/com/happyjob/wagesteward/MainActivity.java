package com.happyjob.wagesteward;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.happyjob.wagesteward.base.AppContext;
import com.happyjob.wagesteward.base.AppManager;
import com.happyjob.wagesteward.base.BaseActivity;
import com.happyjob.wagesteward.fragment.MainTab1Fragment;
import com.happyjob.wagesteward.fragment.MainTab2Fragment;
import com.happyjob.wagesteward.fragment.MainTab3Fragment;
import com.happyjob.wagesteward.fragment.MainTab4Fragment;
import com.happyjob.wagesteward.utils.MyToastUtil;


public class MainActivity extends BaseActivity implements View.OnClickListener{
    public static int showFragmentid = 0;// 用来记录当前显示的fragment是第几个。0表示第一个，1表示第二个，2表示第三个。
    /**
     * 消息的Fragment
     */
    private MainTab1Fragment tab1Fragment;
    /**
     * 用于通讯的Fragment
     */
    private MainTab2Fragment tab2Fragment;
    /**
     * 用于首页Fragment
     */
    public MainTab3Fragment tab3Fragment;
    /**
     * 用于我的Fragment
     */
    public MainTab4Fragment tab4Fragment;
    /**
     * 消息布局
     */
    private View tab1Layout;
    /**
     *通讯录布局
     */
    private View tab2Layout;
    /**
     * 首页布局
     */
    private View tab3Layout;
    /**
     * 工作台布局
     */
    private View tab4Layout;
    /**
     * 我的界面布局
     */
    /**
     * 在Tab布局上显示消息图标的控件
     */
    private ImageView tab1Image;
    /**
     * 在Tab布局上显示联系人图标的控件
     */
    private ImageView tab2Image;
    /**
     * 在Tab布局上显示设置图标的控件
     */
    private ImageView tab3Image;
    /**
     * 在Tab布局上显示设置图标的控件
     */
    private ImageView tab4Image;
    /**
     * 在Tab布局上显示设置图标的控件
     */
    private ImageView tab5Image;
    /**
     * 在Tab布局上显示消息标题的控件
     */
    private TextView tab1Text;
    /**
     * 在Tab布局上显示联系人标题的控件
     */
    private TextView tab2Text;
    /**
     * 在Tab布局上显示设置标题的控件
     */
    private TextView tab3Text;
    /**
     * 在Tab布局上显示设置标题的控件
     */
    private TextView tab4Text;
    /**
     * 在Tab布局上显示设置标题的控件
     */
    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;
    private int lastIndex = -1;


    public static MainActivity instance = null;
    public MainActivity mAct;
    private MainActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        instance=this;
         AppManager.getAppManager().addActivity(this);
        initViews();
        initData();
    }

    @Override
    protected void initView() {

    }

    /**
     * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
     */
    private void initViews() {
        mAct = this;

        tab1Layout = findViewById(R.id.message_layout);
        tab2Layout = findViewById(R.id.contacts_layout);
        tab3Layout = findViewById(R.id.setting_layout);
        tab4Layout = findViewById(R.id.tab4_layout);
        tab1Image = (ImageView) findViewById(R.id.message_image);
        tab2Image = (ImageView) findViewById(R.id.contacts_image);
        tab3Image = (ImageView) findViewById(R.id.setting_image);
        tab4Image = (ImageView) findViewById(R.id.tab4_image);
        tab1Text = (TextView) findViewById(R.id.message_text);
        tab2Text = (TextView) findViewById(R.id.contacts_text);
        tab3Text = (TextView) findViewById(R.id.setting_text);
        tab4Text = (TextView) findViewById(R.id.tab4_text);
        tab1Layout.setOnClickListener(this);
        tab2Layout.setOnClickListener(this);
        tab3Layout.setOnClickListener(this);
        tab4Layout.setOnClickListener(this);
        fragmentManager =getSupportFragmentManager();
    }

    public void initData() {
        tab1Fragment = new MainTab1Fragment();
        tab2Fragment = new MainTab2Fragment();
        tab3Fragment = new MainTab3Fragment();
        tab4Fragment = new MainTab4Fragment();
        setTabSelection(0);
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.message_layout:// 消息
                // 当点击了消息tab时，选中第1个tab
                if (lastIndex != 0) {
                    setTabSelection(0);
                    lastIndex = 0;
                }
                break;
            case R.id.contacts_layout:// 通讯
                if (lastIndex != 1) {
                    setTabSelection(1);
                    lastIndex = 1;
                }
                break;
            case R.id.setting_layout:// 首页
                // 当点击了设置tab时，选中第4个tab
                if (lastIndex != 2) {
                    setTabSelection(2);
                    lastIndex = 2;
                }
                break;
            case R.id.tab4_layout:// 我的
                // 当点击了设置tab时，选中第4个tab
                if (lastIndex != 3) {
                    setTabSelection(3);
                    lastIndex = 3;
                }
                break;
            default:
                break;
        }
    }
    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index
     *            每个tab页对应的下标。0表示消息，1表示联系人，2表示动态，3表示设置。
     */
    public void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                showFragmentid = 0;
                // 当点击了消息tab时，改变控件的图片和文字颜色

                tab1Image.setImageResource(R.mipmap.xiaoxi);

                // 如果MessageFragment为空，则创建一个并添加到界面上
                if (!tab1Fragment.isAdded()) {
                    transaction.add(R.id.content, tab1Fragment);
                    transaction.show(tab1Fragment);
                } else {
                    transaction.show(tab1Fragment);
                }

                break;
            case 1:
                showFragmentid = 1;
                tab2Image.setImageResource(R.mipmap.tongxunlu);
                // 如果MessageFragment为空，则创建一个并添加到界面上
                if (!tab2Fragment.isAdded()) {
                    transaction.add(R.id.content, tab2Fragment);
                    transaction.show(tab2Fragment);
                } else {
                    transaction.show(tab2Fragment);
                }

                break;
            case 2:
                showFragmentid = 2;
                tab3Image.setImageResource(R.mipmap.shouye);
                // 如果MessageFragment为空，则创建一个并添加到界面上
                if (!tab3Fragment.isAdded()) {
                    transaction.add(R.id.content, tab3Fragment);
                    transaction.show(tab3Fragment);
                } else {
                    transaction.show(tab3Fragment);
                }
                break;
            case 3:

                tab4Image.setImageResource(R.mipmap.wode);
                // 如果MessageFragment为空，则创建一个并添加到界面上
                if (!tab4Fragment.isAdded()) {
                    transaction.add(R.id.content, tab4Fragment);
                    transaction.show(tab4Fragment);
                } else {
                    transaction.show(tab4Fragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        tab1Text.setTextColor(getResources().getColor(R.color.crm_gray));
        tab2Text.setTextColor(getResources().getColor(R.color.crm_gray));
        tab3Text.setTextColor(getResources().getColor(R.color.crm_gray));
        tab4Text.setTextColor(getResources().getColor(R.color.crm_gray));
        tab1Image.setImageResource(R.mipmap.xiaoxi1);
        tab2Image.setImageResource(R.mipmap.tongxunlu1);
        tab3Image.setImageResource(R.mipmap.shouye1);
        tab4Image.setImageResource(R.mipmap.wode1);

    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction
     *            用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (tab1Fragment != null) {
            transaction.hide(tab1Fragment);
        }
        if (tab2Fragment != null) {
            transaction.hide(tab2Fragment);
        }
        if (tab3Fragment != null) {
            transaction.hide(tab3Fragment);
        }
        if (tab4Fragment != null) {
            transaction.hide(tab4Fragment);
        }
    }

    public static MainActivity newInstance() {
        // TODO Auto-generated method stub
        if (instance == null) {
            instance = new MainActivity();
        }
        return instance;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - AppContext.exitTime) > 2000) {
           MyToastUtil.toastMsg(context, getString(R.string.repress_exit));
            AppContext.exitTime = System.currentTimeMillis();
        } else {
            try {
               AppManager.getAppManager()
                        .finishAllActivity();

            } catch (Exception e) {
                System.exit(0);
            }
        }
    }

}
