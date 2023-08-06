import React, {useEffect} from "react";
import {useState} from "react";
import {SearchBar} from "./SearchBar";
import {SearchStringParameters} from "../models/searchparameters/SearchStringParameters";
import {Form} from "react-bootstrap";
import {TagAPI} from "../apis/TagAPI";
import Container from "react-bootstrap/Container";
import {AppPagination} from "./AppPagination";

export function TagsSelector({onSelect: onSelect, selectedTags: selectedTagsIds}) {
    const [tags, setTags] = useState([]);
    const [tagSearchParameters, setTagSearchParameters] = useState(new SearchStringParameters(5));

    useEffect(() => {
        TagAPI.get(tagSearchParameters).then(response => {
            setTags(response.data);
        });
    }, [tagSearchParameters]);

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

    return (
        <Container>
            <SearchBar onSearchStringChange={handleSearchStringChange}/>
            {[...selectedTagsIds].map((selectedTagId) => (
                <span key={selectedTagId}>{selectedTagId}</span>
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