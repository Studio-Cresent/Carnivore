pipeline {
    agent any

    tools {
        jdk 'jdk17'
    }

    triggers {
        githubPush()
    }

    stages {
        stage('Git Clone') {
            steps {
                echo '소스 코드 체크아웃 중...'
                checkout scm
            }
            post {
                success {
                    echo '소스 코드 체크아웃 성공'
                }
                failure {
                    echo '소스 코드 체크아웃 실패'
                }
            }
        }

        stage('Gradle Build') {
            steps {
                echo '빌드 중...'
                sh './gradlew clean build'
            }
            post {
                success {
                    echo '빌드 성공'
                }
                failure {
                    echo '빌드 실패'
                }
            }
        }

        stage('Package') {
            steps {
                echo 'JAR 패키징 중...'
                sh './gradlew jar'
            }
            post {
                success {
                    echo '패키징 성공'
                    archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
                }
                failure {
                    echo '패키징 실패'
                }
            }
        }
    }

    post {
        success {
            echo '파이프라인이 성공적으로 완료되었습니다!'
        }
        failure {
            echo '파이프라인 실행 중 오류가 발생했습니다.'
        }
    }
}
