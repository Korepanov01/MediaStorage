import React, {useEffect} from "react";
import {useState} from "react";
import DropdownTreeSelect from "react-dropdown-tree-select";
import {getCategories} from "../../apis/categoryAPI";

export function CategorySelector({onSelect: handleSelect}) {
    const [categories, setCategories] = useState([]);

    const convertToNode = category => ({
        label: category.name,
        value: category,
        children: []
    });

    const fetchCategoryTree = async (parent) => {
        const searchParameters = { parentCategoryId: parent?.id ?? 0, pageSize: 100 };
        const {error, data} = await getCategories(searchParameters);

        if (!error) {
            const categories = data.map(category => convertToNode(category));

            for (const category of categories) {
                category.children = await fetchCategoryTree(category.value);
            }

            return categories;
        }
    };

    useEffect(() => {
        const fetchCategories = async () => {
            const categoryTree = await fetchCategoryTree();
            setCategories(categoryTree);
        };

        fetchCategories();
    }, []);

    function handleChange(currentNode, selectedNodes) {
        handleSelect(selectedNodes.length !== 0
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