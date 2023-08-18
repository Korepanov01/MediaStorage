import React, {useLayoutEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import {Badge, Col, Row, Spinner} from "react-bootstrap";
import {getMediaById} from "../../apis/mediaAPI";
import {Files} from "../files";
import {FileTypes} from "../../enums/fileTypes";
import {MediaRedactor} from "../mediaRedactor";
import {useSelector} from "react-redux";
import {Title} from "../decor/title";
import {InfoCard} from "../decor/infoCard";

export function MediaPage() {
    const {id} = useParams();

    const authUserId = useSelector(state => state.auth.user?.id);

    const [isLoading, setIsLoading] = useState(true);
    const [media, setMedia] = useState(null);

    useLayoutEffect(() => {
        getMediaById(id).then(({data: media, error}) => {
            if (!error) {
                setMedia(media);
                setIsLoading(false);
            }
        });
    }, []);

    return (
        <>
            {isLoading ? (
                <Spinner/>
            ) : (
                <Row>
                    <Col lg={4}>
                        <InfoCard title={"Название"}>
                            <Title level={5}>{media.name}</Title>
                        </InfoCard>
                        <InfoCard title={"Тип"}>
                            <Title level={6}>{media.mediaType.name}</Title>
                        </InfoCard>
                        <InfoCard title={"Категория"}>
                            <Title level={6}>{media.category.name}</Title>
                        </InfoCard>
                        {media.description &&
                            <InfoCard title={"Описание"}>
                                {media.description}
                            </InfoCard>
                        }
                        {media.tags.length !== 0 &&
                            <InfoCard title={"Теги"}>
                                {media.tags.map(tag => (
                                    <Badge key={tag.id}>{tag.name}</Badge>
                                ))}
                            </InfoCard>
                        }
                        {(authUserId === media.user.id) &&
                            <MediaRedactor media={media} setMedia={setMedia}/>
                        }
                    </Col>
                    <Col lg={8}>
                        <Files filesUrls={media.files.filter(file => file.type === FileTypes.main).map(file => file.url)} mediaTypeName={media.mediaType.name}/>
                    </Col>
                </Row>
            )}
        </>
    );
}