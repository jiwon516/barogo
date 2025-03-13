# Springboot-Project-barogo
회원가입, 로그인, 배달조회, 배달 주문수정 구현 프로젝트


## 기능 간략 소개
### 회원가입
- 이름, 아이디, 비밀번호를 입력받아 회원가입 구현
- 회원가입 시 각 항목별 validation 체크
- 중복아이디 확인 로직 구현
- DB에 비밀번호 저장 시 BCrypt 암호화
  
### 로그인
- 등록된 아이디인지 확인
- 입력한 비밀번호와 데이터베이스에 저장된 비밀번호가 일치하는지 확인
- 로그인 성공 시 JWT 발급하여 localStorage에 저장

### 배달조회
- 조회기간 기준으로 주문조회 구현
- 상태값 조건 추가 (전체, 배달대기, 배달중, 배달완료) 
- 조회기간 validation 체크
- 한번에 조회 가능한 기간은 3일로 제한

### 주문수정
- 변경할 주소를 입력받아 주소 수정 구현
- 배달대기(status = 1) 상태가 아닌 경우 주소변경 불가처리

### 기타
- 주문조회,수정 api 호출 시 jwt 인증 구현
- GlobalExeptionController를 통한 예외처리 핸들링

## 개발 환경
- **JDK** : corretto-17
- **Framework** : Springboot(3.x)
- **Database** : H2
- **ORM** : JPA
- **Tool** : IntelliJ IDEA

## API 문서
<https://disco-knave-da7.notion.site/barogo-API-1b4af4cb523780178658df6d4685ec3c?pvs=4>

## TODO
- 회원가입 시 비밀번호 확인 구현
- 로그아웃 기능 구현
- 가게 정보 테이블 생성 및 배달 주문 시 가게 정보 ID 매핑
- 주문 테이블, 배달 테이블 분리해서 관리
- 기타 예외처리
- 간단한 테스트코드 작성
- In Memory 데이터베이스 -> 타 데이터베이스로 변경 고려(oracle, mysql 등)
