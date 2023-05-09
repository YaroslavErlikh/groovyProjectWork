def PrintSysTime(){
    sh(script: "echo CURRENT BRANCH IS: \$(basename \$(git symbolic-ref HEAD)) AND LOCAL TIME IS: \$(date +%H:%M:%S)", returnStdout: true)
}

def CalculateFilesCount() {
    sh(script: "echo TOTAL NUMER OF FILES IS: \$(find . -type f 2>> /dev/null | wc -l)", returnStdout: true)
}

def checkout(name) {
    echo "Performing Git checkout"

    git branch: name, credentialsId: 'github', url: 'git@github.com:a1ntwan/groovy_developer.git'

    if (name == 'homework-4') {
        dir('bom-examples') {
            git branch: 'master', credentialsId: 'github', url: 'git@github.com:CycloneDX/bom-examples.git'
        }
    }
}