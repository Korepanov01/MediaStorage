package com.bftcom.mediastorage.data.model;

public class UserRole extends BaseModel {

    private Long roleId;

    private Long userId;

    public UserRole(Long id, Long roleId, Long userId) {
        super(id);
        this.roleId = roleId;
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
