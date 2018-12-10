pipeline {
    agent { label 'docker' }
    stages {
        stage('Build') {
            steps {
                script {
                    props=readProperties file: 'gradle.properties'
                    VERSION="${props.version}-${props.apiVersion}"
                }
                sh "docker build --tag ${GIT_COMMIT} --build-arg apiVersion=${props.apiVersion} ."
            }
        }
        stage('Publish') {
            when {
                branch 'master'
            }
            steps {
                sh "docker tag ${GIT_COMMIT} dtr.fintlabs.no/beta/consumer-utdanning-elev:${VERSION}"
                withDockerRegistry([credentialsId: 'dtr-fintlabs-no', url: 'https://dtr.fintlabs.no']) {
                    sh "docker push dtr.fintlabs.no/beta/consumer-utdanning-elev:${VERSION}"
                }
                sh "docker tag ${GIT_COMMIT} fintlabs.azurecr.io/consumer-utdanning-elev:${VERSION}"
                withDockerRegistry([credentialsId: 'fintlabs.azurecr.io', url: 'https://fintlabs.azurecr.io']) {
                    sh "docker push 'fintlabs.azurecr.io/consumer-utdanning-elev:${VERSION}'"
                }
            }
        }
        stage('Publish Tag') {
            when { buildingTag() }
            steps {
                sh "docker tag ${GIT_COMMIT} dtr.fintlabs.no/beta/consumer-utdanning-elev:${TAG_NAME}"
                withDockerRegistry([credentialsId: 'dtr-fintlabs-no', url: 'https://dtr.fintlabs.no']) {
                    sh "docker push dtr.fintlabs.no/beta/consumer-utdanning-elev:${TAG_NAME}"
                }
            }
        }
        stage('Publish PR') {
            when { changeRequest() }
            steps {
                sh "docker tag ${GIT_COMMIT} dtr.fintlabs.no/beta/consumer-utdanning-elev:${BRANCH_NAME}"
                withDockerRegistry([credentialsId: 'dtr-fintlabs-no', url: 'https://dtr.fintlabs.no']) {
                    sh "docker push dtr.fintlabs.no/beta/consumer-utdanning-elev:${BRANCH_NAME}"
                }
            }
        }
    }
}
