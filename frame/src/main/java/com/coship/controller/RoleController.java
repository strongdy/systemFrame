package com.coship.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coship.entity.DataGridView;
import com.coship.entity.MenuButton;
import com.coship.entity.Role;
import com.coship.entity.RoleButton;
import com.coship.entity.RoleMenu;
import com.coship.service.MenuButtonService;
import com.coship.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("role")
public class RoleController {
	private Logger logger = LoggerFactory.getLogger(RoleController.class);
	
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private MenuButtonService menuButtonService;
    
    @RequestMapping("loadAllRole")
    @ResponseBody
    public Object loadAllRole(Role role, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Role> roles = roleService.loadAllRole(role);
        PageInfo pageInfo = new PageInfo(roles);
        Map<String, Object> tableData = new HashMap<String, Object>();
        //这是layui要求返回的json数据格式
        tableData.put("code", 0);
        tableData.put("msg", "");
        //将全部数据的条数作为count传给前台（一共多少条）
        tableData.put("count", pageInfo.getTotal());
        //将分页后的数据返回（每页要显示的数据）
        tableData.put("data", pageInfo.getList());
        return tableData;
    }

    @RequestMapping("insertRole")
    @ResponseBody
    public String insertRole(Role role) {
        int i = roleService.insertRole(role);
        if (i == 1) {
            return "添加成功";
        } else {
            return "添加失败";
        }
    }

    @RequestMapping("updateRole")
    @ResponseBody
    public String updateRole(Role role) {
        int i = roleService.updateRole(role);
        if (i == 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

    @RequestMapping("deleteRole")
    @ResponseBody
    public String deleteRole(Integer roleid) {
        roleService.deleteRoleId(roleid);
        roleService.deleteRoleUserId(roleid);
        roleService.deleteRoleMenuId(roleid);
        try {
        	RoleButton rb = new RoleButton();
        	rb.setrId(roleid);
			menuButtonService.deleteRoleMB(rb);
		} catch (Exception e) {
			logger.error("数据库异常", e);
			return "数据库异常";
		}
        return "删除成功";

    }
    //加载角色管理分配菜单
    @RequestMapping("initRoleMenuTreeJson")
    @ResponseBody
    public DataGridView initRoleMenuTreeJson(Integer roleid) {
        return roleService.initRoleMenuTreeJson(roleid);
    }
    //保存角色和菜单的关系
    @RequestMapping("saveRoleMenu")
    @ResponseBody
    public Object saveRoleMenu(RoleMenu roleMenu){

        roleService.saveRoleMenu(roleMenu);
        return "分配成功";
    }
    
    @RequestMapping(value="selectMenuBtns",method =RequestMethod.POST )
    @ResponseBody
    public Object selectMenuBtns(Integer mid,Integer rid){
    	List<MenuButton> btns = menuButtonService.loadMenuButton(mid,rid);
    	if(!CollectionUtils.isEmpty(btns)){
    		for(MenuButton mb : btns){
    			if(null!=mb.getRoleButton()){
    				mb.setChecked(true);
    			}else{
    				mb.setChecked(false);
    			}
    		}
    	}
    	return btns;
    }
    
    @RequestMapping(value="saveMenuBtn",method =RequestMethod.POST )
    @ResponseBody
    public String saveMenuBtn(@RequestParam(value="mbIds[]",defaultValue="") List<Integer> mbIds,Integer rid,Integer mid){
    	List<MenuButton> mbInDB = menuButtonService.loadRoleMB(rid);
    	if(!CollectionUtils.isEmpty(mbInDB)){
    		try {
    			RoleButton rb = new RoleButton();
    			rb.setrId(rid);
    			rb.setmId(mid);
				menuButtonService.deleteRoleMB(rb);
			} catch (Exception e) {
				logger.error("数据库异常", e);
				return "数据库异常";
			}
    	}
    	List<RoleButton> roleButtons=new ArrayList<>();
    	if(!CollectionUtils.isEmpty(mbIds)){
    		for(int i =0;i<mbIds.size();i++){
    			RoleButton rb = new RoleButton();
    			rb.setrId(rid);
    			rb.setMbId(mbIds.get(i));
    			rb.setmId(mid);
    			roleButtons.add(rb);
    		}
    	}
    	menuButtonService.insertRoleMBs(roleButtons);
    	return "分配成功";
    }
}
