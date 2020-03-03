## 다형성과 단형성
- 다형성 : 프로그램 언어의 각 요소들(상수, 변수, 식, 오브젝트, 함수, 메소드 등)이 다양한 자료형(type)에 속하는 것이 허가되는 성질
  - 예제
    ```java
    //숫자를 문자열로 바꾸는 경우
    string = number.StringValue();

    //날짜를 문자열로 바꾸는 경우
    string = date.StringValue();
    ```
- 단형성 : 프로그램 언어의 각 요소들(상수, 변수, 식, 오브젝트, 함수, 메소드 등)이 다양한 자료형(type)에 속하는 것이 허가되지 않는 성질
  - 예제
    ```java
    //숫자를 문자열로 바꾸는 경우
    string = StringFromNumber(number);

    //날짜를 문자열로 바꾸는 경우
    string = StringFromDate(date);
    ```
