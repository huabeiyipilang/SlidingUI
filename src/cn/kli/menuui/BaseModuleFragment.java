package cn.kli.menuui;



import com.actionbarsherlock.app.SherlockActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class BaseModuleFragment extends Fragment {
	private Config mConfig;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		mConfig = Config.getInstance();
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onResume() {
		super.onResume();
		((SherlockActivity) getActivity()).getSupportActionBar().setTitle(
				mConfig.getGroupByClassName(this.getClass().getName()).name);
	}
	
	protected Config getConfig(){
		return mConfig;
	}
}
