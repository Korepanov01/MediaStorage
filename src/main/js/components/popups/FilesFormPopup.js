import React from 'react';
import {Button, Card, Modal} from "react-bootstrap";

export function FilesFormPopup({show: show, onChangeShow: handleChangeShow, mediaFiles: mediaFiles, onSubmit: handleSubmit}) {

    const filesByType = {};
    mediaFiles.forEach(file => {
        if (!filesByType[file.fileType]) {
            filesByType[file.fileType] = [];
        }
        filesByType[file.fileType].push(file);
    });

    return (
        <Modal show={show} onHide={() => handleChangeShow(false)}>
            <Modal.Body>
                {Object.keys(filesByType).map(fileType => (
                    <div key={fileType}>
                        <h3>{fileType}</h3>
                        {filesByType[fileType].map(file => (
                            <Card key={file.fileId}>
                                <Card.Body>
                                    <Card.Title>{file.fileId}</Card.Title>
                                    <Card.Text>{file.url}</Card.Text>
                                </Card.Body>
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