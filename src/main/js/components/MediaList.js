import React from "react";
import { Media } from './Media';

export function MediaList({ medias: medias }) {

    return (
        <>
            {medias.map((media) => (
                <Media key={media.id} media={media} />
            ))}
        </>
    );
}