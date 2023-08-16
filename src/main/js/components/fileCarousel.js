import React from "react";
import {Button, Carousel, Image} from "react-bootstrap";
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
                        <a href={url} download>
                            <Image className={"w-100"} src={url}/>
                        </a>
                    </Carousel.Item>
                ))}
            </Carousel>
        </InfoCard>
    );
}