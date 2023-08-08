import React, {useEffect, useState} from 'react';
import {MediaCards} from "../MediaCards";
import {MediaAPI} from "../../apis/MediaAPI";
import {AppPagination} from "../AppPagination";
import {Button, Col, Row} from "react-bootstrap";
import {UserMenu} from "../UserMenu";
import {UserAPI} from "../../apis/UserAPI";
import {MediaFormPopup} from "../MediaFormPopup";
import {useNavigate} from "react-router-dom";

const PAGE_SIZE = 9;
const CARDS_IN_ROW = 3;

const USER_ID = 1;

export function ProfilePage() {
    const [medias, setMedias] = useState([])
    const [searchParameters, setSearchParameters] = useState({pageIndex: 0, pageSize: PAGE_SIZE, userId: USER_ID});
    const [user, setUser] = useState({})
    const [showMediaForm, setShowMediaForm] = useState(false);

    const navigate = useNavigate();

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

    const handleFormSubmit = (newMedia) => {
        console.log(JSON.stringify(newMedia))
        MediaAPI.post(newMedia)
            .then((response) => {
                let newMediaId = response.data.id;
                navigate(`/media/${newMediaId}`);
            });
    };

    return (
        <>
            <MediaFormPopup show={showMediaForm} onChangeShow={setShowMediaForm} userId={USER_ID} onSubmit={handleFormSubmit}/>
            <Row>
                <Col lg={4}>
                    <UserMenu user={user} onAddMediaClick={() => setShowMediaForm(true)}/>
                </Col>
                <Col lg={8}>
                    <MediaCards medias={medias} cardsInRow={CARDS_IN_ROW}/>
                    <AppPagination pageIndex={searchParameters.pageIndex} onPageChange={onPageChange}/>
                </Col>
            </Row>
        </>
    );
}