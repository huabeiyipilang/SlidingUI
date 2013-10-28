package cn.kli.menuui;

import com.actionbarsherlock.app.SherlockFragment;

public class BaseFragment extends SherlockFragment {

	protected boolean onBackPressed(){
		return false;
	}
}
