package com.happyjob.wagesteward.utils;

import android.os.CountDownTimer;

/**
 * @ClassName: TimerUtil
 * @author zyf
 * 
 */
public class DownTimerUtil extends CountDownTimer {

	private OnTimerListener otl;

	public DownTimerUtil(OnTimerListener otl) {
		this(otl, 10000, 1000);		
	}
	public DownTimerUtil(OnTimerListener otl,long duration) {
		this(otl, duration, 1000);		
	}
	public DownTimerUtil(OnTimerListener otl, long millisInFuture,
			long countDownInterval) {
		super(millisInFuture, countDownInterval);
		this.otl = otl;
	}

	@Override
	public void onFinish() {
		otl.onFinish();
	}

	@Override
	public void onTick(long millisUntilFinished) {
		otl.onTick(millisUntilFinished);
	}

	public interface OnTimerListener {
		public void onFinish();

		public void onTick(long millisUntilFinished);
	}

}
