import axios from "axios"
import { apiConfig } from "../config"
import { refresh, refreshErrorHandle } from "./refresh"

const withTokenApi = axios.create({
    baseURL: apiConfig.apiRoot,
    headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    },
    timeout: 10000,
    params: {},
});

withTokenApi.interceptors.request.use(refresh, refreshErrorHandle)

export default withTokenApi;

// 로그인 상태 유지
export const persistAuth = async () => {
    const { data } = await withTokenApi.get(`/user/myinfo`)
    return data
}

export const userDetail = async () => {
    const { data } = await withTokenApi.get(`/user/${localStorage.getItem('id')}`)
    return data
}

export const fetchChallengeRankList = async () => {
    const { data } = await withTokenApi.get('/challenge/rank')
    return data
}
// 사용법 - 해당 axios는 기본적으로 토큰이 만료되었을 경우 refresh를 겸함.

// GET
// import Api from "lib/customApi.ts";

// const getInfo = async (email: string, pw: string) => {
//     const { data } = await Api.get("/auth/getInfo");
//     return data;
//   }

////////////////////////////////////////////////////////////////////////

//POST
// import Api from "lib/customApi.ts";

// const postParam: object = {
//   enteredId: "test111",
//   enteredPw: '1234'
// }

// const login = async (postParam: object) => {
//     const { data } = await Api.post("/account/login", postParam)
//     return data
// }