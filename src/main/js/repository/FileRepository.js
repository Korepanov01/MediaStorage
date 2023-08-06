import {FileSearchParameters} from "../models/searchparameters/FileSearchParameters";
import {MediaFileAPI} from "../apis/MediaFileAPI";

const THUMBNAIL = "THUMBNAIL";

export const FileRepository = {
    getThumbnailUrl: async function (mediaId) {
        let searchParameters = { ...new FileSearchParameters(1), mediaId: mediaId, type: THUMBNAIL };
        let mediaFiles = (await MediaFileAPI.get(searchParameters)).data;
        if (mediaFiles.length === 0)
            return "https://media.istockphoto.com/id/1324356458/vector/picture-icon-photo-frame-symbol-landscape-sign-photograph-gallery-logo-web-interface-and.jpg?s=170667a&w=0&k=20&c=qUbo9HQ5a5ldsw6Q589Yze9hlrMpiZX-9zo2mwmA1Hg=";

        return mediaFiles[0].url;
    }
}