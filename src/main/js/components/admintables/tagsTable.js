import React, { useState } from "react";
import {Button, ListGroup, Row, Col, Spinner} from "react-bootstrap";
import {useGetTags, deleteTag,} from "../../apis/TagAPI";
import {PageSelector} from "../selectors/PageSelector";
import {toast} from "react-toastify";
import {toastErrors} from "../../services/toastService";

function TagsTable() {
    const [searchParameters, setSearchParameters] = useState({pageIndex: 0, pageSize: 5})
    const {data: tags, error, loaded} = useGetTags(searchParameters);

    function handleDeleteClick(tagId) {
        deleteTag(tagId).then((result) => {
            if (result.error) {
                console.log(JSON.stringify(result))
                toastErrors(result.error.messages);
            } else {
                setSearchParameters({...searchParameters, pageIndex: 0})
                toast.success('Тег удален');
            }
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
