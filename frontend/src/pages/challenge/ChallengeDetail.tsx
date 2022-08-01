import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { fetchChallenge } from "../../lib/withTokenApi";
import { ChallengeDetailState } from "../../store/challenge";

const ChallengeDetail: React.FC = () => {
  const { id } = useParams();
  const [isLoading, setIsLoading] = useState(true);
  const [loadedChallenge, setLoadedChallenge] = useState<
    ChallengeDetailState
  >();
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
            content: res.content
          };
          setIsLoading(false);
          setLoadedChallenge(challenge);
          console.log(challenge)
        })
        // console.log(res)
        .catch((err) => {
          console.log(err);
          setIsLoading(false);
        });
    }
  }, []);
  return <div>ChallengeDetail</div>;
};

export default ChallengeDetail;
