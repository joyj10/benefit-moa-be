# 공공 지원 혜택 모아보기 플랫폼

## 목적
- 사용자가 놓치기 쉬운 중앙정부, 지자체, 공공기관의 다양한 지원 정책(복지/금융/주거/창업 등)을 한 곳에서 검색하고 확인할 수 있도록 돕는 플랫폼
    - 다양한 대상(청년, 부모, 소상공인 등)에 맞춘 맞춤형 필터 제공
    - 조건 기반 추천 기능
    - 원하는 혜택 알림 기능

---

## ⚙️ 기술 스택

| 구분            | 기술 스펙                                            | 비고                             |
|-----------------|--------------------------------------------------|---------------------------------|
| 언어 & 프레임워크 | Java 21, Spring Boot 3.5, JPA, QueryDSL          |             |
| 데이터베이스      | MySQL 8.x                                        |               |
| 데이터 수집/처리  | 공공데이터 API (복지로, 보조금24 등)           | 다양한 데이터 소스 활용          |
| 인프라 & 배포     | AWS EC2, Docker, Kubernetes | 클라우드 환경 |
| CI/CD            | GitHub Actions                                   |      |

---

## 🧩 주요 기능

| 기능명             | 설명                                                                                   |
|-----------------|----------------------------------------------------------------------------------------|
| 정책 목록 조회        | 정책들을 페이징, 정렬, 최신순/마감임박순 등으로 리스트로 표시                           |
| 필터 검색           | 사용자가 연령, 지역, 소득, 분야 등을 체크박스로 선택하여 조건 기반 조회                  |
| 자연어 검색          | 키워드로 "청년 월세", "소상공인 지원" 등의 문장을 입력하면 조건 자동 파악 및 필터 적용  |
| 정책 상세 보기        | 제목, 대상, 신청 조건, 마감일, 지원 내용 등을 구조화된 형태로 출력                      |
| 북마크/저장 (후속)     | 관심 있는 정책을 저장하고 나중에 보기 가능 (선택 기능)                                  |
| 사용자 알림 설정 (후속) | 관심 조건을 저장해두면 해당 조건에 맞는 정책 등록 시 이메일 또는 앱 알림 제공           |


## 📄 API 문서

API 명세는 `docs/` 디렉토리에서 상세 관리:

| 문서명 | 설명 |
|--------|------|
| [api-overview.md](./docs/api-overview.md) | 전체 API 개요 및 인증/응답 구조 등 기본 설명 |
| [api-policy.md](./docs/api-policy.md) | 정책 관련 API |
| [api-bookmark.md](./docs/api-bookmark.md) | 북마크 관련 API |
| [api-user.md](./docs/api-user.md) | 사용자 인증 및 정보 관련 API |
| [api-admin.md](./docs/api-admin.md) | 관리자 전용 API |

> 📁 상세한 내용은 각 파일 참고



---

## 🔄 사용자 흐름 (일반 유저)

- 정책 목록 보기 → 필터 선택 or 키워드 검색 → 검색 결과 확인 → 상세 보기 → 북마크
- "청년 월세 지원"과 같은 문장 → 자연어 조건 매핑 → 추천 결과 확인

---

## ✅ 기능 개발 차수

| 기능명         | 차수 | 설명                                                                 |
|----------------|------|----------------------------------------------------------------------|
| 정책 목록 조회 | 1차  | 필터/정렬 조건 포함 페이징 처리된 전체 목록 조회                     |
| 정책 상세 조회 | 1차  | 제목, 대상, 신청 조건, 마감일, 본문 등을 구조화된 형태로 제공         |
| 필터 기반 검색 | 1차  | 나이, 지역, 소득, 분야 등의 조건별 정책 검색                         |
| 자연어 검색   | 2차  | 자연어 문장을 조건으로 분석하여 필터 검색 수행                        |
| 북마크 기능   | 2차  | 관심 정책 저장 기능                                                  |
| 알림 설정     | 3차  | 조건 저장 + 신규 등록 시 이메일/앱 알림 제공                         |

---

## 🔒 시스템 운영 및 품질 요건

| 항목         | 설명                                                                 |
|--------------|----------------------------------------------------------------------|
| 응답 속도     | API 평균 응답 시간 500ms 이하                                         |
| 동시 접속     | 1,000명 이상 동시 접속 처리 가능하도록 설계                           |
| 배포 환경     | Docker 기반 컨테이너 + EKS로 무중단 배포 가능                         |
| 보안         | JWT 인증, 사용자/관리자 권한 구분, HTTPS 적용                         |
| 로깅         | 에러 로그, 요청 로그, 수집기 실행 로그 등 저장 및 모니터링            |
| 장애 대응     | 오류 발생 시 관리자 이메일 알림 제공                                  |

