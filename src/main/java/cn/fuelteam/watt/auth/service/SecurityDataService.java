package cn.fuelteam.watt.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fuelteam.watt.auth.bean.Permission;
import cn.fuelteam.watt.auth.bean.Role;
import cn.fuelteam.watt.auth.bean.User;
import cn.fuelteam.watt.auth.dao.PermissionDao;
import cn.fuelteam.watt.auth.dao.RoleDao;
import cn.fuelteam.watt.auth.dao.UserDao;
import cn.fuelteam.watt.auth.vo.PermissionVO;

@Service
public class SecurityDataService {
    
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private PermissionDao permissionDao;

	public User findUserByName(String name) {
		return userDao.findUserByName(name);
	}

	public List<Role> findRoleListByUserId(Long userId) {
		return roleDao.findRoleListByUserId(userId);
	}

	public List<PermissionVO> findRoleListByUrl(String url) {
		return roleDao.findRoleListByUrl(url);
	}

	public List<Permission> findPermissionListByUserId(Long userId) {
		return permissionDao.findPermissionListByUserId(userId);
	}

	public List<Permission> findPermissionListByUrl(String url) {
		return permissionDao.findPermissionListByUrl(url);
	}
}
