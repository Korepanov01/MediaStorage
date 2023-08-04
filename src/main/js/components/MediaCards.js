import React from "react";
import { MediaCard } from './MediaCard';

export function MediaCards({ medias: medias }) {

    return (
        <>
            {medias.map((media) => (
                <MediaCard key={media.id} media={media} />
            ))}
        </>
    );
}