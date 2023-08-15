import React, {useEffect, useState} from 'react';
import {MediaCards} from "../mediaCards";
import {PageSelector} from "../selectors/pageSelector";
import {Col, Row} from "react-bootstrap";
import {UserMenu} from "../userMenu";
import {MediaFormPopup} from "../popups/mediaFormPopup";
import {useNavigate} from "react-router-dom";
import {useSelector} from "react-redux";
import {getMedias, postMedia} from "../../apis/mediaAPI";

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
        getMedias({pageIndex, pageSize: PAGE_SIZE, userId: user?.id}).then(({data, error}) => {
            if (!error) setMedias(data);
        });
    }, [pageIndex]);

    function onPageChange(newPageIndex) {
        setPageIndex(newPageIndex);
    }

    const handleFormSubmit = (postRequest) => {
        postRequest.userId = user.id;
        postMedia(postRequest)
            .then(({data, error}) => {
                if (!error) navigate(`/media/${data.id}`);
            });
    };

    return (
        <>
            <MediaFormPopup show={showMediaForm} onChangeShow={setShowMediaForm} onSubmit={handleFormSubmit} initialData={{
                userId: 0,
                categoryId: 0,
                name: "string",
                description: "string",
                mediaTypeId: 0
            }}/>
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