class Tag {
    constructor() {
        this.id = null;
        this.name = "";
    }
}

export const TagBuilder = {
    getDefault: () => new Tag(),

    buildByParams: (id, name) => {
        let tag = new Tag();
        tag.id = id;
        tag.name = name;
        return tag;
    },

    buildByData: (data) => {
        return TagBuilder.buildByParams(
            data.id,
            data.name
        );
    },

    buildArrayByData: (data) => {
        data.forEach(x => TagBuilder.buildByData(x));
        return data;
    }
};