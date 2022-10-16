pipeline {
    agent any
    tools {
        maven 'Maven 3.8.6'
    }
    stages {
        stage('Build') {
            agent any
            steps {
                sh 'mvn clean install test'
            }
        }
    }
}
