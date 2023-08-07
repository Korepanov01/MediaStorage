import React, {useEffect, useState} from 'react';
import {MediaCards} from "../MediaCards";
import {MediaAPI} from "../../apis/MediaAPI";

const CARDS_IN_ROW = 4;

export function MainPage() {
    const [medias, setMedias] = useState([])

    useEffect(() => {
        MediaAPI.get({pageSize: 20, randomOrder: true}).then(response => {
            setMedias(response.data);
        });
    }, []);

    return (
        <MediaCards medias={medias} cardsInRow={CARDS_IN_ROW}/>
    );
}