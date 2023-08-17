import React, {useLayoutEffect, useState} from "react";
import {getCategories} from "../../apis/categoryAPI";

export function Testcat() {
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

    function handleBack(parentCategory) {
        getCategories({parentCategoryId: parentCategory.id})
            .then(({error, data}) => {
                if (!error) {
                    let newParents = [...parents];
                    while (parents.pop().parentCategoryId !== parentCategory.id) {}
                    setParents(newParents);

                    setChildren(data);
                }
            });
    }

    function handleFirst() {
        getCategories({parentCategoryId: 0})
            .then(({error, data}) => {
                if (!error) {
                    setParents([]);
                    setChildren(data);
                }
            })
    }

    return (
        <>
            <h5 onClick={() => handleFirst()}>Категории</h5>
            {parents.map(parent =>
                <h5 onClick={() => handleBack(parent)}>{parent.name}</h5>
            )}
            {children.map(child =>
                <h6 onClick={() => handleExpand(child)}>{child.name}</h6>
            )}
        </>
    );
}