# 정책 관련 API 명세서

---

## 정책 API 리스트

| API 이름         | 메서드 | URI                         | 인증 | 설명                            |
|------------------|--------|-----------------------------|----|---------------------------------|
| 정책 목록 조회    | GET    | /api/policies               | X  | 필터 및 페이징 기반 목록 조회   |
| 정책 상세 조회    | GET    | /api/policies/{id}          | X  | 특정 정책 상세 정보 조회        |
| 자연어 검색       | POST   | /api/policies/search/nlp    | X  | 자연어 문장 기반 정책 검색      |
| 추천 정책 조회    | GET    | /api/policies/recommend     | O  | 사용자 조건 기반 추천 정책 조회 |

---

## 1. 정책 목록 조회

### API 설명
사용자 조건(연령, 지역, 키워드 등)에 따라 지원 정책 목록을 검색하고, 페이징 형태로 결과 반환

### 요청
- **Method**: `GET`
- **URL**: `/api/policies`
- **인증 여부**: X

#### Query Parameters
| 이름 | 타입 | 필수 | 기본값 | 설명 |
|------|------|------|--------|------|
| page | int | ✕ | 0 | 페이지 번호 (0부터 시작) |
| size | int | ✕ | 20 | 페이지당 항목 수 |
| sort | string | ✕ | - | 정렬 기준 (`deadline,asc` 등) |
| region | string | ✕ | - | 지역 필터 |
| ageGroup | string | ✕ | - | 연령대 필터 |
| keyword | string | ✕ | - | 키워드 검색 |

#### 응답 예시
```json
{
  "success": true,
  "status": 200,
  "code": "SUCCESS",
  "message": "요청이 성공적으로 처리되었습니다.",
  "data": {
    "content": [
      {
        "id": 101,
        "title": "청년 월세 지원",
        "region": "서울특별시",
        "deadline": "2025-06-30"
      }
    ],
    "totalElements": 120,
    "totalPages": 6,
    "page": 0,
    "size": 20
  }
}
```

---

## 2. 정책 상세 조회

### API 설명
정책 ID로 단건 상세 정보 조회

### 요청
- **Method**: `GET`
- **URL**: `/api/policies/{id}`
- **인증 여부**: X

#### Path Variable
| 이름 | 타입 | 설명 |
|------|------|------|
| id | int | 정책 ID |

#### 응답 예시
```json
{
  "success": true,
  "status": 200,
  "code": "SUCCESS",
  "message": "요청이 성공적으로 처리되었습니다.",
  "data": {
    "id": 101,
    "title": "청년 월세 지원",
    "region": "서울특별시",
    "target": "만 19세 이상 39세 이하 청년",
    "supportContent": "월세 최대 20만원 지원",
    "applicationMethod": "온라인 신청",
    "deadline": "2025-06-30",
    "sourceUrl": "https://www.gov.kr/support-policy/101"
  }
}
```

---

## 3. 자연어 검색

### API 설명
사용자 자연어 문장을 파싱하여 조건을 추출하고 해당 정책 검색

### 요청
- **Method**: `POST`
- **URL**: `/api/policies/search/nlp`
- **인증 여부**: X

#### Request Body
```json
{
  "query": "20대 청년 월세 지원"
}
```

#### 응답 예시
```json
{
  "success": true,
  "status": 200,
  "code": "SUCCESS",
  "message": "요청이 성공적으로 처리되었습니다.",
  "data": {
    "content": [
      {
        "id": 101,
        "title": "청년 월세 지원",
        "region": "서울특별시"
      }
    ],
    "totalElements": 1
  }
}
```

---

## 4. 추천 정책 조회

### API 설명
사용자 조건(연령, 지역 등)을 기반으로 추천 정책 조회

### 요청
- **Method**: `GET`
- **URL**: `/api/policies/recommend`
- **인증 여부**: 인증 필요 (JWT)

#### Query Parameters (예시)
| 이름 | 타입 | 설명 |
|------|------|------|
| age | int | 사용자 나이 |
| region | string | 지역 정보 |

#### 응답 예시
```json
{
  "success": true,
  "status": 200,
  "code": "SUCCESS",
  "message": "요청이 성공적으로 처리되었습니다.",
  "data": {
    "content": [
      {
        "id": 101,
        "title": "청년 월세 지원",
        "region": "서울특별시"
      }
    ]
  }
}
```

---

## 오류 코드

| 상태 코드 | 코드           | 메시지 예시             | 설명               |
|------------|----------------|-------------------------|--------------------|
| 400        | INVALID_PARAM  | 잘못된 요청 파라미터입니다. | 필드 누락 또는 잘못된 값 |
| 401        | UNAUTHORIZED   | 인증이 필요합니다.       | 토큰 누락 또는 만료됨 |
| 500        | INTERNAL_ERROR | 서버 오류가 발생했습니다. | 시스템 내부 예외 발생 |
