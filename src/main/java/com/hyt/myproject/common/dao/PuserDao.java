package com.hyt.myproject.common.dao;


import com.hyt.myproject.common.dto.PartnersUserDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 操作员数据操作接口
 * @author: geek
 * @datetime: 2017/12/6 上午10:04
 * @returns:
 */
@Mapper
@Repository
public interface PuserDao {


    /**
     * @Description: 根据邮箱得到操作员信息
     * @author: geek
     * @datetime: 2017/12/6 上午10:05
     * @returns:
     */
    PartnersUserDto getPuserInfoByEmail(PartnersUserDto partnersUserDto);

    /**
        * @param
        * @Description: 查询操作员信息
        * @autohr: hyt
        * @datatime: 2017/12/6 16:33
        * @return
        */
    PartnersUserDto getPartnerUserInfo(PartnersUserDto partnersUserDto);

    /**
        * @param
        * @Description: 根据puid更新操作员信息
        * @autohr: hyt
        * @datatime: 2017/12/7 10:54
        * @return
        */
    Integer updatePartnerUserByPuid(PartnersUserDto partnerUserDto);

}
