input 태그의 속성으로 defaultValue를 사용한다.


value를 사용하면 값을 입력하려 해도 바뀌지 않는다.


이 때 넣어줄 값이 null이 될 수도 있다면(타입스크립트 에러 발생 상황) || 연산자를 통해 null인 경우는 빈 문자열로 대체 한다.



```
defaultValue={props.challenge.description || ""}
```
