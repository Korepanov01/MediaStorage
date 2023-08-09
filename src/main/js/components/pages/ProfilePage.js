import React, {useEffect, useState} from 'react';
import {MediaCards} from "../MediaCards";
import {MediaAPI} from "../../apis/MediaAPI";
import {PageSelector} from "../selectors/PageSelector";
import {Col, Row} from "react-bootstrap";
import {UserMenu} from "../UserMenu";
import {UserAPI} from "../../apis/UserAPI";
import {MediaFormPopup} from "../popups/MediaFormPopup";
import {useNavigate} from "react-router-dom";
import {USER_ID} from "../../index";
import {PostPutMediaRequestBuilder} from "../../models/PostPutMediaRequest";

const PAGE_SIZE = 9;
const CARDS_IN_ROW = 3;

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

    const handleFormSubmit = (postRequest) => {
        postRequest.userId = USER_ID;
        MediaAPI.post(postRequest)
            .then((response) => {
                let newMediaId = response.data.id;
                navigate(`/media/${newMediaId}`);
            });
    };

    return (
        <>
            <MediaFormPopup show={showMediaForm} onChangeShow={setShowMediaForm} onSubmit={handleFormSubmit} initialData={PostPutMediaRequestBuilder.getDefault()}/>
            <Row>
                <Col lg={4}>
                    <UserMenu user={user} onAddMediaClick={() => setShowMediaForm(true)}/>
                </Col>
                <Col lg={8}>
                    <MediaCards medias={medias} cardsInRow={CARDS_IN_ROW}/>
                    <PageSelector pageIndex={searchParameters.pageIndex} onPageChange={onPageChange}/>
                </Col>
            </Row>
        </>
    );
}