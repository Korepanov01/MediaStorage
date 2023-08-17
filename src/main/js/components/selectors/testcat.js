import React, {useLayoutEffect, useState} from "react";
import {getCategories} from "../../apis/categoryAPI";

export function Testcat() {
    const [parentCategory, setParentCategory] = useState(null);
    const [childrenCategories, setChildrenCategories] = useState([]);

    useLayoutEffect(() => {
        getCategories({parentCategoryId: parentCategory?.id ?? 0})
            .then(({error, data}) => {
                if (!error) {
                    setChildrenCategories(data);
                }
            })
    }, [parentCategory])

    return (
        <>
            {childrenCategories.map(child =>
                <div>{child.name}</div>
            )}
        </>
    );
}