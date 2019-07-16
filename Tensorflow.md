# Ubuntu 16.04 GPU 사용
## 설치 순서

- docker 설치 
  - `sudo wget -qO- https://get.docker.com/ | sh`

- Docker를 sudo 없이 사용하기(이 설정 후에는 sudo를 안붙여도 되며 재접속 이후 사용 가능) 
  - `sudo usermod -aG docker $USER`
  - `sudo service docker restart`

  - logout후 login하면 적용

- 확인사항(대부분 안해도 무방)
1. uname -r (커널 버전) > 3.10
2. docker --version >= 1.12
3. nvidia gpu architecture 확인(600대 이상은 넘어가기, 이전버전은 확인 필요)

- 드라이버 확인
  - `ubuntu-drivers`

- 드라이버 설치
  - `sudo ubuntu-drivers autoinstall`

- 재부팅
- nvidia-smi 명령어로 설치 확인

- 1.0 설치시 제거 과정
  - `docker volume ls -q -f driver=nvidia-docker | xargs -r -I{} -n1 docker ps -q -a -f volume={} | xargs -r docker rm -f`
  - `sudo apt-get purge -y nvidia-docker`

- 패키지 추가
```
curl -s -L https://nvidia.github.io/nvidia-docker/gpgkey | \
  sudo apt-key add -
distribution=$(. /etc/os-release;echo $ID$VERSION_ID)
curl -s -L https://nvidia.github.io/nvidia-docker/$distribution/nvidia-docker.list | \
  sudo tee /etc/apt/sources.list.d/nvidia-docker.list
```
- `sudo apt-get update`

- nvidia-docker2 설치
  - `sudo apt-get install -y nvidia-docker2`
  - `sudo pkill -SIGHUP dockerd`

- 설치 테스트
  - `docker run --runtime=nvidia --rm nvidia/cuda:9.0-base nvidia-smi`
  
- 본인의 드라이버 및 CUDA 버전에 맞는 tensorflow gpu 이미지 다운로드
  - `docker pull tensorflow/tensorflow:1.12.0-gpu`
    - (주의!) 사람마다 다른 버전의 tensorflow를 깔아야 할 수 있으며, gpu버전과 jupyter버전이 나누어져 있는 경우가 있습니다.
- 실행
  - `docker run -it -d --runtime=nvidia --name tf -p 8888:8888 -v /home/genz/jupyter_notebook:/notebooks tensorflow/tensorflow:1.12.0-gpu jupyter notebook --allow-root`
  - `docker logs tf | grep token`
- 해당 PC의 브라우저에서 다음 주소로 접속
  - `http://localhost:8888`
- 비밀번호로 위에서 검색한 토큰의 값 입력
