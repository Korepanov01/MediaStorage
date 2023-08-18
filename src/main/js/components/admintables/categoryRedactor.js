import React from "react";
import {CategorySelector} from "../selectors/categorySelector";
import {useState} from "react";
import {Button, ButtonGroup} from "react-bootstrap";
import {deleteCategory, postCategory} from "../../apis/categoryAPI";
import {ChangeCategoryPopup} from "../popups/changeCategoryPopup";
import {toast} from "react-toastify";

export default function CategoryRedactor() {
    const [selectedCategory, setSelectedCategory] = useState(null);

    const [parents, setParents] = useState([]);
    const [children, setChildren] = useState([]);

    const [addingSubcategory, setAddingSubcategory] = useState(false);
    const [showCategoryPopup, setShowCategoryPopup] = useState(false);

    function handleDelete() {
        deleteCategory(selectedCategory.id)
            .then(({error}) => {
                if(!error) {
                    setChildren(children.filter(child => child.id !== selectedCategory.id));
                }
            });

    }

    function handleAdd({name}) {
        let parentCategoryId = null;

        if (addingSubcategory) {
            if (!selectedCategory) {
                toast.error("Не выбрана категория!");
                return;
            }
            parentCategoryId = selectedCategory.id;
        } else {
            if (parents.length !== 0) {
                parentCategoryId = parents[parents.length - 1];
            }
        }

        postCategory(name, parentCategoryId)
            .then(({error, data}) => {
                if(!error) {
                    if (!addingSubcategory)
                        setChildren([...children, {id: data.id, name, parentCategoryId}]);
                    toast.success(`Категория "${name}" добавлена`);
                }
            });
    }

    return (
        <>
            <ChangeCategoryPopup show={showCategoryPopup} setShow={setShowCategoryPopup} onSubmit={handleAdd}/>
            <CategorySelector selectedCategory={selectedCategory} setSelectedCategory={setSelectedCategory} parentsState={[parents, setParents]} childrenState={[children, setChildren]} resetOnChange={true}/>
            <ButtonGroup>
                <Button onClick={() => {
                    setShowCategoryPopup(true);
                    setAddingSubcategory(false);
                }}>Добавить категорию</Button>
                <Button onClick={() => {
                    setShowCategoryPopup(true);
                    setAddingSubcategory(true);
                }}>Добавить подкатегорию</Button>
                <Button variant="danger" onClick={handleDelete}>Удалить</Button>
            </ButtonGroup>
        </>
    );
}