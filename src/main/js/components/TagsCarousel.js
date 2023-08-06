import React, {useEffect, useState} from "react";
import {Carousel} from "react-bootstrap";
import {TagAPI} from "../apis/TagAPI";
import {TagSearchParameters} from "../models/searchparameters/TagSearchParameters";

const CAROUSEL_INTERVAL_MS = 2000;

export function TagsCarousel({ mediaId: mediaId }) {
    const searchParameters = {...new TagSearchParameters(100), mediaId: mediaId};
    const [tags, setTags] = useState([])

    useEffect(() => {
        TagAPI.get(searchParameters).then(response => {
            setTags(response.data);
        });
    }, []);

    return (
        <Carousel interval={CAROUSEL_INTERVAL_MS} controls = {false}>
            { tags.map((tag) => (
                <Carousel.Item key={ tag.id }>
                    <span>{ tag.name }</span>
                </Carousel.Item>
            ))}
        </Carousel>
    );
}