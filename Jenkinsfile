pipeline {
    agent  any // agent表明整个pipeline执行的地方 当用docker时，下面所有shell命令都在docker内运行
    stages {
         stage('test') {
             steps {
                sh 'chmod +x gradlew'
                sh 'echo "正在执行代码质量检查"'
                sh './gradlew check'
             }
        }
        stage('build') {
            steps {
                sh 'echo "开始构建并打包成war包"'
                sh './gradlew build'
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
