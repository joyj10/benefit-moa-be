# 📡 API 개요 (Overview)

## 기본 응답 구조
| 필드명       | 타입      | 설명                                           |
| --------- | ------- | -------------------------------------------- |
| `success` | boolean | 요청 처리 성공 여부                                  |
| `status`  | number  | HTTP 의미 기반 숫자 코드 (운영/로깅용 확장 코드)              |
| `code`    | string  | 비즈니스 코드 (e.g. `SUCCESS`, `POLICY_NOT_FOUND`) |
| `message` | string  | 사용자/개발자 메시지                                  |
| `data`    | object  | 실제 반환 데이터                                    |


### 성공 응답 예시

```json
{
  "success": true,
  "status": 200,
  "code": "SUCCESS",
  "message": "요청이 성공적으로 처리되었습니다.",
  "data": {
    // 실제 응답 데이터
  }
}
```

### 실패 응답 예시
```json
{
  "success": false,
  "status": 40401,
  "code": "POLICY_NOT_FOUND",
  "message": "해당 정책을 찾을 수 없습니다.",
  "data": null
}
```

## 상태 코드 설계 예시
| status 코드     | 의미          |
| ------------- | ----------- |
| `40000~40999` | 클라이언트 요청 오류 |
| `40100~40199` | 인증 관련 오류    |
| `40400~40499` | 리소스 없음      |
| `50000~50999` | 서버 내부 오류    |


## 인증 / 인가

### JWT 기반 인증 (Bearer Token)
모든 요청에 아래와 같은 헤더 포함:
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```


### Role 기반 인가

- 사용자(User), 관리자(Admin) 구분
- `@PreAuthorize`, `@Secured`, 커스텀 필터 등 활용 가능

### 토큰 발급/검증

- 로그인 시 AccessToken 발급
- 인증 필터에서 토큰 검증 처리

### 에러 응답 예시

- `401 Unauthorized`: 토큰 누락, 만료, 잘못됨
- `403 Forbidden`: 권한 없음

### 확장 항목

| 항목               | 설명                                 |
|--------------------|--------------------------------------|
| 리프레시 토큰 발급 | 장기 세션 유지를 위한 토큰 갱신 지원 |
| 어드민 경로 분리   | `/api/admin/**` 경로 보호 처리      |


## 📚 엔드포인트 목록

### 📘 정책 관련

| Method | URI                           | 설명                                 |
|--------|-------------------------------|--------------------------------------|
| GET    | `/api/policies`               | 정책 목록 조회 (필터, 검색, 페이징) |
| GET    | `/api/policies/{id}`          | 정책 상세 조회                       |
| GET    | `/api/policies/recommend`     | 맞춤형 추천 정책 조회 (후속 개발)   |
| POST   | `/api/policies/search/nlp`    | 자연어 기반 검색 (후속 개발)        |

---

### 📌 북마크 관련

| Method | URI                        | 설명                        |
|--------|----------------------------|-----------------------------|
| POST   | `/api/bookmarks`           | 정책 북마크 등록           |
| GET    | `/api/bookmarks`           | 사용자 북마크 목록 조회    |
| DELETE | `/api/bookmarks/{id}`      | 북마크 삭제                |

---

### 👤 사용자 인증 및 정보

| Method | URI                         | 설명                    |
|--------|-----------------------------|-------------------------|
| POST   | `/api/auth/signup`          | 회원가입                |
| POST   | `/api/auth/login`           | 로그인 (JWT 발급)       |
| GET    | `/api/auth/me`              | 내 정보 조회            |
| POST   | `/api/auth/refresh`         | 토큰 재발급             |
| PATCH  | `/api/users/profile`        | 프로필 수정             |
| DELETE | `/api/users/withdraw`       | 회원 탈퇴               |

---

### 🛡️ 어드민 전용 (`/api/admin/**`)

| Method | URI                               | 설명                |
|--------|------------------------------------|---------------------|
| POST   | `/api/admin/policies`             | 정책 등록           |
| PUT    | `/api/admin/policies/{id}`        | 정책 수정           |
| DELETE | `/api/admin/policies/{id}`        | 정책 삭제           |
| POST   | `/api/admin/crawler/execute`      | 수집기 수동 실행    |
| GET    | `/api/admin/crawler/logs`         | 수집 로그 목록      |
| GET    | `/api/admin/users`                | 사용자 목록 조회     |

---

### 🔔 알림 설정 관련 (후속 개발)

| Method | URI                        | 설명                      |
|--------|----------------------------|---------------------------|
| POST   | `/api/alerts/subscribe`    | 관심 조건 등록            |
| GET    | `/api/alerts`              | 알림 조건 목록 조회       |
| DELETE | `/api/alerts/{id}`         | 조건 삭제                 |
