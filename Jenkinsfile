pipeline {
  agent any
  tools {
     maven 'Maven 3.8.6'
     jdk 'jdk11'
  }
  stages {
    stage('Build') {
      agent any
      steps {
        sh 'mvn clean install test'
      }
    }
    
    stage('SonarQube Analysis') {
      withSonarQubeEnv() {
        sh "mvn clean verify sonar:sonar -Dsonar.projectKey=dv1c02-pet-clinic"
      }
    }
  }
}
