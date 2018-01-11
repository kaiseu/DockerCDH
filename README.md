# DockerCDH



#### Note
1. After CM installation on Ubuntu16.04, CM service failed to start: Caused by: java.lang.ClassNotFoundException: Could not load requested class : com.mysql.jdbc.Driver

Solved: 
```
sudo apt-get install libmysql-java
ln -s /usr/lib/jvm/java-7-oracle-cloudera/lib/mysql-connector-java.jar /usr/share/java/mysql-connector-java.jar
```
