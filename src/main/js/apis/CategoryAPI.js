import {Api, useGet} from "./Api"

export const useGetCategories = (searchParameters) => useGet("/category", searchParameters);

export const CategoryAPI = {
    get: function (searchParameters) {
        return  Api.get("/category", {params: searchParameters});
    },
}