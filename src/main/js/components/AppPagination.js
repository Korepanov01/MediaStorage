import Pagination from 'react-bootstrap/Pagination';

import React, {useEffect, useState} from "react";

const NEXT_BUTTON_TEXT = "Далее";
const PREVIOUS_BUTTON_TEXT = "Назад";

export function AppPagination({ onPagination: onPagination }) {
    const [pageIndex, setPageIndex] = useState(0);

    const handleNextClick = () => {
        let newIndex = pageIndex + 1;
        setPageIndex(newIndex);
        onPagination(newIndex);
    };

    const handlePreviousClick = () => {
        if (pageIndex <= 0) return;

        let newIndex = pageIndex - 1;
        setPageIndex(newIndex);
        onPagination(newIndex);
    };

    return (
        <Pagination className="justify-content-between">
            <Pagination.Prev onClick={ handlePreviousClick } active={ pageIndex > 0 }>
                { PREVIOUS_BUTTON_TEXT }
            </Pagination.Prev>
            <Pagination.Item>
                { pageIndex + 1 }
            </Pagination.Item>
            <Pagination.Next onClick={ handleNextClick }>
                { NEXT_BUTTON_TEXT }
            </Pagination.Next>
        </Pagination>
    );
}