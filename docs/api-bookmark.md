# 북마크 관련 API 명세서

---

## 북마크 API 리스트

| API 이름              | 메서드 | URI                        | 인증 필요 여부 | 설명             |
|------------------------|--------|-----------------------------|----------------|------------------|
| 북마크 등록            | POST   | /api/bookmarks              | O              | 정책 북마크 등록 |
| 북마크 목록 조회       | GET    | /api/bookmarks              | O              | 북마크 목록 조회 |
| 북마크 삭제            | DELETE | /api/bookmarks/{id}         | O              | 북마크 삭제      |

---

## 1. 북마크 등록

### API 설명
정책을 북마크에 등록

### 요청
- **Method**: `POST`
- **URL**: `/api/bookmarks`
- **인증 필요 여부**: O

### Request Body
| 이름     | 타입    | 필수 | 설명       |
|----------|---------|------|------------|
| policyId | long    | O    | 북마크할 정책 ID |

#### Request Body 예시
```json
{
  "policyId": 101
}
```

#### 응답 예시
```json
{
  "success": true,
  "status": 201,
  "code": "BOOKMARK_ADDED",
  "message": "요청이 성공적으로 처리되었습니다."
}
```

---

## 2. 북마크 목록 조회

### API 설명
등록된 정책 북마크 목록 조회

### 요청
- **Method**: `GET`
- **URL**: `/api/bookmarks`
- **인증 필요 여부**: O

#### 응답 예시
```json
{
  "success": true,
  "status": 200,
  "code": "BOOKMARK_LIST",
  "message": "요청이 성공적으로 처리되었습니다.",
  "data": [
    {
      "id": 1,
      "policyId": 101,
      "title": "청년 월세 지원",
      "region": "서울특별시",
      "deadline": "2025-06-30"
    }
  ]
}
```

---

## 3. 북마크 삭제

### API 설명
지정한 북마크 항목 삭제

### 요청
- **Method**: `DELETE`
- **URL**: `/api/bookmarks/{id}`
- **인증 필요 여부**: O

### Path Variable
| 이름 | 타입 | 필수 | 설명       |
|------|------|------|------------|
| id   | long | O    | 북마크 ID |

#### 응답 예시
```json
{
  "success": true,
  "status": 200,
  "code": "BOOKMARK_DELETED",
  "message": "요청이 성공적으로 처리되었습니다."
}
```

---

## 오류 코드 예시

| 상태 코드 | 코드             | 메시지 예시                   | 설명                    |
|------------|------------------|-------------------------------|-------------------------|
| 400        | INVALID_PARAM    | 잘못된 파라미터입니다.         | 요청 파라미터 유효성 오류     |
| 401        | UNAUTHORIZED     | 인증 정보가 없습니다.           | 토큰 누락, 만료, 위조 등     |
| 404        | NOT_FOUND        | 북마크를 찾을 수 없습니다.      | 존재하지 않는 북마크 ID 등  |
| 500        | INTERNAL_ERROR   | 서버 내부 오류가 발생했습니다.  | 시스템 예외               |