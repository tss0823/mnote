#!/bin/bash
#
echo 172.16.0.236 db-mysql.mnote.com  >> /etc/hosts
echo 172.16.0.236 db-redis.mnote.com  >> /etc/hosts
echo 172.16.0.236 db-es.mnote.com  >> /etc/hosts
echo 172.16.0.236 db1-es.mnote.com  >> /etc/hosts
echo 172.16.0.235 db2-es.mnote.com  >> /etc/hosts

echo 172.16.0.236 db-test-mysql.mnote.com  >> /etc/hosts
echo 172.16.0.236 db-test-redis.mnote.com  >> /etc/hosts
echo 172.16.0.236 db-test-es.mnote.com  >> /etc/hosts
echo 172.16.0.236 db1-test-es.mnote.com  >> /etc/hosts
echo 172.16.0.235 db2-test-es.mnote.com  >> /etc/hosts

cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
