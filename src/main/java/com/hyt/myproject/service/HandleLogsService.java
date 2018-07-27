package com.hyt.myproject.service;

import com.github.pagehelper.PageHelper;

import com.hyt.myproject.common.dao.HandleLogsDao;
import com.hyt.myproject.common.dto.HandleLogsDto;
import com.hyt.myproject.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pc on 2017/12/5.
 */

@Service("SafetyService")
public class HandleLogsService {

    @Autowired
    private HandleLogsDao handleLogsDao;


    public Result<Integer> insertHandleLogs(HandleLogsDto record) {
        Result<Integer> result = new Result();
        Integer integer = handleLogsDao.insertHandleLogs(record);
        if(integer == 1){
            result.setSuccess(true);
            result.setEntry(1);
        }
        return result;
    }

}
