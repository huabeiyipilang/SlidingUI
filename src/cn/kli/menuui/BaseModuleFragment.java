package cn.kli.menuui;

import com.actionbarsherlock.app.SherlockFragmentActivity;

import android.os.Bundle;

public class BaseModuleFragment extends BaseFragment {
	private Config mConfig;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		mConfig = Config.getInstance();
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onResume() {
		super.onResume();
		((SherlockFragmentActivity) getActivity()).getSupportActionBar().setTitle(
				mConfig.getGroupByClassName(this.getClass().getName()).name);
	}
	
	protected Config getConfig(){
		return mConfig;
	}
}
