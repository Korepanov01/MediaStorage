import React from 'react';
import {Badge} from "react-bootstrap";
import {InfoCard} from "./decor/infoCard";
import {Title} from "./decor/title";

export function MediaInfo({media: media}) {
    return (
        <>
            <Title>{media.name}</Title>
            <InfoCard title={"Тип"}>
                {media.mediaType.name}
            </InfoCard>
            <InfoCard title={"Категория"}>
                {media.category.name}
            </InfoCard>
            {media.description &&
                <InfoCard title={"Описание"}>
                    {media.description}
                </InfoCard>
            }
            {media.tags.length !== 0 &&
                <InfoCard title={"Теги"}>
                    {media.tags.map(tag => (
                        <Badge key={tag.id}>{tag.name}</Badge>
                    ))}
                </InfoCard>
            }
        </>
    );
}