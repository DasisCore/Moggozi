import React, { useRef, useEffect } from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import Cookie from "js-cookie";
import moment from "moment"
import { login } from "../../store/user";

import axios from "axios";

const LoginForm: React.FC = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  // input값 가져오기
  const inputEmail = useRef<HTMLInputElement>(null);
  const inputPw = useRef<HTMLInputElement>(null);

  // 현재 페이지에서 login 버튼
  function loginHandler(event: React.FormEvent) {
    event.preventDefault();

    // 입력된 input값 변수에 담기
    const enteredEmail = inputEmail.current!.value;
    const enteredPw = inputPw.current!.value;

    // 둘다 비어있다면 에러 출력 / 이후에 수정해야함.
    // 정합성 검사
    if (enteredEmail.trim().length === 0 || enteredPw.trim().length === 0) {
      alert("입력을 해주세요.");
      return;
    } else {
      const option: object = {
        url: 'https://d197b24a-21f5-4dcf-b2b0-926255a197d0.mock.pstmn.io/testapi/first',
        method: "POST",
        header: {
          Accept: "application/json",
          "Content-Type": "application/json; charset=UTP-8",
        },
        data: {
          id: enteredEmail,
          password: enteredPw,
        },
      };
      // axios.defaults.withCredentials = true;
      axios(option)
        .then((res) => {
          dispatch(login(res.data));
          localStorage.setItem("accessToken", res.data.accessToken);
          Cookie.set("refreshToken", res.data.refreshToken, { secure: true });
          localStorage.setItem("expiresAt", moment().add(1, "hour").format("yyyy-MM-DD HH:mm:ss"))
          navigate("/");
        })
        .catch((err) => alert(err));
    }
  }


  // 이미 로그인해 있을 경우 메인페이지로 이동시킴
  const token = localStorage.getItem('accessToken')
  useEffect(() => {
    if (token) {
      navigate('/')
    }
  }, [token, navigate])


  return (
    <div>
      <h3>Login form</h3>
      <div>
        <form onSubmit={loginHandler}>
          <div>
            <label htmlFor="email">email : </label>
            <input type="text" required id="email" ref={inputEmail} />
          </div>
          <div>
            <label htmlFor="password">password : </label>
            <input type="password" required id="password" ref={inputPw} />
          </div>
          <button type="submit">Login</button>
        </form>
        {/* <SocialLoginForm value={"KAKAO"}></SocialLoginForm>
        <SocialLoginForm value={"GOOGLE"}></SocialLoginForm> */}
      </div>
    </div>
  );
};

export default LoginForm;
