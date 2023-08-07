import React, {useEffect, useState} from 'react';
import {MediaCards} from "../MediaCards";
import {MediaAPI} from "../../apis/MediaAPI";
import {MediaSearchParameters} from "../../models/searchparameters/MediaSearchParameters";
import {AppPagination} from "../AppPagination";
import {Col, Row} from "react-bootstrap";
import {MediaSearchMenu} from "../MediaSearchMenu";

const PAGE_SIZE = 9;
const CARDS_IN_ROW = 3;

export function SearchMediaPage() {
    const [medias, setMedias] = useState([])

    const [searchParameters, setSearchParameters] = useState(new MediaSearchParameters(PAGE_SIZE));

    useEffect(() => {
        MediaAPI.get(searchParameters).then(response => {
            setMedias(response.data);
        });
    }, [searchParameters]);

    function onPageChange(newPageIndex) {
        const updatedSearchParameters = {...searchParameters, pageIndex: newPageIndex};
        setSearchParameters(updatedSearchParameters)
    }

    return (
        <>
            <Row>
                <Col lg={4}>
                    <MediaSearchMenu onSearch={setSearchParameters}/>
                </Col>
                <Col lg={8}>
                    <MediaCards medias={ medias } cardsInRow={CARDS_IN_ROW}/>
                    <AppPagination pageIndex={ searchParameters.pageIndex } onPageChange={onPageChange}/>
                </Col>
            </Row>
        </>
    );
}