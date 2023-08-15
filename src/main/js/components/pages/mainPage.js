import React, {useEffect, useState} from 'react';
import {MediaCards} from "../mediaCards";
import {getMedias} from "../../apis/mediaAPI";

const CARDS_IN_ROW = 4;

export function MainPage() {
    const [medias, setMedias] = useState([])

    useEffect(() => {
        getMedias({pageSize: 20, randomOrder: true}).then(({data, error}) => {
            if (!error) setMedias(data);
        });
    }, []);

    return (
        <MediaCards medias={medias} cardsInRow={CARDS_IN_ROW}/>
    );
}