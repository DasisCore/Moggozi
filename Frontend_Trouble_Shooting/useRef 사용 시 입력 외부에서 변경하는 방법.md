onChange를 이용해 값이 바뀔 때마다 각각의 input에 name으로 걸어줘서 value 값을 바꿔준다.


event처리할 때 <HTMLInputElement>를 붙여줘야 에러가 뜨지 않는다.


값을 사용할 때 inputs를 가져다가 사용하면 되고, setInputs()로 초기화도 시킬 수 있다.


타입스크립트에서 사용할 예제 코드는 다음과 같다.



```
  const contentInputRef = useRef<HTMLInputElement>(null);
  const [inputs, setInputs] = useState({ content: "" });
  const { content } = inputs;
  const onChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
    event.preventDefault();
    const { name, value } = event.target; // 우선 e.target 에서 content와 value 를 추출
    setInputs({
      ...inputs, // 기존의 input 객체를 복사한 뒤
      [name]: value, // content 키를 가진 값을 value 로 설정
    });
  };
...
  return (
    <div>
      <h3>리뷰 생성 Form</h3>
      <form>
        <div>
          <label htmlFor="content">content :</label>
          <input
            name="content"
            placeholder="내용"
            type="text"
            required
            id="content"
            onChange={onChangeHandler}
            value={content}
            ref={contentInputRef}
          />
 ...
```
