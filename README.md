# AWS 배포를 위한 spring boot 개발 연습

## 프로젝트 구성

- java 8
- gradle 4.10
- spring boot version 2.1.7
- Junit4 (4버전에서는 이 애너테이션 필수 - @RunWith(SpringRunner.class))
- bootstrap 4.3.1

## 프로젝트 설정

현재 그레이들 버전이 다음과 같이 4.x 버전으로 설정된 것이 아닌지 확인한다.
![1.png](img/1.png)

4.x 버전으로 설정된 것이 아닌게 확인되면 프로젝트 폴더에서 터미널을 열고 다음과 같이 명령 실행한다.

```bash
cd <spring-boot-aws>경로
./gradlew wrapper --gradle-version 4.10.2
```

스프링 부트 버전은 2.1.7 , 2.1.8., 2.1.9.에서 선택한다  
  
템플릿 엔진으로 mustache를 사용하므로 vscode에 mustache 플러그인 설치  

### 프로젝트 팁

- CSS는 HTML 페이지 위쪽에 js및 js 고나련 라이브러리는 HTML 페이지 아래쪽에 위치시킨다.
  - HTML 페이지가 js 때문에 로딩이 느려지는 현상을 방지한다.