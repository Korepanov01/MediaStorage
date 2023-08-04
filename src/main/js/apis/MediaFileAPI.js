import { Api } from "./Api"

export const MediaFileAPI = {
    get: async function (searchParameters) {
        return await Api.get("/media_file", { params: searchParameters });
    }
}
