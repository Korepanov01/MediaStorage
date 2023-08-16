import React from 'react';
import {Form, Modal} from "react-bootstrap";
import {TagsSelector} from "../selectors/tagsSelector";
import {deleteMediaTag, postMediaTag} from "../../apis/mediaTagsAPI";
import {toast} from "react-toastify";

export function TagsFormPopup({show, setShow, setMedia, media}) {

    function handleSelect(tag) {
        postMediaTag(media.id, tag.id).then(({error}) => {
            if (!error) {
                setMedia({...media, tags: media.tags.concat([tag])});
                toast.success(`Тег "${tag.name}" добавлен`);
            }
        });
    }

    function handleUnselect(deletedTag) {
        deleteMediaTag(media.id, deletedTag.id).then(({error}) => {
            if (!error) {
                setMedia({...media, tags: media.tags.filter((tag) => tag.id !== deletedTag.id)});
                toast.success(`Тег "${deletedTag.name}" удалён`);
            }
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
                        selectedTags={media.tags}
                        onSelect={handleSelect}
                        onUnselect={handleUnselect}/>
                </Form>
            </Modal.Body>
        </Modal>
    );
}