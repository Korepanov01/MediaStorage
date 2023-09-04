export const Text = {
    navbar: {
        brand: 'MediaStorage',
        searchTab: 'Поиск',
        profileTab: 'Личный кабинет',
        adminTab: 'Админ-панель',
    },
    toastsMessages: {
        successDeleteMedia: (mediaName) => `Медиа "${mediaName}" удалено`,
        successDeleteFile: "Файл удалён",
        successAddedFile: "Файл добавлен",
        successDataChanged: 'Данные изменены',
        successRegister: 'Упешная регистрация',
        successChangeTag: (oldName, newName) => `Изменен тег "${oldName}" -> "${newName}"`,
        successAddTag: (tagName) => `Тег "${tagName}" добавлен`,
    },
    titles: {
        changeDataMenu: 'Изменить',
        filesMenu: "Файлы",
        login: 'Вход',
        register: 'Регистрация'
    },
    buttons: {
        changeMediaMainData: 'Основное',
        changeMediaFiles: 'Файлы',
        changeMediaTags: 'Теги',
        delete: 'Удалить',
        logout: 'Выйти',
        register: 'Регистрация',
        login: 'Вход',
        nextPage: "Далее",
        previousPage: "Назад",
        submit: "Готово",
        add: "Добавить",
        close: "Закрыть",
    },
    placeholders: {
        searchString: "Поиск",
    },
    validationErrors: {
        nameRequired: "Название не может быть пустым",
        categoryNameTooLong: "Название категории не может быть длиннее 200 символов!",
        userNameTooLong: "Имя должно быть не больше 200 символов!",
        emailTooLong: "Почта должна быть не больше 500 символов!",
        invalidEmail: "Некорректный адрес электронной почты!",
        tagNameTooLong: "Название тега не может быть длиннее 200 символов!",
        mediaNameTooLong: "Название тега не может быть длиннее 200 символов!",
        passwordTooLong: "Пароль не должен быть больше 100 символов!",
        passwordTooShort: "Пароль должен иметь больше 8 символов!",
        mediaDescriptionTooLong: "Описание должно быть меньше 10 тыс. символов!",
        fileDoesntPicked: "Не выбран файл!",
        passwordRequired: "Введите пароль",
        repeatPasswordRequired: "Повторите пароль",
        passwordsAreNotTheSame: "Пароли должны совпадать",
        emailRequired: "Введите почту",

    },
    formLabels: {
        name: "Название",
        file: "Файл",
        type: "Тип",
        email: "Почта",
        description: "Описание",
        category: "Категория",
        password: "Пароль",
        repeatPassword: "Повторите пароль"
    }
}