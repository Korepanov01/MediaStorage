import React from "react";
import {Badge, Carousel} from "react-bootstrap";
import {Properties} from "../properties";

export default function TagsCarousel({tags}) {
    return (
        <Carousel interval={Properties.tagsCarouselIntervalMs} controls = {false}>
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