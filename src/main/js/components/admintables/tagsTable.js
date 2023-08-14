import React, { useState } from "react";
import {Table, Button, Form, Modal, ListGroup, Row, Col} from "react-bootstrap";
import {
    useGetTags,
    usePostTag,
    usePutTag,
    useDeleteTag, TagAPI,
} from "../../apis/TagAPI";
import {PageSelector} from "../selectors/PageSelector";

function TagsTable() {
    const [searchParameters, setSearchParameters] = useState({pageIndex: 0, pageSize: 5})
    const {data: tags, error, loaded} = useGetTags(searchParameters);

    function handleDeleteClick(tagId) {
        TagAPI.delete(tagId).then(() => setSearchParameters({...searchParameters, pageIndex: 0}));
    }

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
                                        <Row>
                                            <Col>
                                                {tag.name}
                                            </Col>
                                            <Col className={"d-flex justify-content-end align-content-center"}>
                                                <Button variant={"danger"} onClick={() => handleDeleteClick(tag.id)}>Удалить</Button>
                                            </Col>
                                        </Row>
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
