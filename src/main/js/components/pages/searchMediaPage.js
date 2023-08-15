import React, {useEffect, useState} from 'react';
import {MediaCards} from "../mediaCards";
import {MediaAPI} from "../../apis/mediaAPI";
import {PageSelector} from "../selectors/pageSelector";
import {Col, Row} from "react-bootstrap";
import {MediaSearchMenu} from "../mediaSearchMenu";

const PAGE_SIZE = 9;
const CARDS_IN_ROW = 3;

export function SearchMediaPage() {
    const [medias, setMedias] = useState([])

    const [searchParameters, setSearchParameters] = useState({pageSize: PAGE_SIZE});

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
        <Row>
            <Col lg={4}>
                <MediaSearchMenu onSearch={setSearchParameters}/>
            </Col>
            <Col lg={8}>
                <MediaCards medias={ medias } cardsInRow={CARDS_IN_ROW}/>
                <PageSelector pageIndex={ searchParameters.pageIndex } onPageChange={onPageChange}/>
            </Col>
        </Row>
    );
}