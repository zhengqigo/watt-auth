package cn.fuelteam.watt.auth.authorization;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cn.fuelteam.watt.auth.service.SecurityDataService;
import cn.fuelteam.watt.auth.vo.PermissionVO;

@Component
public class SimpleFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private SecurityDataService securityDataService;

    private final static String FORMAT = "%s=%s";

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 获取请求起源路径
        HttpServletRequest request = ((FilterInvocation) object).getRequest();
        String url = request.getRequestURI();
        String method = request.getMethod();
        // String context = request.getContextPath();
        
        AntPathMatcher matcher = new AntPathMatcher();

        // 不需要权限页面
        List<String> ignored = Lists.newArrayList("/login");
        for(String str : ignored) {
            if (matcher.match(str, url)) return null;
        }

        // 获取角色列表
        List<PermissionVO> voList = securityDataService.findRoleListByUrl(url);
        // 没有权限配置为允许访问, 默认禁止resultList.add("DEFAULT_FORBIDDEN");
        if (voList == null || voList.size() == 0) return null;

        // 用来存储访问路径需要的角色或权限信息
        List<String> resultList = Lists.newArrayList();
        Map<String, String> map = Maps.newConcurrentMap();
        for (PermissionVO vo : voList) {
            if (vo == null) continue;
            if (!checkMethod(method, vo.getMethod())) continue;
            // url & method 都能匹配上
            String permitted = map.get(vo.getRoleName());
            if (permitted == null) map.put(vo.getRoleName(), vo.getPermitted());
            if (permitted != null && !permitted.equalsIgnoreCase(vo.getPermitted())) throw new IllegalArgumentException("config problem");
            if (permitted != null && permitted.equalsIgnoreCase(vo.getPermitted())) continue;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String str = String.format(FORMAT, entry.getKey(), entry.getValue());
            resultList.add(str);
        }
        if (resultList.isEmpty()) return null;
        String[] resultArray = resultList.toArray(new String[0]);
        return SecurityConfig.createList(resultArray);
    }

    private boolean checkMethod(String method, String value) {
        if (value == null || "*".equalsIgnoreCase(value)) return true;
        return (value.equalsIgnoreCase(method)) ? true : false;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
