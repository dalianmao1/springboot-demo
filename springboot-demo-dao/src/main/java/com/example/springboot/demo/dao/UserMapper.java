package com.example.springboot.demo.dao;

import com.example.springboot.demo.model.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author zhaoyongqiang
 * @date 2021/7/1
 * @desc
 */
@Mapper
public interface UserMapper {

    /**
     * 通过手机号查询
     *
     * @param mobile
     * @return
     */
    @Select("select * from t_user where mobile = #{mobile,jdbcType=VARCHAR}")
    UserDO selectByMobile(@Param("mobile") String mobile);
}
