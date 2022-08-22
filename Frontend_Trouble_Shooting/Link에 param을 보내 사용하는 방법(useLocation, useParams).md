react-router-dom V6로 업데이트 되면서 그 전에 많이 나와있는 match, RouteComponentProps 등을 사용할 수 없게 되었다.

1. UseParams를 이용하는 방법
2. 

주소창에 있는 값 즉 to에 있는 값만 사용하고 싶으면 useParams()를 사용한다. router에서 설정한 값을 사용한다.

- 보내는 쪽
  
  ```
  <Link to={`/challenge/${challenge.id}`}>
  ```

- 받는 쪽
  
  ```
  import { useParams } from "react-router-dom";
  const { id } = useParams();
  ```
  
  ex). routes에서 경로 명을 `:id`로 설정했으면 {id: 3} 이런 식으로 반환한다.
2. UseLocation를 이용하는 방법

state로 주소값 외의 값을 넘겨줘 사용하고 싶으면 useLocation()를 사용한다.
useLocation()의 state에 Link에 담아 넘겨준 값이 사용된다.



- 보내는 쪽
  
  ```
  <Link to={`/challenge/${challenge.id}`} state={{ challenge }}></Link>
  ```
  
  위와 같이 state에 담아 전송한다. challenge는 객체이다.

- 받는 쪽
  
  ```
  import { useLocaions } from "react-router-dom";
  const challenge = useLocation().state as ChallengeDetailState;
  ```
  
  타입스크립트라 as로 타입을 설정해줘야 한다.
