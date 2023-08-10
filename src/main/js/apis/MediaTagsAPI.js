import { Api } from "./Api"

export const MediaTagAPI = {
    post: function (mediaId, tagId) {
        return Api.post('/media_tag', {mediaId: mediaId, tagId: tagId})
    },

    delete: function (mediaId, tagId) {
        return Api.delete('/media_tag', {data: {mediaId: mediaId, tagId: tagId}});
    }
}
