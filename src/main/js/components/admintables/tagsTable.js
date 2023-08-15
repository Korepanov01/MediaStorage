import React, { useState } from "react";
import {Button, ListGroup, Row, Col, Spinner} from "react-bootstrap";
import {useGetTags, deleteTag,} from "../../apis/TagAPI";
import {PageSelector} from "../selectors/PageSelector";
import {toast} from "react-toastify";
import {toastErrors} from "../../services/toastService";
import {putRequest} from "../../apis/baseApi";
import {ChangeTagFormPopup} from "../popups/changeTagFormPopup";

function TagsTable() {
    const [searchParameters, setSearchParameters] = useState({pageIndex: 0, pageSize: 5})
    const {data: tags, error, loaded} = useGetTags(searchParameters);

    const [showChangeTagPopup, setShowChangeTagPopup] = useState(false);
    const [selectedTag, setSelectedTag] = useState({name: "", id: 0});

    function handleDeleteClick(tagId, tagName) {
        deleteTag(tagId).then((result) => {
            if (result.error) {
                toastErrors(result.error.messages);
            } else {
                toast.success(`Тег "${tagName}" удален`);
            }
        });
    }

    function handleChangeTagSubmit(tag) {
        setSearchParameters(searchParameters);
    }

    function handlePutClick(tag) {
        setSelectedTag(tag);
        setShowChangeTagPopup(true);
    }

    return (
        <>
            <ChangeTagFormPopup tag={selectedTag} show={showChangeTagPopup} onChangeShow={setShowChangeTagPopup} onSubmit={handleChangeTagSubmit}/>
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
                                                <Button variant={"warning"} onClick={() => handlePutClick(tag)}>Изменить</Button>
                                                <Button variant={"danger"} onClick={() => handleDeleteClick(tag.id, tag.name)}>Удалить</Button>
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
