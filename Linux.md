## SSH Key
- window의 putty는 .ppk를 사용하지만, 리눅스는 .pem 방식을 사용한다. 따라서 키를 옮겨서 사용하려면 변환이 필요하다.
  ```
  sudo apt-get install putty-tools
  sudo puttygen ppkkey.ppk -O private-openssh -o pemkey.pem
  ```
