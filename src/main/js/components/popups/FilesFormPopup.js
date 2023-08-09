import React from 'react';
import {Button, Card, CardGroup, Modal} from "react-bootstrap";
import {FileAPI} from "../../apis/FileAPI";

export function FilesFormPopup({show: show, onChangeShow: handleChangeShow, mediaFiles: mediaFiles, onSubmit: handleSubmit}) {

    const filesByType = {};
    mediaFiles.forEach(file => {
        if (!filesByType[file.fileType]) {
            filesByType[file.fileType] = [];
        }
        filesByType[file.fileType].push(file);
    });

    function deleteFile(id) {
        FileAPI.delete(id).then(handleSubmit());
    }

    return (
        <Modal show={show} onHide={() => handleChangeShow(false)}>
            <Modal.Body>
                {Object.keys(filesByType).map(fileType => (
                    <div key={fileType}>
                        <h3>{fileType}</h3>
                            {filesByType[fileType].map(file => (
                                <Card key={file.fileId}>
                                    <Card.Img src={file.url}/>
                                    <Card.Footer>
                                        <Button onClick={() => deleteFile(file.fileId)} className={"w-100"} variant={"danger"}>Удалить</Button>
                                    </Card.Footer>
                                </Card>
                            ))}
                    </div>
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