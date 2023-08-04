import React, {useEffect, useState} from 'react';
import {SearchBar} from "./SearchBar";
import {MediaList} from "./MediaList";
import {MediaAPI} from "../apis/MediaAPI";
import {MediaSearchParameters} from "../models/MediaSearchParameters";
import {AppPagination} from "./AppPagination";

const PAGE_SIZE = 8;

export function MediaPage() {
    const [medias, setMedias] = useState([])
    const [searchParameters, setSearchParameters] = useState(new MediaSearchParameters(PAGE_SIZE));

    useEffect(() => {
        MediaAPI.get(searchParameters).then(response => {
            setMedias(response.data);
        });
    }, [searchParameters]);

    return (
        <>
            <SearchBar
                searchString={ searchParameters.searchString }
                onSearchStringChange={ (searchString) => {
                    const updatedSearchParameters = {...searchParameters, searchString: searchString};
                    setSearchParameters(updatedSearchParameters)
                } }/>
            <MediaList medias={ medias }/>
            <AppPagination pageIndex={ searchParameters.pageIndex } onPageChange={ (newPageIndex) => {
                const updatedSearchParameters = {...searchParameters, pageIndex: newPageIndex};
                setSearchParameters(updatedSearchParameters)
            }}/>
        </>
    );
}