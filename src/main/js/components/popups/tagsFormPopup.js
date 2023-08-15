import React from 'react';
import {Button, Form, Modal} from "react-bootstrap";
import {TagsSelector} from "../selectors/tagsSelector";
import {deleteMediaTag, postMediaTag} from "../../apis/mediaTagsAPI";

export function TagsFormPopup({show: show, onChangeShow: handleChangeShow, onTagsChange: handleTagsChange, tags: tags, mediaId: mediaId}) {

    function handleSelect(tagId) {
        postMediaTag(mediaId, tagId).then(({data, error}) => {
            if (!error) handleTagsChange(tags.concat([tagId]));
        });
    }

    function handleUnselect(tagId) {
        deleteMediaTag(mediaId, tagId).then(({data, error}) => {
            if (!error) handleTagsChange(tags.filter((tag) => tag.id !== tagId));
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