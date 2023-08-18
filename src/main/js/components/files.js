import React from "react";
import {Carousel, Image} from "react-bootstrap";
import {InfoCard} from "./decor/infoCard";
import {MediaTypes} from "../enums/mediaTypes";

export function Files({filesUrls, mediaTypeName}) {
    return (
        <InfoCard title={"Файлы"}>
            {filesUrls.length !== 0 &&
                <>
                    {mediaTypeName === MediaTypes.images &&
                        <Carousel>
                            {filesUrls.map((url, i) => (
                                <Carousel.Item key={i}>
                                    <a href={url} download>
                                        <Image className={"w-100"} src={url}/>
                                    </a>
                                </Carousel.Item>
                            ))}
                        </Carousel>
                    }
                    {mediaTypeName === MediaTypes.audio &&
                        <>
                            {filesUrls.map((url, i) => (
                                <audio className={"w-100"} key={i} controls src={url}/>
                            ))}
                        </>
                    }
                    {mediaTypeName === MediaTypes.video &&
                        <>
                            {filesUrls.map((url, i) => (
                                <video controls className={"w-100"} key={i} src={url}/>
                            ))}
                        </>
                    }
                </>
            }
        </InfoCard>
    );
}