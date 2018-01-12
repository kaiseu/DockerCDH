# DockerCDH
For easy installing CDH in Docker Containers with different OS.
## Test the installation
```
hadoop jar /usr/lib/hadoop-mapreduce/hadoop-mapreduce-examples.jar  pi 100 100

spark-submit --class org.apache.spark.examples.SparkPi --master yarn-client --num-executors 4 --executor-memory 1g --executor-cores 1 /usr/lib/spark/lib/spark-examples.jar 10
```


#### Note
1. After CM installation on Ubuntu16.04, CM service failed to start: Caused by: java.lang.ClassNotFoundException: Could not load requested class : com.mysql.jdbc.Driver

Solved: 
```
sudo apt-get install libmysql-java
ln -s /usr/lib/jvm/java-7-oracle-cloudera/lib/mysql-connector-java.jar /usr/share/java/mysql-connector-java.jar
```

2. CM service failed to start on Debian8.4
```
root@debian8:/opt# /etc/init.d/cloudera-scm-server-db start
initdb: invalid locale name "en_US.UTF8"
[FAIL] The ... failed!
```

Solved:
```
localedef -v -c -i en_US -f UTF-8 en_US.UTF-8
```
