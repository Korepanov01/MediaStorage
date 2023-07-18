package com.bftcom.mediastorage.data.model;

public class UserRole extends BaseModel {

    private long roleId;

    private long userId;

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
