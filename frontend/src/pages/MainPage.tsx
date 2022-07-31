import type { RootState } from "../store/store";

import LogoutBtn from "../components/accounts/LogoutBtn";
import { useSelector } from "react-redux";
import { Link } from "react-router-dom"

function MainPage() {
  const currentState = useSelector((state: RootState) => state);
  const isLoggedIn = currentState.auth.isLoggedIn;
  // console.log(currentState.auth.isLoggedIn);
  // const myPageHandler = () => {
  //   navigate(`/user/${currentState.user.user_id}`, {state: currentState.user.user_id})
  // }

const MainPage: React.FC = () => {
  const dispatch = useDispatch();
  const challengeState = useSelector((state: RootState) => state.challenge);
  console.log(challengeState)
  function addHandler(event: React.FormEvent) {
    const data = {
      id: 1,
      name: '브롤스타즈',
      img: 'https://dullyshin.github.io/2018/08/30/HTML-imgLink/',
      description: '아주 재밌는 브롤스타즈 놀이에요',
      hobbies: [{id : 11, name: '게임'}, {id : 2, name: '놀이'}],
      writer: {id: 24, name: '허재영'},
      level: 3,
      user_progress: 2
    }
    event.preventDefault();
    dispatch(challengeCreate(data))
  }
  return (
    <div>
      MainPage
      <LogoutBtn />
      <button onClick={addHandler}>add</button>
      <ChallengeList challenges = {challengeState}></ChallengeList>
    </div>
  );
}

export default MainPage;
