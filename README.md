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
4. Кликнуть на тестовый класс DebitTest - запустить тесты кнопкой `Run` 
5. Кликнуть на тестовый класс CreditTest - запустить тесты кнопкой `Run`
6. Сформировать отчет командой `.\gradlew allureServe`

