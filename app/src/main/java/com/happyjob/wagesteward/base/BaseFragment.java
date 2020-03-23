package com.happyjob.wagesteward.base;

import android.view.View;
import android.view.View.OnClickListener;

import androidx.fragment.app.Fragment;


public abstract class BaseFragment extends Fragment implements OnClickListener {
	public abstract void initView(View view);

	public abstract void initData();

	public abstract void setListener();
//	public ImageLoader imageLoader =

}
