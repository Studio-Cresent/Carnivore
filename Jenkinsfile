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
                // gradlew 파일에 실행 권한 부여
                sh 'chmod +x gradlew'
                // 테스트는 제외하고 빌드 실행 (필요에 따라 -x test 옵션 추가)
                sh './gradlew clean build -x test -x incrementPatchVersion'
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
                sh './gradlew jar -x test'
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
        always {
            discordSend(
                description: "**빌드 #${env.BUILD_NUMBER}**: ${currentBuild.currentResult}",
                link: env.BUILD_URL,
                result: currentBuild.currentResult,
                title: "${env.JOB_NAME}",
                webhookURL: "https://discord.com/api/webhooks/1357005699179221153/oS2K013AJNGM2lkZ4JI0Vqi-8g9Y-4kItCO2eGgFpiQOIgO3DPthPwtAk-oPna9cXkIq",
                footer: "빌드 시간: ${currentBuild.durationString}"
            )
        }
        success {
            echo '파이프라인이 성공적으로 완료되었습니다!'
        }
        failure {
            echo '파이프라인 실행 중 오류가 발생했습니다.'
        }
    }
}