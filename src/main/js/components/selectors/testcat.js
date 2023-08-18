import React, {useLayoutEffect, useState} from "react";
import {getCategories} from "../../apis/categoryAPI";
import {Card, Form} from "react-bootstrap";

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
        getCategories({parentCategoryId: parent.id})
            .then(({error, data}) => {
                if (!error) {
                    setSelectedCategory(null);

                    let newParents = [...parents];
                    while (newParents.pop().parentCategoryId !== parent.id) {}
                    setParents(newParents);

                    setChildren(data);
                }
            });
    }

    function handleFirst() {
        getCategories({parentCategoryId: 0})
            .then(({error, data}) => {
                if (!error) {
                    setSelectedCategory(null);
                    setParents([]);
                    setChildren(data);
                }
            })
    }

    return (
        <Card>
            <Card.Header>
                <h6 onClick={() => handleFirst()}>Категории</h6>
                {parents.map(parent =>
                    <h6 key={parent.id} onClick={() => handleBack(parent)}>{parent.name}</h6>
                )}
            </Card.Header>
            <Card.Body>
                {children.map(child =>
                    <Form.Check
                        key={child.id}
                        type="radio"
                        checked={child.id === selectedCategory?.id}
                        onChange={(e) => setSelectedCategory(child)}
                        onClick={() => handleExpand(child)}
                        label={child.name}
                    />
                )}
            </Card.Body>
        </Card>
    );
}