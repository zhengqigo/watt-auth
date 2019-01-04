package cn.fuelteam.watt.auth.vo;

import java.io.Serializable;

public class PermissionVO implements Serializable{
    
    private static final long serialVersionUID = -4481764937239014018L;

    private String url;
    
    private String method;
    
    private Long roleId;
    
    private String roleName;
    
    private String permitted;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPermitted() {
        return permitted;
    }

    public void setPermitted(String permitted) {
        this.permitted = permitted;
    }
}
