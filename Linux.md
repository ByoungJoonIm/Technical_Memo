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
- vi 에디터 주석 색 바꾸기
  - `vi ~/.vimrc`
  - `colorscheme desert` 입력 후 저장
  
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




## reference
- [압축 및 해제](https://nota.tistory.com/53)
