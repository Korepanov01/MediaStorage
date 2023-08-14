import React, { useState } from "react";
import {Table, Button, Form, Modal, ListGroup} from "react-bootstrap";
import {
    useGetTags,
    usePostTag,
    usePutTag,
    useDeleteTag,
} from "../../apis/TagAPI";

function TagsTable() {
    const {data: tags, error, loaded} = useGetTags({pageSize: 100});


    return (
        <>
            {!loaded ? (
                    <h2>Загрузка...</h2>
                ) : (
                    error ? (error.messages.map(message =><div>{message}</div>)) : (
                            <ListGroup>
                                {tags.map(tag => (
                                    <ListGroup.Item key={tag.id}>
                                        {tag.name}
                                    </ListGroup.Item>
                                ))};
                            </ListGroup>
                        )
                )
            }
        </>
    )
}

export default TagsTable;
