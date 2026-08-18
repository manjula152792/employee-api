pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }
        stage('Docker Build') {
            steps {
                bat 'docker build -t employee-api:1.0 .'
            }
        }
    }
}
