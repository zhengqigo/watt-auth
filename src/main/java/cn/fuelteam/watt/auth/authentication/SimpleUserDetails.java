package cn.fuelteam.watt.auth.authentication;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import cn.fuelteam.watt.auth.bean.Role;
import cn.fuelteam.watt.auth.bean.User;

public class SimpleUserDetails extends User implements UserDetails {

    private static final long serialVersionUID = -713675596248887512L;

    // 用户角色列表
    private List<Role> roleList = null;

    public SimpleUserDetails(User user, List<Role> roleList) {
        super(user);
        this.roleList = roleList;
    }

    // 获取用户角色列表
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roleList = getRoleList();
        if (roleList == null) return AuthorityUtils.commaSeparatedStringToAuthorityList("");
        StringBuilder sb = new StringBuilder("");
        for (Role role : roleList) {
            sb.append(",").append(role.getName());
        }
        String authorities = sb.deleteCharAt(0).toString();
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getName();
    }

    // 判断账号是否已经过期,默认没有过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 判断账号是否被锁定,默认没有锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 判断信用凭证是否过期,默认没有过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 判断账号是否可用,默认可用
    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
