package cn.fuelteam.watt.auth.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import cn.fuelteam.watt.auth.bean.User;

@Mapper
public interface UserDao {

    @Select(value = " SELECT u.* FROM user u WHERE u.name = #{name} ")
    public User findUserByName(String name);
}