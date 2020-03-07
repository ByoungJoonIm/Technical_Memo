## 용어
### remote to remote
- fork : 다른 사람의 repository를 나의 remote repository로 복사
- pull request : 원래의 프로젝트에서 나의 프로젝트로 혹은 나의 프로젝트에서 원래의 프로젝트로 merge 요청

### remote to local
- clone : remote repository에 있는 프로젝트를 local repository로 복사
- fetch : remote repository의 변경 이력을 가져옴(origin/master branch 업데이트됨)
- pull : fetch + merge

### local to remote
- push : local repository의 변경사항을 remote repository에 반영. 이때 remote repository의 수정 권한 필요

### local
- add : 파일을 stage에 올림
- commit : 변경 이력을 기록
- branch : 커밋 기록 포인터를 새로 생성
- checkout : 커밋 기록 포인터를 이동
- merge : 현재 커밋 기록 포인터와 지정한 커밋 기록 포인터를 합침
  - fast-forward-mrege : 두 커밋 포인터의 관계가 일직선상에 위치해서(한 커밋 포인터가 다른 커밋 포인터의 과거 이력) 선행 커밋 포인터로 위치 변경
  - 3-way-merge : 두 커밋 포인터의 관계가 일직선상에 위치하지 않아서, 새로운 커밋을 자동으로 생성하여 merge하는 커밋 포인터를 새로운 커밋에 위치시킴
- conflict : merge시에 파일 변경 이력이 충돌하는 경우(같은 위치에 다른 내용이 있는 경우)에 발생
- rebase : 서로 다른 줄기의 브랜치들을 하나의 줄기의 브랜치로 합병
- reset : 커밋 포인터의 위치를 되돌리고, 그 변경 이력을 기록하지 않음
  - soft : 특정 커밋으로 되돌리며 파일들의 staging 상태는 유지
  - mixed : 특정 커밋으로 되돌리며 파일들의 staging 상태 해제(변경된 내용 및 파일은 그대로 유지)
  - hard : 특정 커밋의 상태로 완전히 되돌림(변경된 내용 및 파일도 삭제됨)
- revert : 특정 커밋으로 되돌리며 되돌리는 행위 자체를 커밋으로 기록
- amend : 커밋의 내용 수정
- stash : 현재 변경 이력을 임시 저장하여 다른 브랜치로 checkout이 가능하도록 함

