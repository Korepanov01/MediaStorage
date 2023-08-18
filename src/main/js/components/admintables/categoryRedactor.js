import React from "react";
import {CategorySelector} from "../selectors/categorySelector";
import {useState} from "react";
import {Button, ButtonGroup} from "react-bootstrap";
import {deleteCategory, postCategory, putCategory} from "../../apis/categoryAPI";
import {ChangeCategoryPopup} from "../popups/changeCategoryPopup";
import {toast} from "react-toastify";

export default function CategoryRedactor() {
    const [selectedCategory, setSelectedCategory] = useState(null);

    const [parents, setParents] = useState([]);
    const [children, setChildren] = useState([]);

    const [addingSubcategory, setAddingSubcategory] = useState(false);
    const [showCategoryPopup, setShowCategoryPopup] = useState(false);
    const [showPutCategoryPopup, setPutShowCategoryPopup] = useState(false);

    function handleDelete() {
        deleteCategory(selectedCategory.id)
            .then(({error}) => {
                if(!error) {
                    setChildren(children.filter(child => child.id !== selectedCategory.id));
                }
            });

    }

    function handleAdd({ name }) {
        if (addingSubcategory && !selectedCategory) {
            toast.error('Не выбрана категория!');
            return;
        }

        let parentCategoryId = addingSubcategory ? selectedCategory.id : parents[parents.length - 1]?.id;

        postCategory(name, parentCategoryId)
            .then(({error, data}) => {
                if (!error) {
                    if (!addingSubcategory) {
                        let newCategory = {id: data.id, name, parentCategoryId: parentCategoryId ?? 0};
                        setChildren(prevChildren => [...prevChildren, newCategory]);
                    }
                    toast.success(`Категория "${name}" добавлена`);
                    setShowCategoryPopup(false);
                }
            });
    }

    function handlePut({name}) {
        if (!selectedCategory) {
            toast.error('Не выбрана категория!');
            return;
        }

        putCategory(selectedCategory.id, name, selectedCategory.parentCategoryId !== 0 ? selectedCategory.parentCategoryId : undefined)
            .then(({error}) => {
                if (!error) {
                    let newCategory = {...selectedCategory, name};
                    setChildren(prevChildren => prevChildren.map(category => category.id === selectedCategory.id ? newCategory : category));
                    toast.success(`Категория "${selectedCategory.name}" изменена на "${newCategory.name}"`);
                    setSelectedCategory(newCategory);
                    setShowCategoryPopup(false);
                }
            });
    }

    return (
        <>
            <ChangeCategoryPopup show={showPutCategoryPopup} setShow={setPutShowCategoryPopup} onSubmit={handlePut} selectedCategory={selectedCategory}/>
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
                <Button disabled={!selectedCategory} onClick={() => {
                    setPutShowCategoryPopup(true);
                }}>Изменить</Button>
                <Button variant="danger" onClick={handleDelete}>Удалить</Button>
            </ButtonGroup>
        </>
    );
}