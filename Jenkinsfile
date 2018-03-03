// pipeline {
//     agent { docker 'maven:3.3.3' }
//     stages {
//         stage('build') {
//             steps {
//                 sh 'mvn --version'
//             }
//         }
//     }
// }
/*



pipeline {
    agent { docker 'maven:3.3.3' }
    stages {
        stage('build') {
            steps {
                sh 'docker info'
            }
        }
    }
}
https://hub.docker.com/_/openjdk/

hseeberger/scala-sbt
openjdk:8 java -version
*/

pipeline {
    agent {
        //docker { image 'openjdk:8' }
        docker { image 'hseeberger/scala-sbt:8u151-2.12.4-1.1.1' }
        
    }
    environment {
        DISABLE_AUTH = 'true'
        DB_ENGINE    = 'sqlite'
        JAVA_TOOL_OPTIONS = '-Dsbt.log.noformat=true'
    }
    stages {
        stage('Dump') {
            steps {
                sh 'printenv'
            }
        }
        stage('Java') {
            steps {
                //sh 'sbt version'
                sh 'java -version'
            }
        }
        stage('Build') {
            steps {
                sh 'echo "Hello World"'
                sh '''
                    echo "Multiline shell steps works too"
                    ls -lah
                '''
            }
        }
        stage('Test') {
            steps {
                sh 'sbt test'
            }
        }
    }
    post {
        always {
            echo 'This will always run'
            //archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
            echo 'Collect Unit Test output'
            junit 'target/test-reports/**/*.xml'
        }        
        success {
            echo 'This will run only if successful'
        }
        failure {
            echo 'This will run only if failed'
        }
        unstable {
            echo 'This will run only if the run was marked as unstable'
        }
        changed {
            echo 'This will run only if the state of the Pipeline has changed'
            echo 'For example, if the Pipeline was previously failing but is now successful'
        }
    }    
}
