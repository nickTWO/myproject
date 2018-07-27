package com.hyt.myproject.common.dto;

import java.io.Serializable;

/**
 * @Description: 操作员角色
 * @author: geek
 * @datetime: 2017/12/4 下午4:41
 * @returns:
 */
public class PartnersRoleDto implements Serializable {


  private static final long serialVersionUID = -229896291547486334L;
  private Long mrid;
  private Long partnerUserId;
  private String roleName;

  public Long getMrid() {
    return mrid;
  }

  public void setMrid(Long mrid) {
    this.mrid = mrid;
  }

  public Long getPartnerUserId() {
    return partnerUserId;
  }

  public void setPartnerUserId(Long partnerUserId) {
    this.partnerUserId = partnerUserId;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }
}
