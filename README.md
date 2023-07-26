# Описание

## Архитектура
### Слои приложения
Слои приложения представлены на рисунке:
![diagrams-Слои](https://github.com/Korepanov01/MediaStorage/assets/87029646/4f184561-1588-41e0-8026-c3bdb4ba15d9)

### База данных
Модель данных создана с помощью ресурса [DB Designer](https://dbdesigner.net) ([cсылка на проект](https://dbdesigner.page.link/4bQb9XbETWbZPmYv9)).

Итоговая модель данных выглядит следующим образом ([код разметки для DB Designer](doc/data/dbdesigner.code)):
![mediastorage (1)](https://github.com/Korepanov01/MediaStorage/assets/87029646/29899cff-1455-4aa0-b5e1-c0bec03be7cf)

Выделены следующие индексы:
Индекс | Уникальность | Комментарий
--- | --- | ---
media.name | - | Для поиска медиа по названию
media.media_type_id | - | Для поиска медиа по типу
media.category_id | - | Для поиска медиа по категории
media.created_at | - | Для сортировки медиа по дате создания
media_edited_at | - | Для сортировки медиа по дате изменения
tag.name | + | Для поиска тегов по названию и исключения повторений имён
media_tag.media_id | - | Для поиска тегов у медиа
media_tag.tag_id | - | Для поиска медиа по тегам
(media_tag.media_id, media_tag.tag_id) | + | Для исключения повторяющихся связей
user.name | + | Для исключения повторяющихся никнеймов
user.email | + | Для исключения повторяющихся адресов
category.name | + | Для поиска категории по названию и исключения повторяющихся имён
category.parent_category_id | - | Для поиска подкатегорий по родительской категории
role.name | + | Для поиска ролей по имени и исключения повторяющихся имён
user_role.user_id | - | Для поиска ролей пользователя
(user_role.role_id, user_role.user_id) | + | Для исключения повторяющихся связей
media_file.media_id | - | Для поиска файлов у медиа
media_file.file_type_id | - | Для поиска файлов определенного типа
(media_file.media_id, media_file.media_id) | + | Для исключения повторяющихся связей
file_type.name | + | Для исключения повторений названий
media_type.name | + | Для исключения повторений названий
