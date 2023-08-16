import React, {useEffect, useState} from 'react';
import {Button, Card, Form, FormGroup, Modal} from "react-bootstrap";
import {FileAPI} from "../../apis/fileAPI";
import {getFileTypes} from "../../apis/fileTypeAPI";

export function FilesFormPopup({show, setShow, setMedia, media}) {
    const [fileTypes, setFileTypes] = useState([]);

    const [selectedFile, setSelectedFile] = useState(null);
    const [selectedFileType, setSelectedFileType] = useState("1");

    useEffect(() => {
        getFileTypes().then(({data, error}) => {
            if (!error) setFileTypes(fileTypes);
        });
    }, []);

    const filesByType = {};
    files.forEach(file => {
        if (!filesByType[file.fileType]) {
            filesByType[file.fileType] = [];
        }
        filesByType[file.fileType].push(file);
    });

    function deleteFile(id) {
        FileAPI.delete(id).then(setMedia());
    }

    function addFile() {
        let params = {fileTypeId: selectedFileType, mediaId: media}
        FileAPI.post(selectedFile, params).then(setMedia());
    }

    return (
        <Modal show={show} onHide={() => setShow(false)}>
            <Modal.Header>
                <Form>
                    <Form.Text>Добавить файл</Form.Text>
                    <FormGroup>
                        <Form.Label>Файл</Form.Label>
                        <Form.Control type="file" onChange={(e) => setSelectedFile(e.target.files[0])} />
                    </FormGroup>
                    <FormGroup>
                        <Form.Label>Тип</Form.Label>
                        <Form.Select
                            defaultValue={selectedFileType}
                            onChange={(e) => setSelectedFileType(e.target.value)}
                            aria-label="Тип">
                            {fileTypes.map((fileType) => (
                                <option key={fileType.id} value={fileType.id}>{fileType.name}</option>
                            ))};
                        </Form.Select>
                    </FormGroup>
                    <FormGroup>
                        <Button onClick={addFile}>Добавить</Button>
                    </FormGroup>
                </Form>
            </Modal.Header>
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
                <Button variant="secondary" onClick={() => setShow(false)}>
                    Закрыть
                </Button>
            </Modal.Footer>
        </Modal>
    );
}