import React from "react";
import {Carousel, Image} from "react-bootstrap";
import {InfoCard} from "./decor/infoCard";
import {MediaTypes} from "../enums/mediaTypes";

export function Files({filesUrls, mediaTypeName}) {
    return (
        <InfoCard title={"Файлы"}>
            {filesUrls.length !== 0 &&
                <>
                    <Carousel>
                        {filesUrls.map((url, i) => (
                            <Carousel.Item key={i}>
                                {mediaTypeName === MediaTypes.images &&
                                    <a href={url} download>
                                        <Image className={"w-100"} src={url}/>
                                    </a>
                                }
                                {mediaTypeName === MediaTypes.video &&
                                    <a href={url} download>
                                        <video controls className={"w-100"} key={i} src={url}/>
                                    </a>
                                }
                                {mediaTypeName === MediaTypes.audio &&
                                    <audio className={"w-100"} key={i} controls src={url}/>
                                }
                            </Carousel.Item>
                        ))}
                    </Carousel>
                </>
            }
        </InfoCard>
    );
}