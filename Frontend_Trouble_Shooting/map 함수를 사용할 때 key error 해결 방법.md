map 함수를 사용해 key를 매핑해줄 때 감싸는 태그가 있는 경우 거기에 id를 넣어줘야 한다.
최외곽 태그에 key를 지정해주면 에러가 발생하지 않는다.

- 예시
  
  ```
        {stages.map((stage) => (
          <Fragment key={stage.id}>
            <StageItem stage={stage} />
            <StageUpdateBtn stage={stage} />
            <StageDeleteBtn id={stage.id!} />
          </Fragment>
        ))}
  ```
