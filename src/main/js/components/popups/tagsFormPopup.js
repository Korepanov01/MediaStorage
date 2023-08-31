import React from 'react';
import {Form, FormGroup, Modal} from "react-bootstrap";
import TagsSelector from "../selectors/tagsSelector";
import {toast} from "react-toastify";
import Title from "../decor/title";
import {addTagToMedia, removeTagFromMedia} from "../../apis/mediaAPI";

export default function TagsFormPopup({show, setShow, setMedia, media}) {

    function handleSelect(tag) {
        addTagToMedia(media.id, tag.id).then(({error}) => {
            if (!error) {
                setMedia({...media, tags: media.tags.concat([tag])});
                toast.success(Text.toastsMessages.successAddTag(tag.name));
            }
        });
    }

    function handleUnselect(deletedTag) {
        removeTagFromMedia(media.id, deletedTag.id).then(({error}) => {
            if (!error) {
                setMedia({...media, tags: media.tags.filter((tag) => tag.id !== deletedTag.id)});
                toast.success(`Тег "${deletedTag.name}" удалён`);
            }
        });
    }

    return (
        <Modal show={show} onHide={() => setShow(false)}>
            <Modal.Header>
                <Title level={4}>Изменить теги</Title>
            </Modal.Header>
            <Modal.Body>
                <Form>
                    <FormGroup>
                        <TagsSelector
                            selectedTags={media.tags}
                            onSelect={handleSelect}
                            onUnselect={handleUnselect}/>
                    </FormGroup>
                </Form>
            </Modal.Body>
        </Modal>
    );
}