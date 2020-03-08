### 추상화 단계
- 물리 단계 : 레코드가 어떻게 저장되었는지를 기술
- 논리 단계 : DB에 저장된 데이터와 데이터간의 관계를 기술
- 뷰 단계 : 응용 프로그램은 데이터 형의 상세 사항은 감춤

### 용어
- 스키마 : DB의 논리 구조
- 데이터 독립성 : 차상위 단계의 스키마 정의에 영향을 주지 않고 어떤 단계의 스키마 정의를 수정할 수 있는 능력

## 관계형 데이터베이스(Relational Database)
- 정의 : 키와 값들을 테이블화 시킨 데이터베이스
- 용어
  - 속성 값(attribute types) : 데이터
    - 원자값의 성질을 만족해야 한다
  - 도메인(domain) : 속성 값으로 허용 되는 값의 집합
    - 널(null)값은 모든 도메인에 포함됨
  - 슈퍼키(super key) : K의 값이 릴레이션의 고유한 튜플을 구분하는데 충분하다면 K는 슈퍼키이다
  - 후보키(candidate Key) : 슈퍼키가 최소한의 조건을 만족시키면 후보키이다
  - 주키(primary key) : 후보키중 하나가 주키로 선택된다
  - 외래키 제약조건 : 릴레이션 내 속성 값이 다른 릴레이션에 존재해야 한다
- 특징
  - 튜플들의 순서에는 의미가 없다