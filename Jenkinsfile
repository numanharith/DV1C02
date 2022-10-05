pipeline {
  agent any
  stages {
    stage('Build') {
      agent any
      environment {
        tools = 'maven \'Maven 3.8.6\''
      }
      steps {
        sh 'man clean install test'
      }
    }

  }
  tools {
    maven 'Maven 3.8.6'
    jdk 'jdk11'
  }
  environment {
    tools = 'jdk \'jdk11\''
  }
}