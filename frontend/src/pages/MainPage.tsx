import type { RootState } from "../store/store";

import LogoutBtn from "../components/accounts/LogoutBtn";
import { useSelector } from "react-redux";

function MainPage() {
  const currentState = useSelector((state: RootState) => state);
  const isLoggedIn = currentState.user.isLoggedIn;

  // const myPageHandler = () => {
  //   navigate(`/user/${currentState.user.user_id}`, {state: currentState.user.user_id})
  // }

  return (
    <div>
      Mainpage
      {/* 로그인 상태일 경우에만 로그아웃 버튼 생성 */}
      {!isLoggedIn || <LogoutBtn />}
      <p></p>
      {!isLoggedIn || <button>go to mypage</button>}
    </div>
  );
}

export default MainPage;