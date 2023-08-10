import React from 'react';
import {Button, Form, Modal} from "react-bootstrap";
import {TagsSelector} from "../selectors/TagsSelector";
import {MediaTagAPI} from "../../apis/MediaTagsAPI";

export function TagsFormPopup({show: show, onChangeShow: handleChangeShow, onTagsChange: handleTagsChange, tags: tags, mediaId: mediaId}) {

    function handleSelect(tagId) {
        MediaTagAPI.post(mediaId, tagId).then(() => {
            handleTagsChange(tags.concat([tagId]));
        });
    }

    function handleUnselect(tagId) {
        MediaTagAPI.delete(mediaId, tagId).then(() => {
            handleTagsChange(tags.filter((tag) => tag.id !== tagId));
        });
    }

    return (
        <Modal show={show} onHide={() => handleChangeShow(false)}>
            <Modal.Body>
                <Form>
                    <Form.Text>Добавить теги</Form.Text>
                    <TagsSelector
                        selectedTags={tags}
                        onSelect={handleSelect}
                        onUnselect={handleUnselect}/>
                </Form>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={() => handleChangeShow(false)}>
                    Закрыть
                </Button>
            </Modal.Footer>
        </Modal>
    );
}