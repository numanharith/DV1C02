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
            agent any
            steps {
                sh 'mvn sonar:sonar -Pcoverage'
            }
        }

        stage('Deploy JAR') {
            agent any
            steps {
                echo "****************"
                echo "Deploying JAR..."
                echo "****************"
                java -jar /Users/numan/.jenkins/workspace/1c02-pet-clinic-multibranch_main@2/target/petclinic-0.0.1-SNAPSHOT.jar
            }
        }
    }
}
