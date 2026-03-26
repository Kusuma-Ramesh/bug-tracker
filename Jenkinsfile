pipeline {
    agent any

    environment {
        IMAGE_NAME = "bugtracker-app"
        IMAGE_TAG = "latest"
    }
    
    stages {

        stage('Checkout') {
    steps {
        git branch: 'main', url: 'https://github.com/Kusuma-Ramesh/bug-tracker.git'
    }
}

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Docker Build') {
            steps {
                sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                docker rm -f bug-tracker-pipeline || true
                docker run -d -p 8083:8082 --name bug-tracker-pipeline bugtracker-app:latest
                '''
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check logs.'
        }
    }
}
