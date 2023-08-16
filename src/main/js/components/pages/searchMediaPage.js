import React, {useEffect, useLayoutEffect, useState} from 'react';
import {MediaCards} from "../mediaCards";
import {PageSelector} from "../selectors/pageSelector";
import {Button, Card, Col, Form, FormGroup, Row} from "react-bootstrap";
import {getMedias} from "../../apis/mediaAPI";
import {Title} from "../decor/title";
import {TagsSelector} from "../selectors/tagsSelector";
import {CategorySelector} from "../selectors/categorySelector";
import {InfoCard} from "../decor/infoCard";

const PAGE_SIZE = 9;
const CARDS_IN_ROW = 3;

export function SearchMediaPage() {
    const [medias, setMedias] = useState([])
    const [selectedTags, setSelectedTags] = useState([]);
    const [searchString, setSearchString] = useState("");
    const [searchParameters, setSearchParameters] = useState({
        pageIndex: 0,
        pageSize: PAGE_SIZE,
        categoryId: null,
        typeIds: [],
    });

    useLayoutEffect(updateMedias, []);

    function updateMedias() {
        getMedias({...searchParameters,
            tagIds: selectedTags.map(tag => tag.id),
            searchString: searchString
        }).then(({data: medias, error}) => {
            if (!error) setMedias(medias);
        });
    }

    return (
        <Row>
            <Col lg={4}>
                <Card title={"Поиск"}>
                    <Form>
                        <Form.Group>
                            <Form.Control
                                type="search"
                                placeholder="Название медиа"
                                value={searchString}
                                onChange={(e) => {setSearchString(e.target.value)}}
                            />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Тип</Form.Label>
                            {/*<MediaTypeSelector selectedTypesIds={selectedTypesIds} onSelect={setSelectedTypesIds}/>*/}
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Теги</Form.Label>
                            <TagsSelector
                                onSelect={(tag) => setSelectedTags([...selectedTags, tag])}
                                onUnselect={(tag) => setSelectedTags(selectedTags.filter(({id}) => id !== tag.id))}
                                selectedTags={selectedTags}/>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Категория</Form.Label>
                            {/*<CategorySelector onSelect={setCategoryId}/>*/}
                        </Form.Group>
                        <Form.Group>
                            <Button className="w-100" onClick={updateMedias}>Поиск</Button>
                        </Form.Group>
                    </Form>
                </Card>
            </Col>
            <Col lg={8}>
                <MediaCards medias={medias} cardsInRow={CARDS_IN_ROW}/>
                <PageSelector pageIndex={searchParameters.pageIndex} onPageChange={(newIndex) => setSearchParameters({...searchParameters, pageIndex: newIndex})}/>
            </Col>
        </Row>
    );
}