import React, {useEffect} from "react";
import {useState} from "react";
import DropdownTreeSelect from "react-dropdown-tree-select";
import {CategoryAPI} from "../../apis/categoryAPI";

export function CategorySelector({onSelect: onSelect}) {
    const [categories, setCategories] = useState([]);

    const convertToNode = category => ({
        label: category.name,
        value: category.id,
        children: []
    });

    const fetchCategoryTree = async (parentId = 0) => {
        const searchParameters = { parentCategoryId: parentId, pageSize: 100 };
        const response = await CategoryAPI.get(searchParameters);

        const categories = response.data.map(category => convertToNode(category));

        for (const category of categories) {
            category.children = await fetchCategoryTree(category.value);
        }

        return categories;
    };

    useEffect(() => {
        const fetchCategories = async () => {
            const categoryTree = await fetchCategoryTree();
            setCategories(categoryTree);
        };

        fetchCategories();
    }, []);

    function handleChange(currentNode, selectedNodes) {
        onSelect(selectedNodes.length !== 0
            ? selectedNodes[0].value
            : null);
    }

    return (
        <DropdownTreeSelect
            onChange={handleChange}
            data={categories}
            texts={{ placeholder: "Поиск" }}
            mode="radioSelect"
            inlineSearchInput="true"
        />
    );
}