## 객체 정렬
- comparable [[예제](https://github.com/BJ-Lim/Frameworks/blob/master/java/ComparableExample.java)]
: 객체의 정렬 기준을 정할 때 사용
  - 클래스를 정의할 때 인터페이스를 상속받아서 작성
- comparator [[예제](https://github.com/BJ-Lim/Frameworks/blob/master/java/ComparatorExample.java)] : 기존에 정해놓은 객체의 정렬 기준과 다를 때 일시적으로 사용
  - 객체를 정렬할 때 Comparator 객체를 익명 클래스로 생성하여 사용
  - 자바에서는 배열도 객체이기 때문에, 이미 정의된 2차원 배열을 정렬할 때 유용
  - 익명 클래스 정의시 람다식을 사용하면 더 깔끔하게 작성 가능
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
