package com.hyt.myproject.common.dao;


import com.hyt.myproject.common.dto.PartnersMessageDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PartnersMessageDao {

    PartnersMessageDto getPartnersMessageInfo(PartnersMessageDto record);

    Integer updatePartnerMessage(PartnersMessageDto record);

}