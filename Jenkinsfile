pipeline {
  agent any
  stages {
    stage('Build') {
      agent any
      environment {
        maven = 'Maven 3.8.6'
        jdk = 'jdk11'
      }
      steps {
        sh 'mvn clean install test'
      }
    }

  }
}