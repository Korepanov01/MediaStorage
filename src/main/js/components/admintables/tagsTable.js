import React, { useState } from "react";
import {Table, Button, Form, Modal, ListGroup} from "react-bootstrap";
import {
    useGetTags,
    usePostTag,
    usePutTag,
    useDeleteTag,
} from "../../apis/TagAPI";
import {PageSelector} from "../selectors/PageSelector";

function TagsTable() {
    const [searchParameters, setSearchParameters] = useState({pageIndex: 0, pageSize: 5})
    const {data: tags, error, loaded} = useGetTags(searchParameters);

    return (
        <>
            {!loaded ? (
                    <h2>Загрузка...</h2>
                ) : (
                    error ? (error.messages.map(message =><div>{message}</div>)) : (
                        <>
                            <ListGroup>
                                {tags.map(tag => (
                                    <ListGroup.Item key={tag.id}>
                                        {tag.name}
                                    </ListGroup.Item>
                                ))}
                            </ListGroup>
                            <PageSelector pageIndex={searchParameters.pageIndex} onPageChange={(newPageIndex) => setSearchParameters({...searchParameters, pageIndex: newPageIndex})}/>
                        </>
                        )
                )
            }
        </>
    )
}

export default TagsTable;
