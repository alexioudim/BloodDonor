pipeline {
    agent any

    options {
        buildDiscarder(logRotator(numToKeepStr: '30', artifactNumToKeepStr: '30'))
    }

    environment {
        DOCKER_TOKEN = credentials('docker-push-secret')
        DOCKER_USER = 'tasosk7'
        DOCKER_SERVER = 'ghcr.io'
        DOCKER_PREFIX = 'ghcr.io/tasosk7/bd-backend'
        DOCKER_PREFIX_VUE = 'ghcr.io/tasosk7/bd-frontend'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'rest', url: 'git@github.com:alexioudim/BloodDonor.git'
            }
        }
        stage('Docker build and push') {
                    steps {
                        sh '''
                            HEAD_COMMIT=$(git rev-parse --short HEAD)
                            TAG=$HEAD_COMMIT-$BUILD_ID
                            docker build --rm -t $DOCKER_PREFIX:$TAG -t $DOCKER_PREFIX:latest  -f backend.Dockerfile .
                            echo $DOCKER_TOKEN | docker login $DOCKER_SERVER -u $DOCKER_USER --password-stdin
                            docker push $DOCKER_PREFIX --all-tags
                        '''
                    }
        }
        stage('Checkout') {
                    steps {
                        git branch: 'docker', url: 'git@github.com:TasosK7/BloodDonorVue.git'
                    }
                }
    }
        stage('Docker build and push') {
                        steps {
                            sh '''
                                HEAD_COMMIT=$(git rev-parse --short HEAD)
                                TAG=$HEAD_COMMIT-$BUILD_ID
                                docker build --rm -t $DOCKER_PREFIX_VUE:$TAG -t $DOCKER_PREFIX_VUE:latest  -f Dockerfile .
                                echo $DOCKER_TOKEN | docker login $DOCKER_SERVER -u $DOCKER_USER --password-stdin
                                docker push $DOCKER_PREFIX --all-tags
                            '''
                        }
        }

}