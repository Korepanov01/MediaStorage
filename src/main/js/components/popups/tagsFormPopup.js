import React from 'react';
import {Form, Modal} from "react-bootstrap";
import {TagsSelector} from "../selectors/tagsSelector";
import {deleteMediaTag, postMediaTag} from "../../apis/mediaTagsAPI";

export function TagsFormPopup({show, setShow, setTags, tags, mediaId}) {

    function handleSelect(tag) {
        postMediaTag(mediaId, tag.id).then(({error}) => {
            if (!error) setTags(tags.concat([tag]));
        });
    }

    function handleUnselect(deletedTag) {
        deleteMediaTag(mediaId, deletedTag.id).then(({error}) => {
            if (!error) setTags(tags.filter((tag) => tag.id !== deletedTag.id));
        });
    }

    return (
        <Modal show={show} onHide={() => setShow(false)}>
            <Modal.Header>
                <h1 className={"text-center w-100"}>Изменить теги</h1>
            </Modal.Header>
            <Modal.Body>
                <Form>
                    <TagsSelector
                        selectedTags={tags}
                        onSelect={handleSelect}
                        onUnselect={handleUnselect}/>
                </Form>
            </Modal.Body>
        </Modal>
    );
}