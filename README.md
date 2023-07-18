# Описание

## Архитектура
### Слои приложения
Слои приложения представлены на рисунке:
![diagrams-Слои](https://github.com/Korepanov01/MediaStorage/assets/87029646/4f184561-1588-41e0-8026-c3bdb4ba15d9)

### База данных
Модель данных создана с помощью ресурса [DB Designer](https://dbdesigner.net) ([cсылка на проект](https://dbdesigner.page.link/4bQb9XbETWbZPmYv9)).

Итоговая модель данных выглядит следующим образом ([код разметки для DB Designer](doc/data/dbdesigner.code)):
![db_model](https://github.com/Korepanov01/MediaStorage/assets/87029646/55bfd7b7-861a-4e10-bb36-3a3e75d20606)

Выделены следующие индексы:
Индекс | Описание
--- | ---
role.name | Для поиска ролей по имени
user_role.user_id | Для поиска ролей пользователя
media.name | Для поиска медиа по названию
media.media_type_id | Для поиска медиа по типу
media.category_id | Для поиска медиа по категории
media.created_at | Для сортировки медиа по дате создания
media_edited_at | Для сортировки медиа по дате изменения
tag.name | Для поиска тегов по названию
media_tag.media_id | Для поиска тегов у медиа
media_tag.tag_id | Для поиска медиа по тегам
category.name | Для поиска категории по названию
category.parent_category_id | Для поиска подкатегорий по родительской категории
media_file.media_id | Для поиска файлов у медиа
