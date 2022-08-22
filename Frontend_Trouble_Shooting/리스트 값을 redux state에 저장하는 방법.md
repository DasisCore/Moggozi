리스트 값을 저장하기 위해서는 map함수를 활용한다.

```
  reducers: {
    fetchStage(state, action) {
      action.payload.map((stage: StageState) => {
        return state.push(stage);
      });
    },
  },
```

위 코드와 같이 map으로 받아온 리스트의 요소를 하나씩 순회하며 state에 push()를 이용해 넣어준다.


이 때, 타입스크립트를 사용하니 리스트 안의 요소의 타입을 지정해줘야 한다.
