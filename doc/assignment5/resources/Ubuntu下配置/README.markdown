# Ubuntu下配置Hadop(伪分布式）

## 前置：

系统: Ubuntu13.04(32位）
JDK: 1.7.0_51
SSH:免密码登录
Hadoop: 2.2.0

## 配置

修改的配置文件，yarn-site.xml使用默认的。core-site.xml, hdfs-site.xml, mapred-site.xml 见资源文件。

## 运行

```
sbin/hadoop-daemon.sh start namenode
sbin/hadoop-daemon.sh start datanode
sbin/yarn-daemon.sh start resourcemanager
sbin/yarn-daemon.sh start nodemanager
```

### 运行测试结果

```
jps
13338 NodeManager
13111 ResourceManager
12935 NameNode
5309 Jps
13012 DataNode
```

## 对hdfs系统操作的测试

上传文件：

```
bin/hdfs dfs -put NOTICE.txt /input
```

查看文件系统已有文件列表：

```
zhang@localhost:/usr/hadoop/hadoop-2.2.0$ bin/hdfs dfs -ls /
Found 2 items
drwxr-xr-x   - zhang supergroup          0 2014-03-04 21:36 /input
drwxr-xr-x   - zhang supergroup          0 2014-03-04 21:49 /test
zhang@localhost:/usr/hadoop/hadoop-2.2.0$ bin/hdfs dfs -ls /input
Found 3 items
-rw-r--r--   3 zhang supergroup        101 2014-03-04 15:38 /input/NOTICE.txt
-rw-r--r--   3 zhang supergroup       1366 2014-03-04 11:44 /input/README.txt
-rw-r--r--   3 zhang supergroup        620 2014-03-04 21:36 /input/site.xml
```


## Web查看Hadoop

查看HDFS的状态: `http://localhost:50070`

查看YARN的状态: `http://localhost:8088`

