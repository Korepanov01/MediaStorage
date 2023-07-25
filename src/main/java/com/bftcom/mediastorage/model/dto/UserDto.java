package com.bftcom.mediastorage.model.dto;

import com.bftcom.mediastorage.model.entity.User;

public class UserDto {

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

    static public UserDto ConvertToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName()
        );
    }
}
