package com.coship.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.entity.MenuButton;
import com.coship.entity.RoleButton;
import com.coship.mapper.MenuButtonMapper;
import com.coship.service.MenuButtonService;

@Service
@Transactional
public class MenuButtonServiceImpl implements MenuButtonService{
	
	@Resource
    private MenuButtonMapper menuButtonMapper;

	@Override
	public List<MenuButton> loadRoleMB(Integer roleId) {
		return menuButtonMapper.loadRoleMB(roleId);
	}

	@Override
	public List<MenuButton> loadAllMB() {
		return menuButtonMapper.loadAllMB();
	}

	@Override
	public List<MenuButton> loadMenuButton(Integer mId,Integer rid) {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("mid", mId);
		paramMap.put("rid", rid);
		return menuButtonMapper.loadMenuButton(paramMap);
	}

	@Override
	public void deleteRoleMB(RoleButton rb)throws Exception  {
		menuButtonMapper.deleteRoleMB(rb);
	}

	@Override
	public void insertRoleMBs(List<RoleButton> rbs) {
		menuButtonMapper.insertRoleMBs(rbs);		
	}
}
