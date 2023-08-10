import React from 'react';
import {Badge, Button, Card, Form, FormGroup, Modal} from "react-bootstrap";
import {TagsSelector} from "../selectors/TagsSelector";
import {MediaTagAPI} from "../../apis/MediaTagsAPI";

export function TagsFormPopup({show: show, onChangeShow: handleChangeShow, onTagsChange: handleTagsChange, tags: tags, mediaId: mediaId}) {
    function deleteTag(tagId) {
        MediaTagAPI.delete(mediaId, tagId).then(() => {
            handleTagsChange(tags.filter((tag) => tag.id !== tagId));
        });
    }

    function addTags(tagsIds) {
        tagsIds.forEach(tagId => addTag(tagId));
    }

    function addTag(tagId) {
        MediaTagAPI.post(mediaId, tagId).then(() => {
            handleTagsChange(tags.concat([tagId]));
        });
    }

    return (
        <Modal show={show} onHide={() => handleChangeShow(false)}>
            <Modal.Header>
                <Form>
                    <Form.Text>Добавить теги</Form.Text>
                    <TagsSelector selectedTagsIds={tags.map(tag => tag.id)} onSelect={addTags}/>
                </Form>
            </Modal.Header>
            <Modal.Body>
                {tags.map(tag => (
                    <Badge key={tag.id}>
                        {tag.name}
                        <Badge bg={"danger"} onClick={() => deleteTag(tag.id)}>
                            X
                        </Badge>
                    </Badge>
                ))}
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={() => handleChangeShow(false)}>
                    Закрыть
                </Button>
            </Modal.Footer>
        </Modal>
    );
}