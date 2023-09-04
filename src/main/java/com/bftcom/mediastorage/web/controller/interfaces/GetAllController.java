package com.bftcom.mediastorage.web.controller.interfaces;

import com.bftcom.mediastorage.service.CrudService;
import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

public interface GetAllController <
        ListItemDto,
        Entity> {

    @Transactional(readOnly = true)
    @GetMapping
    default List<ListItemDto> getAll() {
        return getMainService()
                .findAll()
                .stream()
                .map(this::convertToListItemDto)
                .collect(Collectors.toList());
    }

    ListItemDto convertToListItemDto(@NonNull Entity entity);

    CrudService<Entity> getMainService();
}