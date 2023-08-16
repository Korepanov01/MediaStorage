import React, {useEffect, useState} from 'react';
import {Button, Card, Form, FormGroup, Modal} from "react-bootstrap";
import {deleteFile, getFileUrl, postFile} from "../../apis/fileAPI";
import {getFileTypes} from "../../apis/fileTypeAPI";
import {toast} from "react-toastify";
import Container from "react-bootstrap/Container";
import {Formik} from "formik";
import {object, string} from "yup";

export function FilesFormPopup({show, setShow, setMedia, media}) {
    const [fileTypes, setFileTypes] = useState([]);

    useEffect(() => {
        getFileTypes().then(({data: fileTypes, error}) => {
            if (!error && fileTypes.length !== 0) {
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

    function handleAddFile({fileTypeId, file}) {
        postFile(file, fileTypeId, media.id).then(({error, data}) => {
            if (!error) {
                toast.success("Файл добавлен");
                setMedia({...media, files: [{
                        id: data.id,
                        type: fileTypes.find(fileType => fileType.id == fileTypeId).name,
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

    const validationSchema = object({
        file: string().required("Не выбран файл")
    });

    return (
        <Modal show={show} onHide={() => setShow(false)}>
            {fileTypes.length !== 0 &&
                <Modal.Header>
                    <Formik
                        initialValues={{fileTypeId: fileTypes[0].id, file: null}}
                        onSubmit={handleAddFile}
                        validationSchema={validationSchema}
                    >
                        {({ handleChange, handleSubmit, values, errors, touched }) => (
                            <Form onSubmit={handleSubmit} className={"w-100"}>
                                <FormGroup>
                                    <Form.Label>Файл</Form.Label>
                                    <Form.Control
                                        type="file"
                                        name="file"
                                        onChange={handleChange}
                                        value={values.file}
                                        isInvalid={touched.file && errors.file}
                                    />
                                    <Form.Control.Feedback type="invalid">{errors.file}</Form.Control.Feedback>
                                </FormGroup>
                                <FormGroup>
                                    <Form.Label>Тип</Form.Label>
                                    <Form.Select
                                        name={"fileTypeId"}
                                        onChange={handleChange}
                                        value={values.fileTypeId}
                                        isInvalid={touched.fileTypeId && errors.fileTypeId}
                                    >
                                        {fileTypes.map((fileType) => (
                                            <option key={fileType.id} value={fileType.id}>{fileType.name}</option>
                                        ))};
                                    </Form.Select>
                                    <Form.Control.Feedback type="invalid">{errors.fileTypeId}</Form.Control.Feedback>
                                </FormGroup>
                                <FormGroup>
                                    <Button type={"submit"} className={"w-100"} >Добавить</Button>
                                </FormGroup>
                            </Form>
                        )}
                    </Formik>
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