import {  useEffect } from "react";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import { postSet } from "../../store/post";
import PostDetailItem from "../../components/post/PostDetailItem";
import PostForm from "../../components/post/PostForm";
import PostList from "../../components/post/PostList";
import Modal from "../../components/ui/Modal";
import {  postRead } from "../../lib/withTokenApi";
import { RootState } from "../../store/store";
import PostUpdateForm from "../../components/post/PostUpdateForm";
import {
  setPostFormModalOpen,
  setPostFormButtonState,
  setPostUpdateFormState,
  setPostModalState,
} from "../../store/postModal";
import { useParams } from "react-router-dom";

const PostStage = () => {
  document.body.style.overflow = "auto"; //모달때문에 이상하게 스크롤이 안되서 강제로 스크롤 바 생성함

  const {stageId} = useParams()

  const dispatch = useDispatch();
  const postListState = useSelector((state: RootState) => state.post.posts);
  const {
    postModalOpen,
    postFormModalOpen,
    postUpdateFormOpen,
    postFormButtonOpen,
  } = useSelector((state: RootState) => state.postModal);

  const closePostModal = () => {
    dispatch(setPostModalState(false));
    dispatch(setPostUpdateFormState(false));
  };
  const closePostFormModal = () => {
    dispatch(setPostFormModalOpen());
  };

  useEffect(() => {
    console.log(
      stageId,
      "번 스테이지의 포스팅을 불러옵니다."
    );
    postRead(Number(stageId))
      .then((res) => {
        console.log("포스팅 불러오기 성공", res);
        dispatch(postSet(res));
        dispatch(setPostFormButtonState(true));
      })
      .catch((err) => {
        console.log("ERR", err);
      });
  }, []);

  return (
<div>
      <h1>PostStage</h1>
      {postFormButtonOpen && (
        <button onClick={() => dispatch(setPostFormModalOpen())}>
          포스팅 생성
        </button>
      )}
      {postListState && (
        <>
          <PostList posts={postListState} />
        </>
      )}
      <div style={{ height: "200rem" }}>
        {postModalOpen && (
          <Modal
            open={postModalOpen}
            close={closePostModal}
            header="Modal heading"
          >
            {!postUpdateFormOpen && <PostDetailItem />}
            {postUpdateFormOpen && <PostUpdateForm />}
          </Modal>
        )}
        {postFormModalOpen && (
          <Modal
            open={postFormModalOpen}
            close={closePostFormModal}
            header="Modal heading"
          >
            "생성폼"
            <PostForm
              stageId={Number(stageId)}
              modalClose={closePostFormModal}
            />
          </Modal>
        )}
      </div>
    </div>
  );
};

export default PostStage;
