import React, {useEffect, useState} from 'react';
import {SearchBar} from "./SearchBar";
import {MediaList} from "./MediaList";
import {MediaAPI} from "../apis/MediaAPI";
import {MediaSearchParameters} from "../models/MediaSearchParameters";

const PAGE_SIZE = 8;

export function MediaPage() {
    const [medias, setMedias] = useState([])
    const [searchParameters, setSearchParameters] = useState(new MediaSearchParameters(PAGE_SIZE));

    useEffect(() => {
        MediaAPI.get(searchParameters).then(response => {
            setMedias(response.data)
        });
    }, [searchParameters]);

    const handleSearch = (searchInput) => {
        const updatedSearchParameters = { ...searchParameters, searchString: searchInput };
        setSearchParameters(updatedSearchParameters);
    };

    return (
        <>
            <SearchBar onSearch={ handleSearch }/>
            <MediaList medias={ medias }/>
        </>
    );
}