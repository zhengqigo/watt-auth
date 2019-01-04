package cn.fuelteam.watt.auth.authentication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cn.fuelteam.watt.auth.bean.Role;
import cn.fuelteam.watt.auth.bean.User;
import cn.fuelteam.watt.auth.service.SecurityDataService;

@Service
public class SimpleUserDetailsService implements UserDetailsService {

    @Autowired
    private SecurityDataService securityDataService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = securityDataService.findUserByName(username);
        List<Role> roleList = securityDataService.findRoleListByUserId(user.getId());
        return new SimpleUserDetails(user, roleList);
    }
}
