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

    String tempString;
    withCredentials([usernamePassword(credentialsId: '8047ae57-cfa7-4ee1-86aa-be906b124593', passwordVariable: 'credPw', usernameVariable: 'credName')]) {
        tempString = sh(returnStatus: true, script: 'kubectl get secrets | grep -c mysql-pass')
        if(tempString.trim().equals("1")){
            println("Adding Secret");
            sh "kubectl create secret generic mysql-pass --from-literal=password=${credPw}"
        }
    }

    tempString = sh(returnStatus: true, script: 'kubectl get deployments | grep -c gateway-demo')
    if(!tempString.trim().equals("1")){
        println("Removing gateway_demo deployment");
        sh "kubectl delete deployment gateway-demo"
    }
    sh "kubectl create -f k3s/deployment.yml"

    tempString = sh(returnStatus: true, script: 'kubectl get svc | grep -c gateway-demo')
    if(!tempString.trim().equals("1")){
        println("removing gateway_demo svc");
        sh "kubectl delete svc gateway-demo"
    }
    sh "kubectl apply -f k3s/service.yml"

    tempString = sh(returnStatus: true, script: 'kubectl get deployments | grep -c pi-mariadb')
    if(tempString.trim().equals("1")){
        tempString = sh(returnStatus: true, script: 'kubectl get pvc | grep -c mariadb-pv-claim')
        if(!tempString.trim().equals("1")){
            println("removing gateway_demo svc");
            sh "kubectl delete pvc mariadb-pv-claim"
        }
        tempString = sh(returnStatus: true, script: 'kubectl get svc | grep -c pi-mariadb')
        if(!tempString.trim().equals("1")){
            println("removing pi-mariadb svc");
            sh "kubectl delete svc pi-mariadb"
        }
        println("Adding pi-mariadb deployment");
        sh "kubectl create -f k3s/pi-mariadb.yml"
    }
}
