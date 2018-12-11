#!/bin/bash
#上传clean脚本
scp clean.sh $testserver:/www/wwwroot/questionnairetest_jieblog_win/
#执行clean脚本
ssh  $testserver <<EOF
 cd  /www/wwwroot/questionnairetest_jieblog_win
  chmod +x clean.sh
  ./clean.sh
EOF

#复制文件
scp build/libs/*.war  $testserver:/www/wwwroot/questionnairetest_jieblog_win/
#运行
ssh  $testserver  >/dev/null 2>&1 << EOF
cd /www/wwwroot/questionnairetest_jieblog_win
nohup java -jar  *.war& >/dev/null 2>&1
 wait
EOF