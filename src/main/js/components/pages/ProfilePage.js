import React, {useEffect, useLayoutEffect, useState} from 'react';
import {MediaCards} from "../MediaCards";
import {MediaAPI} from "../../apis/MediaAPI";
import {PageSelector} from "../selectors/PageSelector";
import {Col, Row} from "react-bootstrap";
import {UserMenu} from "../UserMenu";
import {MediaFormPopup} from "../popups/MediaFormPopup";
import {useNavigate} from "react-router-dom";
import {PostPutMediaRequestBuilder} from "../../models/PostPutMediaRequest";
import {useSelector} from "react-redux";

const PAGE_SIZE = 9;
const CARDS_IN_ROW = 3;

export function ProfilePage() {
    const [medias, setMedias] = useState([])
    const [pageIndex, setPageIndex] = useState(0);
    const [showMediaForm, setShowMediaForm] = useState(false);

    const navigate = useNavigate();
    const user = useSelector(state => state.auth.user);
    const isLoggedIn = useSelector(state => state.auth.isLoggedIn);

    if (!isLoggedIn)
        navigate("/");

    useEffect(() => {
        MediaAPI.get({pageIndex, pageSize: PAGE_SIZE, userId: user?.id}).then(response => {
            setMedias(response.data);
        });
    }, [pageIndex]);

    function onPageChange(newPageIndex) {
        setPageIndex(newPageIndex);
    }

    const handleFormSubmit = (postRequest) => {
        postRequest.userId = user.id;
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
                    <PageSelector pageIndex={pageIndex} onPageChange={onPageChange}/>
                </Col>
            </Row>
        </>
    );
}