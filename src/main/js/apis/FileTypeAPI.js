import { Api } from "./Api"
import {FileTypeBuilder} from "../models/FileType";

export const FileTypeAPI = {
    get: async function () {
        return FileTypeBuilder.buildArrayByData((await Api.get("/file_types")).data);
    }
}