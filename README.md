# MTT Framework Prototype

## 프로젝트 가이드

이 프로젝트는 Openshift 기반 애플리케이션 배포 및 관리를 위한 웹 애플리케이션 프레임워크 프로토타입입니다.  프론트엔드는 React, 백엔드는 Spring Boot를 사용하여 개발되었습니다.

## 기술 스택

* 프론트엔드: React, Vite, Bootstrap
* 백엔드: Spring Boot, Spring Security, Mybatis, Log4j2
* 데이터베이스: MySQL, MariaDB
* 빌드 도구: Maven
* 
## 환경 구성 가이드

### 1. Java Development Kit (JDK) 설치

Java 17 이상 버전을 설치해야 합니다.  [Oracle JDK](https://www.oracle.com/java/technologies/javase-downloads.html) 또는 [OpenJDK](https://openjdk.java.net/)를 설치할 수 있습니다.

### 2. Maven 설치

프로젝트 의존성 관리를 위해 Maven을 설치해야 합니다.  [Maven 공식 웹사이트](https://maven.apache.org/)에서 다운로드 및 설치 가이드를 확인할 수 있습니다.

### 3. Node.js 및 npm 설치

프론트엔드 개발을 위해 Node.js와 npm을 설치해야 합니다.  [Node.js 공식 웹사이트](https://nodejs.org/)에서 다운로드 및 설치 가이드를 확인할 수 있습니다.

## 설치 가이드

1. 프로젝트 저장소를 클론합니다.
   ```bash
   git clone [프로젝트 저장소 URL]
   ```

2. 프로젝트 루트 디렉토리로 이동합니다.
   ```bash
   cd mtt-framework-prototype
   ```

3. 프론트엔드 의존성을 설치합니다.
   ```bash
   cd frontend
   npm install
   ```

4. 백엔드 의존성을 설치합니다.
   ```bash
   cd ..
   mvn clean install
   ```

## 서버 띄우는 법

### 1. 백엔드 서버 실행

프로젝트 루트 디렉토리에서 다음 명령어를 실행합니다.
```bash
mvnw.cmd clean install
mvnw.cmd spring-boot:run
```

### 2. 프론트엔드 서버 실행 (관리자 페이지)

`frontend` 디렉토리에서 다음 명령어를 실행합니다.
```bash
npm run admin
```

### 3. 프론트엔드 서버 실행 (웹 페이지)

`frontend` 디렉토리에서 다음 명령어를 실행합니다.
```bash
npm run web
```

### 4. 일괄 서버 실행 (쉘스크립트)

프로젝트 루트 디렉토리에서 다음 명령어를 실행합니다.
관리자 페이지, 웹 페이지, 스프링부트 서버 실행
```bash
mvnw.cmd start.sh
```

### 5. 일괄 서버 종료 (쉘스크립트)

프로젝트 루트 디렉토리에서 다음 명령어를 실행합니다.
관리자 페이지, 웹 페이지, 스프링부트 서버 실행
```bash
mvnw.cmd stop.sh
```


## 서버 설정 가이드

### 1. 백엔드/프론트엔드 통합 서버

통합서버란 스프링부트 빌드시에 /frontend/web 경로의 프론트엔드도 함께 빌드처리
빌드된 파일은 현재는 ```classpath:/static``` 경로에 빌드된다. (```vite.dev.config.js```)
그대로 스프링부트서버(로컬:8080/)으로 화면 접근
통합서버이므로 웹반영 필요 시 빌드/배포 필요

프론트엔드 빌드 설정은 ```MAVEN frontend-maven-plugin``` 해당 플러그인 설정 참조 

### 2. 백엔드/프론트엔드 분리 서버

분리 서버란 백엔드와 프론트엔드를 분리하여 관리한다.
프론트서버/백엔드서버 각각 빌드/배포 가능
프론트서버는 SPA 기능


### 3. 관리자 클라이언트

관리자 클라이언트는 반드시 ```npm run admin``` 명령어 실행하여 접근 가능

# Project Structure

## /src

```
com.mtt
├── MttFrameworkPrototypeApplication.java <-(메인 애플리케이션 클래스)
com.mtt.admin
│   ├── controller
│   │   └── AdminController.java <-(관리자 컨트롤러)
│   └── dto
│       └── AdminDto.java <-(관리자 DTO)
com.mtt.common
│   ├── comm
│   │   ├── controller
│   │   │   └── CommController.java <-(공통 컨트롤러)
│   │   ├── dto
│   │   │   └── CmmCdDto.java <-(공통 코드 DTO)
│   │   ├── mapper
│   │   │   └── CommMapper.java <-(공통 매퍼)
│   │   ├── repository
│   │   │   └── CommRepository.java <-(공통 저장소)
│   │   └── service
│   │       ├── CommService.java <-(공통 서비스 인터페이스)
│   │       └── impl
│   │           └── CommServiceImpl.java <-(공통 서비스 구현체)
│   ├── exception
│   │   ├── CustomException.java <-(사용자 정의 예외)
│   │   ├── CustomExceptionHandler.java <-(예외 핸들러)
│   │   └── ErrorRes.java <-(에러 응답 객체)
│   ├── filter
│   │   ├── CncLogFilter.java <-(로그 필터)
│   │   └── CustomExceptionFilter.java <-(예외 필터)
│   └── hendler
│       ├── CustomAuthenticationSuccessHandler.java <-(인증 성공 핸들러)
│       ├── CustomDeniedHandler.java <-(권한 거부 핸들러)
│       └── CustomFailureHandler.java <-(인증 실패 핸들러)
com.mtt.config
│   ├── MvcConfig.java <-(MVC 설정)
│   └── SecurityConfig.java <-(보안 설정)
com.mtt.error
│   ├── CustomErrorController.java <-(에러 컨트롤러)
│   └── enums
│       └── ErrorCode.java <-(에러 코드)
com.mtt (test)
└── MttFrameworkPrototypeApplicationTests.java <-(테스트 클래스)
```

## /frontend/admin

```
admin
├── .env  <-- 환경 변수 설정 파일
├── index.html <-- 애플리케이션의 진입점이 되는 HTML 파일. JavaScript 파일을 로드하고 애플리케이션의 루트 컴포넌트를 렌더링합니다.
└── public
    └── vite.svg <-- Vite 로고 파일
└── src
    ├── App.css <-- 애플리케이션의 메인 스타일시트
    ├── App.jsx <-- 애플리케이션의 메인 컴포넌트 파일
    ├── assets
    │   └── react.svg <-- React 로고 파일
    ├── components
    │   ├── BuilderCanvas.jsx <-- 빌더 캔버스 컴포넌트
    │   ├── BuilderPage.jsx <-- 빌더 페이지 컴포넌트
    │   ├── Document.jsx <-- 문서 컴포넌트
    │   ├── DraggableModule.jsx <-- 드래그 가능한 모듈 컴포넌트
    │   ├── Footer.jsx <-- 푸터 컴포넌트
    │   ├── Header.jsx <-- 헤더 컴포넌트
    │   ├── ModulesLayout.jsx <-- 모듈 레이아웃 컴포넌트
    │   ├── Sidebar.jsx <-- 사이드바 컴포넌트
    │   ├── SortableItem.jsx <-- 정렬 가능한 아이템 컴포넌트
    │   └── modulesSet.jsx <-- 모듈 세트 컴포넌트
    ├── css
    │   ├── App.css <-- 애플리케이션의 메인 스타일시트
    │   └── index.css <-- 전역 스타일시트
    ├── index.css <-- 전역 스타일시트
    ├── index.jsx <-- 애플리케이션의 진입점 파일
    ├── project_spec.json <-- 프로젝트 설정 파일
    ├── store.jsx <-- 애플리케이션의 상태 관리를 위한 저장소
    ├── target
    └── utils
        ├── commonModule.jsx <-- 공통 모듈 컴포넌트
        ├── getModules.jsx <-- 모듈 가져오기 함수
        └── useFetch.jsx <-- fetch 훅
```

## /frontend/target

```
target
├── index.html <-- 배포를 위한 메인 HTML 파일
└── public
    └── vite.svg <-- Vite 로고 파일
└── src
    ├── App.css <-- 빌드된 애플리케이션의 메인 스타일시트
    ├── App.jsx <-- 빌드된 애플리케이션의 메인 컴포넌트 파일
    ├── assets
    │   └── react.svg <-- React 로고 파일
    ├── components
    │   ├── Header.jsx <-- 헤더 컴포넌트
    │   ├── TestPage.jsx <-- 테스트 페이지 컴포넌트
    │   └── modules
    │       ├── contents
    │       │   ├── CafeBoard.jsx <-- 카페 게시판 컴포넌트
    │       │   └── InstagramStoryList.jsx <-- 인스타그램 스토리 목록 컴포넌트
    │       └── header
    │           ├── HeaderType1.jsx <-- 헤더 타입 1 컴포넌트
    │           └── HeaderType2.jsx <-- 헤더 타입 2 컴포넌트
    ├── index.css <-- 빌드된 애플리케이션의 전역 스타일시트
    └── index.jsx <-- 빌드된 애플리케이션의 진입점 파일

```
