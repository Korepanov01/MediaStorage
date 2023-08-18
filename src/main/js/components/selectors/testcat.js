import React, {useLayoutEffect, useState} from "react";
import {getCategories} from "../../apis/categoryAPI";
import {Badge, Card, Form} from "react-bootstrap";

export function Testcat({selectedCategory, setSelectedCategory}) {
    const [parents, setParents] = useState([]);
    const [children, setChildren] = useState([]);

    useLayoutEffect(() => {
        getCategories({parentCategoryId: 0})
            .then(({error, data}) => {
                if (!error) {
                    setChildren(data);
                }
            });
    }, []);

    function handleExpand(child) {
        getCategories({parentCategoryId: child.id})
            .then(({error, data}) => {
                if (!error && data.length !== 0) {
                    setChildren(data);
                    setParents([...parents, child]);
                }
            })
    }

    function handleBack(parent) {
        let newParents = [...parents];
        while (newParents.pop().id !== parent.id) {}

        getCategories({parentCategoryId: newParents.length === 0 ? 0 : newParents[newParents.length - 1].id})
            .then(({error, data}) => {
                if (!error) {
                    setSelectedCategory(null);
                    setParents(newParents);
                    setChildren(data);
                }
            });
    }

    return (
        <Card>
            {parents.length !== 0 &&
                <Card.Header>
                    {parents.map((parent, i) =>
                        <div key={parent.id} style={{paddingLeft: `${i * 20}px`}}>
                            <Badge role="button" onClick={() => handleBack(parent)}>⬉</Badge>
                            <span>{parent.name}</span>
                        </div>
                    )}
                </Card.Header>
            }
            <Card.Body>
                {children.map(child =>
                    <Form.Check
                        key={child.id}
                        type="radio"
                        checked={child.id === selectedCategory?.id}
                        onChange={() => setSelectedCategory(child)}
                        onClick={() => handleExpand(child)}
                        label={child.name}
                    />
                )}
            </Card.Body>
        </Card>
    );
}