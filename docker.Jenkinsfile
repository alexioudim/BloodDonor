pipeline {
    agent any

    options {
        buildDiscarder(logRotator(numToKeepStr: '30', artifactNumToKeepStr: '30'))
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'rest', url: 'git@github.com:alexioudim/BloodDonor.git'
            }
        }
        stage('run ansible pipeline') {
            steps {
                build job: 'ansible'
            }
        }
        stage('Install project with docker compose') {
            steps {
                sh '''
                            export ANSIBLE_CONFIG=~/workspace/ansible/ansible.cfg
                            ansible-playbook -i ~/workspace/ansible/hosts.yaml -l azure-docker-server ~/workspace/ansible/playbooks/docker-app.yaml
                        '''
            }
        }

    }

}