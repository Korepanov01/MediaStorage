class AuthInfo {
    constructor() {
        this.jwt = ""
        this.id = null;
        this.name = "";
        this.email = "";
        this.roles = []
    }
}

export const AuthInfoBuilder = {
    getDefault: () => new AuthInfo(),

    buildByParams: (jwt, id, name, email, roles) => {
        let authInfo = new AuthInfo();
        authInfo.jwt = jwt;
        authInfo.id = id;
        authInfo.name = name;
        authInfo.email = email;
        authInfo.roles = roles;
        return authInfo;
    },

    buildByData: (data) => {
        return AuthInfoBuilder.buildByParams(
            data.jwt,
            data.id,
            data.name,
            data.email,
            data.roles,
        )
    }
}