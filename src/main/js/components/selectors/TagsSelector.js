import React, {useEffect} from "react";
import {useState} from "react";
import {SearchBar} from "./SearchBar";
import {Badge, Form, FormGroup} from "react-bootstrap";
import {TagAPI} from "../../apis/TagAPI";
import {PageSelector} from "./PageSelector";

export function TagsSelector({onSelect: handleSelect, onUnselect: handleUnselect, selectedTags: selectedTags}) {
    const [tags, setTags] = useState([]);
    const [tagSearchParameters, setTagSearchParameters] = useState({pageSize: 5, pageIndex: 0});

    useEffect(() => {
        TagAPI.get(tagSearchParameters).then(tags => {
            setTags(tags);
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
        if (e.target.checked) {
            handleSelect(e.target.value);
        } else {
            handleUnselect(e.target.value);
        }
    }

    return (
        <FormGroup>
            <Form.Label>Тэги</Form.Label>
            <SearchBar onSearchStringChange={handleSearchStringChange}/>
            {selectedTags.map((selectedTag) => (
                <Badge
                    onClick={() => handleUnselect(selectedTag.id)}
                    size={"sm"}
                    key={selectedTag.id}>
                    {selectedTag.name}
                </Badge>
            ))}
            {tags.map((tag) => (
                <Form.Check
                    checked={selectedTags.some(t => t.id === tag.id)}
                    onChange={handleCheckBoxOnChange}
                    type={"checkbox"}
                    label={tag.name}
                    key={tag.id}
                    value={tag.id}
                />
            ))}
            <PageSelector pageIndex={tagSearchParameters.pageIndex} onPageChange={handlePageChange}/>
        </FormGroup>
    );
}