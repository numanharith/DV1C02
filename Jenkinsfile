pipeline {
  agent any
  stages {
    stage('Build') {
      parallel {
        stage('Build') {
          agent any
          steps {
            sh 'mvn clean install test'
          }
        }

        stage('Tools') {
          steps {
            tool(name: 'Maven 3.8.6', type: 'maven')
            tool(name: 'jdk11', type: 'jdk')
          }
        }

      }
    }

  }
}