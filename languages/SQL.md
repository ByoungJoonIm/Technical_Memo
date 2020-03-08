## 정의
- SQL(Structured Query Language) : RDBMS(Relational database management system)를 위해 설계된 특수 언어

## 문법
### DDL(Data Definition Language)
- create : 테이블 생성
  ```SQL
  create table instructor(
    ID          char(5),
    name        varchar(20) not null,
    dept_name   varchar(20),
    salary      numeric(8, 2)
    primary key(ID),
    foreign key (dept_name) references department)
  ```
- drop : 테이블 삭제
  ```SQL
  drop table student
  ```
- alter : 스키마 수정
  ```SQL
  alter table r add A D
  ```
  ```
  A : 릴레이션 r에 추가될 애트리뷰트명
  D : A의 도메인
  실행 완료시 새로운 애트리뷰트의 값으로 널이 할당됨
  ```
### DML(Data Management Language)
- select : 데이터 조회
  ```SQL
  select distinct dept_name
  from instructor
  where salary >= 80000 and salary <= 100000
  ```
  - `where salary between 80000 and 100000`과 동일
  - distinct : 중복 제거
- join : 두 릴레이션간을 함께 조회
  ```SQL
  select I.name, T.course_id
  from instructor as I, teaches as T
  where I.ID = T.ID
  order by I.name, T.course_id desc
  ```
  - desc : 내림차순. 지정 안할시 기본 오름차순(asc)
- natural join : 모든 공동 속성 값이 같은 튜플만을 매치하여 결과를 돌려줌
  ```SQL
  select *
  from instructor natural join teaches;
  ```
- 집합 연산
  ```SQL
  (select course_id from section where sem = 'Fall' and year = 2009)
  union / intersect / except
  (select course_id from section where sem = 'Spring' and year = 2010)
  ```
  - 이 연산은 자동으로 중복을 제거하며, 중복을 유지하려면 `all`을 사용한다.
    - ex) `union all`
  - union : 합집합, or
  - intersect : 교집합, and
  - except : 차집합, not in
