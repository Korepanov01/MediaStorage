import React, {useEffect} from "react";
import {useState} from "react";
import {Form} from "react-bootstrap";
import Container from "react-bootstrap/Container";
import {getMediaTypes} from "../../apis/mediaTypeAPI";

export function MediaTypeSelector({onSelect: onSelect, selectedTypesIds: selectedTypesIds}) {
    const [types, setTypes] = useState([]);

    useEffect(() => {
        getMediaTypes().then(({data, error}) => {
            if (!error) setTypes(data);
        });
    }, []);

    function handleCheckBoxOnChange(e) {
        let newSelectedTypesIds = new Set(selectedTypesIds);
        if (e.target.checked) {
            newSelectedTypesIds.add(e.target.value);
        } else {
            newSelectedTypesIds.delete(e.target.value);
        }
        onSelect(newSelectedTypesIds);
    }

    return (
        <Container>
            <Form>
                <Form.Group>
                    <Form.Label>Тип</Form.Label>
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