import React from "react";
import {Card, ListGroup} from "react-bootstrap";
import {TagsCarousel} from "./tagsCarousel";
import {Link} from "react-router-dom";
import {defaults} from "../enums/defaults";

export function MediaCard({media}) {
    return (
        <Link to={`/media/${media.id}`} style={{ textDecoration: 'none' }}>
            <Card className={"flex-fill"} style={{margin: "5px"}}>
                <Card.Img src={media.thumbnailUrl ?? defaults.defaultImageUrl}/>
                <Card.Body>
                    <Card.Title className={"text-center"}>{media.name}</Card.Title>
                    <ListGroup>
                        <ListGroup.Item className={"text-center"}>
                            {media.mediaType.name}
                        </ListGroup.Item>
                        <ListGroup.Item className={"text-center"}>
                            {media.category.name}
                        </ListGroup.Item>
                    </ListGroup>
                </Card.Body>
                {media.tags.length !== 0 &&
                    <Card.Footer>
                        <TagsCarousel tags={media.tags}/>
                    </Card.Footer>
                }
            </Card>
        </Link>
    );
}