// stage store에서 스테이지 번호를 갱신하고..접근해야하ㅡ나?

import { GoBackButton } from "../../layout/HistoryButton";
import { Link } from "react-router-dom";
import { useState } from "react";
import { useCallback } from "react";
import PostList from "../post/PostList";
import { Stage } from "../../store/stage";
import { stageDelete } from "../../lib/withTokenApi";

const StageDetailItem: React.FC<{ stage: Stage }> = ({ stage }) => {
  const [isOpenModal, setOpenModal] = useState<boolean>(false);

  const OnClickToggleModal = useCallback(() => {
    console.log(isOpenModal);
    setOpenModal(!isOpenModal);
  }, [isOpenModal]);

  function removeHandler() {
    console.log("현재 스테이지를 삭제합니다.");
    stageDelete(stage.id)
      .then((res) => {
        console.log("삭제 성공", res);
      })
      .catch((err) => {
        console.log("삭제 실패", err);
      });
  }

  return (
    <div>
      StageDetail
      <div style={{ border: "solid", margin: "1rem", padding: "1rem" }}>
        <h2>{stage.id}번 stage item 입니다.</h2>
        <p>{stage.challengeId}</p>
        <p>{stage.name}</p>
        <p>{stage.postOrder}</p>
        <p>{stage.period}</p>
        <p>{stage.content}</p>
        <img src={stage.stageImg} alt="img" />
      </div>
      <div style={{ border: "solid", margin: "1rem", padding: "1rem" }}>
        <PostList posts={stage.postList} />
      </div>
      <div style={{ border: "solid", margin: "1rem", padding: "1rem" }}>
        <GoBackButton />
        <Link
          to={`/stage/${stage.id}/update`}
          style={{ color: "inherit", textDecoration: "none" }}
        >
          <button>수정하기</button>
        </Link>
        <button onClick={OnClickToggleModal}>삭제하기</button>
        {isOpenModal && (
          <div>
            정말 삭제하시겠습니까?
            <button onClick={removeHandler}>삭제</button>
            <button onClick={OnClickToggleModal}>취소</button>
          </div>
        )}
      </div>
    </div>
  );
};

export default StageDetailItem;
