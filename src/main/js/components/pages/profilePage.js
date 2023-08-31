import React, {useEffect, useState} from 'react';
import MediaCards from "../mediaCards";
import PageSelector from "../selectors/pageSelector";
import {Button, Col, Row} from "react-bootstrap";
import MediaFormPopup from "../popups/mediaFormPopup";
import {useNavigate} from "react-router-dom";
import {useSelector} from "react-redux";
import {getMedias, postMedia} from "../../apis/mediaAPI";
import InfoCard from "../decor/infoCard";

const PAGE_SIZE = 9;
const CARDS_IN_ROW = 3;

export default function ProfilePage() {
    const [medias, setMedias] = useState([])
    const [pageIndex, setPageIndex] = useState(0);
    const [showMediaForm, setShowMediaForm] = useState(false);

    const navigate = useNavigate();
    const user = useSelector(state => state.auth.user ?? navigate("/"));

    useEffect(() => {
        getMedias({pageIndex, pageSize: PAGE_SIZE, userId: user?.id}).then(({data, error}) => {
            if (!error) setMedias(data);
        });
    }, [pageIndex]);

    function onPageChange(newPageIndex) {
        setPageIndex(newPageIndex);
    }

    const handleFormSubmit = (values) => {
        let payload = {
            userId: user.id,
            name: values.name,
            description: values.description,
            mediaTypeId: values.mediaTypeId,
            categoryId: values.category?.id
        }
        postMedia(payload)
            .then(({data, error}) => {
                if (!error) navigate(`/media/${data.id}`);
            });
    };

    return (
        <>
            <MediaFormPopup show={showMediaForm} onChangeShow={setShowMediaForm} onSubmit={handleFormSubmit}/>
            <Row>
                <Col lg={4}>
                    <InfoCard title={"Имя"}>
                        {user.name}
                    </InfoCard>
                    <InfoCard title={"Почта"}>
                        {user.email}
                    </InfoCard>
                    <Button className={"w-100"} onClick={() => setShowMediaForm(true)}>Добавить медиа</Button>
                </Col>
                <Col lg={8}>
                    <MediaCards medias={medias} cardsInRow={CARDS_IN_ROW}/>
                    <PageSelector pageIndex={pageIndex} onPageChange={onPageChange}/>
                </Col>
            </Row>
        </>
    );
}