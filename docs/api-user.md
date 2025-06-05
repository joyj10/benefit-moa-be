# 사용자 인증 및 정보 관련 API 명세서

---

## 사용자 API 리스트

| API 이름           | 메서드 | URI                     | 인증 필요 여부 | 설명                              |
|--------------------|--------|--------------------------|----------------|-----------------------------------|
| 회원가입            | POST   | /api/auth/signup         | X              | 사용자 회원가입                   |
| 로그인              | POST   | /api/auth/login          | X              | 로그인 후 JWT 발급               |
| 내 정보 조회        | GET    | /api/auth/me             | O              | JWT 기반 사용자 정보 조회         |
| 토큰 재발급         | POST   | /api/auth/refresh        | O              | 리프레시 토큰으로 액세스 토큰 재발급 |
| 내 정보 수정        | PATCH  | /api/users/profile       | O              | 사용자 이름, 연락처 등 정보 수정     |
| 회원 탈퇴           | DELETE | /api/users/withdraw      | O              | 사용자 계정 탈퇴 처리               |

---

## 1. 회원가입

### API 설명
사용자가 이메일, 비밀번호 등으로 회원가입 진행

### 요청
- **Method**: `POST`
- **URL**: `/api/auth/signup`
- **인증 필요 여부**: X

### Request Body
| 이름 | 타입 | 필수 | 설명 |
|------|------|------|------|
| email | string | O | 사용자 이메일 |
| password | string | O | 비밀번호 |
| name | string | O | 이름 |
| phone | string | O | 전화번호 |

#### Request Body 예시
```json
{
  "email": "user@example.com",
  "password": "password123",
  "name": "홍길동",
  "phone": "010-1234-5678"
}
```

#### 응답 예시
```json
{
  "success": true,
  "status": 201,
  "code": "SIGNUP_SUCCESS",
  "message": "요청이 성공적으로 처리되었습니다."
}
```

---

## 2. 로그인

### API 설명
사용자가 이메일과 비밀번호로 로그인 후 JWT 발급

### 요청
- **Method**: `POST`
- **URL**: `/api/auth/login`
- **인증 필요 여부**: X

### Request Body
| 이름 | 타입 | 필수 | 설명 |
|------|------|------|------|
| email | string | O | 사용자 이메일 |
| password | string | O | 비밀번호 |

#### Request Body 예시
```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

#### 응답 예시
```json
{
  "success": true,
  "status": 200,
  "code": "LOGIN_SUCCESS",
  "message": "요청이 성공적으로 처리되었습니다.",
  "data": {
    "accessToken": "jwt-access-token",
    "refreshToken": "jwt-refresh-token"
  }
}
```

---

## 3. 내 정보 조회

### API 설명
JWT 토큰을 사용하여 현재 로그인된 사용자의 정보 조회

### 요청
- **Method**: `GET`
- **URL**: `/api/auth/me`
- **인증 필요 여부**: O

#### Path Variable: 없음
#### Query Parameters: 없음

#### 응답 예시
```json
{
  "success": true,
  "status": 200,
  "code": "USER_INFO",
  "message": "요청이 성공적으로 처리되었습니다.",
  "data": {
    "id": 1,
    "email": "user@example.com",
    "name": "홍길동",
    "phone": "010-1234-5678"
  }
}
```

---

## 4. 액세스 토큰 재발급

### API 설명
리프레시 토큰을 통해 새로운 액세스 토큰 발급

### 요청
- **Method**: `POST`
- **URL**: `/api/auth/refresh`
- **인증 필요 여부**: O

### Request Body
| 이름 | 타입 | 필수 | 설명 |
|------|------|------|------|
| refreshToken | string | O | 리프레시 토큰 값 |

#### Request Body 예시
```json
{
  "refreshToken": "jwt-refresh-token"
}
```

#### 응답 예시
```json
{
  "success": true,
  "status": 200,
  "code": "TOKEN_REFRESHED",
  "message": "요청이 성공적으로 처리되었습니다.",
  "data": {
    "accessToken": "new-access-token"
  }
}
```

---

## 5. 내 정보 수정

### API 설명
사용자의 이름, 연락처 등의 개인정보 수정

### 요청
- **Method**: `PATCH`
- **URL**: `/api/users/profile`
- **인증 필요 여부**: O

### Request Body
| 이름 | 타입 | 필수 | 설명 |
|------|------|------|------|
| name | string | ✕ | 이름 |
| phone | string | ✕ | 전화번호 |

#### Request Body 예시
```json
{
  "name": "홍길동",
  "phone": "010-4321-8765"
}
```

#### 응답 예시
```json
{
  "success": true,
  "status": 200,
  "code": "PROFILE_UPDATED",
  "message": "요청이 성공적으로 처리되었습니다."
}
```

---

## 6. 회원 탈퇴

### API 설명
사용자 계정 삭제 및 서비스 탈퇴 처리

### 요청
- **Method**: `DELETE`
- **URL**: `/api/users/withdraw`
- **인증 필요 여부**: O

#### 응답 예시
```json
{
  "success": true,
  "status": 200,
  "code": "ACCOUNT_DELETED",
  "message": "요청이 성공적으로 처리되었습니다."
}
```

---

## 오류 코드 예시

| 상태 코드 | 코드             | 메시지 예시                   | 설명                    |
|------------|------------------|-------------------------------|-------------------------|
| 400        | INVALID_PARAM    | 이메일 형식이 잘못되었습니다.   | 요청 파라미터 유효성 오류     |
| 401        | UNAUTHORIZED     | 인증 정보가 없습니다.           | 토큰 누락, 만료, 위조 등     |
| 403        | FORBIDDEN        | 접근 권한이 없습니다.           | 관리자 권한 등 제한       |
| 404        | NOT_FOUND        | 사용자를 찾을 수 없습니다.      | 존재하지 않는 사용자 ID 등  |
| 409        | CONFLICT         | 이미 가입된 이메일입니다.       | 리소스 충돌               |
| 500        | INTERNAL_ERROR   | 서버 내부 오류가 발생했습니다.  | 시스템 예외               |