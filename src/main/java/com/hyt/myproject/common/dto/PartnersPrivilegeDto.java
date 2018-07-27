package com.hyt.myproject.common.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 操作员菜单权限
 * @author: geek
 * @datetime: 2017/12/4 下午4:43
 * @returns:
 */
public class PartnersPrivilegeDto implements Serializable {
  private static final long serialVersionUID = -8662495823853186250L;
  private Long mpid;
  private Long roleId;
  private Long menuId;
  private String name;
  private String url;
  private String icon;

  private PartnersRoleDto partnersRoleDto;
  private PartnersMenusDto partnersMenusDto;

  //-----------------
  private List<PartnersPrivilegeDto> actionmenusList;
  private List<PartnersPrivilegeDto> children;
  //-----------------

  //根据商户类型条件查找权限菜单
  private String permission;

  public Long getMpid() {
    return mpid;
  }

  public void setMpid(Long mpid) {
    this.mpid = mpid;
  }

  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

  public Long getMenuId() {
    return menuId;
  }

  public void setMenuId(Long menuId) {
    this.menuId = menuId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public PartnersMenusDto getPartnersMenusDto() {
    return partnersMenusDto;
  }

  public void setPartnersMenusDto(PartnersMenusDto partnersMenusDto) {
    this.partnersMenusDto = partnersMenusDto;
  }

  public List<PartnersPrivilegeDto> getActionmenusList() {
    return actionmenusList;
  }

  public void setActionmenusList(List<PartnersPrivilegeDto> actionmenusList) {
    this.actionmenusList = actionmenusList;
  }

  public List<PartnersPrivilegeDto> getChildren() {
    return children;
  }

  public void setChildren(List<PartnersPrivilegeDto> children) {
    this.children = children;
  }

  public PartnersRoleDto getPartnersRoleDto() {
    return partnersRoleDto;
  }

  public void setPartnersRoleDto(PartnersRoleDto partnersRoleDto) {
    this.partnersRoleDto = partnersRoleDto;
  }

  public String getPermission() {
    return permission;
  }

  public void setPermission(String permission) {
    this.permission = permission;
  }

  @Override
  public String toString() {
    return "PartnersPrivilegeDto{" +
            "mpid=" + mpid +
            ", roleId=" + roleId +
            ", menuId=" + menuId +
            ", name='" + name + '\'' +
            ", url='" + url + '\'' +
            ", icon='" + icon + '\'' +
            ", partnersRoleDto=" + partnersRoleDto +
            ", partnersMenusDto=" + partnersMenusDto +
            ", actionmenusList=" + actionmenusList +
            ", children=" + children +
            '}';
  }
}
