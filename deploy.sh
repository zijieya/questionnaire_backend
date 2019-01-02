#!/bin/bash
#上传clean脚本
scp clean.sh $testserver:~/questionbackend/
#执行clean脚本
ssh  $testserver <<EOF
 cd  ~/questionbackend
  chmod +x clean.sh
  ./clean.sh
EOF

#复制文件
scp build/libs/*.war  $testserver:~/questionbackend/
#运行
ssh  $testserver  >/dev/null 2>&1 << EOF
cd ~/questionbackend
sudo ln -s * /etc/init.d/questionnaire
sudo service questionnaire start
EOF