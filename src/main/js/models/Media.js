import {UserBuilder} from "./User";
import {CategoryBuilder} from "./Category";
import {MediaTypeBuilder} from "./MediaType";

class Media {
    constructor() {
        this.id = null;
        this.name = "";
        this.user = UserBuilder.getDefault();
        this.category = CategoryBuilder.getDefault();
        this.mediaType = MediaTypeBuilder.getDefault();
        this.description = "";
        this.createdAt = new Date();
        this.editedAt = new Date();
    }
}

export const MediaBuilder = {
    getDefault: () => new Media(),

    buildByParams: (id, name, user, category, mediaType, description, createdAt, editedAt) => {
        let media = new Media();
        media.id = id;
        media.name = name;
        media.user = user;
        media.category = category;
        media.mediaType = mediaType;
        media.description = description;
        media.createdAt = createdAt;
        media.editedAt = editedAt;
        return media;
    },

    buildByData: (data) => {
        const user = UserBuilder.buildByData(data.user);
        const category = CategoryBuilder.buildByData(data.category);
        const mediaType = MediaTypeBuilder.buildByData(data.mediaType);

        return MediaBuilder.buildByParams(
            data.id,
            data.name,
            user,
            category,
            mediaType,
            data.description,
            new Date(data.createdAt),
            new Date(data.editedAt)
        );
    }
};