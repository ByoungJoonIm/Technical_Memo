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
