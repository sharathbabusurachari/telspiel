pipeline {
    agent any

    environment {
            ARTIFACTORY_SERVER = 'http://ci1.saswatfinance.com:8082/' // Define the configured Artifactory server ID
            ARTIFACTORY_REPO = 'qa-saswat-java-repo'     // Define the Artifactory repository key
        }

    tools {
        maven 'MAVEN'
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building the env using pipeline'
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/sharathbabusurachari/telspiel']])
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }}
        stage('Test') {
            steps {
                echo 'Testing the env using pipeline'
            }}

        stage('Deploy') {
            steps {
                echo 'Deploying the env using pipeline'
                sh '''
                status=`ps -ef | grep telspiel | grep jar | awk '{print $2}'`
                if [ -z "$status" ];
                        then echo "Application is NOT running & we're starting now...";
                        else echo "Application is already running & hence killing previous pid :"$status;
                       kill -9 $status;
                fi
                '''
                script {
                        withEnv(['JENKINS_NODE_COOKIE=dontkill']) {
                        sh "nohup java -jar $WORKSPACE/target/telspiel-0.0.1-SNAPSHOT.jar &"
                        }
                }

                sh '''
                                status=`ps -ef | grep telspiel | grep jar | awk '{print $2}'`
                                if [ -z "$status" ];
                                        then echo "Deployment is unsuccessful...";
                                        else echo "Deployment is Successful...";
                                fi
                 '''
                   }
             }

            /*stage('CODE ANALYSIS with SONARQUBE') {
                environment {
                              scannerHome = tool 'SonarScanner'
                             }
                 steps {
                          withSonarQubeEnv('SonarQubeServer') {
                           sh '''${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=saswat_telspiel_project \
                               -Dsonar.projectName=saswat_telspiel_project-repo \
                               -Dsonar.projectVersion=1.0 \
                               -Dsonar.sources=$WORKSPACE/src/ \
                               -Dsonar.java.binaries=target/test-classes/com/ \
                               -Dsonar.junit.reportsPath=target/surefire-reports/ \
                               -Dsonar.jacoco.reportsPath=target/jacoco.exec \
                               -Dsonar.java.checkstyle.reportPaths=target/checkstyle-result.xml'''
                           }
                           script {
                           def qg = waitForQualityGate()
                               if (qg.status != 'OK') {
                                   error "Pipeline aborted due to Quality Gate failure: ${qg.status}"
                                }
                           }
                                /*timeout(time: 5, unit: 'MINUTES') {
                                  waitForQualityGate abortPipeline: true
                                } */
                        }
            }*/

            stage('Publish to Artifactory') {
                        steps {
                            script {
                                def server = Artifactory.server ARTIFACTORY_SERVER
                                def buildInfo = Artifactory.newBuildInfo()

                                server.publishBuildInfo buildInfo

                                // Example: Maven deployment
                                server.mavenDeployer {
                                    deployerCredentialsId = 'qa-jfrog-password' // Configure Jenkins credentials for Artifactory
                                    repositoryKey = ARTIFACTORY_REPO
                                    snapshotsRepositoryKey = ARTIFACTORY_REPO
                                    version = '1.0-SNAPSHOT'
                                }
                            }
                        }
                    }
        }
 }