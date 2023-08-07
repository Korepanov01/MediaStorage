import React, {useEffect, useState} from 'react';
import {MediaCards} from "../MediaCards";
import {MediaAPI} from "../../apis/MediaAPI";
import {AppPagination} from "../AppPagination";
import {Col, Row} from "react-bootstrap";
import {UserMenu} from "../UserMenu";
import {UserAPI} from "../../apis/UserAPI";

const PAGE_SIZE = 9;
const CARDS_IN_ROW = 3;

const USER_ID = 1;

export function ProfilePage() {
    const [medias, setMedias] = useState([])
    const [searchParameters, setSearchParameters] = useState({pageIndex: 0, pageSize: PAGE_SIZE, userId: USER_ID});

    const [user, setUser] = useState({})

    useEffect(() => {
        MediaAPI.get(searchParameters).then(response => {
            setMedias(response.data);
        });
    }, [searchParameters]);

    useEffect(() => {
        UserAPI.getById(USER_ID).then(response => {
            setUser(response.data);
        });
    }, []);

    function onPageChange(newPageIndex) {
        setSearchParameters({...searchParameters, pageIndex: newPageIndex})
    }

    return (
        <Row>
            <Col lg={4}>
                <UserMenu user={user}/>
            </Col>
            <Col lg={8}>
                <MediaCards medias={medias} cardsInRow={CARDS_IN_ROW}/>
                <AppPagination pageIndex={searchParameters.pageIndex} onPageChange={onPageChange}/>
            </Col>
        </Row>
    );
}