def PrintSysTime(){
    sh(script: "echo CURRENT BRANCH IS: \$(basename \$(git symbolic-ref HEAD)) AND LOCAL TIME IS: \$(date +%H:%M:%S)", returnStdout: true)
}

def CalculateFilesCount() {
    sh(script: "echo TOTAL NUMER OF FILES IS: \$(find . -type f 2>> /dev/null | wc -l)", returnStdout: true)
}

def checkout(name) {
    echo "Performing Git checkout"
    git branch: name, credentialsId: 'github', url: 'git@github.com:YaroslavErlikh/groovyProjectWork.git'
}

def testModule(name) {
    echo "Performing tests"
    sh(script: "./gradlew -p ${name} test", returnStdout: true)
}

def jibBuild(name) {
    echo "Performing dockerization and pushing to Dockerhub"

    withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
        sh(script: "./gradlew -p ${name} jib -Djib.to.auth.username=${USERNAME} -Djib.to.auth.password=${PASSWORD}", returnStdout: true)
    }
}

properties([
        parameters([
                extendedChoice(name: 'MODULES', description: 'Choose your branch', multiSelectDelimiter: ',', type: 'PT_CHECKBOX', value: 'groovyProjectWork', visibleItemCount: 6)
        ])
])

node {
    stage("CLEAN WORKSPACE") {
        cleanWs()
    }

    stage("CREATE DIRS FOR MODULES") {
        params['MODULES'].split(',').each { m ->
            String path = moduleToBranch.findResult { it[m] }
            new File("${WORKSPACE}/${path}").mkdirs()
        }
        sh "ls ${WORKSPACE}"
    }

    stage("PARELLEL GIT CHECKOUT") {
        def branchedStages = [:]

        params['MODULES'].split(',').each { m ->
            branchedStages[m] = {
                stage("PARALLEL GIT CHECKOUT: ${m}") {
                    String path = moduleToBranch.findResult { it[m] }
                    dir("${WORKSPACE}/${path}") {
                        checkout(path)
                    }
                }
            }
        }
        parallel branchedStages
    }

    stage("PARELLEL TEST") {
        def branchedStages = [:]

        params['MODULES'].split(',').each { m ->
            branchedStages[m] = {
                stage("PARALLEL TEST: ${m}") {
                    String path = moduleToBranch.findResult { it[m] }
                    dir("${WORKSPACE}/${path}") {
                        testModule(m)
                    }
                }
            }
        }
        parallel branchedStages
    }

    stage("PARELLEL BUILD AND PUSH") {
        def branchedStages = [:]

        params['MODULES'].split(',').each { m ->
            branchedStages[m] = {
                stage("PARELLEL BUILD AND PUSH: ${m}") {
                    String path = moduleToBranch.findResult { it[m] }
                    dir("${WORKSPACE}/${path}") {
                        PrintSysTime()
                        jibBuild(m)
                        CalculateFilesCount()
                    }
                }
            }
        }
        parallel branchedStages
    }
}
