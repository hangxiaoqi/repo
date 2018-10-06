

### 查看访问量排名前十的 IP

```shell 
cat access.log | cut -f1 -d " " | sort | uniq -c |sort -k 1 -n -r | head 10
```



### 页面访问量排名前10 的URL

```shell
cat access.log | cut -f4 -d " " | sort | uniq -c | sort -k 1 -n -r | head 10
```



### 查看最耗时的页面

```shell
cat access.log | sort -k 2 -n -r | head 10
```



### 统计404 的页面的占比

```shell
export total_line=`wc -l access.log | cut -f1 -d " "` && export not_found_line=`awk '$6=='404' {print $6}' access.log | wc -l` && expr $not_found_line \* 100  / $ total_line
```



### 日志分析脚本,统计每一个url的平均响应时间， 使用方式： awk -f 脚本名称 access.log
``` shell
{ 
	if (map[$4] > 0 ){
		map[$4]=map$[4] +  $2 
		map_time[$4] = map_time[$4] + 1
	}
	else{
		map[$4]=$2
		map_time[$4]=1
	}
}
END{
	for(i in map){
		print i"map[i]/map_time[i];
	}
}
```



### 通过top 命令取的系统的load的值， -n 1 表示只刷新一次，然后用sed 过滤出第一行，通过awk筛选出一分钟的平均load ${load%\,*} 从右边开始过滤掉不需要的逗号。

```shell
!/bin/bash
load=top -n 1 | sed -n '1p' | awk '{print $11}'
load=${load%\,*}
disk_usage=df -h | sed -n '2p' | awk '{print  $(NF -1)}'
disk_usage=${disk_usage%\%*}
overhead=expr $laod \> 2.00
if [ $overhead - eq 1 ];then
	echo "system load is overhead"
fi
if [ $disk_usage -gt 85 ];then
	echo "disk is nearly full, need more disk space"
fi
exit 0

```



### 分解access.log 并且存储到db

```sql
-- 建表语句
create table access_log(
ip varchar(20), #ip 地址
rt bigint, # 响应时间
method varchar（400), # 请求方式
url varchar(400), # 请求地址
refer varchar(400), # 请求源
return_code int, #返回码
response_size bigint # 响应大小
);
```

```shell
!/bin/bash
ACCESS_FILE=/home/longlong/temp/access.log
MYSQL=/usr/bin/mysql
while read LINE
do
	OLD_IFS="$IFS"
	IFS=" "
	filed_arr=($LINE)
	IFS="$OLD_IFS"
	STATEMENT="insert into access_log values('{field_arr[0]}','{field_arr[1]}','{field_arr[2]}','{field_arr[3]}','{field_arr[4]}','{field_arr[5]}',${field_arr[6]});"
	echo $STATEMENT
	MYSQL test -uroot -proot -e "STATEMENT"
done < $ACCESS_FILE
exit 0

```



### linux 系统监控指标

 * load 在linux系统中，可以通过`top`和`uptime`命令来查看系统的`load`值，系统的`load`值被定义为特定时间间隔内运行队列中的平均线程数，如果一个线程满足一下条件：

   * 没有处于I/O等待状态
   * 没有主动进入等待状态，也就是没有调用wait操作；
   * 没有被终止。

   >每个cpu的核都维护了一个运行队列，系统的`load`主要是由运行队列来决定。
   >
   >`load`值越大也就意味着系统越繁忙，这样线程运行完以后等待系统分配下一个时间片的时间也就越长。
   >可以使用 `uptime` 查看系统的 `load`

   

* cpu的利用率 `top | grep Cpu` 。其中， CPU 后面跟的各个列是各种状态下的cpu的时间占比。

  * 用户时间（User Time) 即 us 所对应的列，标识cpu执行用户进程所占用的时间

  * 系统时间（system Time) 即 sy所对应的列， 标识cpu在内核态所花费的时间。

  * Nice时间（Nice Time) 即 ni所对应的列， 标识系统在调整进程优先级所花费的时间。

  * 空闲时间（Idle Time) 即 id所对应得列， 标识系统处于空闲期所花费的时间。

  * 等待时间（Waiting Time) 即 wa所对应得列， 标识cpu在等待I/O 所花费的时间.

  * 硬件中断时间（Hard Irq Time) 即 hi所对应得列， 标识系统在处理硬件中断所花费的时间。

  * 软件中断处理时间（ Soft Irq Time) 即 si所对应得列， 标识系统在处理软件中断所花费的时间。

  * 丢失时间（Steal Time）即 st所对应得列， 是硬件虚拟化开始流行以后操作系统增加的一列， 标识强制等待虚拟cpu的时间。

    

* 其他指标 `top -p 进程 id`

>1. 磁盘剩余空间
>   `df -h`
>   `du -d 1 -h /home` : -d 标识递归深度
>2. 网络 traffic 
>   `sar -n DEV 1 1` ： -n表示汇报网络状况 ，DEV表示查看各网卡的流量， 第一个1 表示每一秒抽样一次，第二个1 表示总共取一次
>   展示的结果中， rxpck/s 表示每秒接收的数据包，txpck/s  表示每一秒发出的数据包，rxKB/s 每一秒接收到的字节数，txKB/s 表示的是每一秒发出的字节数，rxcmp/s 表示每一秒接收到的压缩包数， txcmp/s 表示每一秒发出的压缩包数，rxmcst/s 表示接收的广播， Average 表示多次取样的平均值
>3. 磁盘I/O
>   `iostat -d -k`
>4. 内存使用
>   `free -m`
>   其中： total 表示 内存总共的大小； used表示已使用的内存的大小；free表示可使用的内存的大小； shared 表示多个进程共享的内存空间的大小；buffers表示缓冲区的大小； cached表示缓存的大小；
>   `vmstat` : 查看swap 的情况
>5. qps ： query per second
>6. rt  : response time

