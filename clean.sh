#!/usr/bin/bash
pid=`ps -aux |grep java|grep  questionnaire-0.0.1-SNAPSHOT.war |grep -v grep | awk '{print $2}'`
echo $pid
if [ $pid  ];then
    kill -9 $pid
fi
rm -rf /www/wwwroot/questionnairetest_jieblog_win/*