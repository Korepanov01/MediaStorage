import React, {useEffect, useState} from "react";
import {Card} from "react-bootstrap";
import {TagsCarousel} from "./TagsCarousel";
import {FileRepository} from "../repository/FileRepository";

export function MediaCard({ media }) {
    const [thumnail, setThumbnail] = useState(null)


    useEffect(() => {
        FileRepository.getThumbnailUrl(media.id).then(url => {
            setThumbnail(url);
        });
    });

    return (
        <Card className={"flex-fill"} style={ {margin: "5px"} }>
            <Card.Img src={thumnail}/>
            <Card.Body>
                <Card.Title>{media.name}</Card.Title>
                <Card.Subtitle>Тип: {media.mediaType.name}</Card.Subtitle>
                <Card.Subtitle>Категория: {media.category.name}</Card.Subtitle>
                <Card.Subtitle>Пользователь: {media.user.name}</Card.Subtitle>
            </Card.Body>
            <Card.Footer>
                <Card.Subtitle>Теги:</Card.Subtitle>
                <TagsCarousel mediaId={ media.id }/>
            </Card.Footer>
        </Card>
    );
}