pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    environment {
        IMAGE_NAME = 'bug-tracker'
        IMAGE_TAG = 'latest'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Cloning from GitHub...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building with Maven...'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                echo 'Running JUnit Tests...'
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Building Docker Image...'
                sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying container...'
                sh '''
                    docker stop bug-tracker || true
                    docker rm bug-tracker || true
                    docker run -d -p 8082:8082 --name bug-tracker bug-tracker:latest
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