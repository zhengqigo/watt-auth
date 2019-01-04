package cn.fuelteam.watt.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import cn.fuelteam.watt.auth.bean.Role;
import cn.fuelteam.watt.auth.vo.PermissionVO;

@Mapper
public interface RoleDao {

    // 根据用户ID获取角色列表
    @Select(value = "SELECT r.* FROM `role` r LEFT JOIN `user_role` ur ON r.id = ur.role_id LEFT JOIN `user` u ON ur.user_id = u.id WHERE u.id = #{userId}")
    public List<Role> findRoleListByUserId(Long userId);

    // 根据资源路径获取角色列表
    @Select(value = " SELECT p.url,p.method, r.id roleId, r.name roleName, pr.permitted FROM role r LEFT JOIN permission_role pr ON r.id = pr.role_id LEFT JOIN permission p ON pr.permission_id = p.id WHERE p.url = #{url} ")
    public List<PermissionVO> findRoleListByUrl(String url);
}
