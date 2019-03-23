# Django

## 설치
- `sudo apt install python3`
- `sudo apt install python3-pip`
- `pip3 install django`
- 주의 : python3에서 실행할때는 pip도 3이어야하고, 예제에서 python ~ 으로 실행할때 python3 ~ 으로 변경해 주어야 합니다.
- `sudo apt install tree` : 폴더의 구조를 살펴볼 때 유용

## 예제
- 첫 번째 장고 앱 작성[원문](https://docs.djangoproject.com/ko/2.1/intro/tutorial01/)
  - `python3 -m django --version` : 장고 버전 확인
  - `mkdir jangoTest` : 장고 베이스 디렉토리 생성
  - `cd jangoTest` : 장고 베이스 디렉토리로 진입
  - `django-admin startproject mysite` : 장고 예제 프로젝트 생성
  - `cd mysite` : mysite dir로 진입
  - `python3 manage.py runserver` : 개발 서버 가동. *http://localhost:8000* 으로 접속 가능
  - `python3 manage.py startapp polls` : 예제 app 생성
  - `vi polls/views.py` : polls/views.py를 편집
    - polls/views.py
    ```
    from django.http import HttpResponse

    def index(request):
      return HttpResponse("Hello, world. You're at the polls index.")
    ```
  - `vi polls/urls.py` : urls.py를 생성 및 수정
    - polls/urls.py
    ```
    from django.urls import path
    from . import views

    urlpatterns = [
      path('', views.index, name='index'),
    ]
    ```
  - `vi mysite/urls.py` : mysite(main) urls.py를 수정
    - mysite/urls.py
    ```
    from django.contrib import admin
    from django.urls import include, path

    urlpatterns = [
      path('polls/', include('polls.urls')),
      path('admin/', admin.site.urls),
    ]
    ```
  - `python3 manage.py runserver` : 개발 서버 구동
  - *http://localhost:8000/polls* 로 접속
  
## 함수 인수
- path()
  ```
  route : 우리가 관리할 URL
  view : 경로로 부터 캡처된 값으로 특정 view 호출
  kwargs : 목표 view에 사전형으로 전달
  name : URL에 이름 짓기
  ```



## Reference
- [link](https://docs.djangoproject.com/ko/2.1/intro/tutorial01/)
