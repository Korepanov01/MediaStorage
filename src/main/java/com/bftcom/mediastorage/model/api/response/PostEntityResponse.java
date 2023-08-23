package com.bftcom.mediastorage.model.api.response;

import com.bftcom.mediastorage.model.entity.Identical;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostEntityResponse {

    private final Long id;

    public PostEntityResponse(Identical identical) {
        id = identical.getId();
    }
}
