import React, {useEffect, useState} from 'react';
import {MediaCards} from "../mediaCards";
import {getMedias} from "../../apis/mediaAPI";

const PARAMS = {pageSize: 20, randomOrder: true};
const CARDS_IN_ROW = 4;

export function MainPage() {
    const [medias, setMedias] = useState([])

    useEffect(() => {
        getMedias(PARAMS).then(({data: medias, error}) => {
            if (!error) setMedias(medias);
        });
    }, []);

    return (
        <MediaCards medias={medias} cardsInRow={CARDS_IN_ROW}/>
    );
}