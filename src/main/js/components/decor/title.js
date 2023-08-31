import React from "react";

export default function Title({ level = 4, children }) {
    const Heading = `h${level}`;

    return (
        <Heading className="text-center mb-0">{children}</Heading>
    );
}