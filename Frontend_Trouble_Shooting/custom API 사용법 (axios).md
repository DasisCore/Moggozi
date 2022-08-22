# [FE] custom API 사용법 (axios)

> 모듈화된 axios를 사용하기 위해서는 `generalApi.ts` 과 `withTokenApi.ts`를 다룹니다.

# 

# generalApi.ts

토큰이 사용되지 않는 일반 axios를 만들 수 있는 파일입니다.

generalApi라는 이름을 가진 axios는 다음과 같이 구성되어 있으며

```
const generalApi = axios.create({
    baseURL: apiConfig.apiRoot,
    timeout: 10000,
    params: {},
})
```

다음과 같은 형식을 사용해 원하는 방식으로 만들어 사용 가능합니다.

```
// 예시)
export const signUpApi = async (option: object) => {
    const { data } = await generalApi.post('/user/register', option)
    return data
}
```

해당 Api를 만든 후, 다른 컴포넌트에서 `import { signUpApi } from "../../lib/generalApi.ts"`를 헤더에 적어 불러와 사용하며,

이때 axios의 바디에 해당하는 값(option)들을 `signUpApi(option)`와 같이 넣어 사용합니다.

해당 함수는 async await가 사용되었으므로, Promise 객체가 반환되기 때문에,

기본적으로 비동기방식을 사용하고 있으며, 이 때문에 해당 함수를 호출한 후 ,

`.then((res) => {})` / `.catch((err) => {})`를 사용하여 값을 이용하시면 됩니다.

토큰이 필요하지 않은 Api를 요청하실 때 사용하면 되는 axios 입니다.

# 

# wihtTokenApi.ts

기본 베이스는 위의 `generalApi.ts`와 동일하나, 토큰을 헤더에 담아서 보내는 형식을 취하고 있습니다.

```
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
```

해당 함수는 기본적으로 실행되기 전에 `refresh.ts`를 거쳐가며,

`refresh.ts`에서 가지고 있는 `accessToken`의 유효시간을 파악해 실제 서버에서 만료가 되기 전, `refreshToken`을 이용해 `accessToken`토큰을 갱신받는 작업을 합니다.

토큰이 필요한 Api를 요청하실 때 사용하면 되는 axios입니다.

사용법은 `generalApi.ts`와 동일합니다.

## 

## promise 객체는 다음 중 하나의 상태를 가집니다.

- 대기 (pending) : 이행하지도, 거부하지도 않은 초기 상태
- 이행 (fufilled) : 연산이 성공적으로 완료됨.
- 거부 (rejected) : 연산이 실패함.
