# Django

## 설치
- `sudo apt install python3`
- `sudo apt install python3-pip`
- `pip3 install django`
- 주의 : python3에서 실행할때는 pip도 3이어야하고, 예제에서 python ~ 으로 실행할때 python3 ~ 으로 변경해 주어야 합니다.
- `sudo apt install tree` : 폴더의 구조를 살펴볼 때 유용

## 예제
- 첫 번째 장고 앱 작성(part1)[[원문](https://docs.djangoproject.com/ko/2.1/intro/tutorial01/)]
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
- 첫 번째 장고 앱 작성(part2)[[원문](https://docs.djangoproject.com/ko/2.1/intro/tutorial02/)]  
  - `vi /home/ubuntu/jangoTest/mysite/mysite/settings.py` : 환경설정 파일 변경
    - `TIME_ZONE = 'Asia/Seoul'` 으로 변경
  - `cd /home/ubuntu/jangoTest/mysite` : manage.py가 있는 폴더로 변경
  - `python3 manage.py migrate` : 환경설정 파일의 INSTALLED_APPS 설정을 자동으로 데이터베이스에 구조 등록
  - `vi polls/models.py` 를 다음과 같이 수정
    ```
    from django.db import models

    class Question(models.Model):
      question_text = models.CharField(max_length=200)
      pub_date = models.DateTimeField('date published')

    class Choice(models.Model):
      question = models.ForeignKey(Question, on_delete=models.CASCADE)
      choice_text = models.CharField(max_length=200)
      votes = models.IntegerField(default=0)
    ```
  - `vi mysite/settings.py` 에서 INSTALLED_APPS 부분을 다음과 같이 수정
    ```
    INSTALLED_APPS = [
    'polls.apps.PollsConfig',
    'django.contrib.admin',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.messages',
    'django.contrib.staticfiles',
    ]
    ```
  - `python3 manage.py makemigrations polls` : 변경사항을 저장(polls/migrations/0001_initial.py)
  - `python manage.py sqlmigrate polls 0001` : 0001 마이그레이션 파일의 SQL 문장을 분석
  - `python3 manage.py migrate` : 변경사항(0001_...)을 적용하여 데이터베이스의 스키마 동기화
- 첫 번째 장고 앱 작성(part2-API 가지고 놀기)[[원문](https://docs.djangoproject.com/ko/2.1/intro/tutorial02/)]
  - API를 탐색하는 방법으로 링크 참조. 이 글에선 다루지 않음
- 첫 번째 장고 앱 작성(part2-Django Admin 모듈 소개)[[원문](https://docs.djangoproject.com/ko/2.1/intro/tutorial02/)]
  - `python3 manage.py createsuperuser` : 관리 사이트에 로그인 할 수 있는 사용자 생성
    - username : 관리자 계정의 이름
    - email address : 관리자 계정의 메일
    - password : 관리자 계정의 비밀번호
  - `python3 manage.py runserver` : 개발 서버 시작
  - 브라우저에서 `http://localhost:8000/admin` 으로 접속
  - 위에서 생성한 관리자 계정과 비밀번호로 접속
  - `vi polls/admin.py` 내용을 다음과 같이 수정하여 관리 인터페이스 인덱스 등록
    ```
    from django.contrib import admin

    from .models import Question

    admin.site.register(Question)
    ```
  - 사이트 새로고침 후 POLLS 메뉴가 생성된 것을 확인

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
