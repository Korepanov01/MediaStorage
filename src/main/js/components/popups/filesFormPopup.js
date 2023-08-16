import React, {useEffect, useState} from 'react';
import {Button, Card, Form, FormGroup, Modal} from "react-bootstrap";
import {deleteFile, getFileUrl, postFile} from "../../apis/fileAPI";
import {getFileTypes} from "../../apis/fileTypeAPI";
import {toast} from "react-toastify";
import Container from "react-bootstrap/Container";

export function FilesFormPopup({show, setShow, setMedia, media}) {
    const [fileTypes, setFileTypes] = useState([]);

    const [selectedFile, setSelectedFile] = useState(null);
    const [selectedFileTypeId, setSelectedFileTypeId] = useState(null);

    useEffect(() => {
        getFileTypes().then(({data: fileTypes, error}) => {
            if (!error && fileTypes.length !== 0) {
                setSelectedFileTypeId(fileTypes[0].id);
                setFileTypes(fileTypes);
            }
        });
    }, []);

    function handleDeleteFile(fileId) {
        deleteFile(fileId).then(({error}) => {
            if (!error) {
                toast.success("Файл удалён");
                setMedia({...media, files: media.files.filter(file => file.id !== fileId)});
            }
        });
    }

    function handleAddFile() {
        postFile(selectedFile, selectedFileTypeId, media.id).then(({error, data}) => {
            if (!error) {
                toast.success("Файл добавлен");
                setMedia({...media, files: [{
                        id: data.id,
                        type: fileTypes.find(fileType => fileType.id == selectedFileTypeId).name,
                        url: getFileUrl(data.id)}, ...media.files]});
            }
        });
    }

    let filesByType = (() => {
        let filesByType= {};
        media.files.forEach(file => {
            if (!filesByType[file.type]) {
                filesByType[file.type] = [];
            }
            filesByType[file.type].push(file);
        });
        return filesByType;
    })();

    return (
        <Modal show={show} onHide={() => setShow(false)}>
            {fileTypes.length !== 0 &&
                <Modal.Header>
                    <Form className={"w-100"}>
                        <FormGroup>
                            <Form.Label>Файл</Form.Label>
                            <Form.Control type="file" onChange={(e) => setSelectedFile(e.target.files[0])}/>
                        </FormGroup>
                        <FormGroup>
                            <Form.Label>Тип</Form.Label>
                            <Form.Select
                                onChange={(e) => setSelectedFileTypeId(e.target.value)}
                                value={selectedFileTypeId}
                                aria-label="Тип">
                                {fileTypes.map((fileType) => (
                                    <option key={fileType.id} value={fileType.id}>{fileType.name}</option>
                                ))};
                            </Form.Select>
                        </FormGroup>
                        <FormGroup>
                            <Button className={"w-100"} onClick={handleAddFile}>Добавить</Button>
                        </FormGroup>
                    </Form>
                </Modal.Header>
            }
            {media.files.length !== 0 &&
                <Modal.Body>
                    {Object.keys(filesByType).map(fileType => (
                        <Container fluid key={fileType}>
                            <h3 className={"text-center"}>{fileType}</h3>
                                {filesByType[fileType].map(file => (
                                    <Card key={file.id}>
                                        <Card.Body>
                                            <Card.Img src={file.url}/>
                                        </Card.Body>
                                        <Card.Footer className={"w-100"}>
                                            <Button onClick={() => handleDeleteFile(file.id)} className={"w-100"} variant={"danger"}>Удалить</Button>
                                        </Card.Footer>
                                    </Card>
                                ))}
                        </Container>
                    ))}
                </Modal.Body>
            }
            <Modal.Footer>
                <Button className={"w-100"} variant="secondary" onClick={() => setShow(false)}>
                    Закрыть
                </Button>
            </Modal.Footer>
        </Modal>
    );
}