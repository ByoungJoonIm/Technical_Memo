## 개념
### 실행 과정
- 편집기에서 작성한 코드의 확장자 명은 `.java`이다.
- `.java` 파일을 javac로 컴파일하면 `.class` 파일이 생성된다.
  - `.class` 파일은 JVM(Java Virtual Machine)에 의해 즉시 실행 가능한 바이트코드이다.
  - `.class`파일은 JVM이 실행하기 때문에 운영체제가 변경되더라도 재컴파일 없이 실행 가능하며, 이를 플랫폼 독립적이라고 부른다.

### 상속
- 상속의 키워드는 `extends`이며, 클래스명의 뒤에 붙는다.
  - ex) public class server extends serverBase
- 메소드 오버라이딩 혹은 필드명을 참조할 때 `super` 키워드를 사용할 수 있다.
  ```java
  public class Server extends ServerBase{
    @Override
    public void run(){
      super.serverCreatedTime = curTime;  //명시적으로 부모클래스의 필드 참조
      super.run();                        //명시적으로 부모클래스의 메소드 호출
    }
  }
  ```
- 생성자는 묵시적으로 호출하는 경우 부모-자식 순으로 호출되며, 명시적으로 호출시 `super()`를 사용한다.
  - 부모 클래스의 생성자 형식을 따르며, `super(args...)`의 형식을 따른다.
- 모호성 방지를 위해서 다중 상속은 불가능하다.

### 추상클래스
- 몸체를 의도적으로 구현하지 않은(추상) 메소드가 하나라도 있는 클래스를 추상클래스라고 한다.
- 추상 메소드를 제외한 메소드는 몸체의 구현이 있을 수 있다.
- 예제
  ```java
  public abstract class AbstractClass {
    public void method1(){
      System.out.println("Hello Abstract Class!");
    }
	  
    public abstract void method2();
  }
  ```
  ```java
  public class AbstractClassTest extends AbstractClass {     
  //abstract class를 상속받으면, abstract method는 모두 구현해야 한다.
  //@Override도 사용 가능하다.
    public void method2(){
		  System.out.println("This was implemented in child class!");
    }

    public static void main(String args[]){
		  AbstractClassTest act = new AbstractClassTest();
		  act.method1();
		  act.method2();
    }
  }
  ```
  - 실행 결과
    ```
    Hello Abstract Class!
    This was implemented in child class!
    ```
### 인터페이스
- 인터페이스의 키워드는 `implements`이며, 클래스명의 뒤에 붙는다.
  - ex) public class server implements iServer
- 인터페이스는 특정 함수의 구현을 강제할 때 사용된다.
- 메소드의 몸체가 없다.
- 1개 이상의 implements가 가능하다.
- 예제
  ```java
  public interface InterfaceA {
    default void method1(){	//default keyword를 이용한 구현
      System.out.println("Method1 was implemented in Interface by using default keyword!");
    };
    public void method2();
  }
  ```
  ```java
  public interface InterfaceB {
    public void method2();
    public void method3();
  }
  ```
  ```java
  public class InterfaceTest implements InterfaceA, InterfaceB {
  //InterfaceA와 InterfaceB 모두 method2를 구현하도록 강제하지만,
  //구현은 이 클래스에서 일어나므로 모호성은 발생하지 않는다.
    public void method2(){
      System.out.println("Method2 was implemented in interfaceTest class! It was forced by InterfaceA and InterfaceB.");
    }
	
    public void method3(){
      System.out.println("Method3 was implemented in interfaceTest class! It was forced by InterfaceB.");
    }
	
    public static void main(String[] args){
      InterfaceTest ift = new InterfaceTest();
      ift.method1();
      ift.method2();
      ift.method3();
    }
  }
  ```
  - 결과
    ```
    Method1 was implemented in Interface by using default keyword!
    Method2 was implemented in interfaceTest class! It was forced by InterfaceA and InterfaceB.
    Method3 was implemented in interfaceTest class! It was forced by InterfaceB.
    ```
### 추상 클래스와 인터페이스의 비교
... | 추상 클래스 | 인터페이스
---- | ---- | ----
목적 | 추상 클래스를 상속받아 기능을 이용 및 확장 | 함수의 구현을 강제하여 구현된 클래스로부터 생성된 객체가 동일한 동작이 가능
특징 | 상속을 받아 사용하기 때문에, 다중 상속이 불가능 | 1개 이상의 implements 가능. 즉, 다중 상속과 비슷한 효과 기대 가능
구현 정도 | 추상 메소드 이외의 메소드는 구현이 있을 수 있음 | 모든 메소드는 구현이 없음(자바8에서는 default 키워드로 구현 가능)

### 예외 처리
- 예외 처리 흐름
  ```java
  import java.io.IOException;

  public class TryCatchFlow {
    private static void depth3() throws IOException{
      throw new IOException();	//IOException 발생
    }
	
    private static void depth2() throws IOException{
      depth3();
    }
	
    private static void depth1(){
      try{
        depth2();
      } catch (IOException ioe){	//IOException 처리
        System.out.println("Catched IOException in depth1");
      }
    }
	
    public static void main(String[] args) {
      depth1();
    }
  }
  ```
  - 호출 순서 : main -> depth1 -> depth2 -> depth3 -> 예외 발생 -> depth2 -> depth1 -> 예외 처리

