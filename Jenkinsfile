	pipeline {
    agent {
        label 'master'
    }
    tools {
        maven 'M3'
    }
    options {
        buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '30', numToKeepStr: '10')
        timeout(time: 30, unit: 'MINUTES')
    }
    
    stages {
        
        stage('Maven Build') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: "*/main"]], doGenerateSubmoduleConfigurations: false, extensions: [], gitTool: 'Default', submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'SCMAUTO_GITHUB', url: 'https://github.com/avinash-kulkarni/learnAws.git']]])
                sh "mvn clean install"
            }
        }
        stage('Docker Build') {

            steps {
				//upload docker image to hub
                sh "docker login -u avinask -p Lawliet@7"
                sh "docker build -t learnaws ."
            }
        }

        stage('Docker Push') {
            steps {
                sh "docker push avinask/learnaws:latest"
            }
        }
    }
}
	