package com.hyt.myproject.common.dao;



import com.hyt.myproject.common.dto.PartnersPrivilegeDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 操作员权限类
 * @author: geek
 * @datetime: 2017/12/6 下午2:28
 * @returns:
 */
@Mapper
@Repository
public interface PartnersPrivilegeDao {

    /**
     * @Description: 根据角色ID得到权限
     * @author: geek
     * @datetime: 2017/12/6 下午2:30
     * @returns:
     */
    List<PartnersPrivilegeDto> getPartnersPrivilegeByRoleId(PartnersPrivilegeDto partnersPrivilegeDto);
}
