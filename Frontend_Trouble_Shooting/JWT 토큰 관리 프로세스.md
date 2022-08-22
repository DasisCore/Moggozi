# [FE] JWT 토큰 관리 프로세스

# 

# 1. 로그인 / 로그인 유지

1. 로그인 api에 id와 password를 가지고 post 요청을 보냄.

2. 응답으로 받은 데이터 처리

3. `userData`(id, username, ... )은 dispatch를 통해 redux로 전송.

4. `accessToken`은 localstorage에 저장

5. `refreshToken`은 cookie에 저장.

6. 액세스 토큰 만료 사전 확인을 위한 `expiresAt`을 만들어 localstroage에 저장함(현재 시간 + 1시간 - 수정 가능)

7. 메인페이지로 이동.

8. 새로고침 시 데이터가 날아가므로, App.ts에서 항상 토큰 유무 주시 중.

9. 토큰이 존재한다면, 유저 데이터 api로 `토큰`과 `user_id`를 보내 redux에 유저 데이터 저장.

# 

# 2. auth refresh

1. `/src/lib`에 axios 관련 메소드 모듈화

2. 기본적으로 저장되어있는 const Api는 axios 요청을 보내기 전 refresh를 행함.

3. 에러가 발생했을 경우 refreshToken 삭제

#### 

#### refresh

- localstorage에 있는 expireAt을 확인

- 프론트 기준의 expireAt이 만료되었고, refreshToken이 존재한다면, 실제로 accessToken이 만료되기 전에 api 서버에 먼저 refresh를 행함.

#### 

#### env

- api 서버 주소등은 보안을 위해 코드 자체에 작성을 지양 따라서 env. 파일에 작성 후
- src/config.ts에 불러온 후 해당 코드를 import 하는 식으로 진행
