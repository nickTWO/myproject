package com.hyt.myproject.service;


import com.hyt.myproject.common.dao.PartnersPrivilegeDao;
import com.hyt.myproject.common.dto.PartnersPrivilegeDto;
import com.hyt.myproject.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("partnersPrivilegeService")
public class PartnersPrivilegeService  {

    @Autowired
    private PartnersPrivilegeDao partnersPrivilegeDao;

    /**
     * @param partnersPrivilegeDto
     * @Description: 获取角色下菜单
     * @author: geek
     * @datetime: 2017/12/4 下午4:55
     * @returns:
     */
    public Result<List<PartnersPrivilegeDto>> getPartnersPrivilegeByRoleId(PartnersPrivilegeDto partnersPrivilegeDto) {
        Result<List<PartnersPrivilegeDto>> result = new Result();
        List<PartnersPrivilegeDto> partnersPrivilegeList = partnersPrivilegeDao.getPartnersPrivilegeByRoleId(partnersPrivilegeDto);
        if (partnersPrivilegeList != null) {
            result.setEntry(partnersPrivilegeList);
            result.setSuccess(true);
        }
        return result;
    }
}
