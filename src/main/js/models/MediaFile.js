class MediaFile {
    constructor() {
        this.fileId = "";
        this.url = "";
        this.fileType = "";
    }
}

export const MediaFileBuilder = {
    getDefault: () => new MediaFile(),

    buildByParams: (fileId, url, fileType) => {
        let mediaFile = new MediaFile();
        mediaFile.fileId = fileId;
        mediaFile.url = url;
        mediaFile.fileType = fileType;
        return mediaFile;
    },

    buildByData: (data) => {
        return MediaFileBuilder.buildByParams(
            data.fileId,
            data.url,
            data.fileType
        );
    },

    buildArrayByData: (data) => {
        data.forEach(x => MediaFileBuilder.buildByData(x));
        return data;
    }
};