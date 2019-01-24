pipeline {
    agent  any // agent表明整个pipeline执行的地方 当用docker时，下面所有shell命令都在docker内运行
    stages {
         stage('test') {
             steps {
                sh 'chmod +x gradlew'
                sh 'echo "正在执行代码质量检查"'
                sh './gradlew check'
                junit 'build/test-results/**/*.xml'
             }
        }
        stage('build') {
            steps {
                sh 'echo "修改自带的tomcat端口"'
                sh 'echo  " " >> src/main/resources/application.properties'//换行
                sh 'echo  "server.port=12321" >> src/main/resources/application.properties'//部署的vps端口8080被占用 需要修改默认的端口
                sh 'echo "开始构建并打包成war包"'
                sh './gradlew bootWar'
                archiveArtifacts artifacts: 'build/libs/**/*.war', fingerprint: true//归档成品
            }
        }
         stage('Deploy') {
            when {
              expression {
                         currentBuild.result == null || currentBuild.result == 'SUCCESS'
                       }
                     }
             steps {
                sh 'echo "开始部署到测试环境"'
                         sh 'chmod +x deploy.sh'
                         sh './deploy.sh'
                     }
                 }
 }
 post{
        success{
        echo "成功构建并部署"
        }
        failure{
        echo "构建失败"
        }
 }
 }
