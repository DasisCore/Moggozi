import { useEffect } from "react";
import { useSelector } from "react-redux";
import { RootState } from "../../store/store";

import { commentRead } from "../../lib/withTokenApi";
import { commentSet } from "../../store/comment";

import CommentList from "../comment/CommentList";
import { useDispatch } from "react-redux";
import PostModifyBtn from "./PostModifyBtn";
import PostLikeBtn from "./PostLikeBtn";
import { Link } from "react-router-dom";

const PostDetailItem: React.FC<{}> = () => {
  const dispatch = useDispatch();
  const post = useSelector((state: RootState) => state.postModal);
  const commentState = useSelector(
    (state: RootState) => state.comment.comments
  );
  console.log("postDetail", post.postModalState);
  useEffect(() => {
    commentRead(post.postModalState!.id)
      .then((res) => {
        console.log(`${post.postModalState!.id}의 댓글`);
        dispatch(commentSet(res));
      })
      .catch((err) => {
        console.log("ERR", err);
      });
  }, [post, dispatch]);

  return (
    <div style={{ height: "25rem", overflow: "scroll" }}>
      <div style={{ border: "solid", margin: "1rem", padding: "1rem" }}>
        <img src="" alt="포스팅이미지" />
        {/* 수정 버튼 */}
        <PostModifyBtn />

        <div>postid:{post.postModalState!.id}</div>
        <div>
          <div>프로필이미지 : {post.postModalState!.writer?.img}</div>
          <Link to={`/user/${post.postModalState.writer!.id}`}>
            <div>작성자 : {post.postModalState!.writer?.nickname}</div>
          </Link>
          <hr />
        </div>
        <div>
          <>
            <div>제목 : {post.postModalState!.title}</div>
            <p>포스팅 내용 : {post.postModalState!.content}</p>
            {/* 좋아요 버튼 */}
            <PostLikeBtn />
            좋아요갯수:{post.postModalState!.likeNum}
            <br />
            포스팅작성일 :{post.postModalState!.modifiedTime}
          </>
        </div>
        <div style={{ border: "solid", margin: "1rem", padding: "1rem" }}>
          댓글창
          {/* // 해당 포스팅에 대한 댓글 불러온다는 가정하에 구현
        // comment store에 state 저장해서 사용할것. */}
          <CommentList comments={commentState} />
        </div>
      </div>
    </div>
  );
};
export default PostDetailItem;
