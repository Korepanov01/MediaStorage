class PostPutMediaRequest {
    constructor() {
        this.name = "";
        this.userId = null;
        this.categoryId = null;
        this.mediaTypeId = null;
        this.description = "";
    }
}

export const PostPutMediaRequestBuilder = {
    getDefault: () => new PostPutMediaRequest(),

    buildByParams: (name, userId, categoryId, mediaTypeId, description) => {
        let postPutMediaRequest = new PostPutMediaRequest();
        postPutMediaRequest.name = name;
        postPutMediaRequest.userId = userId;
        postPutMediaRequest.categoryId = categoryId;
        postPutMediaRequest.mediaTypeId = mediaTypeId;
        postPutMediaRequest.description = description;
        return postPutMediaRequest;
    },

    buildByMedia: (media) => {
        return PostPutMediaRequestBuilder.buildByParams(
            media.name,
            media.user.id,
            media.category.id,
            media.mediaType.id,
            media.description
        );
    }
};