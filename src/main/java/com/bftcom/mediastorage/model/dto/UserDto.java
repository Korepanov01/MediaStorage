package com.bftcom.mediastorage.model.dto;

import com.bftcom.mediastorage.model.entity.User;

public class UserDto extends BaseDto {

    private Long id;

    private String name;

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

    public UserDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserDto(User user) {
        this(user.getId(),user.getName());
    }
}
