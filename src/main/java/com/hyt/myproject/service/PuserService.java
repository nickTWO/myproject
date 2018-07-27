package com.hyt.myproject.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.myproject.common.dao.PuserDao;
import com.hyt.myproject.common.dto.PartnersUserDto;
import com.hyt.myproject.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 操作员service实现类
 * @author: geek
 * @datetime: 2017/12/6 上午10:03
 * @returns:
 */
@Service("puserService")
public class PuserService {

    @Autowired
    private PuserDao puserDao;

    /**
     * @param email
     * @Description: 根据登陆邮箱得到操作员信息
     * @author: geek
     * @datetime: 2017/12/4 下午1:22
     * @returns:
     */
       public Result<PartnersUserDto> getPuserInfoByEmail(String email) {
        Result<PartnersUserDto> result = new Result();
        PartnersUserDto userDto = new PartnersUserDto();
        userDto.setEmail(email);
        PartnersUserDto partnersUserDto = puserDao.getPuserInfoByEmail(userDto);
        if (partnersUserDto != null) {
            result.setEntry(partnersUserDto);
            result.setSuccess(true);
        }
        return result;
    }



    /**
        * @param
        * @Description: 查询操作员信息
        * @autohr: hyt
        * @datatime: 2017/12/6 16:36
        * @return
        */
    public Result<PartnersUserDto> getPartnerUserInfo(PartnersUserDto partnersUserDto) {
        Result<PartnersUserDto> result = new Result();
        PartnersUserDto partnerUserInfo = puserDao.getPartnerUserInfo(partnersUserDto);
        if(partnerUserInfo != null){
            result.setEntry(partnerUserInfo);
            result.setSuccess(true);
        }
        return result;
    }



    /**
     * @param
     * @Description: 根据puid更新操作员信息
     * @autohr: hyt
     * @datatime: 2017/12/7 10:52
     * @return
     */
    public Result<Integer> updatePartnerUserByPuid(PartnersUserDto partnerUserDto){
        Result<Integer> result = new Result();
        Integer updateInteger = puserDao.updatePartnerUserByPuid(partnerUserDto);
        if(updateInteger == 1){
            result.setEntry(updateInteger);
            result.setSuccess(true);
        }
        return result;
    }


}
