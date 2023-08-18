import React from "react";
import {CategorySelector} from "../selectors/categorySelector";
import {useState} from "react";
import {Button, ButtonGroup} from "react-bootstrap";

export default function CategoryRedactor() {

    const [selectedCategory, setSelectedCategory] = useState(null);
    const [parents, setParents] = useState([]);
    const [children, setChildren] = useState([]);

    function handleDelete() {}

    return (
        <>
            <CategorySelector selectedCategory={selectedCategory} setSelectedCategory={setSelectedCategory} parentsState={[parents, setParents]} childrenState={[children, setChildren]}/>
            <ButtonGroup>
                <Button onClick={handleDelete}/>
            </ButtonGroup>
        </>
    );
}