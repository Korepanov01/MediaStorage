import React, {useEffect} from "react";
import {useState} from "react";
import {SearchBar} from "./SearchBar";
import {Badge, Form, FormGroup} from "react-bootstrap";
import {TagAPI} from "../../apis/TagAPI";
import {PageSelector} from "./PageSelector";

export function TagsSelector({onSelect: onSelect, selectedTagsIds: selectedTagsIds}) {
    const [tags, setTags] = useState([]);
    const [tagSearchParameters, setTagSearchParameters] = useState({pageSize: 5, pageIndex: 0});
    const [selectedTags, setSelectedTags] = useState([]);

    useEffect(() => {
        TagAPI.get(tagSearchParameters).then(tags => {
            setTags(tags);
        });
    }, [tagSearchParameters]);

    useEffect(() => {
        let newSelectedTags = [];
        const tagPromises = [...selectedTagsIds].map(async selectedTagId => await TagAPI.getById(selectedTagId));

        Promise.all(tagPromises)
            .then(tags => {
                newSelectedTags = tags;
                setSelectedTags(newSelectedTags);
            });
    }, [selectedTagsIds]);

    function handleSearchStringChange(searchString) {
        setTagSearchParameters({...tagSearchParameters, pageIndex: 0, searchString: searchString});
    }

    function handlePageChange(newPageIndex) {
        const updatedSearchParameters = {...tagSearchParameters, pageIndex: newPageIndex};
        setTagSearchParameters(updatedSearchParameters)
    }

    function handleCheckBoxOnChange(e) {
        let newSelectedTagsIds = new Set(selectedTagsIds);
        if (e.target.checked) {
            newSelectedTagsIds.add(e.target.value);
        } else {
            newSelectedTagsIds.delete(e.target.value);
        }
        onSelect(newSelectedTagsIds);
    }

    function handleSelectedTagClick(tagId) {
        let newSelectedTagsIds = new Set(selectedTagsIds);
        newSelectedTagsIds.delete(tagId);
        onSelect(newSelectedTagsIds);
    }

    return (
        <FormGroup>
            <Form.Label>Тэги</Form.Label>
            <SearchBar onSearchStringChange={handleSearchStringChange}/>
            {selectedTags.map((selectedTag) => (
                <Badge
                    onClick={() => handleSelectedTagClick(selectedTag.id)}
                    size={"sm"}
                    key={selectedTag.id}>
                    {selectedTag.name}
                </Badge>
            ))}
            {tags.map((tag) => (
                <Form.Check
                    onChange={handleCheckBoxOnChange}
                    type={"checkbox"}
                    label={tag.name}
                    key={tag.id}
                    value={tag.id}
                />
            ))}
            <PageSelector pageIndex={ tagSearchParameters.pageIndex } onPageChange={handlePageChange}/>
        </FormGroup>
    );
}