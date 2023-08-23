package com.bftcom.mediastorage.api.controller.interfaces;

import com.bftcom.mediastorage.service.CrudService;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

public interface GetAllController <
        ListItemDto,
        Entity> {

    @GetMapping
    default List<ListItemDto> getAll() {
        return getMainService()
                .findAll()
                .stream()
                .map(this::convertToListItemDto)
                .collect(Collectors.toList());
    }

    ListItemDto convertToListItemDto(Entity entity);

    CrudService<Entity> getMainService();
}