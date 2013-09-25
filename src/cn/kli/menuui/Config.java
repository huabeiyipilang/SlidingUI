package cn.kli.menuui;

import java.util.ArrayList;
import java.util.List;

public class Config {
	private static Config sConfig;
	
	private List<Module> mModuleList = new ArrayList<Module>();
	
	private Config(){
		
	}
	
	public static Config getInstance(){
		if(sConfig == null){
			sConfig = new Config();
		}
		return sConfig;
	}
	
	public List<Module> getModuleList(){
		return mModuleList;
	}
	
	public Module getGroupByClassName(String name){
		for(Module group : mModuleList){
			if(group.cls.getName().equals(name)){
				return group;
			}
		}
		return null;
	}

	public void addModule(Module module){
		mModuleList.add(module);
	}
	
	//TODO: config change listener
}
