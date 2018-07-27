package com.hyt.myproject.common.dao;



import com.hyt.myproject.common.dto.SafetyPolicyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SafetyPolicyDao {






    /**
    * @param
    * @Description: 获取操作用户当前操作的安全策略数据
    * @author: lhx
    * @Date: 15:35 2017/12/8
    * @return
    */
    SafetyPolicyDto getSafetyPolicyByPuidPmodule(SafetyPolicyDto record);

}