import React from "react";
import {Badge, Carousel} from "react-bootstrap";

const CAROUSEL_INTERVAL_MS = 2000;

export function TagsCarousel({tags}) {
    return (
        <Carousel interval={CAROUSEL_INTERVAL_MS} controls = {false}>
            {tags.map((tag) => (
                <Carousel.Item key={tag.id}>
                    <div className={'d-flex justify-content-center'}>
                        <Badge>{tag.name}</Badge>
                    </div>
                </Carousel.Item>
            ))}
        </Carousel>
    );
}