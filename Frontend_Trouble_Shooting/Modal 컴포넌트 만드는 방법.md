#### 해당 문서는 modal을 만들고 다루는 방법에 대해 다룹니다.

## modal.ts

모달은 기본적으로 props를 통해 내려받은 것을 바탕으로 활용하므로,
<br/><br/>

제일 먼저 typescript에서 props로 내려받을 인자들을 정의해주어야 합니다.
<br/><br/>

해당 문서에서는 <br/><br/>
`open : 모달이 열려있는지 유무`<br/><br/>
`close : 모달을 닫기 위한 함수`<br/><br/>
`header : 모달의 header`<br/><br/>
`children : 모달안의 내용` 을 props로 내려받을 것이므로 다음과 같이 type을 지정해줍니다.<br/>
<br/>

```typescript
interface Props {
  open: boolean;
  close: () => void;
  header: string;
  children: React.ReactNode;
}
```

<br/>

이후 모달로 사용할 컴포넌트를 생성해줍니다.
<br/><br/>

props의 타입과 해당 컴포넌트의 타입 또한 함께 지정해줍니다.
<br/><br/>

다음은 EmailModal의 예시입니다. 
<br/><br/>

해당 예시는 props로 받는 `open`의 상태에 따라 class의 값이 유동적으로 바뀌므로 삼항연산자를 사용하며,
<br/><br/>

~~.module.scss가 className에서 따로 작동할 수 있도록 ``${styles.openModal}` + " " + `${styles.modal}`` 처럼 띄어쓰기를 넣어준 것이 특징입니다.
<br/><br/>

```typescript
import React, { ReactElement} from 'react';
import styles from "./EmailModal.module.scss"

interface EmailProps {
  open: boolean;
  close: () => void;
  header: string;
  children: React.ReactNode;
}

const Modal = (props: Props): ReactElement => {
  // 열기, 닫기, 모달 헤더 텍스트를 부모로부터 받아옴
  const { open, close, header } = props;

  return (
    // 모달이 열릴때 openModal 클래스가 생성된다.

    <div className={open ? `${styles.openModal} ${styles.modal}` : `${styles.modal}` }>
    {/* <div className={open ? 'openModal modal' : 'modal'}> */}
      {open ? (
        <section>
          <header>
            {header}
            <button className="close" onClick={close}>
              ×
            </button>
          </header>
          <main>{props.children}</main>
          <footer>
            <button className="close" onClick={close}>
              close
            </button>
          </footer>
        </section>
      ) : null}
    </div>
  );
};
```

<br/>

다음은 Modal을 넣을 컴포넌트입니다.
<br/>

```typescript
import React, { useState } from 'react';
import Modal from './commons/components/Modals/Modal';

function App() {
  // useState를 사용하여 open상태를 변경한다. (open일때 true로 만들어 열리는 방식)
  const [modalOpen, setModalOpen] = useState(false);

  const openModal = () => {
    setModalOpen(true);
  };
  const closeModal = () => {
    setModalOpen(false);
  };

  return (
    <React.Fragment>
      <button onClick={openModal}>모달팝업</button>
      //header 부분에 텍스트를 입력한다.
      <Modal open={modalOpen} close={closeModal} header="Modal heading">
        // 아래의 내용은 {props.children}로 꺼내어 사용할 수 있습니다.
        팝업창입니다.
      </Modal>
    </React.Fragment>
  );
}

export default App;
```
