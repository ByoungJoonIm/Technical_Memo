## 초기 설정
- 디스플레이 확장 기능 설치
  - $ `sudo apt update`
  - $ `sudo apt-get install build-essential gcc make perl dkms`
  - $ `sudo reboot`
  - `장치` - `게스트 확장 CD 이미지 삽입` 후 명령어가 모두 실행될때 까지 기다림
  - $ `sudo reboot`
- vim 에디터 설치 및 기본 설정
  - $ `apt install vim`
  - $ `vi ~/.vimrc`를 한 뒤 다음과 같은 설정 추가(파이썬 기준 문법)
    ```
    colorscheme desert
    syntax on
    set smartindent
    set autoindent
    set cindent
    set ts=4
    set softtabstop=4
    set expandtab
    filetype indent plugin on
    let python_version_3 = 1
    let python_highlight_all = 1
    ```
- python & pip 설치
  - $ `sudo apt install python3 python3-pip`
  - 설치한 python3와 pip3를 기본으로 설정
    - $ `sudo vi /etc/bash.bashrc`
    - 가장 하단에 다음 2줄을 추가
      ```
      alias python=python3
      alias pip=pip3
      ```
    - $ `source /etc/bash.bashrc`
    - $ `python --version` 으로 적용 확인    
    
## bash
- timezone 설정
  - $`timedatectl seet-timezone 'Asia/Seoul'`
