import React, {useEffect} from "react";
import {useState} from "react";
import {SearchBar} from "./SearchBar";
import {Badge, Form} from "react-bootstrap";
import {TagAPI} from "../apis/TagAPI";
import Container from "react-bootstrap/Container";
import {AppPagination} from "./AppPagination";

export function TagsSelector({onSelect: onSelect, selectedTags: selectedTagsIds}) {
    const [tags, setTags] = useState([]);
    const [tagSearchParameters, setTagSearchParameters] = useState({pageSize: 5});
    const [selectedTags, setSelectedTags] = useState([]);

    useEffect(() => {
        TagAPI.get(tagSearchParameters).then(response => {
            setTags(response.data);
        });
    }, [tagSearchParameters]);

    useEffect(() => {
        let newSelectedTags = [];
        const tagPromises = [...selectedTagsIds].map(selectedTagId => TagAPI.getById(selectedTagId).then(response => response.data));

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
        <Container>
            <h5>Тэги:</h5>
            <SearchBar onSearchStringChange={handleSearchStringChange}/>
            {selectedTags.map((selectedTag) => (
                <Badge
                    onClick={() => handleSelectedTagClick(selectedTag.id)}
                    size={"sm"}
                    key={selectedTag.id}>
                    {selectedTag.name}
                </Badge>
            ))}
            <Form>
                <Form.Group>
                    {tags.map((tag) => (
                        <Form.Check
                            onChange={handleCheckBoxOnChange}
                            type={"checkbox"}
                            label={tag.name}
                            key={tag.id}
                            value={tag.id}
                        />
                    ))}
                </Form.Group>
            </Form>
            <AppPagination pageIndex={ tagSearchParameters.pageIndex } onPageChange={handlePageChange}/>
        </Container>
    );
}