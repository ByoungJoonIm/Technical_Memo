## Concept
- response 객체가 자동으로 json으로 변환되는 이유
  - spring은 Jackson2가 클래스 패스에 있고, 자동으로 객체 인스턴스를 json으로 변환해주기 때문
- org.springframework.boot.CommandLineRunner
  - spring-boot는 모든 CommandLineRunner를 실행하므로 log를 기록할 때 유용

## Annotation
- @RestController = @Controller + @ResponseBody
- @Entity = 현재 객체가 JPA-based 데이터로 저장되기 위한 JPA annotation

## Build
- Maven
  - mvn VS mvnw
    - mvn : 로컬에서 빌드툴인 maven을 실행하는 명령어로, 환경변수가 설정되어 있어야 함
    - mvnw : 필요한 툴을 알아서 다운받아 빌드까지 해주는 스크립트로, spring-boot 사용시 제공됨
      - window의 경우 경로를 확인한 뒤 실행
  - mvnw
    - 바로 실행하기
      - `mvnw spring-boot:run`
    - jar로 만들고 실행하기
      - `mvnw clean package`
      - `java -jar target/[생성된파일].jar`
