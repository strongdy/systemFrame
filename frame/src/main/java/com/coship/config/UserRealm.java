package com.coship.config;


import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.coship.entity.MenuButton;
import com.coship.entity.Role;
import com.coship.entity.User;
import com.coship.service.MenuButtonService;
import com.coship.service.MenuService;
import com.coship.service.RoleService;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private MenuService menuService;
    
    @Autowired
    private MenuButtonService menuButtonService;
    
    @Autowired
    private RoleService roleService;

    /*
     * 认证
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取的前台username

        String username = (String) authenticationToken.getPrincipal();

        User sysUser = null;
        try {
            sysUser = menuService.loginname(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //判断对象是否有值
        if (sysUser == null) {
            return null;
        }
        
        //密码不需要我们比对，shiro会给我们比对                      //对象    //获取前台密码
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysUser, sysUser.getPwd(), ByteSource.Util.bytes(sysUser.getSalt()), getName());
        return info;

    }

    /*
     * 授权
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
    	 //获取的前台username

    	User sysUser = (User) SecurityUtils.getSubject().getPrincipal();

        //判断对象是否有值
        if (sysUser == null) {
            return null;
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<String> persList = new ArrayList<>();
        if(sysUser.getType() == 1){//管理员账户添加全部按钮权限
        	List<MenuButton> mbList = menuButtonService.loadAllMB();
        	if(!CollectionUtils.isEmpty(mbList)){
    			for(MenuButton mb : mbList){
    				if(!StringUtils.isEmpty(mb.getButtonPath())){
        				persList.add(mb.getButtonPath());
    				}
    			}
    		}
        }else{
        	List<Role> roleList = roleService.loadUserRole(sysUser.getUserid());
            if(!CollectionUtils.isEmpty(roleList)){
            	for(Role role : roleList){
            		List<MenuButton> mbList = menuButtonService.loadRoleMB(role.getRoleid());
            		if(!CollectionUtils.isEmpty(mbList)){
            			for(MenuButton mb : mbList){
            				if(!StringUtils.isEmpty(mb.getButtonPath())){
                				persList.add(mb.getButtonPath());
            				}
            			}
            		}
            	}
            }
        }
        info.addStringPermissions(persList);
        return info;
    }
}
