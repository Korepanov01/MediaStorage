import React, { useState } from 'react';
import {SearchBar} from "./selectors/searchBar";
import {TagsSelector} from "./selectors/tagsSelector";
import {Button} from "react-bootstrap";
import {MediaTypeSelector} from "./selectors/mediaTypeSelector";
import {CategorySelector} from "./selectors/categorySelector";
import {getTagById} from "../apis/tagAPI";

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
        getTagById(tagId).then(({error, data: tag}) => {
            if (!error) setSelectedTags(selectedTags.concat([tag]));
        });
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
