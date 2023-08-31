import React from "react";
import Pagination from 'react-bootstrap/Pagination';

export default function PageSelector({ onPageChange: onPageChange, pageIndex: pageIndex}) {

    return (
        <Pagination className="justify-content-between">
            <Pagination.Prev onClick={ () => { if (pageIndex > 0) onPageChange(pageIndex - 1)} } active={ pageIndex > 0 }>
                {Text.buttons.previousPage}
            </Pagination.Prev>

            <Pagination.Item>{pageIndex + 1}</Pagination.Item>

            <Pagination.Next onClick={ () => { onPageChange(pageIndex + 1)} } active>
                {Text.buttons.nextPage}
            </Pagination.Next>
        </Pagination>
    );
}