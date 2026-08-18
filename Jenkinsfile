pipeline {
    agent any

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
                bat 'echo %PATH%'
            }
        }
        stage('Docker Build') {
            steps {
                bat 'docker build -t employee-api:1.0 .'
            }
        }
        stage('Run Container') {
            steps {
                bat 'docker run -d --name employee-api-container -p 8080:8080 employee-api:1.0'
            }
        }
    }
}
