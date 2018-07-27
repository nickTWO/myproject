package com.hyt.myproject.common.dao;

import com.hyt.myproject.common.dto.HandleLogsDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pc on 2017/12/5.
 */
@Mapper
public interface HandleLogsDao {

    /**
        * @param
        * @Description: 添加操作记录
        * @autohr: hyt
        * @datatime: 2017/12/11 14:11
        * @return
        */
    int insertHandleLogs(HandleLogsDto record);
}
