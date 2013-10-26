package cn.kli.menuui;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

public class MenuUIActivity extends SherlockFragmentActivity  implements OnClickListener, OnItemClickListener {
	private ListView mMenuView;
	private DrawerLayout mDrawLayout;
	private Config mConfig;
	private Handler mHandler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		mConfig = Config.getInstance();
		initUI();
	}
	
	
	private void initUI() {
		mMenuView = (ListView) findViewById(R.id.lv_menu_list);
		mDrawLayout = (DrawerLayout)findViewById(R.id.dl_main);
//		mDrawLayout.setBackgroundDrawable(getWallpaper());
		mDrawLayout.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
			
			
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getSupportActionBar().setDisplayHomeAsUpEnabled(false); 
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				getSupportActionBar().setDisplayHomeAsUpEnabled(true); 
			}
		});
		mMenuView.setAdapter(new GroupListAdapter());
		mMenuView.setOnItemClickListener(this);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true); 
		translateToFragment(mConfig.getModuleList().get(0));
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case android.R.id.home:
			mDrawLayout.openDrawer(mMenuView);
			break;
		}
		return true;
	}

	@Override
	public void onClick(View view) {
		switch(view.getId()){
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, final int pos,
			long arg3) {
		mDrawLayout.closeDrawer(mMenuView);
		translateToFragment(mConfig.getModuleList().get(pos));
	}

	private void translateToFragment(final Module module) {
		if (module == null) {
			return;
		}
		mHandler.postDelayed(new Runnable(){

			@Override
			public void run() {
				FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
				tx.replace(R.id.fl_main,
						Fragment.instantiate(MenuUIActivity.this, module.cls.getName()));
				tx.commit();
			}
			
		}, 500);
	}
	
	private class GroupListAdapter extends BaseAdapter{
		
		List<Module> modules = mConfig.getModuleList();
		
		@Override
		public int getCount() {
			return modules.size();
		}

		@Override
		public Object getItem(int arg0) {
			return modules.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int pos, View convertView, ViewGroup arg2) {
			ViewHolder holder = null;
			if(convertView == null){
				LayoutInflater inflater = MenuUIActivity.this.getLayoutInflater();
				convertView = inflater.inflate(R.layout.group_list_item, null);
				holder = new ViewHolder();
				holder.title = (TextView)convertView.findViewById(R.id.tv_title);
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder)convertView.getTag();
			}
			holder.title.setText(modules.get(pos).name);
			return convertView;
		}
		
		class ViewHolder{
			TextView title;
		}
	}

	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	}

	/*
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			if(!mDrawLayout.isDrawerOpen(mMenuView)){
				mDrawLayout.openDrawer(mMenuView);
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	*/
	
	
}
