package cn.kli.menuui;

import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

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
				mCurrentFragment = (BaseFragment)Fragment.instantiate(BaseActivity.this, clsName);
				tx.replace(R.id.fl_main, mCurrentFragment);
				tx.commit();
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
