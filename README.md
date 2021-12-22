****Запуск тестов****

Необходимое ПО для работы с репозиторием:
1. Windows 10
2. IntelliJIDEA Ultimate 2021.3
3. Docker 4.2.0
4. Java 11

***Шаги:***
1. Скачать репозиторий командой `git clone https://github.com/laroffi/Diploma`
2. Запустить контейнеры командой `docker-compose up -d`
3. Запустить приложение командой `java -jar aqa-shop.jar`
4. Для работы с СУБД MySQL в файле **build.gradle** в Test указать `systemProperty 'db.url', System.getProperty('db.url', 'jdbc:mysql://localhost:3306/app')`
5. Для работы с СУБД Postgres в файле **build.gradle** в Test указать`systemProperty 'db.url', System.getProperty('db.url', 'jdbc:postgresql://localhost:5432/postgres')`
6. Кликнуть на тестовый класс **Java** правой кнопкой мыши, нажать на `Run Tests in Diploma`
7. Сформировать отчет командами `.\gradlew allureReport` и `.\gradlew allureServe`
8. Остановить приложение командой `Ctrl+C`
9. Остарновить контейнеры командой `docker-compose down`

