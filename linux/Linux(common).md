## SSH Key
- window의 putty는 .ppk를 사용하지만, 리눅스는 .pem 방식을 사용한다. 따라서 키를 옮겨서 사용하려면 변환이 필요하다.
  ```
  sudo apt-get install putty-tools
  sudo puttygen ppkkey.ppk -O private-openssh -o pemkey.pem
  ```
- [참고](https://aws.amazon.com/ko/premiumsupport/knowledge-center/convert-pem-file-into-ppk/)

## bash
- 출력을 redirection 했을 때, `[^` 문자가 보이는 경우
  - ansi 스타일의 출력을 사용했기 때문으로, 글씨에 색을 넣기 위한 기호임
  - ansi 관련 on, off 기능이 있는 경우 off로 하면 이와같은 문제가 발생하지 않음
  - [참고](https://stackoverflow.com/questions/23225064/how-to-avoid-special-characters-when-redirecting-output-in-bash-scripts)
- 특정 파일 내용 찾기
  - `grep -iRl "your-text-to-find" ./`
- SCP
  - `scp -P 10022 src_file_name user_name@host_name:/home/usr_name/dst_file_name`
  ```
  scp : 리눅스간 파일 전송
  -P 10022 : 수신측의 포트는 10022를 사용(일반적으로 22번을 사용하며, 이는 default이다.)
  schema.sql : 보낼 파일(현재 명령어를 실행하는 PC의 위치)
  user_name@host_name:/home/usr_name/schema.sql : 받을 파일(파일을 수신받을 PC)
  ```
- SHA256
  ```
  echo -n "foobar" | shasum -a 256
  ```
- substring
  ```
  cmd=`echo -n "foobar" | shasum -a 256`
  echo ${cmd:0:64}
  ```
- vi 에디터 기본 설정 변경
  - `vi ~/.vimrc`(유저별 개별 적용) 혹은 `vi /etc/vimrc`의 가장 아랫부분에 추가(전체 적용)
    ```
    colorscheme desert  # style 변경, 보통 주석 색이 잘 안보여서 사용
    set autoindent      # 자동 들여쓰기
    syntax on           # 언어에서 식별자 색 입히기
    ``` 
- 환경변수 편집 및 영구 적용(전체 쉘)
  - `vi ~/.bashrc`
  - 맨 밑줄에 `PATH=$PATH:target_path` 추가 후 저장
    - target_path : 추가할 path
  - `source ~/.bashrc` 명령으로 환경변수 적용
- 특정 크기의 의미없는 파일 생성
  1. dd를 이용
    - `dd if=/dev/zero of=temp bs=1G count=16`
      - if : 복사할 파일(src)
      - of : 복사될 파일(dest)
      - bs : 블록 사이즈
        - 최대 2GB 인듯. 특정 시스템에서만 그럴 수 있음
      - count : 블록을 반복할 횟수
      - 생성되는 파일 사이즈 = bs * count
  2. fallocate 이용
    - `fallocate -l 2GB swap_tmp`
      - 2GB : 용량 지정
      - swap_tmp : 파일명 지정
- 터미널에 메시지 보내기
  - broadcast
    - `wall [message]`
    - `wall` 입력 후 여러 라인 입력 -> `ctrl+d`로 보내기
  - unicast
    - `w` or `who`로 메시지 대상자에 해당하는 tty 찾기
    - `echo "message" > /dev/pts/1`
- 특정 사용자의 권한으로 실행(현재 루트일 때)
  - $ `su -c '명령어' '사용자명'`

## User 관리
- User 삭제
  - $ `userdel [사용자명]`
- User & 해당 사용자의 홈 디렉토리까지 삭제
  - $ `userdel -r [사용자명]`

## apt
- 깔려있는 apt 패키지 리스트 확인
  - $ `dpkg -l`
- openjdk 설치
  - $ `apt install openjdk-8-jdk`

## bash shell 문법
- 파라미터가 있는지 체크하는 구문
  ```
  if [ $# -eq 0 ]
  then
    echo "usage : ./script.sh [param1]"
    exit
  fi
  #do something
  ```  
  
## Grub
- 리눅스의 기본 boot 매니저이다.
- grub가 깔려있는 경우 기본적으로 10초동안 사용자가 운영체제를 선택하도록 기다리는데, 이는 서버로 사용하기에 적합하지 않다.
- 시작시 timeout을 0으로 주려면 다음과 같이 하면된다.
  - grub 설정 파일 변경
    - `sudo vi /etc/default/grub`
    - `GRUB_TIMEOUT=10` 을 `GRUB_TIMEOUT=0.0`으로 변경후 저장
  - grub 설정 파일 변경사항 적용
    - `sudo update-grub`
  - 재시작
    - `sudo reboot`
    
## 압축 및 해제
- 리눅스는 다양한 압축 및 해제 패키지를 제공한다.
- 압축 및 해제는 다음과 같이 하면 된다.
  - tar 압축
    - `tar -cvf [target.tar] [dir_name]`
      - target.tar : 압축될 파일명
      - dir_name : 압축할 디렉토리명
  - tar 압축 해제
    - `tar -xvf [target.tar]`
      - target.tar : 압축 해제할 파일명
  - tar.gz로 압축
    - `tar -zcvf [target.tar.gz] [dir_name]`
      - target.tar : 압축될 파일명
      - dir_name : 압축할 디렉토리명
  - tar.gz 압축 해제
    - `tar -zxvf [target.tar.gz]`
      - target.tar.gz : 압축 해제할 파일명
- 옵션은 다음과 같다.

  옵션 | 설명
  ---- | ----
  -c | 파일을 tar로 묶기
  -p | 파일 권한을 저장
  -v | 묶거나 파일을 풀 때 과정을 화면으로 출력
  -f | 파일 이름을 지정
  -C | 경로를 지정
  -x | tar 압축 풀기
  -z | gzip으로 압축하거나 해제

## Swap 관리
- swap file 추가
  - 큰 파일 생성
    - `fallocate -l 2GB swap_temp`
    - 파일 권한 변경
      - `chmod 600 swap_temp`
    - 파일 소유주 및 그룹 변경
      - `sudo chown root:root swap_temp`
  - 스왑 파일 시스템 생성
    - `sudo mkswap swap_temp`
  - 활성화
    - `sudo swapon swap_temp`
      - -p 우선순위 : 해당 스왑 파일의 우선순위를 설정
  - 부팅시에 스왑 마운트
    - `sudo vi /etc/fstab`
- swap file 제거
  - 부팅시에 스왑 마운트 취소
    - `sudo vi /etc/fstab`
  - 비활성화
    - `sudo swapoff swap_temp`
  - 스왑 파일 삭제
    - `sudo rm swap_temp`

## Network
- 고정 IP 설정
  - `ifconfig` 명령어로 랜카드 이름 확인
  - `vi /etc/network/interfaces`
    - 다음과 같이 입력
    ```
    auto lo
    iface lo inet loopback

    auto enp3s0
    iface enp3s0 inet static
    address 192.0.0.2
    netmask 255.255.255.0
    gateway 192.0.0.1
    dns-nameservers 192.0.0.3 192.0.0.4
    ```
      - enp3s0 : `ifconfig`시 확인한 랜카드 명
      - 라인 4~9가 enp3s0에 대한 고정 IP를 설정하는 부분
  - 서비스 재시작
    - `sudo service networking restart`
    - 위 명령어로 서비스 재시작이 가능하지만, 방화벽등의 이유로 재부팅 권장
  

## reference
- [압축 및 해제](https://nota.tistory.com/53)
- [swap 관리](https://htst.tistory.com/32)
- [network](https://www.lesstif.com/pages/viewpage.action?pageId=24445571)
