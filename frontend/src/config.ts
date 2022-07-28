// .env 파일에 주소를 적고
// 해당 파일에서 서버 주소등을 불러다 씀

export const apiConfig = {
  apiRoot: process.env.REACT_APP_API_ROOT
}

export const socialLogin = {
  kakaoClientID: process.env.KAKAO_CLIENT_ID,
  kakaoRedirectURI: process.env.KAKAO_REDIRECT_URI,
  naverClientID: process.env.NAVER_CLIENT_ID,
  naverRedirectURI: process.env.NAVER_REDIRECT_URI,
}
