package com.coship.entity;

/**
 * 菜单按钮实体类
 * @author 910077
 *
 */
public class MenuButton {

	private Integer id;
	private Integer mId;
	private String buttonPath;
	private String buttonDesc;
	private boolean checked =false;
	private RoleButton roleButton;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getmId() {
		return mId;
	}
	public void setmId(Integer mId) {
		this.mId = mId;
	}
	public String getButtonPath() {
		return buttonPath;
	}
	public void setButtonPath(String buttonPath) {
		this.buttonPath = buttonPath;
	}
	public String getButtonDesc() {
		return buttonDesc;
	}
	public void setButtonDesc(String buttonDesc) {
		this.buttonDesc = buttonDesc;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public RoleButton getRoleButton() {
		return roleButton;
	}
	public void setRoleButton(RoleButton roleButton) {
		this.roleButton = roleButton;
	}
	
}
