package cn.kli.menuui;

import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;

import com.actionbarsherlock.app.SherlockFragmentActivity;

public class BaseActivity extends SherlockFragmentActivity {

	private BaseFragment mCurrentFragment;

	protected void translateToFragment(final String clsName) {
		if (TextUtils.isEmpty(clsName)) {
			return;
		}
		new Thread(){

			@Override
			public void run() {
				super.run();
				Looper.prepare();
				FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
				BaseFragment newFragment = (BaseFragment)Fragment.instantiate(BaseActivity.this, clsName);
				if(mCurrentFragment == null || !newFragment.getClass().getName().equals(mCurrentFragment.getClass().getName())){
					mCurrentFragment = newFragment;
					tx.replace(R.id.fl_main, mCurrentFragment);
					tx.commit();
				}
			}
			
		}.start();
	}

	@Override
	public void onBackPressed() {
		if(mCurrentFragment.onBackPressed()){
			return;
		}else{
			super.onBackPressed();
		}
	}
	
	
}
