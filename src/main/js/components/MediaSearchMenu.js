import React, { useState } from 'react';
import {SearchBar} from "./selectors/SearchBar";
import {TagsSelector} from "./selectors/TagsSelector";
import {Button} from "react-bootstrap";
import {MediaTypeSelector} from "./selectors/MediaTypeSelector";
import {CategorySelector} from "./selectors/CategorySelector";
import {TagAPI} from "../apis/TagAPI";

const SEARCH_BUTTON_TITLE = "Найти"

export function MediaSearchMenu({onSearch: onSearch, searchParameters: searchParameters}) {
    const [selectedTypesIds, setSelectedTypesIds] = useState(new Set());
    const [selectedTags, setSelectedTags] = useState([]);
    const [searchString, setSearchString] = useState("")
    const [categoryId, setCategoryId] = useState(null)

    function handleClickSearchButton() {
        onSearch({...searchParameters,
            categoryId: categoryId,
            typeIds: [...selectedTypesIds],
            searchString: searchString, 
            tagIds: selectedTags.map(tag => tag.id),
            pageIndex: 0})
    }

    function handleSelect(tagId) {
        TagAPI.getById(tagId).then(tag => setSelectedTags(selectedTags.concat([tag])));
    }

    function handleUnselect(tagId) {
        setSelectedTags(selectedTags.filter(tag => tag.id !== tagId));
    }

    return (
        <>
            <SearchBar onSearchStringChange={setSearchString}/>
            <MediaTypeSelector selectedTypesIds={selectedTypesIds} onSelect={setSelectedTypesIds}/>
            <TagsSelector onSelect={handleSelect} onUnselect={handleUnselect} selectedTags={selectedTags}/>
            <CategorySelector onSelect={setCategoryId}/>
            <Button onClick={handleClickSearchButton}>{SEARCH_BUTTON_TITLE}</Button>
        </>
    );
}
