pipeline {
  agent any
  stages {
    stage('Build') {
      agent any
      steps {
        sh 'mvn clean install test'
      }
    }

  }
  tools {
    maven 'Maven 3.8.6'
    jdk 'jdk11'
  }
}