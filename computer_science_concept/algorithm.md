## 정렬
- O(n^2)
  - 선택 정렬 : 가장 작은 원소를 찾아 0번부터 올바른 위치에 배열
    ```java
    private static void selectionSort(int arr[]){
      for (int i = 0; i < arr.length - 1; i++) {
        int minIndex = i;
        for (int j = i + 1; j < arr.length; j++) {
          if(arr[j] < arr[minIndex])
            minIndex = j;
          }
          swap(arr, i, minIndex);
      }
    }
    ```
  - 버블 정렬 : 가장 큰 원소를 오른쪽 끝으로 보내며 순차적으로 윈도우를 왼쪽으로 옮기며 정렬
    ```java
    private static void bubbleSort(int arr[]){
      for (int i = arr.length - 1; i > 0; i--) {
        for (int j = 0; j < i; j++) {
          if(arr[j] > arr[j + 1])
            swap(arr, j, j + 1);
        }
      }
    }
  ```
  - 삽입 정렬 : 윈도우를 0번부터 시작하여 끝가지 이동하며 지금까지 정렬된 값 중 올바른 위치에 삽입
- O(nlogn)
  - 합병 정렬 : 배열을 이등분하여 각각 정렬한 후 합병
  - 퀵 정렬 : 한 원소를 pivot으로 선정하고 pivot 기준으로 2개의 파티션으로 분할한 뒤 왼쪽 파티션에는 pivot보다 작은 값, 오른쪽 파티션에는 pivot보다 크거나 같은 값을 배치하여 순환 적용
  - 히프 정렬 : 정렬할 원소를 모두 공백 히프에 삽입한 뒤 루트 노드를 차례로 삭제하여 리스트 뒤에 삽입
  - 트리 정렬 : 배열을 (중복이 허용되는)이진 정렬 트리에 삽입한 뒤, 중위 우선 순회 방법으로 원소를 하나씩 검색하여 원래의 배열에 저장
- O(n^1.25)
  - 쉘 정렬 : 원소의 비교 연산과 먼 거리의 이동을 줄이기 위해 몇 개의 서브리스트로 나누어 삽입 정렬을 반복 수행
    - 간격(interval)이 작을수록 짧은 거리를 이동하고, 원소의 이동이 적다
- O(n)
  - 기수 정렬 : 정렬할 원소의 키값을 나타내는 숫자의 자리수를 기초로 정렬하는 기법
  
## 탐색
- 이진 탐색
  ```java  
  import java.io.BufferedWriter;
  import java.io.IOException;
  import java.io.OutputStreamWriter;

  public class BinarySearch {
    //재귀로 구현
    private static int binSearch(int arr[], int left, int right, int value){
      if(left > right)
        return -1;
		
      int mid = (left + right) / 2;
		
      if(arr[mid] == value)
        return mid;
      if(arr[mid] < value)
        return binSearch(arr, mid + 1, right, value);
      if(arr[mid] > value)
        return binSearch(arr, left, mid - 1, value);
		
      return -1;	//This will not occured
    }

    //반복으로 구현
    private static int binSearch(int arr[], int value){
      int left = 0;
      int right = arr.length - 1;
		
      while(left <= right){
        int mid = (left + right) / 2;
			
        if(arr[mid] == value)
          return mid;
        if(arr[mid] < value)
          left = mid + 1;
        if(arr[mid] > value)
          right = mid - 1;
      }
		
      return -1;
    }
	
    public static void main(String[] args) throws IOException {
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      int arr[] = {1,2,3,4,5,6,7,8,9};
		
      bw.write("-----recursive binSearch()-----\n");
      for (int i = 0; i < arr.length + 2; i++) {
        bw.write(i + " : " + binSearch(arr, 0, arr.length - 1, i) + "\n");
      }
		
      bw.write("-----binSearch() without recursive call-----\n");
      for (int i = 0; i < arr.length + 2; i++) {
        bw.write(i + " : " + binSearch(arr, i) + "\n");
      }
		
      bw.close();
    }
  }
  ```
 
## 최단 경로
- 하나의 정점에서 다른 모든 정점까지의 최단 경로 : Dijkstra 알고리즘
- 음의 가중치가 허용된 최단 경로 : Bellman and Ford 알고리즘
- 모든 정점 쌍의 최단 경로 : allShortestPath 알고리즘
- 이행적 폐쇄 행렬 : 가중치 없는 방향 그래프에서 경로의 존재하는지 표현
  
## 위상 순서(Topological order)
- AOV(Activity on vertex) 네트워크 : 작업간의 선후 관계를 나타내는 방향 그래프
  - 정점들은 서로 선행자와 후속자의 관계로 이루어짐
- 위상 순서 : 방향 그래프에서 두 정점 i와 j에 대해, i가 j의 선행자이면 반드시 i가 j보다 먼저 나오는 정점의 순차 리스트

## 임계 경로(Critical path)
- AOE(Activity on edge) 네트워크 : 프로젝트의 스케쥴을 표현하는 DAG(Directed acyclic graph, 사이클이 없는 방향 그래프)
  - 정점 : 프로젝트 수행을 위한 공정 단계
  - 간선 : 공정들의 선후 관계와 각 공정의 작업 소요 시간
- 임계 경로 : 시작점에서 완료점까지 시간이 가장 많이 걸리는 경로
- 공정 P의 조기 완료 시간(EC, Earliest completion time) : 시작점부터 공정 P까지의 최장 경로 길이
- 공정 P의 완료 마감 시간(LC, Latest completion time) : 전체 프로젝트의 최단 완료 시간에 영향을 주지 않고, 공정 P가 여유를 가지고 지연하여 완료할 수 있는 시간
- 공정 P의 임계도(criticality) : EC(i)와 LC(i)의 시간 차이
- 임계 작업(critical activity) : 임계 경로 상에 있는 작업들

  
