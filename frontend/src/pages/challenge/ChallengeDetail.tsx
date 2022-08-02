import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import HobbyList from "../../components/challenge/HobbyList";
import { fetchChallenge } from "../../lib/withTokenApi";
import { ChallengeDetailState } from "../../store/challenge";

const ChallengeDetail: React.FC = () => {
  const { id } = useParams();
  const [isLoading, setIsLoading] = useState(true);
  const [loadedChallenge, setLoadedChallenge] =
    useState<ChallengeDetailState>();
  useEffect(() => {
    setIsLoading(true);
    if (id) {
      fetchChallenge(id)
        .then((res) => {
          const challenge: ChallengeDetailState = {
            id: res.challenge_id,
            name: res.name,
            img: res.challenge_img,
            // description: res.content,
            hobbies: res.tagList,
            writer: {
              nickname: res.writer.nickname,
              userId: res.writer.id,
              userImg: res.writer.userImg,
            },
            level: res.level,
            userProgress: res.user_progress,
            stageList: res.stageList,
            likeNum: res.like_num,
            content: res.content,
          };
          setIsLoading(false);
          setLoadedChallenge(challenge);
        })
        // console.log(res)
        .catch((err) => {
          console.log(err);
          setIsLoading(false);
        });
    }
  }, [ id ]);
  return (
    <div>
      ChallengeDetail
      {isLoading === true && (
        <section>
          <p>Loading...</p>
        </section>
      )}
      {isLoading === false && (
        <div>
          <p>챌린지 name: {loadedChallenge!.name}</p>
          <p>챌린지 img: {loadedChallenge!.img}</p>
          <p>챌린지 취미들: {loadedChallenge!.name}</p>
          <p>챌린지 만든 사람: {loadedChallenge!.writer.nickname}</p>
          <p>챌린지 level: {loadedChallenge!.level}</p>
          <p>챌린지 유저 진행도: {loadedChallenge!.userProgress}</p>
          <p>챌린지 내용: {loadedChallenge!.content}</p>
          <p>챌린지 좋아요 수: {loadedChallenge!.likeNum}</p>
          <p>챌린지 취미</p>
          <HobbyList hobbies={loadedChallenge!.hobbies} />
          <p>스테이지</p>
          {/* <StageList stages={loadedChallenge!.stageList}/> */}
        </div>
      )}
    </div>
  );
};

export default ChallengeDetail;
