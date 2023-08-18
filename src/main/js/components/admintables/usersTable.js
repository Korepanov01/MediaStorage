import React, {useLayoutEffect, useState} from "react";
import {Button, ListGroup, Row, Col, Form, Badge} from "react-bootstrap";
import {PageSelector} from "../selectors/pageSelector";
import {toast} from "react-toastify";
import {SearchBar} from "../selectors/searchBar";
import {deleteUser, getUsers} from "../../apis/userAPI";
import {Roles} from "../../enums/roles";
import {giveAdmin, removeAdmin} from "../../apis/userRoleAPI";

const PAGE_SIZE = 100;

export default function UsersTable({currentUser}) {
    const [searchParameters, setSearchParameters] = useState({pageSize: PAGE_SIZE, pageIndex: 0})
    const [users, setUsers] = useState([])

    useLayoutEffect(() => {
        getUsers(searchParameters).then(({error, data}) => {
            if (!error) setUsers(data.filter(user => user.id !== currentUser.id));
        })
    }, [searchParameters]);

    function handleDeleteClick(id, name) {
        deleteUser(id).then(({error}) => {
            if (!error) {
                toast.success(`Пользователь "${name}" удален`);
                setUsers(users.filter(user => user.id !== id));
            }
        });
    }

    function handleAdminChange(userId, checked) {
        (checked ? giveAdmin(userId) : removeAdmin(userId))
            .then(({error}) => {
                if (!error) {
                    toast.success("Права изменены");
                    let newRoles = checked
                        ? (roles) => [...roles, Roles.ADMIN]
                        : (roles) => roles.filter(role => role !== Roles.ADMIN);
                    setUsers(users.map(user => user.id === userId ? {...user, roles: newRoles(user.roles)} : user));
                }
            })
    }

    return (
        <>
            <SearchBar onSearchStringChange={(searchString) => setSearchParameters({
                ...searchParameters,
                searchString: searchString
            })}/>
            <ListGroup>
                {users.map(user => (
                    <ListGroup.Item key={user.id}>
                        <Row className="align-items-center">
                            <Col>
                                <span>{user.name}</span>
                            </Col>
                            <Col>
                                <span>{user.email}</span>
                            </Col>
                            <Col>
                                {user.roles.includes(Roles.SUPER_ADMIN) &&
                                    <Badge className="bg-danger">Супер-админ</Badge>
                                    ||
                                    <>
                                        {currentUser.roles.includes(Roles.SUPER_ADMIN) &&
                                            <Form.Check type="switch" label="Админ" checked={user.roles.includes(Roles.ADMIN)} onChange={(e) => handleAdminChange(user.id, e.target.checked)}/>
                                        }
                                    </>
                                }

                            </Col>
                            <Col className="d-flex justify-content-end align-content-center">
                                <Button variant="danger"
                                        onClick={() => handleDeleteClick(user.id, user.name)}>Удалить</Button>
                            </Col>
                        </Row>
                    </ListGroup.Item>
                ))}
            </ListGroup>
            <PageSelector pageIndex={searchParameters.pageIndex} onPageChange={(newPageIndex) => setSearchParameters({
                ...searchParameters,
                pageIndex: newPageIndex
            })}/>
        </>
    )
}