# 어드민 전용 API 명세서

---

## 어드민 API 리스트

| API 이름             | 메서드 | URI                              | 인증 필요 여부 | 설명                 |
|----------------------|--------|-----------------------------------|----------------|----------------------|
| 정책 등록            | POST   | /api/admin/policies               | O              | 정책 등록            |
| 정책 수정            | PUT    | /api/admin/policies/{id}          | O              | 정책 수정            |
| 정책 삭제            | DELETE | /api/admin/policies/{id}          | O              | 정책 삭제            |
| 수집기 수동 실행     | POST   | /api/admin/crawler/execute        | O              | 수집기 수동 실행     |
| 수집 로그 목록       | GET    | /api/admin/crawler/logs           | O              | 수집 로그 목록       |
| 사용자 목록 조회     | GET    | /api/admin/users                  | O              | 사용자 목록/정보 조회 |

---

## 1. 정책 등록

### API 설명
정책 등록

### 요청
- **Method**: `POST`
- **URL**: `/api/admin/policies`
- **인증 필요 여부**: O

### Request Body
| 이름       | 타입   | 필수 | 설명         |
|------------|--------|------|--------------|
| title      | string | O    | 정책 제목    |
| region     | string | O    | 대상 지역    |
| deadline   | string | O    | 마감일 (YYYY-MM-DD) |
| content    | string | O    | 상세 내용     |

#### Request Body 예시
```json
{
  "title": "청년 월세 지원",
  "region": "서울특별시",
  "deadline": "2025-06-30",
  "content": "청년 대상 월세 지원 정책입니다."
}
```

---

## 2. 정책 수정

### API 설명
기존 정책 정보 수정

### 요청
- **Method**: `PUT`
- **URL**: `/api/admin/policies/{id}`
- **인증 필요 여부**: O

### Path Variable
| 이름 | 타입 | 필수 | 설명       |
|------|------|------|------------|
| id   | long | O    | 정책 ID |

### Request Body
위 정책 등록과 동일

---

## 3. 정책 삭제

### API 설명
정책 삭제

### 요청
- **Method**: `DELETE`
- **URL**: `/api/admin/policies/{id}`
- **인증 필요 여부**: O

### Path Variable
| 이름 | 타입 | 필수 | 설명       |
|------|------|------|------------|
| id   | long | O    | 정책 ID |

---

## 4. 수집기 수동 실행

### API 설명
수집기 수동 실행

### 요청
- **Method**: `POST`
- **URL**: `/api/admin/crawler/execute`
- **인증 필요 여부**: O

---

## 5. 수집 로그 목록

### API 설명
수집 로그 목록 조회

### 요청
- **Method**: `GET`
- **URL**: `/api/admin/crawler/logs`
- **인증 필요 여부**: O

---

## 6. 사용자 목록/정보 조회

### API 설명
전체 사용자 목록 또는 상세 조회

### 요청
- **Method**: `GET`
- **URL**: `/api/admin/users`
- **인증 필요 여부**: O

### Query Parameters
| 이름     | 타입   | 필수 | 설명                 |
|----------|--------|------|----------------------|
| page     | int    | ✕    | 페이지 번호 (기본: 0) |
| size     | int    | ✕    | 페이지 크기 (기본: 20) |
