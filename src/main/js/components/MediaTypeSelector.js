import React, {useEffect} from "react";
import {useState} from "react";
import {Form} from "react-bootstrap";
import Container from "react-bootstrap/Container";
import {MediaTypeAPI} from "../apis/MediaTypeAPI";

const TITLE = "Типы медиа:";

export function MediaTypeSelector({onSelect: onSelect, selectedTypesIds: selectedTypesIds}) {
    const [types, setTypes] = useState([]);

    useEffect(() => {
        MediaTypeAPI.get().then(response => {
            setTypes(response.data);
        });
    }, []);

    function handleCheckBoxOnChange(e) {
        let newSelectedTypesIds = new Set(selectedTypesIds);
        if (e.target.checked) {
            selectedTypesIds.add(e.target.value);
        } else {
            selectedTypesIds.delete(e.target.value);
        }
        onSelect(selectedTypesIds);
    }

    return (
        <Container>
            <Form>
                <h5>Тип:</h5>
                <Form.Group>
                    {types.map((type) => (
                        <Form.Check
                            onChange={handleCheckBoxOnChange}
                            type={"checkbox"}
                            label={type.name}
                            key={type.id}
                            value={type.id}
                        />
                    ))}
                </Form.Group>
            </Form>
        </Container>
    );
}