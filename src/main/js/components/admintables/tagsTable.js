import React, { useState } from "react";
import {Table, Button, Form, Modal, ListGroup, Row, Col, Spinner} from "react-bootstrap";
import {
    useGetTags,
    usePostTag,
    usePutTag,
    useDeleteTag, TagAPI,
} from "../../apis/TagAPI";
import {PageSelector} from "../selectors/PageSelector";
import {toast} from "react-toastify";

function TagsTable() {
    const [searchParameters, setSearchParameters] = useState({pageIndex: 0, pageSize: 5})
    const {data: tags, error, loaded} = useGetTags(searchParameters);

    function handleDeleteClick(tagId) {
        TagAPI.delete(tagId).then(() => {
            setSearchParameters({...searchParameters, pageIndex: 0})
            toast.success('Тег удален');
        });
    }

    return (
        <>
            {!loaded ? (
                <Spinner animation="border"/>
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
