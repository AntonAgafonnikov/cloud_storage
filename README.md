# Дипломный проект "Облачное хранилище"

Разработанное приложение представляет собой RESTful API приложение для хранения файлов в облаке.
Заранее подготовленное веб-приложение (FRONT) подключается к разработанному сервису без доработок и использует его функционал.

# Краткая характеристика приложения

- Java 17;
- Spring Boot 3.0.11 + Spring Security;
- PostgreSQL 16.0;
- Node.js 19.7.0;
- Сборщик пакетов Maven;
- Развёртывание приложения осуществляется в Docker 24.0.5. Три образа (Back, Front и Postgres) собираются в один контейнер,
где могут взаимодействовать между собой.

# Схема приложения
![Schema.jpg](imagesForReadme%2FSchema.jpg)


# Запуск приложения

Для запуска используется скрипт start.sh, вызываемый командой:

`start start.sh`

На Front введите заранее подготовленного пользователя:

Login:      `bob`
Password:   `1234`

# Тестирование

## 1. Ввод команды запуска приложения

После ввода команды `start start.sh` проект пересобирается, запуская команды mvn clean и mvn package,
описанные в скрипте start.sh. Далее происходит остановка запущенных контейнеров Docker (команда `docker-compose stop`),
после чего осуществляется развёртывание нашего приложения (команда `docker-compose up --build -d`).

В результате, в Docker Desktop можем увидеть нашу связку контейнеров:

![DockerDesktop1.jpg](imagesForReadme%2FDockerDesktop1.jpg)

## 2. Проверка ответа от Front

Вводим в браузере адрес, на котором должен быть развёрнут Front:

`http://localhost:8080`

Нас автоматически перебрасывает на форму вводу логина и пароля:

![Front1.jpg](imagesForReadme%2FFront1.jpg)

Front развернулся корректно.

## 3. Проверка ответа от Back и базы данных

В базу заранее, с помощью миграций, введены 3 пользователя:

![db1.jpg](imagesForReadme%2Fdb1.jpg)

Их пароли кодировались после регистрации, но в оригинале выглядят как `1234`. Вводим данные пользователя `bob`.
Видим, что Back получил данный запрос с Front:

![Front2.jpg](imagesForReadme%2FFront2.jpg)

и вернул ответ в виде JWT-токена (срок жизни - 60 минут):

![Front3.jpg](imagesForReadme%2FFront3.jpg)

После этого, Front отправил запрос с Header - Auth-Token, равному полученному значению:

![Front4.jpg](imagesForReadme%2FFront4.jpg)

Back вернул описание, принадлежащих пользователю `bob` файлов (заранее загруженных, при прошлом тестировании):

![Front5.jpg](imagesForReadme%2FFront5.jpg)

Front распарсил этот ответ и представил его в более понятном пользователю виде:

![Front6.jpg](imagesForReadme%2FFront6.jpg)

В базе данных видим тот же набор:

![db2.jpg](imagesForReadme%2Fdb2.jpg)

Попробуем разлогиниться, нажав на кнопку `Выйти`:

![logout1.jpg](imagesForReadme%2Flogout1.jpg)

Видим, что Front отправил Back запрос на эндпоинт `/logout`, после чего нас опять перебрасывает на форму ввода логина
и пароля:
 
![logout1.jpg](imagesForReadme%2Flogout2.jpg)

## 4. Тестирование оставшихся функций

Войдём под другим пользователем, чтобы убедиться, что файлы пользователя `bob` недоступны другим:

Login:      `sam`
Password:   `1234`

![Front7.jpg](imagesForReadme%2FFront7.jpg)

Убеждаемся, что здесь пусто. Попробуем добавить файл пользователю `sam`:

![Front8.jpg](imagesForReadme%2FFront8.jpg)

Смотрим базу данных:

![db3.jpg](imagesForReadme%2Fdb3.jpg)

Всё корректно отработало. Попробуем изменить имя файла:

![Front9.jpg](imagesForReadme%2FFront9.jpg)

![db4.jpg](imagesForReadme%2Fdb4.jpg)

Загрузим наш файл:

![Front10.jpg](imagesForReadme%2FFront10.jpg)

Удалим его:

![Front11.jpg](imagesForReadme%2FFront11.jpg)

![db5.jpg](imagesForReadme%2Fdb5.jpg)

## 5. Ввод команды остановки нашего приложения:
После ввода команды `start stop.sh` Docker останавливает работу наших контейнеров:

![DockerDesktop2.jpg](imagesForReadme%2FDockerDesktop2.jpg)

# Заключение

Приложение корректно развёртывается, выполняет обработку всех запросов от Front и успешно взаимодействует с базой данных.