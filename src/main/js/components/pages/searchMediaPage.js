import React, {useLayoutEffect, useState} from 'react';
import {MediaCards} from "../mediaCards";
import {PageSelector} from "../selectors/pageSelector";
import {Button, Card, Col, Form, Row} from "react-bootstrap";
import {getMedias} from "../../apis/mediaAPI";
import {Title} from "../decor/title";
import {TagsSelector} from "../selectors/tagsSelector";
import {MediaTypeSelector} from "../selectors/mediaTypeSelector";
import {CategorySelector} from "../selectors/categorySelector";

const PAGE_SIZE = 9;
const CARDS_IN_ROW = 3;

export function SearchMediaPage() {
    const [medias, setMedias] = useState([])

    const [pageIndex, setPageIndex] = useState(0);
    const [selectedTags, setSelectedTags] = useState([]);
    const [selectedTypesIds, setSelectedTypesIds] = useState([]);
    const [searchString, setSearchString] = useState("");
    const [selectedCategory, setSelectedCategory] = useState(null);

    useLayoutEffect(updateMedias, [pageIndex]);

    function updateMedias() {
        getMedias({
            pageIndex,
            pageSize: PAGE_SIZE,
            tagIds: selectedTags.map(tag => tag.id),
            searchString: searchString,
            categoryId: selectedCategory?.id,
            typeIds: selectedTypesIds
        }).then(({data: medias, error}) => {
            if (!error) setMedias(medias);
        });
    }

    return (
        <Row>
            <Col lg={4}>
                <Card title={"Поиск"}>
                    <Card.Header><Title>Поиск</Title></Card.Header>
                    <Form>
                        <Card.Body>
                            <Form.Group>
                                <Form.Control
                                    type="search"
                                    placeholder="Название медиа"
                                    value={searchString}
                                    onChange={(e) => {
                                        setSearchString(e.target.value)
                                    }}
                                />
                            </Form.Group>
                            <Form.Group>
                                <Form.Label>Тип</Form.Label>
                                <MediaTypeSelector selectedTypesIds={selectedTypesIds} setSelectedTypesIds={setSelectedTypesIds}/>
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
                                <CategorySelector selectedCategory={selectedCategory} setSelectedCategory={setSelectedCategory}/>
                            </Form.Group>
                        </Card.Body>
                        <Card.Footer>
                            <Form.Group>
                                <Button className="w-100" onClick={updateMedias}>Поиск</Button>
                            </Form.Group>
                        </Card.Footer>
                    </Form>
                </Card>
            </Col>
            <Col lg={8}>
                <MediaCards medias={medias} cardsInRow={CARDS_IN_ROW}/>
                <PageSelector pageIndex={pageIndex} onPageChange={setPageIndex}/>
            </Col>
        </Row>
    );
}