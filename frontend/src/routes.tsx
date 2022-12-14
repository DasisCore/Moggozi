import { useRoutes } from "react-router-dom";

// Layouts
import MainLayout from "./layout/MainLayout";
import AccountLayout from "./layout/AccountsLayout";
import ChallengeLayout from "./layout/ChallengeLayout";
import PostLayout from "./layout/PostLayout";
import StageLayout from "./layout/StageLayout";
import UserLayout from "./layout/UserLayout";
import SearchLayout from "./layout/SearchLayout";
import AboutPage from "./pages/AboutPage";
import MainNotice from "./pages/MainNotice"

// Accounts
import Login from "./pages/accounts/Login";
import CompleteSignUp from "./pages/accounts/CompleteSignUp";
import Signup from "./pages/accounts/Signup";
import UserUpdate from "./pages/accounts/UserUpdate";
import SocialUserUpdate from "./pages/accounts/SocialUserUpdate";
import UpdatePassword from "./pages/accounts/UpdatePassword";
import PasswordReissue from "./pages/accounts/PasswordReissue";
import Withdrawal from "./pages/accounts/Withdrawal";

//OAuth
import OAuthRedirectHandler from "./pages/OAuthRedirectHandler";

// Challenge
import ChallengeDetail from "./pages/challenge/ChallengeDetail";
import ChallengeNew from "./pages/challenge/ChallengeNew";
import Challenges from "./pages/challenge/Challenges";
import ChallengeUpdate from "./pages/challenge/ChallengeUpdate";

// Post
import PostStage from "./pages/post/PostStage";
import PostAll from "./pages/post/PostAll";

// Stage

// User
import UserPage from "./pages/user/UserPage";

// Main
import MainPage from "./pages/MainPage";
import SearchPage from "./pages/SearchPage";
import UnknownPage from "./pages/UnknownPage";
import StageEdit from "./pages/stage/StageEdit";
import NoticePage from "./pages/notice/NoticePage";
import NoticeDetailPage from "./pages/notice/NoticeDetailPage";
// import NoticeDetailPage from ""
export default function Router() {
  return useRoutes([
    {
      path: "/",
      element: <MainLayout />,
      children: [
        {
          path: "",
          element: <MainPage />,
        },
        {
          path: "/notice/:noticePageNum",
          element: <NoticePage/>,
        },
        {
          path: "/notice/detail/:noticeId",
          element: <NoticeDetailPage/>,
        },
        {
          path: "/about",
          element: <AboutPage />,
        },
        {
          path: "/mainNotice",
          element: <MainNotice />,
        }
      ],
    },
    {
      path: "/search",
      element: <SearchLayout />,
      children: [
        {
          path: "",
          element: <SearchPage />,
        },
      ],
    },
    {
      path: "/oauth/callback",
      element: <OAuthRedirectHandler />,
    },
    {
      path: "/account",
      element: <AccountLayout />,
      children: [
        {
          path: "login",
          element: <Login />,
        },
        {
          path: "complete",
          element: <CompleteSignUp />,
        },
        {
          path: "signup",
          element: <Signup />,
        },
        {
          path: "userUpdate",
          element: <UserUpdate />,
        },
        {
          path: "socialUserUpdate",
          element: <SocialUserUpdate />,
        },
        {
          path: "passwordReissue",
          element: <PasswordReissue />,
        },
        {
          path: "updatePw",
          element: <UpdatePassword />,
        },
        {
          path: "withdrawal",
          element: <Withdrawal />,
        },
      ],
    },
    {
      path: "/challenge",
      element: <ChallengeLayout />,
      children: [
        {
          path: "",
          element: <Challenges />,
        },
        {
          path: ":id",
          element: <ChallengeDetail />,
        },
        {
          path: ":id/update",
          element: <ChallengeUpdate />,
        },
        {
          path: "new",
          element: <ChallengeNew />,
        },
      ],
    },
    {
      path: "/post",
      element: <PostLayout />,
      children: [
        {
          path: ":stageId",
          element: <PostStage />,
        },
        {
          path: "all/",
          element: <PostAll />,
        },
      ],
    },
    {
      path: "/stage",
      element: <StageLayout />,
      children: [
        {
          path: ":challengeId",
          element: <StageEdit />,
        },
      ],
    },
    {
      path: "/user",
      element: <UserLayout />,
      children: [{ path: ":id", element: <UserPage /> }],
    },

    {
      path: "/*",
      element: <UnknownPage />,
    },
  ]);
}
