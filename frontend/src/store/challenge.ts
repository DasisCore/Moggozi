import { UserInfo } from "./auth";
import { StageState } from "./stage";

export interface Hobby {
  id: number | null;
  name: string | null;
}

// 챌린지 리스트 정보
export interface ChallengeItemState {
  id: number | null;
  name: string | null;
  img: string | null;
  description: string | null;
  hobbies: Hobby[];
  writer: UserInfo;
  level: number | null;
  userProgress: number | null;
}

// 챌린지 디테일 정보
export interface ChallengeDetailState {
  id: number | null;
  name: string | null;
  img: string | null;
  content: string | null;
  level: number | null;
  userProgress: number | null;  
  writer: UserInfo;
  stageList: StageState[];
  likeNum: number | null;
  // 리뷰
  // reviewList: 
  hobbies: Hobby[];

  // 백에서 추가해줘야 함
  // createTime: string | null;
  // updateTime: string | null;
  // description 추가?
}