---

## ✨ 향후 계획
- 알림 기능 정교화 (주제 기반 구독, 푸시 설정)
- 벡터 검색 기반 유사 정책 추천

---

## 로컬 개발 환경 실행 가이드
### 1. 환경변수 파일(.env) 생성
-  로컬 환경에서 개발을 진행하려면 데이터베이스 연결 및 보안 설정 등을 위한 두 개의 설정 파일 필요

#### 1.1 Docker 데이터베이스 환경 설정 파일 (.env)
- docker/mysql 디렉터리(docker-compose.yml 파일이 있는 위치)에 .env 파일 생성
  - 이 파일은 Docker가 데이터베이스를 설정하는 데 사용
- 아래 내용 복사 후 붙여 넣기
```text
# .env

# MySQL 루트 계정의 비밀번호. 원하는 값으로 자유롭게 설정
MYSQL_ROOT_PASSWORD=your_root_password

# benefitmoa 데이터베이스의 benefitmoa_user 계정 비밀번호. 원하는 값으로 자유롭게 설정
MYSQL_PASSWORD=your_user_password
```

#### 1.2 백엔드 애플리케이션 환경 설정 파일 (application-secret.yml)
- backend/src/main/resources 디렉터리에 application-secret.yml 파일 생성
  - 이 파일은 Spring Boot 애플리케이션이 데이터베이스와 통신하는 데 필요한 설정을 포함
```text
# src/main/resources/application-secret.yml

# 위에 작성한 .env 파일의 MYSQL_PASSWORD 동일한 값을 사용
DB_PASSWORD: your_user_password

# 토큰 시크릿 키. JWT 인증에 사용(임의 변경 가능)
JWT_SECRET: test-secret-key-1234123412341234
```
※ 중요: .env와 application-secret.yml 파일은 Git에 의해 추적되지 않아야 함
  - 이 파일들은 보안 정보를 포함하고 있으므로, .gitignore 파일에 추가하여 Git에 커밋되지 않도록 해야 함


### 2. Docker 컨테이너 (MySQL) 실행
- 터미널에서 프로젝트 루트 디렉터리로 이동한 후, 다음 명령어를 실행하여 Docker 컨테이너를 백그라운드에서 실행
```bash
docker compose up -d
```
- 이 명령어로 데이터베이스 컨테이너가 실행됨
  - 컨테이너가 최초로 생성될 때 init.sql과 data.sql 스크립트가 실행되어 초기 데이터가 자동으로 설정

### 3. 백엔드 애플리케이션 실행
- 데이터베이스가 실행된 후, 백엔드 애플리케이션을 local 프로필로 실행
  - local 프로필은 로컬 데이터베이스 설정을 사용하도록 지정

#### 3.1 IntelliJ IDEA에서 실행
- Run -> Edit Configurations... 로 이동
- 실행할 Spring Boot 애플리케이션 설정 선택
- Configuration 탭에서 Active profiles 필드를 찾아 local 입력
- Apply와 OK를 눌러 설정을 저장한 후, 애플리케이션 실행

#### 3.2 Visual Studio Code에서 실행
1. VS Code용 Extension Pack for Java가 설치되어 있는지 확인 
2. 2 .vscode/launch.json 파일을 열고, 아래와 같이 env 속성에 spring.profiles.active 추가
```json
{
  "version": "0.2.0",
  "configurations": [
    {
      "type": "java",
      "name": "Spring Boot App",
      "request": "launch",
      "mainClass": "com.benefitmoa.BenefitMoaApplication",
      "projectName": "benefitmoa",
      "env": {
        "spring.profiles.active": "local"
      }
    }
  ]
}
```

3. Run and Debug 탭에서 설정한 구성으로 애플리케이션 실행

---

### [참고] 자주 사용하는 Docker 명령어
- 컨테이너 중지:
```bash
docker compose down
```

- 컨테이너 상태 확인:
```bash
docker compose ps
```

- 컨테이너 로그 확인:
```bash
docker compose logs mysql
```

- 모든 데이터를 초기화하고 싶을 때 (주의!):
  - 컨테이너와 함께 데이터 볼륨까지 삭제하여 완전히 깨끗한 상태에서 다시 시작하고 싶을 때 사용
```bash
docker compose down -v
```

이후 다시 docker compose up -d를 실행하면 초기 데이터 다시 세팅됨
