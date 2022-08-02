import { Hobby } from "../../store/challenge";
import HobbyItem from "./HobbyItem";

const HobbyList: React.FC<{ hobbies: Hobby[] }> = ({ hobbies }) => {
  return (
    <div>
      <h4>취미</h4>
      <ul>
        {hobbies.map((hobby) => (
          <HobbyItem key={hobby.id} hobby={hobby} />
        ))}
      </ul>
    </div>
  );
};

export default HobbyList;
