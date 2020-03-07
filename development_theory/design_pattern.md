### MVC(Model-View-Controller) / MVT(Model-View-Template) Pattern
- 정의 : 모델, 뷰, 컨트롤러로 각각의 역할을 나눠 유지보수가 용이하도록 한 디자인 패턴으로 java(spring)에서는 MVC, Django에서는 MVT라 부른다
  - Model : '무엇을'을 정의하는 부분으로, 데이터의 구조 등을 정의
  - Controller : '어떻게'를 담당하는 부분으로, 처리 내용은 모두 이곳에 위치
  - View : 사용자에게 보여지는 화면만을 정의
- 흐름 : client request -> controller -> Model -> controller -> view -> client response
  ```
  client request : 웹 페이지 혹은 서비스를 서버에 요청
  controller : 요청을 분석하여 처리 루틴을 가짐. 데이터의 생성/삽입/조회/삭제가 필요한 경우 Model로 가고, 아니면 바로 View로 이동
  model : DB / file 등을 엑세스하여 controller의 요청에 따라 데이터의 생성/삽입/조회/삭제 등을 수행
  view : controller가 model에서 받아온 정보를 기반으로 웹 페이지를(사용자에게 보여질 화면) 작성
  client response : view에서 작성된 페이지 혹은 응답 결과를 client에 전달
  ```
  
### Singleton Pattern
- 정의 : 한 클래스에서 객체를 생성할 때, 단 한개의 객체만 가지도록 강제하는 디자인 패턴
- 방법 : 처음 객체를 생성할때는 객체를 생성하여 반환, 다음부터는 이미 있는 객체를 반환한다. 객체 생성에는 메소드를 사용한다(new를 직접 사용 안함)
- DB Connector 등에서 사용하면 유용
