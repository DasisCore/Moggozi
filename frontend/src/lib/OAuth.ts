
// 나중에 다 환경 변수로 바꾸어야 함.

// 카카오
const KAKAO_CLIENT_ID = "57f691a3a4531a90f55db4c765207754";
const KAKAO_REDIRECT_URI = "http://localhost:3000/oauth/callback/kakao"

// 네이버
const NAVER_CLIENT_ID = "TiPNapaUkQP_WdTUJjE9";
const NAVER_REDIRECT_URI = "http://localhost:3000/oauth/callback/naver"

// const NAVER_CLIENT_SECRET = "ZnICYpatN7";



export const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${KAKAO_CLIENT_ID}&redirect_uri=${KAKAO_REDIRECT_URI}&response_type=code`;
export const NAVER_AUTH_URL = `https://nid.naver.com/oauth2.0/authorize?client_id=${NAVER_CLIENT_ID}&response_type=code&redirect_uri=${NAVER_REDIRECT_URI}&state=state`
// export const NAVER_AUTH_ACCESS_URL = `https://nid.naver.com/oauth2.0/token?client_id=${NAVER_CLIENT_SECRET}&grant_type=authorization_code&state=state&code=`