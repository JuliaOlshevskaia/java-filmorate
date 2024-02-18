# java-filmorate
Template repository for Filmorate project.

<image src="схема БД.png" alt="Схема БД">

Описание таблиц:
1. Таблица films - для хранения фильмов
Запрос для фильма по id:
select * from films where id=1
   
2. Таблица users - для хранения информации о пользователях
Запрос для пользователя по id:
select * from users where id=1

3. Таблица friends - для хранения информации о друзьях и запросов друзей пользователей
Запрос для списка друзей пользователя по id:
select friend_id from friends where user_id=1

4. Таблица likes - для хранения лайков пользователей на фильмах
Запрос для посчета количества лайков фильма по ид:
select count(distinct user_id) count_likes from likes where film_id=1

5. Таблица genre - словарь жанров фильмов
6. Таблица genre_films - таблица для хранения жанров фильмов

Запрос для определения жанров фильма по ид:
select g.genre
from films f
left join genre_films gf on f.id=gf.film_id
left join genre g on gf.genre_id=g.genre_id
where f.id=1


   
