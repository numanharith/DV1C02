pipeline {
  agent any
  stages {
    stage('Configure Tools') {
      parallel {
        stage('Configure Tools') {
          agent any
          steps {
            tool(name: 'Maven 3.8.6', type: 'maven')
            tool(name: 'jdk11', type: 'jdk')
          }
        }

        stage('Build') {
          steps {
            sh 'mvn clean install test'
          }
        }

      }
    }

  }
}