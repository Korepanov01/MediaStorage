import React, {useEffect} from "react";
import {useState} from "react";
import {SearchBar} from "./searchBar";
import {Badge, Form, FormGroup} from "react-bootstrap";
import {getTags} from "../../apis/tagAPI";
import {PageSelector} from "./pageSelector";

export function TagsSelector({onSelect: handleSelect, onUnselect: handleUnselect, selectedTags: selectedTags}) {
    const [tags, setTags] = useState([]);
    const [tagSearchParameters, setTagSearchParameters] = useState({pageSize: 10, pageIndex: 0});

    useEffect(() => {
        getTags(tagSearchParameters).then(({error, data: tags}) => {
            if(!error) setTags(tags);
        });
    }, [tagSearchParameters]);

    return (
        <FormGroup>
            <SearchBar onSearchStringChange={(searchString) => setTagSearchParameters({...tagSearchParameters, pageIndex: 0, searchString: searchString})}/>
            {selectedTags.map((selectedTag) => (
                <Badge
                    onClick={() => handleUnselect(selectedTag)}
                    size={"sm"}
                    key={selectedTag.id}>
                    {selectedTag.name}
                </Badge>
            ))}
            {tags.map((tag) => (
                <Form.Check
                    checked={selectedTags.some(t => t.id === tag.id)}
                    onChange={(e) => e.target.checked ? handleSelect(tag) : handleUnselect(tag)}
                    type={"checkbox"}
                    label={tag.name}
                    key={tag.id}
                    value={tag.id}
                />
            ))}
            <PageSelector pageIndex={tagSearchParameters.pageIndex} onPageChange={(newPageIndex) => setTagSearchParameters({...tagSearchParameters, pageIndex: newPageIndex})}/>
        </FormGroup>
    );
}