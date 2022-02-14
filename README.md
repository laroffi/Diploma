**Тестирование веб-приложения "Путешествие дня"**

Описание приложения: веб-сервис для приобретения тура по дебетовой карте или выдача кредита по данным банковской карты.
![Приложение](https://user-images.githubusercontent.com/80891415/153856780-8259632d-88a4-4d55-b7b4-72c00878d5d8.jpg)

Само приложение не обрабатывает данные по картам, а пересылает их банковским сервисам:

- сервису платежей (далее - Payment Gate)
- кредитному сервису (далее - Credit Gate)

Приложение должно в собственной СУБД сохранять информацию о том, каким способом был совершён платёж и успешно ли он был совершён (при этом данные карт сохранять не допускается).

**Основная документация проекта**

- [План автоматизации проекта](https://github.com/laroffi/Diploma/blob/master/Docs/Plan.md)
- [Отчет по итогам тестирования](https://github.com/laroffi/Diploma/blob/master/Docs/Report.md)
- [Отчет по итогам автоматизации](https://github.com/laroffi/Diploma/blob/master/Docs/Summary.md)

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
9. Остановить контейнеры командой `docker-compose down`

