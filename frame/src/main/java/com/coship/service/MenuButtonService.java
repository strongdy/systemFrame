package com.coship.service;

import java.util.List;

import com.coship.entity.MenuButton;
import com.coship.entity.RoleButton;

public interface MenuButtonService {

	List<MenuButton> loadRoleMB(Integer roleId);
	
	List<MenuButton> loadAllMB();
	
	List<MenuButton> loadMenuButton(Integer mId,Integer rid);
	
	void deleteRoleMB(RoleButton rb) throws Exception;

	void insertRoleMBs(List<RoleButton> rbs);
}
