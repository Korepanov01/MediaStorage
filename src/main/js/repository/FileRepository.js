import {FileTypes, MediaFileAPI} from "../apis/MediaFileAPI";

const DEFAULT_IMAGE_URL = "https://media.istockphoto.com/id/1324356458/vector/picture-icon-photo-frame-symbol-landscape-sign-photograph-gallery-logo-web-interface-and.jpg?s=170667a&w=0&k=20&c=qUbo9HQ5a5ldsw6Q589Yze9hlrMpiZX-9zo2mwmA1Hg=";

export const FileRepository = {
    getThumbnailUrl: async function (mediaId) {
        let searchParameters = {pageIndex: 0, pageSize: 1, mediaId: mediaId, type: FileTypes.thumbnail};
        let mediaFiles = (await MediaFileAPI.get(searchParameters)).data;
        if (mediaFiles.length === 0)
            return DEFAULT_IMAGE_URL;

        return mediaFiles[0].url;
    },

    getMainUrls: async function (mediaId) {
        let searchParameters = {pageIndex: 0, pageSize: 100, mediaId: mediaId, type: FileTypes.main};
        let mediaFiles = (await MediaFileAPI.get(searchParameters)).data;
        if (mediaFiles.length === 0)
            return  [DEFAULT_IMAGE_URL];

        return mediaFiles.map(mediaFile => mediaFile.url);
    }
}