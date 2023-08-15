import React, {useLayoutEffect, useState} from "react";
import {Button, ListGroup, Row, Col, ButtonGroup} from "react-bootstrap";
import {deleteTag, getTags,} from "../../apis/TagAPI";
import {PageSelector} from "../selectors/PageSelector";
import {toast} from "react-toastify";
import {toastErrors} from "../../services/toastService";
import {ChangeTagFormPopup} from "../popups/changeTagFormPopup";

export default function TagsTable() {
    const [searchParameters, setSearchParameters] = useState({pageIndex: 0, pageSize: 5})
    const [tags, setTags] = useState([])

    const [showChangeTagPopup, setShowChangeTagPopup] = useState(false);
    const [selectedTag, setSelectedTag] = useState({name: "", id: 0});

    useLayoutEffect(() => {
        getTags(searchParameters).then(result => {
            if (!result.error) setTags(result.data);
        })
    }, [searchParameters]);

    function handleDeleteClick(tagId, tagName) {
        deleteTag(tagId).then((result) => {
            if (!result.error) toast.success(`Тег "${tagName}" удален`);
        });
    }

    function handleChangeTagSubmit(changedTag) {
        setTags(tags.map(tag => tag.id === changedTag.id ? changedTag : tag));
    }

    function handlePutClick(tag) {
        setSelectedTag(tag);
        setShowChangeTagPopup(true);
    }

    return (
        <>
            <ChangeTagFormPopup tag={selectedTag} show={showChangeTagPopup} onChangeShow={setShowChangeTagPopup} onSubmit={handleChangeTagSubmit}/>
            <ListGroup>
                {tags.map(tag => (
                    <ListGroup.Item key={tag.id}>
                        <Row>
                            <Col>
                                {tag.name}
                            </Col>
                            <Col className={"d-flex justify-content-end align-content-center"}>
                                <ButtonGroup>
                                    <Button variant={"warning"} onClick={() => handlePutClick(tag)}>Изменить</Button>
                                    <Button variant={"danger"} onClick={() => handleDeleteClick(tag.id, tag.name)}>Удалить</Button>
                                </ButtonGroup>
                            </Col>
                        </Row>
                    </ListGroup.Item>
                ))}
            </ListGroup>
            <PageSelector pageIndex={searchParameters.pageIndex} onPageChange={(newPageIndex) => setSearchParameters({...searchParameters, pageIndex: newPageIndex})}/>
        </>
    )
}