## 개념
- Pod : 하나 이상의 어플리케이션 컨테이너를 포함한 개념. 컨테이너, DB등을 포함
  - 각 팟은 고유의 IP를 가진다.
- Node : Pod 들을 포함하는 가상/물리 머신
- service : 네트워크와 관련된 오브젝트. 하나의 논리적인 파드 셋과 그 파드들에 접근할 수 있는 정책을 정의하는 추상적 개념
  - 애플리케이션에 트래픽이 실릴 수 있도록 해준다.
- deployment : 어플리케이션들을 관리한다.
- ReplicaSet : pod을 생성 및 갯수 유지

### YAML
- Port : 쿠버네티스 서비스를 클러스터 안에서 특정 포트로 노출. 같은 클러스터에 있는 다른 팟들은 이 포트로 통신 가능
- TargetPort : 서비스가 request 날리는 포트로, 이 포트는 pod이 listening하고 있는 포트. 컨테이너 안의 어플리케이션도 이 포트에 대해 listening 상태여야함
- NodePort : 서비스를 클러스터 밖으로 노출시킴

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
- $ `kubectl get pods`
  - 현재 구동되고 있는 모든 팟 정보 확인
  - 옵션
    - `-l` : label을 사용하여 필터링 가능
- $ `kubectl get rs`
  - deployment에 의해 생성된 ReplicaSet을 보여준다.
- $ `kubectl describe pods`
  - 현재 구동되고 있는 모든 팟에 대한 상세 정보를 확인. 어떤 컨테이너가 구동되고 있는지, IP가 뭔지, 서비스는 뭐가있는지, 포트는 뭐가 열렸는지 등의 정보 확인 가능
- $ `cubectl logs $POD_NAME`
  - `$POD_NAME` 팟에 대한 로그를 출력한다. 로그는 해당 팟의 STDOUT을 포함한다.
- $ `kubectl exec $POD_NAME env`
  - 해당 팟에서 명령어를 실행하고, 결과를 출력한다.
  - `$POD_NAME` : 팟 이름
  - `env` : 명령어로 대체됨
  - $ `kubectl exec -it $POD_NAME bash`
    - 컨테이너 안의 shell을 실행한다.
- $ `kubectl get services`
  - 서비스 리스트를 출력
- $ `kubectl scale delployments/kubernetes-bootcamp --replicas=4`
  - deployments/kubernetes-bootcamp의 replicaSet을 스케일링한다.
  - $ `kubectl get deployments`로 스케일링 이후에 상태를 확인 가능하다.
  - $ `kubectl get pods -o wide`로 스케일링 이후 각 팟을 확인할 수 있다.
  - $ `kubectl describe deployments/kubernetes-bootcamp`로 스케일링 이후에 이벤트에 스케일링 로그가 있는 것을 확인할 수 있다.
- $ `kubectl delete service -l run=kubernetes-bootcamp`
  - `run=kubernetes-bootcamp`를 라벨로 가진 서비스를 삭제
- $ `kubectl create deployment [deployment name] --image=[image location]`
  - deployment name : 배포에 대해 지어줄 이름
  - --image=[image location] : 이미지가 있는 uri. ex) docker hub container uri
- $ `kubectl get deployments`
  - 현재 배포된 서비스들의 상태를 확인
- $ `kubectl describe deployment`
  - 배포에 대한 상세 정보 확인. 레이블도 함께 확인 가능
- $ `kubectl label pod $POD_NAME app=v1`
  - $POD_NAME 팟에 app=v1이라는 레이블 등록.
- $ `kubectl set image deployments/kubernetes-bootcamp kubernetes-bootcamp=jocatalin/kubernetes-bootcamp:v2`
  - rolling 업데이트 수행. 이미지를 새로운 이미지로 교체
  - $ `kubectl get pods`를 실행하면 terminating된 pods와 Running중인 pods 확인 가능
- $ `kubectl rollout status deployments/kubernetes-bootcamp`
  - 현재 상태를 기록. 다음 rollback시 이 상태로 돌아옴
- $ `kuberctl rollout undo deployments/kubernetes-bootcamp`
  - 업데이트를 이전 상태로 되돌림
- $ `kubectl expose deployment/kubernetes-bootcamp --type="NodePort" --port 8080`
  - deployment의 특정 팟의 포트를 노출시킨다. 해당 노출 정보는 service에 등록된다.
  - $ `kubectl get services`를 실행하면 추가된 서비스를 확인할 수 있다.
  - $ `kubectl describe services/kubernetes-bootcamp` 를 실행하면 해당 서비스의 상세정보를 확인 가능하다. 
  - $ `export NODE_PORT=$(kubectl get services/kubernetes-bootcamp -o go-template='{{(index .spec.ports 0).nodePort}}')`
    - NODE_PORT라는 환경변수에 방금 할당받은 포트 번호를 등록한다.
  - $ `curl $(minikube ip):$NODE_PORT`
    - 노출된 포트에 접속해서 확인한다.
- $ `kubectl proxy`
  - 독립된 네트워크의 각각의 Pod들에 엑세스하는 API endpoint를 제공하기 위해 사용. 각각의 PODS은 고립되어 있어서, proxy를 활용하여 컨트롤해야한다.
  - $ `curl http://localhost:8001/version`
    - proxy가 구동되고 있다면, JSON 형태로 확인 가능
- $ `export POD_NAME=$(kubectl get pods -o go-template --template '{{range .items}}{{.metadata.name}}{{"\n"}}{{end}}')`
  - 환경변수 POD_NAME에 pod의 이름을 저장
  
## K3s
- 경량화 kubernetes
- k3s 서비스 종료
  - $ `k3s-killall.sh`
- k3s 실행 옵션 변경
  - $ `sudo vi /etc/systemd/system/k3s.service`
    ```
    ExecStart=/usr/local/bin/k3s \
    server \
        '--write-kubeconfig-mode' \
        '644' \
        '--disable' \
        'coredns' \
        '--disable' \
        'servicelb' \
        '--disable' \
        'metrics-server'
        '--disable' \
        'traefik' \
    ```
    - 맨 밑에 실행 옵션 추가
  - $ `sudo systemctl daemon-reload`
  - $ `sudo systemctl restart k3s`
  
  
## reference
- [kubernetes](https://kubernetes.io/ko/docs/tutorials/kubernetes-basics/create-cluster/cluster-interactive/)
  - ingress
    - [쿠버네티스 Ingress 개념 및 사용 방법, 온-프레미스 환경에서 Ingress 구축하기](https://blog.naver.com/PostView.nhn?blogId=alice_k106&logNo=221502890249&parentCategoryNo=&categoryNo=&viewDate=&isShowPopularPosts=false&from=postView)
    - [쿠버네티스 Ingress의 ClusterIP Bypass, Annotation, SSL/TLS를 위한 인증서 적용](https://blog.naver.com/PostView.nhn?blogId=alice_k106&logNo=221503924911&parentCategoryNo=&categoryNo=&viewDate=&isShowPopularPosts=false&from=postView)
- k3s
  - [k3s 시리즈 - 간단하게 Kubernetes 환경 구축하기](https://si.mpli.st/dev/2020-01-01-easy-k8s-with-k3s/)
  - [Kubernetes에서 쉽게 HTTPS 웹 서비스 돌리기](https://si.mpli.st/dev/2020-03-01-k3s-https/)

