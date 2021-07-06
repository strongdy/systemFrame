package com.coship.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.coship.entity.MenuButton;
import com.coship.entity.RoleButton;

@Mapper
public interface MenuButtonMapper {

	List<MenuButton> loadRoleMB(Integer roleId);

	List<MenuButton> loadAllMB();

	List<MenuButton> loadMenuButton(Map<String,Object> param);

	void deleteRoleMB(RoleButton rb);

	void insertRoleMBs(@Param("rbs")List<RoleButton> rbs);

}
