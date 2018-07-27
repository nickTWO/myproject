package com.hyt.myproject.common.dto;

import java.io.Serializable;

/**
 * @author geek
 */
public class PartnersMenusDto implements Serializable {
  private static final long serialVersionUID = -554690189820390229L;
  private Long mmid;
  private Long parentMid;
  private Long menuType;
  private String menuCode;
  private String menuName;
  private String menuNameEn;
  private String menuUrl;
  private String routerUri;
  private Long displayOrder;
  private Long isLog;
  private Long status;
  private String menuIcon;
  private String permission;

  public Long getMmid() {
    return mmid;
  }

  public void setMmid(Long mmid) {
    this.mmid = mmid;
  }

  public Long getParentMid() {
    return parentMid;
  }

  public void setParentMid(Long parentMid) {
    this.parentMid = parentMid;
  }

  public Long getMenuType() {
    return menuType;
  }

  public void setMenuType(Long menuType) {
    this.menuType = menuType;
  }

  public String getMenuCode() {
    return menuCode;
  }

  public void setMenuCode(String menuCode) {
    this.menuCode = menuCode;
  }

  public String getMenuName() {
    return menuName;
  }

  public void setMenuName(String menuName) {
    this.menuName = menuName;
  }

  public String getMenuNameEn() {
    return menuNameEn;
  }

  public void setMenuNameEn(String menuNameEn) {
    this.menuNameEn = menuNameEn;
  }

  public String getMenuUrl() {
    return menuUrl;
  }

  public void setMenuUrl(String menuUrl) {
    this.menuUrl = menuUrl;
  }

  public Long getDisplayOrder() {
    return displayOrder;
  }

  public void setDisplayOrder(Long displayOrder) {
    this.displayOrder = displayOrder;
  }

  public Long getIsLog() {
    return isLog;
  }

  public void setIsLog(Long isLog) {
    this.isLog = isLog;
  }

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

  public String getMenuIcon() {
    return menuIcon;
  }

  public void setMenuIcon(String menuIcon) {
    this.menuIcon = menuIcon;
  }

  public String getRouterUri() {
    return routerUri;
  }

  public void setRouterUri(String routerUri) {
    this.routerUri = routerUri;
  }

  public String getPermission() {
    return permission;
  }

  public void setPermission(String permission) {
    this.permission = permission;
  }

  @Override
  public String toString() {
    return "PartnersMenusDto{" +
            "mmid=" + mmid +
            ", parentMid=" + parentMid +
            ", menuType=" + menuType +
            ", menuCode='" + menuCode + '\'' +
            ", menuName='" + menuName + '\'' +
            ", menuNameEn='" + menuNameEn + '\'' +
            ", menuUrl='" + menuUrl + '\'' +
            ", displayOrder=" + displayOrder +
            ", isLog=" + isLog +
            ", status=" + status +
            ", menuIcon='" + menuIcon + '\'' +
            '}';
  }
}
