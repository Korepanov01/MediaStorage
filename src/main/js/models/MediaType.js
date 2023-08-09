class MediaType {
    constructor() {
        this.id = null;
        this.name = "";
    }
}

export const MediaTypeBuilder = {
    getDefault: () => new MediaType(),

    buildByParams: (id, name) => {
        let mediaType = new MediaType();
        mediaType.id = id;
        mediaType.name = name;
        return mediaType;
    },

    buildByData: (data) => {
        return MediaTypeBuilder.buildByParams(
            data.id,
            data.name
        );
    }
};