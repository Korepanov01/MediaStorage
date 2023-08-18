import React from "react";
import {CategorySelector} from "../selectors/categorySelector";
import {useState} from "react";
import {Button, ButtonGroup} from "react-bootstrap";
import {deleteCategory} from "../../apis/categoryAPI";

export default function CategoryRedactor() {

    const [selectedCategory, setSelectedCategory] = useState(null);
    const [parents, setParents] = useState([]);
    const [children, setChildren] = useState([]);

    function handleDelete() {
        deleteCategory(selectedCategory.id)
            .then(({error}) => {
                if(!error) {
                    setChildren(children.filter(child => child.id !== selectedCategory.id));
                }
            });

    }

    return (
        <>
            <CategorySelector selectedCategory={selectedCategory} setSelectedCategory={setSelectedCategory} parentsState={[parents, setParents]} childrenState={[children, setChildren]} resetOnChange={true}/>
            <ButtonGroup>
                <Button variant="danger" onClick={handleDelete}>Добавить</Button>
                <Button variant="danger" onClick={handleDelete}>Удалить</Button>
            </ButtonGroup>
        </>
    );
}