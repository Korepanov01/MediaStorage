import React, {useLayoutEffect, useState} from "react";
import {getChildrenCategory} from "../../apis/categoryAPI";
import {Badge, Card} from "react-bootstrap";


export default function CategorySelector({selectedCategory, setSelectedCategory, parentsState= undefined, childrenState=undefined, resetOnChange = false}) {
    const [parents, setParents] = !parentsState ? useState([]) : parentsState;
    const [children, setChildren] = !childrenState ? useState([]) : childrenState;

    useLayoutEffect(() => {
        getChildrenCategory(0)
            .then(({error, data}) => {
                if (!error) {
                    setChildren(data);
                }
            });
    }, []);

    function handleExpand(child) {
        getChildrenCategory(child.id)
            .then(({error, data}) => {
                if (!error && data.length !== 0) {
                    setChildren(data);
                    setParents([...parents, child]);
                    if (resetOnChange) setSelectedCategory(null);
                }
            })
    }

    function handleBack(parent) {
        let newParents = [...parents];
        while (newParents.pop().id !== parent.id) {}

        getChildrenCategory(newParents.length === 0 ? 0 : newParents[newParents.length - 1].id)
            .then(({error, data}) => {
                if (!error) {
                    setParents(newParents);
                    setChildren(data);
                    if (resetOnChange) setSelectedCategory(null);
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
                    <div key={child.id} className={"form-check"}>
                        <input type={"radio"} className={"form-check-input"} checked={child.id === selectedCategory?.id} onChange={() => setSelectedCategory(child)}/>
                        <label className={"form-check-label"} onClick={() => handleExpand(child)}>{child.name}</label>
                    </div>
                )}
            </Card.Body>
            {!resetOnChange && selectedCategory &&
                <Card.Footer>
                    <Badge role="button" onClick={() => setSelectedCategory(null)}>{selectedCategory.name}</Badge>
                </Card.Footer>
            }
        </Card>
    );
}