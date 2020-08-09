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
  
## reference
- [kubernetes](https://kubernetes.io/ko/docs/tutorials/kubernetes-basics/create-cluster/cluster-interactive/)
