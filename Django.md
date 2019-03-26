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
  - `python3 manage.py shell`
    - ***>>>*** `from polls.models import Choice, Question`
    - ***>>>*** `Question.objects.all()`
    - ***>>>*** `from django.utils import timezone`
    - ***>>>*** `q = Question(question_text="What's new?", pub_date=timezone.now())`
    - ***>>>*** `q.save()`
    - ***>>>*** `q.id`
    - ***>>>*** `q.question_text`
    - ***>>>*** `q.pub_date`
    - ***>>>*** `q.question_text = "What's up?"`
    - ***>>>*** `q.save()`
    - ***>>>*** `Question.objects.all()`
  - `vi polls/models.py`
    ```
    import datetime
    from django.db import models
    from django.utils import timezone

    class Question(models.Model):
      # ...
      def __str__(self):
        return self.question_text
      def was_published_recently(self):
        return self.pub_date >= timezone.now() - datetime.timedelta(days=1)

    class Choice(models.Model):
      # ...
      def __str__(self):
        return self.choice_text
    ```
  - `python3 manage.py shell`
    - ***>>>*** `from polls.models import Choice, Question`
    - ***>>>*** `Question.objects.all()`
    - ***>>>*** `Question.objects.filter(id=1)`
    - ***>>>*** `Question.objects.filter(question_text__startswith='What')`
    - ***>>>*** `from django.utils import timezone`
    - ***>>>*** `current_year = timezone.now().year`
    - ***>>>*** `Question.objects.get(pub_date__year=current_year)`
    - ***>>>*** `Question.objects.get(id=2)`
    - ***>>>*** `Question.objects.get(pk=1)`
    - ***>>>*** `q.was_published_recently()`
    - ***>>>*** `q = Question.objects.get(pk=1)`
    - ***>>>*** `q.choice_set.all()`
    - ***>>>*** `q.choice_set.create(choice_text='Not much', votes=0)`
    - ***>>>*** `q.choice_set.create(choice_text='The sky', votes=0)`
    - ***>>>*** `c = q.choice_set.create(choice_text='Just hacking again', votes=0)`
    - ***>>>*** `c.question`
    - ***>>>*** `q.choice_set.all()`
    - ***>>>*** `q.choice_set.count()`
    - ***>>>*** `Choice.objects.filter(question__pub_date__year=current_year)`
    - ***>>>*** `c = q.choice_set.filter(choice_text__startswith='Just hacking')`
    - ***>>>*** `c.delete()`
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
- 첫 번째 장고 앱 작성(part3)[[원문](https://docs.djangoproject.com/ko/2.1/intro/tutorial03/)]
  - `vi polls/views.py`
    ```
    ##원래의 코드에 아랫 부분을 추가합니다.
    def detail(request, question_id):
    return HttpResponse("You're looking at question %s." % question_id)

    def results(request, question_id):
    response = "You're looking at the results of question %s."
    return HttpResponse(response % question_id)

    def vote(request, question_id):
    return HttpResponse("You're voting on question %s." % question_id)
    ```
  - `vi polls/urls.py`
    - error가 발생하고 파일이 열린다면, /home/[userName]/.vimrc 에 `set nomodeline`을 추가한다.
    ```
    from django.urls import path
    from . import views

    urlpatterns = [
      # ex: /polls/
      path('', views.index, name='index'),
      # ex: /polls/5/
      path('<int:question_id>/', views.detail, name='detail'),
      # ex: /polls/5/results/
      path('<int:question_id>/results/', views.results, name='results'),
      # ex: /polls/5/vote/
      path('<int:question_id>/vote/', views.vote, name='vote'),
    ]
    ```
  - `vi polls/views.py`
    ```
    from django.http import HttpResponse
    from .models import Question

    def index(request):
      latest_question_list = Question.objects.order_by('-pub_date')[:5]
      output = ', '.join([q.question_text for q in latest_question_list])
      return HttpResponse(output)

    # (detail, results, vote)는 변경되지 않은 상태로 남깁니다.
    ```
  - `mkdir -p polls/templates/polls`
  - `vi polls/templates/polls/index.html`
    ```
    {% if latest_question_list %}
    <ul>
    {% for question in latest_question_list %}
        <li><a href="/polls/{{ question.id }}/">{{ question.question_text }}</a></li>
    {% endfor %}
    </ul>
    {% else %}
    <p>No polls are available.</p>
    {% endif %}
    ```
  - `vi polls/views.py`
    ```
    ## 마찬가지로 index 아래의 다른 함수들은 그대로 변경하지 않습니다.
    from django.http import HttpResponse
    from django.template import loader
    from .models import Question

    def index(request):
      latest_question_list = Question.objects.order_by('-pub_date')[:5]
      template = loader.get_template('polls/index.html')
      context = {
        'latest_question_list': latest_question_list,
      }
      return HttpResponse(template.render(context, request))
    ```
  - `vi polls/views.py`
    ```
    ## 마찬가지로 index 아래의 다른 함수들은 그대로 변경하지 않습니다.
    from django.shortcuts import render
    from .models import Question

    def index(request):
      latest_question_list = Question.objects.order_by('-pub_date')[:5]
      context = {'latest_question_list': latest_question_list}
      return render(request, 'polls/index.html', context)
    ```

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
