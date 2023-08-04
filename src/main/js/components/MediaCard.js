import React, {useEffect, useState} from "react";
import {Card, Image} from "react-bootstrap";
import {TagsCarousel} from "./TagsCarousel";
import {MediaAPI} from "../apis/MediaAPI";
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
                <Card.Subtitle>Пользователь: {media.user.name}</Card.Subtitle>
            </Card.Body>
            <Card.Footer>
                <TagsCarousel mediaId={ media.id }/>
            </Card.Footer>
        </Card>
    );
}