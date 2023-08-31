import React from "react";
import {Carousel, Image} from "react-bootstrap";
import InfoCard from "./decor/infoCard";
import {MediaTypes} from "../enums/mediaTypes";
import {Text} from "../text";

export default function Files({filesUrls, mediaTypeName}) {
    return (
        <InfoCard title={Text.titles.filesMenu}>
            {filesUrls.length !== 0 &&
                <Carousel style={{paddingBottom: "10px"}}>
                    {filesUrls.map((url, i) => (
                        <Carousel.Item key={i} style={{padding: "0 100px 0 100px"}}>
                            {mediaTypeName === MediaTypes.images &&
                                <a href={url} download>
                                    <Image className={"w-100"} src={url}/>
                                </a>
                            }
                            {mediaTypeName === MediaTypes.video &&
                                <video controls className={"w-100"} key={i} src={url}/>
                            }
                            {mediaTypeName === MediaTypes.audio &&
                                <audio className={"w-100"} key={i} controls src={url}/>
                            }
                        </Carousel.Item>
                    ))}
                </Carousel>
            }
        </InfoCard>
    );
}