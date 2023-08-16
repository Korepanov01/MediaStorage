import React from "react";
import {Carousel, Image} from "react-bootstrap";
import {defaults} from "../enums/defaults";
import {InfoCard} from "./decor/infoCard";

export function FilesCarousel({filesUrls: filesUrls}) {
    return (
        <InfoCard title={"Файлы"}>
            <Carousel>
                {filesUrls.length === 0 &&
                    <Carousel.Item>
                        <Image className={"w-100"} src={defaults.defaultImageUrl}/>
                    </Carousel.Item>
                }
                {filesUrls.map((url, i) => (
                    <Carousel.Item key={i}>
                        <Image className={"w-100"} src={url}/>
                    </Carousel.Item>
                ))}
            </Carousel>
        </InfoCard>
    );
}