# Homework

How to build
----------------------------
```
$ mvn clean package
```

How to execute MapJoinDriver
----------------------------
MapJobDriver need three arguments.

arg[0] : product directory on HDFS

arg[1] : purchase directory on HDFS

arg[2] : output directory on HDFS

Before execute, prepare product.txt, purchase.txt on corresponding HDFS dirs.
```
$ hadoop jar homework/target/gagamel-homework-0.9-SNAPSHOT.jar com.seoeun.homework.ps.MapJoinDriver /user/seoeun/homework/product /user/seoeun/homework/purchase /user/seoeun/homework/output
```

How to execute PalindromeApp
----------------------------

```
$ java -cp homework/target/gagamel-homework-0.9-SNAPSHOT.jar com.seoeun.homework.app.PalindromeApp
```