### 쓰레드
- 목적 : 하나의 프로그램이 여러 작업을 동시에 수행하기 위해서 사용
- 사용 방법
  - Thread 클래스를 상속받아 run 메소드를 재정의
  - Runnable 인터페이스를 구현한 객체를 쓰레드 생성시 생성자 매개변수로 전달해주는 방법
- 예제
  ```java
  public class ThreadTest {
    private static class Thread1 extends Thread{
      @Override
        public void run(){
          System.out.println("This is printed in Thread1!");
        }
    }
	
    private static class Thread2 implements Runnable{
      @Override
      public void run(){
        System.out.println("This is printed in Thread2!");
      }
    }
	
    public static void main(String[] args) {
      Thread1 thread1 = new Thread1();
      Thread thread2 = new Thread(new Thread2());
		
      thread1.start();
      thread2.start();
    }
  }
- 결과
  ```
  This is printed in Thread1!
  This is printed in Thread2!
  ```
  또는
  ```
  This is printed in Thread2!
  This is printed in Thread1!
  ```
- 동기화 문제 해결 : 공유자원을 사용하는 메소드에 `synchronized` 키워드 사용

## 응용
- Collection Interface
  - [참조](https://www.tutorialspoint.com/java/java_collection_interface.htm)

## 심화
### 객체 정렬
- comparable [[예제](https://github.com/ByoungJoonIm/Technical_Memo/blob/master/languages/java/ComparableExample.java)]
: 객체의 정렬 기준을 정할 때 사용
  - 클래스를 정의할 때 인터페이스를 상속받아서 작성
  - `java.lang` 패키지에 속하기 때문에, 별도의 import없이 implements 가능
- comparator [[예제](https://github.com/BJ-Lim/Frameworks/blob/master/languages/java/ComparatorExample.java)] : 기존에 정해놓은 객체의 정렬 기준과 다를 때 일시적으로 사용
  - 객체를 정렬할 때 Comparator 객체를 익명 클래스로 생성하여 사용
  - 자바에서는 배열도 객체이기 때문에, 이미 정의된 2차원 배열을 정렬할 때 유용
  - 익명 클래스 정의시 람다식을 사용하면 더 깔끔하게 작성 가능
  - `java.util` 패키지에 속하기 때문에, 별도의 import 필요
- [객체 정렬 개념 참고 사이트](https://gmlwjd9405.github.io/2018/09/06/java-comparable-and-comparator.html) 

## 그 외 문법
- 문자열 앞 뒤 공백 제거
  ```java
  String str = "   abc  ".trim();
  ```
- 문자열 전체 소문자로 변경
  ```java
  str = str.toLowerCase();
  ```
- 문자열 전체 대문자로 변경
  ```java
  str = str.toUpperCase();
  ```
- 문자열 내에 특정 문자열을 다른 문자열로 치환
  ```java
  String str = "ABABAB";
  str = str.replace("AB", "C");   // rs : CCC
  ```
- Key, Value를 가지는 HashMap
  ```java
  import java.util.HashMap;

  HashMap<String, Integer> hm = new HashMap<String, Integer>();
  
  hm.put("abc", 10);
  if(hm.containsKey("abc"))
  	hm.put("abc", hm.get("abc") + 1);
  System.out.println(hm.get("abc"));      //11
  ```
- HashMap과 람다식으로 더 간단하게 사용하기
  ```java
  hm.computeIfPresent("abc", (String key, Integer value) -> value++);
  hm.putIfAbsent("abc", 10);
  ```
- 2차원 배열 이상에서 인덱스 사용하기
  ```java
  int arr[][] = new int[10][2];
  for (int i = 0; i < arr.length; i++) {
  	for (int j = 0; j < arr[0].length; j++) {
  		arr[i][j] = 1;
  		System.out.println(i + "," + j + " : " + arr[i][j]);
  	}
  }
  ```
- ArrayList의 배열 선언
  ```java
  ArrayList<Integer> ali[] = new ArrayList[10];
  ali[0] = new Arraylist<Integer>();
  ali[0].add(10);
  ```
  ```java
  List<List<Node>> list = new ArrayList<List<Node>>();
  list.add(new ArrayList<Node>());
  ```
- HashMap의 iterator
  ```java
  HashMap<Integer, Integer> hs = new HashSet<Integer, Integer>();
  hs.put(1, 3);
  Iterator it = hs.keySet().iteraotr();
  while(it.hasNext)
    int key = it.next();
  ```

- 특정 값으로 배열 채우기
  ```java
  Arrays.fill(arr, INF);
  ```
- 컬렉션을 배열로 변환
  ```java
  ArrayList<Integer> al = new ArrayList<Integer>();
  int arr[] = Collections.toArray(al);
  ```
- 큰 정수 표현 객체
  ```java
  BigInteger n = new BigInteger(br.readLine());
  BigInteger m = new BigInteger(br.readLine());
  bw.write(n.multiply(m) + "\n");
  bw.write(n.add(m) + "\n");
  ```
