import React, {useEffect, useState} from "react";
import {Badge, Carousel} from "react-bootstrap";
import {TagAPI} from "../apis/TagAPI";

const CAROUSEL_INTERVAL_MS = 2000;

export function TagsCarousel({ mediaId: mediaId }) {
    const [tags, setTags] = useState([])

    useEffect(() => {
        TagAPI.getAllByMedia(mediaId).then(tags => {
            setTags(tags);
        });
    }, []);

    return (
        <Carousel interval={CAROUSEL_INTERVAL_MS} controls = {false}>
            {tags.map((tag) => (
                <Carousel.Item key={tag.id}>
                    <Badge>{tag.name}</Badge>
                </Carousel.Item>
            ))}
        </Carousel>
    );
}