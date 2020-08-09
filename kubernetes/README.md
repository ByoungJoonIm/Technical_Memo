## 개념
- Pod : 하나 이상의 어플리케이션 컨테이너
- Node : Pod 들을 포함하는 가상/물리 머신

## 사용법
- $ `minikube version`
  - 설치된 minikube의 버전을 확인
- $ `minikube start`
  - kubernetes 구동
- $ `kubectl version`
  - kubectl 설치 여부 확인 및 클러스터 정보 확인
  - 클라이언트 버전 : kubectl 버전
  - 서버 버전 : 마스터에 설치된 쿠버네티스 버전
- $ `kubectl cluster-info`
  - 마스터와 DNS가 물리적으로 어떤 서버에서 구동되는지 확인
- $ `kubectl get nodes`
  - 현재 구동되고 있는 모든 노드 정보 확인
- $ `kubectl create deployment [deployment name] --image=[image location]`
  - deployment name : 배포에 대해 지어줄 이름
  - --image=[image location] : 이미지가 있는 uri. ex) docker hub container uri
- $ `kubectl get deployments`
  - 현재 배포된 서비스들의 상태를 확인
- $ `kubectl proxy`
  - 독립된 네트워크의 각각의 Pod들에 엑세스하는 API endpoint를 제공하기 위해 사용
  - $ `curl http://localhost:8001/version`
    - proxy가 구동되고 있다면, JSON 형태로 확인 가능
- $ `export POD_NAME=$(kubectl get pods -o go-template --template '{{range .items}}{{.metadata.name}}{{"\n"}}{{end}}')`
  - 환경변수 POD_NAME에 pod의 이름을 저장
  
  
## reference
- [kubernetes](https://kubernetes.io/ko/docs/tutorials/kubernetes-basics/create-cluster/cluster-interactive/)
