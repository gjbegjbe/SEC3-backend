pipeline {
    agent any

    stages {
        stage('pull code') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: '25d35eeb-25a7-4bcb-b913-b24121e43a49', url: 'http://212.129.149.40/181250035_6pluscoin/backend-coin.git']]])
            }
        }
        stage('build project'){
            steps{
                sh 'mvn clean package'
            }
        }
        stage('run'){
            steps{
                sh '''sh stop.sh
sh start.sh'''
            }
        }
    }
}
