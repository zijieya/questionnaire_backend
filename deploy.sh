#!/bin/bash
#清理文件
ssh  $testserver <<EOF
 cd  /www/wwwroot/questionnairetest_jieblog_win
  rm -rf *
EOF
#复制文件
scp build/libs/*.war  $testserver:/www/wwwroot/questionnairetest_jieblog_win/
#运行
ssh  $testserver << EOF
cd /www/wwwroot/questionnairetest_jieblog_win
 java -jar  *.war >/dev/null 2>&1 &
sudo service tomcat stop
 sudo service tomcat start
EOF