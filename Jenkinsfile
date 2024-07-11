pipeline {
    agent any

    environment {
            JAVA_HOME = "/usr/lib/jvm/java-17-openjdk-amd64"
        }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'rest', url: 'git@github.com:alexioudim/BloodDonor.git'
            }
        }

        stage('preparation') {
                    steps {
                        sh 'chmod +x ./mvnw'
                    }
                }

        stage('Test') {
                    steps {
                        sh './mvnw test'
                    }
                }


        stage('run ansible pipeline') {
            steps {
                build job: 'ansible'
            }
        }

        stage('Install postgres') {
            steps {
                sh '''
                    export ANSIBLE_CONFIG=~/workspace/ansible/ansible.cfg
                    ansible-playbook -i ~/workspace/ansible/hosts.yaml -l azure-db-server ~/workspace/ansible/playbooks/postgres.yaml
                '''
            }
        }

        stage('Deploy spring boot app') {
            steps {
                sh '''
                    export ANSIBLE_CONFIG=~/workspace/ansible/ansible.cfg
                    ansible-playbook -i ~/workspace/ansible/hosts.yaml -l azure-app-server ~/workspace/ansible/playbooks/spring.yaml
                '''
            }
        }
        stage('Deploy frontend') {
            steps {
                sh '''
                    sed -i 's/dbserver/4.211.249.239/g' ~/workspace/ansible/host_vars/azure-app-server.yaml
                    export ANSIBLE_CONFIG=~/workspace/ansible/ansible.cfg
                    ansible-playbook -i ~/workspace/ansible/hosts.yaml -l azure-frontend-server -e branch=main -e backend_server_url=http://localhost:7070 ~/workspace/ansible/playbooks/vuejs.yaml
                '''
            }
        }
    }
}