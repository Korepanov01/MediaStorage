import React, { useState } from 'react';
import {SearchBar} from "./SearchBar";
import {TagsSelector} from "./TagsSelector";
import {Button} from "react-bootstrap";
import {MediaTypeSelector} from "./MediaTypeSelector";
import {CategorySelector} from "./CategorySelector";

const SEARCH_BUTTON_TITLE = "Найти"

export function MediaSearchMenu({onSearch: onSearch, searchParameters: searchParameters}) {
    const [selectedTypesIds, setSelectedTypesIds] = useState(new Set());
    const [selectedTagsIds, setSelectedTagsIds] = useState(new Set());
    const [searchString, setSearchString] = useState("")
    const [categoryId, setCategoryId] = useState(null)

    function handleClickSearchButton() {
        onSearch({...searchParameters,
            categoryId: categoryId,
            typeIds: [...selectedTypesIds],
            searchString: searchString, 
            tagIds: [...selectedTagsIds], 
            pageIndex: 0})
    }

    return (
        <>
            <SearchBar onSearchStringChange={setSearchString}/>
            <MediaTypeSelector selectedTypesIds={selectedTypesIds} onSelect={setSelectedTypesIds}/>
            <TagsSelector onSelect={setSelectedTagsIds} selectedTags={selectedTagsIds}/>
            <CategorySelector onSelect={setCategoryId}/>
            <Button onClick={handleClickSearchButton}>{SEARCH_BUTTON_TITLE}</Button>
        </>
    );
}
