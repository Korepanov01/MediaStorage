import Pagination from 'react-bootstrap/Pagination';

import React from "react";

const NEXT_BUTTON_TEXT = "Далее";
const PREVIOUS_BUTTON_TEXT = "Назад";

export function AppPagination({ onPageChange: onPageChange, pageIndex: pageIndex}) {

    return (
        <Pagination className="justify-content-between">
            <Pagination.Prev onClick={ () => { if (pageIndex > 0) onPageChange(pageIndex - 1)} } active={ pageIndex > 0 }>
                { PREVIOUS_BUTTON_TEXT }
            </Pagination.Prev>
            <Pagination.Item>
                { pageIndex + 1 }
            </Pagination.Item>
            <Pagination.Next onClick={ () => { onPageChange(pageIndex + 1)} } active>
                { NEXT_BUTTON_TEXT }
            </Pagination.Next>
        </Pagination>
    );
}