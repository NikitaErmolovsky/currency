# Запуск с докером 
1. сделать docker pull nikitaermolovsky/currency
2. выполнить команду docker run -p 8080:8080 nikitaermolovsky/currency
3. зайти на localhost:8080
___
# Сборка проекта без докера 
1. выполнить команду git clone https://github.com/NikitaErmolovsky/currency.git
2. выполнить команду cd currency
3. создать файл gradle.properties
4. внутри созданного файла написать org.gradle.java.home = путь_к_jdk_11
5. забилдить командой .\gradlew.bat build (./gradlew build в linux)
6. запустить командой .\gradlew.bat bootRun (./gradlew bootRun в linux)
7. зайти на localhost:8080
___
# Настройки 
-  параметры вынесены в файл application.properties
-  **указать значения myAppId (для api курса валют) и myGifId (для api гифок)**
- для изменения валюты, по отношению к которой смотрится курс доллара, нужно изменить значение перемнной currency



