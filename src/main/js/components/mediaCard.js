import React, {useEffect, useState} from "react";
import {Card, ListGroup} from "react-bootstrap";
import {TagsCarousel} from "./tagsCarousel";
import {Link} from "react-router-dom";
import {FileAPI} from "../apis/fileAPI";
import {getTags} from "../apis/tagAPI";

export function MediaCard({ media }) {
    const [thumbnail, setThumbnail] = useState(null)
    const [tags, setTags] = useState([])

    useEffect(() => {
        getTags({mediaId: media.id}).then(({error, data: tags}) => {
            if(!error) setTags(tags);
        });
    }, []);

    useEffect(() => {
        FileAPI.getThumbnailUrl(media.id).then(url => {
            setThumbnail(url);
        });
    });

    return (
        <Link to={`/media/${media.id}`} style={{ textDecoration: 'none' }}>
            <Card className={"flex-fill"} style={{margin: "5px"}}>
                <Card.Img src={thumbnail}/>
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
                {tags.length !== 0 &&
                    <Card.Footer>
                        <TagsCarousel tags={tags}/>
                    </Card.Footer>
                }
            </Card>
        </Link>
    );
}