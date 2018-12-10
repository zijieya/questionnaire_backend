#!/bin/bash
#清理文件
ssh  $testserver <<EOF
 cd  www/wwwroot/questionnairetest_jieblog_win
  rm -rf *
EOF
#复制文件
scp build/libs/*.war  $testserver:~/web/test/
#运行
ssh  $testserver << EOF
 jar -xf *.war
 service tomcat stop
 service tomcat start
EOF