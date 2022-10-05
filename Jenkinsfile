pipeline {
  agent any
  stages {
    stage('Tools') {
      steps {
        tool(name: 'maven', type: 'Maven 3.8.6')
        tool(name: 'jdk', type: 'jdk11')
      }
    }

    stage('Build') {
      steps {
        sh 'man clean install test'
      }
    }

  }
}