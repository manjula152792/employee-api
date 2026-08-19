pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'manjula152792/employee-api'
    }

    stages {

        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Check Docker') {
            steps {
                bat 'where docker'
                bat 'docker --version'
            }
        }

        stage('Docker Build') {
            steps {
                bat 'docker build -t %DOCKER_IMAGE%:1.0 .'
            }
        }

        stage('Docker Push') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-credentials',
                    usernameVariable: 'DOCKER_USERNAME',
                    passwordVariable: 'DOCKER_PASSWORD'
                )]) {
                    bat 'docker login -u %DOCKER_USERNAME% -p %DOCKER_PASSWORD%'
                    bat 'docker push %DOCKER_IMAGE%:1.0'
                }
            }
        }
        stage('Deploy to AWS') {
    steps {
        sshagent(['ec2-ssh-key']) {
            bat '''
                ssh -o StrictHostKeyChecking=no ubuntu@13.233.101.76 "docker pull manjula152792/employee-api && docker stop employee-api-container || true && docker rm employee-api-container || true && docker run -d --name employee-api-container -p 8080:8080 manjula152792/employee-api"
            '''
        }
    }
}
    }
}