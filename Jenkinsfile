pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'rest', url: 'git@github.com:alexioudim/BloodDonor.git'
            }
        }
        stage('Preparation') {
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
                   # replace dbserver in host_vars
                    # sed -i 's/dbserver/4.211.249.239/g' ~/workspace/ansible/host_vars/appserver-vm.yaml
                   # replace workingdir in host_vars
                    # sed -i 's/vagrant/azureuser/g' ~/workspace/ansible/host_vars/appserver-vm.yaml
                '''
                sh '''
                    # edit host var for appserver

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
                    ansible-playbook -i ~/workspace/ansible/hosts.yaml -l azure-app-server -e branch=main -e backend_server_url=http://localhost:7070 ~/workspace/ansible/playbooks/vuejs.yaml
                '''
            }
        }
    }
}