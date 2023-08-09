class FileType {
    constructor() {
        this.id = null;
        this.name = "";
    }
}

export const FileTypeBuilder = {
    getDefault: () => new FileType(),

    buildByParams: (id, name) => {
        let fileType = new FileType();
        fileType.id = id;
        fileType.name = name;
        return fileType;
    },

    buildByData: (data) => {
        return FileTypeBuilder.buildByParams(
            data.id,
            data.name
        );
    },

    buildArrayByData: (data) => {
        data.forEach(x => FileTypeBuilder.buildByData(x));
        return data;
    }
};