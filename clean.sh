#!/usr/bin/bash
#pid=`ps -aux |grep java|grep  questionnaire-0.0.1-SNAPSHOT.war |grep -v grep | awk '{print $2}'`
#echo $pid
#if [ $pid  ];then
#    kill -9 $pid
#fi
#判断软连接是否存在
if [ -e "/etc/init.d/questionnaire" ]; then
    #停止服务
    sudo service questionnaire stop
    #删除文件及软连接
    rm -rf ~/questionbackend/*
    rm -rf /etc/init.d/questionnaire
fi