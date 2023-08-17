import React, {useLayoutEffect, useState} from "react";
import {Button, ListGroup, Row, Col, ButtonGroup} from "react-bootstrap";
import {deleteTag, getTags,} from "../../apis/tagAPI";
import {PageSelector} from "../selectors/pageSelector";
import {toast} from "react-toastify";
import {ChangeTagFormPopup} from "../popups/changeTagFormPopup";
import {AddTagForm} from "../forms/addTagForm";
import {SearchBar} from "../selectors/searchBar";
import {deleteUser, getUsers} from "../../apis/userAPI";

const PAGE_SIZE = 100;

export default function UsersTable({currentUserId}) {
    const [searchParameters, setSearchParameters] = useState({pageSize: PAGE_SIZE, pageIndex: 0})
    const [users, setUsers] = useState([])

    useLayoutEffect(() => {
        getUsers(searchParameters).then(({error, data}) => {
            if (!error) setUsers(data.filter(user => user.id !== currentUserId));
        })
    }, [searchParameters]);

    function handleDeleteClick(id, name) {
        deleteUser(id).then(({error}) => {
            if (!error) {
                toast.success(`Тег "${name}" удален`);
                setUsers(users.filter(user => user.id !== id));
            }
        });
    }

    return (
        <>
            <SearchBar onSearchStringChange={(searchString) => setSearchParameters({...searchParameters, searchString: searchString})}/>
            <ListGroup>
                {users.map(user => (
                    <ListGroup.Item key={user.id}>
                        <Row>
                            <Col>
                                <span>{user.name}</span>
                                <span>{user.email}</span>
                            </Col>
                            <Col className={"d-flex justify-content-end align-content-center"}>
                                <ButtonGroup>
                                    <Button variant={"danger"} onClick={() => handleDeleteClick(user.id, user.name)}>Удалить</Button>
                                </ButtonGroup>
                            </Col>
                        </Row>
                    </ListGroup.Item>
                ))}
            </ListGroup>
            <PageSelector pageIndex={searchParameters.pageIndex} onPageChange={(newPageIndex) => setSearchParameters({...searchParameters, pageIndex: newPageIndex})}/>
        </>
    )
}