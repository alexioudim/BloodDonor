pipeline {
    agent any

    options {
        buildDiscarder(logRotator(numToKeepStr: '30', artifactNumToKeepStr: '30'))
    }


    stages {
        stage('run image build pipeline') {
            steps {
                build job: 'image-build'
            }
        }
        stage('deploy backend to k8s') {
            steps {
                sh '''
                    HEAD_COMMIT=$(git rev-parse --short HEAD)
                    TAG=$HEAD_COMMIT-$BUILD_ID
                    # if we had multiple configurations in kubeconfig file, we should select the correct one
                    # kubectl config use-context devops
                    kubectl set image deployment/spring-deployment spring=$DOCKER_PREFIX:$TAG
                    kubectl rollout status deployment spring-deployment --watch --timeout=2m
                '''
            }
        }

        stage('deploy backend to k8s') {
                    steps {
                        sh '''
                            HEAD_COMMIT=$(git rev-parse --short HEAD)
                            TAG=$HEAD_COMMIT-$BUILD_ID
                            # if we had multiple configurations in kubeconfig file, we should select the correct one
                            # kubectl config use-context devops
                            kubectl set image deployment/vue-deployment vue=$DOCKER_PREFIX_VUE:$TAG
                            kubectl rollout status deployment vue-deployment --watch --timeout=2m
                        '''
                    }
                }

    }


}