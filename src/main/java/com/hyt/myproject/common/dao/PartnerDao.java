package com.hyt.myproject.common.dao;



import com.hyt.myproject.common.dto.PartnerDto;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

@Mapper
public interface PartnerDao {


    /**
    * @param
    * @Description: 商户信息
    * @author: hyt
    * @datatime: 2017/12/6 15:35
    * @return
    */
    PartnerDto getPartnerInfo(PartnerDto partnerDto);


}
