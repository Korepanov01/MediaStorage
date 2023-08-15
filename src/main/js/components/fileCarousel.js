import React from "react";
import {Carousel, Image} from "react-bootstrap";
import {Defaults} from "../enums/defaults";

export function FilesCarousel({filesUrls: filesUrls}) {
    return (
        <Carousel>
            {filesUrls.length === 0 &&
                <Carousel.Item>
                    <Image src={Defaults.defaultImageUrl}/>
                </Carousel.Item>
            }
            {filesUrls.map((url, i) => (
                <Carousel.Item key={i}>
                    <Image src={url}/>
                </Carousel.Item>
            ))}
        </Carousel>
    );
}