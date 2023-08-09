class User {
    constructor() {
        this.id = null;
        this.name = "";
    }
}

export const UserBuilder = {
    getDefault: () => new User(),

    buildByParams: (id, name) => {
        let user = new User();
        user.id = id;
        user.name = name;
        return user;
    },

    buildByData: (data) => {
        return UserBuilder.buildByParams(
            data.id,
            data.name
        );
    }

};