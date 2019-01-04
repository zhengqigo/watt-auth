package cn.fuelteam.watt.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import cn.fuelteam.watt.auth.bean.Permission;

@Mapper
public interface PermissionDao {

    // 根据用户ID获取资源权限列表
    @Select(value = " SELECT * FROM permission p LEFT JOIN permission_role pr ON p.id = pr.permission_id "
            + " LEFT JOIN role r ON pr.role_id = r.id LEFT JOIN user_role ur ON r.id = ur.role_id "
            + " LEFT JOIN user u ON ur.user_id = u.id WHERE u.id = #{userId} ")
    public List<Permission> findPermissionListByUserId(Long userId);

    // 根据资源路径获取资源权限列表
    @Select(value = " SELECT * FROM permission p WHERE p.url = #{url} ")
    public List<Permission> findPermissionListByUrl(String url);
}
