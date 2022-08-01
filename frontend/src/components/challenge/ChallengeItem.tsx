import { Link } from "react-router-dom";
import { ChallengeItemState } from "../../store/challenge";
import HobbyList from "./HobbyList";

const ChallengeItem: React.FC<{ challenge: ChallengeItemState }> = ({
  challenge,
}) => {
  return (
    <li>
      <Link to={"/"}>
        <h3>챌린지 이름 : {challenge.name}</h3>
      </Link>
      <p>챌린지 소개 : {challenge.description}</p>
      <HobbyList hobbies={challenge.hobbies} />
      <p>사진 : {challenge.img}</p>
      <p>level : {challenge.level}</p>
      <p>진행도 : {challenge.userProgress}</p>
      <p>작성자 : {challenge.writer.nickname}</p>
    </li>
  );
};

export default ChallengeItem;
