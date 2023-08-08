import React from "react";
import {Carousel, Image} from "react-bootstrap";

export function FilesCarousel({filesUrls: filesUrls}) {
    return (
        <Carousel>
            {filesUrls.map((url, i) => (
                <Carousel.Item key={i}>
                    <Image src={url}/>
                </Carousel.Item>
            ))}
        </Carousel>
    );
}