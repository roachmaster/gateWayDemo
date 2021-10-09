node("kube2"){
    sh 'ls'
    sh 'pwd'
    git 'git@github.com:roachmaster/gateWayDemo.git'
    sh "./gradlew clean build test"
    sh "docker build --build-arg -t something ."
}
