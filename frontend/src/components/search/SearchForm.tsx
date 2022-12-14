import React, { useRef, useState } from "react";
import { useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { searchChallengeApi, searchUserApi } from "../../lib/generalApi";
import { hobbySearch, isLoginSearchChallengeApi } from "../../lib/withTokenApi";
import { UserInfo } from "../../store/auth";
import { ChallengeItemState, Hobby } from "../../store/challenge";
import { RootState } from "../../store/store";

import UserList from "../accounts/UserList";
import SearchChallengeList from "../challenge/SearchChallengeList";

import styles from "./SearchForm.module.scss";
import SearchIcon from "@mui/icons-material/Search";

interface Props {
  close: () => void;
}

const SearchForm = (props: Props) => {
  const { close } = props;
  const searchInputRef = useRef<HTMLInputElement>(null);
  // 자동완성 기능을 위한 dropDownList
  const [dropDownChallengeList, setDropDownChallengeList] = useState<
    ChallengeItemState[]
  >([]);
  const [dropDownHobbyList, setDropDownHobbyList] = useState<Hobby[]>([]);
  const [dropDownUserList, setDropDownUserList] = useState<UserInfo[]>([]);

  const navigate = useNavigate();
  const isLoggedIn = useSelector((state: RootState) => state.auth.isLoggedIn);

  const submitHandler = (event: React.FormEvent) => {
    event.preventDefault();
    const enteredSearch = searchInputRef.current!.value;
    close();
    navigate(`/search/?keyword=${enteredSearch}&page=0&size=4&choice=0`);
  };

  const onKeyPressHandler = (event: React.KeyboardEvent) => {
    if (event.key === "Enter") {
      const enteredSearch = searchInputRef.current!.value;
      close();
      navigate(`/search/?keyword=${enteredSearch}&page=0&size=4&choice=0`);
    }
  };

  const onKeyDownHandler = (event: React.KeyboardEvent) => {
    if (event.keyCode === 27) {
      event.preventDefault();
      document.body.style.overflow = "unset";
      close();
    }
  };

  const changeInputHandler = (event: React.ChangeEvent) => {
    event.preventDefault();
    const enteredQuery = searchInputRef.current!.value;
    if (enteredQuery === "") {
      setDropDownChallengeList([]);
      setDropDownHobbyList([]);
      setDropDownUserList([]);
    } else {
      hobbySearch(enteredQuery)
        .then((res) => {
          setDropDownHobbyList(res);
        })
        .catch((err) => {
          console.log(err.response);
        });
      searchUserApi(enteredQuery, 0, 5)
        .then((res) => {
          setDropDownUserList(res.content);
        })
        .catch((err) => {
          console.log(err.response);
        });
      if (isLoggedIn) {
        isLoginSearchChallengeApi(enteredQuery, 0, 5)
          .then((res) => {
            setDropDownChallengeList(res.content);
          })
          .catch((err) => {
            console.log(err.response);
          });
      } else {
        searchChallengeApi(enteredQuery, 0, 5)
          .then((res) => {
            setDropDownChallengeList(res.content);
          })
          .catch((err) => {
            console.log(err.response);
          });
      }
    }
  };

  return (
    <div>
      <header>
        <input
          type="text"
          required
          id="search"
          ref={searchInputRef}
          placeholder="무엇이든 검색하세요."
          onKeyPress={onKeyPressHandler}
          onKeyDown={onKeyDownHandler}
          onChange={changeInputHandler}
        ></input>
        <div className={styles.searchBtn} onClick={submitHandler}>
          <SearchIcon />
        </div>
      </header>

      {/* <main> */}
      <div className={styles.mainScroll}>
        <main className={styles.tag}>
          <h1>유저</h1>
          {dropDownUserList.length === 0 && (
            <div className={styles.noUser}>해당하는 유저가 없습니다.</div>
          )}
          <UserList users={dropDownUserList} close={close} />
        </main>
        <main>
          <h1>챌린지</h1>
          {dropDownChallengeList.length === 0 && (
            <div className={styles.challenge}>해당하는 챌린지가 없습니다.</div>
          )}
          <SearchChallengeList challenges={dropDownChallengeList} close={close} />
        </main>
        <main className={styles.tagPart}>
          <h1>태그</h1>
          {dropDownHobbyList.length === 0 && (
            <div className={styles.tagg}>해당하는 태그가 없습니다.</div>
          )}
          {dropDownHobbyList.map((dropDownItem) => {
            return (
              <div className={styles.hobbyTag}>
                <Link
                  to={`/search?keyword=${dropDownItem.name}&page=0&size=4&choice=2`}
                  key={dropDownItem.id}
                  onClick={close}
                >
                  # {dropDownItem.name}
                </Link>
              </div>
            );
          })}
        </main>
      </div>
      {/* </main> */}
    </div>
  );
};
export default SearchForm;
