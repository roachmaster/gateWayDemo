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
    withCredentials([usernamePassword(credentialsId: '8047ae57-cfa7-4ee1-86aa-be906b124593', passwordVariable: 'credPw', usernameVariable: 'credName')]) {

    sh """cat<<EOF > k3s/kustomization.yml
secretGenerator:
- name: mysql-pass
  literals:
  - password=${credPw}
resources:
  - pi-mariadb.yml
  - deployment.yml
  - service.yml
EOF"""
    }
    String tempString = sh(returnStatus: true, script: 'kubectl get secrets | grep -c mysql-pass')
    sh "echo test $tempString"
    if(tempString.trim().equals("0")){
        println("Adding Secret");
        sh "kubectl apply -k k3s/"
    }

    tempString = sh(returnStatus: true, script: 'kubectl get deployments | grep -c gateway-demo')
    if(!tempString.trim().equals("0")){
        println("removing gateway_demo deployment");
        sh "kubectl delete deployment gateway-demo"
    }
    sh "kubectl create -f k3s/deployment.yml"

    tempString = sh(returnStatus: true, script: 'kubectl get svc | grep -c gateway-demo')
    if(!tempString.trim().equals("0")){
        println("removing gateway_demo svc");
        sh "kubectl delete svc gateway-demo"
    }
    sh "kubectl apply -f k3s/service.yml"
}
