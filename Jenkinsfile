pipeline {
    agent any

    stages {
        stage ('Compile Stage') {
            steps {
                sh 'echo compile stage done'
            }
        }

        stage ('Deployment Stage') {
                    steps {
                        echo " ===========start server"
                        sh 'chmod 755 boot.sh'
                        sh 'JENKINS_NODE_COOKIE=dontKillMe ./boot.sh &'
                        //sh 'cp -r /var/lib/jenkins/workspace/atom/* /home/jenkins/atom/'
                        //sh 'cd /home/jenkins/atom/'
                        //sh 'npm build'
                        //sh 'export JENKINS_NODE_COOKIE=dontKillMe && npm start &'
                        echo 'Deployment stage done'
                    }
                }
    }
}