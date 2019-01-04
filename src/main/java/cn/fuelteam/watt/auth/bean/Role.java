package cn.fuelteam.watt.auth.bean;

public class Role {
    
    private Long id;
    
    private String name;
    
    private String remark;

    public Role() {}

    public Role(Long id, String name, String remark) {
        super();
        this.id = id;
        this.name = name;
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setDescribe(String remark) {
        this.remark = remark;
    }
}
