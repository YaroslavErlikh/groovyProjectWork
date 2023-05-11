properties([
  parameters([
    string(name: 'FIREFOX_TAG', defaultValue: 'test')
  ])
])

node {
    stage("BUILD IMAGE") {
        echo "Build image for integration tests"
        git branch: 'main', credentialsId: 'github', url: 'git@github.com:yaroslaverlikh/groovyprojectwork.git'

        docker.withRegistry('https://index.docker.io/v1/', 'dockerhub') {

            dockerImage = docker.build("YaroslavErlikh/firefox:${FIREFOX_TAG}")

            dockerImage.push()
        }
    }
}
