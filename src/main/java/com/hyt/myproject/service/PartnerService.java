package com.hyt.myproject.service;

import com.alibaba.fastjson.JSONObject;
import com.hyt.myproject.common.dao.PartnerDao;
import com.hyt.myproject.common.dto.PartnerDto;

import com.hyt.myproject.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Echo_ayalamih
 * @description desc.
 * @packagename com.paycloudx.server.implement.service
 * @date 16:46, 22/11/2017
 */
@Service("partnerService")
public class PartnerService {

    @Autowired
    private PartnerDao partnerDao;

    public Result<PartnerDto> getPartnerInfo(PartnerDto partnerDto) {
        Result<PartnerDto> result = new Result();
        PartnerDto partner = partnerDao.getPartnerInfo(partnerDto);
        if (null != partner) {
            result.setEntry(partner);
            result.setSuccess(true);
        }
        return result;
    }

}
