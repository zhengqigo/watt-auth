package cn.fuelteam.watt.auth.authorization;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class SimpleAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        // 当前用户有的权限
        Collection<? extends GrantedAuthority> authorizedList = authentication.getAuthorities();
        // 循环对比一旦发现有权限匹配即返回
        for (ConfigAttribute configAttribute : configAttributes) {
            String formatted = configAttribute.getAttribute();
            String[] array = formatted.split("=");
            for (GrantedAuthority authorized : authorizedList) {
                String authority = authorized.getAuthority();
                if (array[0].equalsIgnoreCase(authority) && array[1].equalsIgnoreCase("Y")) return;
            }
        }
        // 执行到这里说明没有匹配到应有权限
        throw new AccessDeniedException("denied");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
