import React, {useEffect, useState} from 'react';
import { Media } from './Media';
import {MediaAPI} from "../apis/MediaAPI"; // Import the previously defined Media component

export function MediaList({ mediaList }) {
    const [medias, setMedias] = useState([])

    useEffect(() => {
        MediaAPI.get().then(response => {
            setMedias(response.data)
        });
    }, []);

    return (
        <div>
            {medias.map((media) => (
                <Media key={media.id} media={media} />
            ))}
        </div>
    );
}