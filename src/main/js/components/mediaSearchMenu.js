import React, { useState } from 'react';
import {SearchBar} from "./selectors/searchBar";
import {TagsSelector} from "./selectors/tagsSelector";
import {Button, Card, Form, FormGroup} from "react-bootstrap";
import {MediaTypeSelector} from "./selectors/mediaTypeSelector";
import {CategorySelector} from "./selectors/categorySelector";
import {getTagById} from "../apis/tagAPI";
import {Title} from "./decor/title";

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
        <Form>
            <FormGroup>
                <Title>Поиск</Title>
            </FormGroup>
            <Form.Group>
                <Form.Label>Название</Form.Label>
                <SearchBar onSearchStringChange={setSearchString}/>
            </Form.Group>
            <Form.Group>
                <Form.Label>Тип</Form.Label>
                <MediaTypeSelector selectedTypesIds={selectedTypesIds} onSelect={setSelectedTypesIds}/>
            </Form.Group>
            <Form.Group>
                <Form.Label>Теги</Form.Label>
                <TagsSelector onSelect={handleSelect} onUnselect={handleUnselect} selectedTags={selectedTags}/>
            </Form.Group>
            <Form.Group>
                <Form.Label>Категория</Form.Label>
                <CategorySelector onSelect={setCategoryId}/>
            </Form.Group>
            <Form.Group>
                <Button onClick={handleClickSearchButton}>{SEARCH_BUTTON_TITLE}</Button>
            </Form.Group>
        </Form>
    );
}
