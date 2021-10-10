node("kube2"){
    sh 'ls'
    sh 'pwd'
    git 'git@github.com:roachmaster/gateWayDemo.git'
    sh "./gradlew clean build test"
    withCredentials([usernamePassword(credentialsId: '87e61f11-079d-4052-b083-ea5859f0f85b', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
        sh "docker build -t ${DOCKER_USERNAME}/gateway_demo:0.0.1-SNAPSHOT ."
        sh "docker login --username ${DOCKER_USERNAME} --password ${DOCKER_PASSWORD}"
        sh "docker push ${DOCKER_USERNAME}/gateway_demo:0.0.1-SNAPSHOT"
        sh "docker rmi ${DOCKER_USERNAME}/gateway_demo:0.0.1-SNAPSHOT"
    }
    String tempString = sh(returnStdout: true, script: 'kubectl get deployments | grep -c gateway-demo')
    println("test: ${tempString}");
    if(tempString.trim().equals("1")){
        println("removing gateway_demo deployment");
        sh "kubectl delete gateway-demo"
    }
    sh "kubectl create -f k3s/deployment.yml"
}
