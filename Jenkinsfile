pipeline {
  agent any
  stages {
    stage('Log Tools Versions') {
      parallel {
        stage('Log Tools Versions') {
          steps {
            sh '''mvn --version
git --version
java -version'''
          }
        }

        stage('Check if POM file exists') {
          steps {
            fileExists 'pom.xml'
          }
        }

      }
    }

    stage('Maven Build') {
      steps {
        sh 'man compile test package'
      }
    }

  }
}