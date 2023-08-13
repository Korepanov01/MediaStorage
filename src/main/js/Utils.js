export function utils (array, chunkSize) {
    let chunks = [];
    for (let i = 0; i < array.length; i += chunkSize) {
        const chunk = array.slice(i, i + chunkSize);
        chunks.push(chunk);
    }
    return chunks;
}

export function authHeader() {
    const user = JSON.parse(localStorage.getItem('user'));

    if (user && user.jwt) {
        return { Authorization: 'Bearer ' + user.jwt};
    } else {
        return {};
    }
}