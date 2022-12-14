![image-20221228180415736](assets/image-20221228180415736.png)

# 취미공유 서비스 - 모꼬지

##### 📑 목차

- [1. 소개](#1-소개)
- [2. 주요 기능](#2-주요-기능)
- [3. 아키텍처](#3-아키텍처)
- [4. ERD 다이어그램](#4-erd-다이어그램)
- [5. 시연시나리오](#5-시연시나리오)
- [6. 개발자](#6-개발자)
- [7. 기술적 고민](#7-기술적-고민)
  - [Front Trouble Shooting](#front-issues)
  - [Back Trouble Shooting](#back-issues)
- [8. 기타 자료](#8-기타-자료)
  - [모꼬지 요구사항 정의서](#모꼬지-요구사항-정의서)
  - [모꼬지 사용자 흐름도](#모꼬지-사용자-흐름도)
  - [모꼬지 와이어프레임](#모꼬지-와이어프레임)
  - [모꼬지 API 문서](#모꼬지-api-문서)
  - [모꼬지 포팅 매뉴얼](#모꼬지-포팅-매뉴얼)
  - [모꼬지 시연시나리오](#모꼬지-시연시나리오)
  - [모꼬지 UCC](#모꼬지-ucc)

---

<br/>

## 1. 소개⭐

취미 공유 웹사이트 모꼬지는 여러 사람들이 다양한 취미를 차근차근 밟아 나갈 수 있는 서비스를 제공합니다. 

모꼬지는 **'함께 어울려 즐기다'** 라는 순 우리말로 어떤 형태의 취미든 마음껏 즐길 수 있습니다. 

저희는 '챌린지' 를 통해서 그동안 관심있었던 취미에 쉽게 도전할 수 있도록 돕습니다. 

또한, '챌린지' 별 '스테이지' 라는 과제를 구성해서 부담없이 취미를 즐길 수 있도록 하였습니다. 

모꼬지에서는 유저가 기획자가 되어 자신만의 노하우로 챌린지를 만들 수도 있습니다. 

취미를 시작하고 싶지만 어떻게 시작할지 몰랐던 사람들에게 자신만 알고있기엔 아까웠던 

취미 노하우를 마음껏 공유할 수 있는 공간, 그리하여 거창한 취미가 아니더라도 

모두가 자신만의 취미를 즐길 수 있도록 돕는 공간, **모꼬지가 지향하는 가치**입니다.<br/>

---

<br/>

## 2. 주요 기능⭐

> _모꼬지에서 제공하는 주요 기능입니다.
> 서비스를 이해하기 위한 용어를 정의하고 각 기능에서 어떤 것을 경험할 수 있는지 설명하였습니다.
> 이미지와 함께 자세한 설명은 [5.시연시나리오](#5-시연시나리오) 에서 확인하세요!_ 

<br/>

#### 1) 회원 가입 및 로그인✔️

- 모꼬지 사이트 내에서 `회원가입` 및 `로그인`을 진행할 수 있습니다.
- 사용자의 편의를 위해 구글, 카카오, 네이버 계정을 통해 회원가입 및 로그인을 진행할 수 있도록 하였습니다.

<br/>

#### 2) 챌린지✔️

- 모꼬지에서 챌린지란 각 취미별로 도전할 수 있는 큰 과제를 의미합니다.
- 예를 들어 식물키우기라는 취미가 있다면 '꽃 키우기', '고구마 키우기', '선인장 키우기' 등이 챌린지로 등록될 수 있습니다.

<br/>

#### 3) 스테이지✔️

- 챌린지가 큰 과제라면 스테이지는 큰 과제를 이루는 작은 도전 과제들을 의미합니다.
- '고구마 키우기'라는 챌린지에는 '씨앗 심기', '흙 선별하기', '새싹 틔우기' 등이 스테이지로 등록될 수 있습니다.

<br/>

#### 4) 포스팅✔️

- 스테이지를 성공적으로 끝냈다면, 사용자는 결과를 포스팅할 수 있습니다.
- 에디터를 이용하여 사진과 자신만의 스타일로 커스텀하여 글을 꾸밀 수 있습니다.
- 다양한 유저들이 포스팅을 볼 수 있고 댓글을 달 수 있습니다.

<br/>

#### 5) 검색✔️

- 유저 닉네임, 챌린지 명, 태그 명으로 실시간 검색이 가능합니다.

<br/>

#### 6) 유저페이지 - 팔로우/팔로워✔️

- 유저페이지에서 자신이 만든 챌린지, 도전 중인 챌린지, 작성한 포스팅을 확인할 수 있습니다.
- 팔로우/팔로워하는 사람을 확인할 수 있습니다.
  - 팔로우 리스트를 열면 로그인한 유저가 상대를 팔로우하는지 아닌지에 따라 팔로우 버튼이 활성화 됩니다.
- 타 유저의 페이지에 방문하여 타유저의 활동을 확인하고 좋아요를 누를 수 있습니다.

---

<br/>

## 3. 아키텍처

<br/>

#### 1) 아키텍처✔️

![image-20220821180621639](assets/image-20220821180621639.png)

<br/>

#### 2) 기술스택✔️

| Part           | Tech ⚙                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
|:--------------:|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| **Front**      | <img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=white"><img src="https://img.shields.io/badge/redux-764ABC?style=for-the-badge&logo=react&logoColor=white"><img src="https://img.shields.io/badge/axios-0088CC?style=for-the-badge&logo=axios&logoColor=white"><img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"><img src="https://img.shields.io/badge/reactrouter-CA4245?style=for-the-badge&logo=reactrouter&logoColor=white"> <img src="https://img.shields.io/badge/css3-1572B6?style=for-the-badge&logo=css3&logoColor=white"><img src="https://img.shields.io/badge/sass-CC6699?style=for-the-badge&logo=sass&logoColor=white"><img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=white"><img src="https://img.shields.io/badge/firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=white"> |
| **Back**       | <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=spring&logoColor=white"><img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white"><img src="https://img.shields.io/badge/mariadb-003545?style=for-the-badge&logo=mariadb&logoColor=white"><img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
| **Deployment** | <img src="https://img.shields.io/badge/amazon_ec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white"><img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"><img src="https://img.shields.io/badge/jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=white"><img src="https://img.shields.io/badge/nginx-009639?style=for-the-badge&logo=nginx&logoColor=white">                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |

- **OS**: Windows 10
* **사용 IDE**
  
  - IntelliJ IDEA 2022.1.3
  - Visual Studio Code : 1.70.2v
  - UI/UX: Figma

  
  
* **백엔드 기술스택**
  
  - Springboot : 2.6.9
  - MariaDB : mariadb Ver 15.1 Distrib 10.3.34-MariaDB, for debian-linux-gnu (x86_64) using readline 5.2
  - AWS : ubuntu 20.04.4 LTS
  - Jenkins : 2.346.2
  - Docker : 20.10.17
  - Openjdk : 11.0.16
  - Spring: gradle
  - nginx : nginx/1.18.0 (Ubuntu)

  
  
* **프론트엔드 기술스택**
  
  - node.js : 16.15.0v 64bit (LST 버전 사용)
  - npm : 8.5.5v
  - react : 18.2.0v
  - react-router-dom : 6.3.0v
  - react-redux : 8.0.2v
  - redux Toolkit : 1.8.3v
  - TypeScript : 4.7.4v
  - Sass : 1.54.4v

---

<br/>

## 4. ERD 다이어그램

![image-20220821182933861](assets/image-20220821182933861.png)

---

<br/>

⭐ **프론트엔드 폴더 구조**

```
FrontEnd
├─asset
│  ├─banner
│  └─main_notice
├─components
│  ├─accounts
│  ├─alert
│  ├─challenge
│  ├─comment
│  ├─notices
│  ├─post
│  ├─review
│  ├─search
│  ├─stage
│  ├─ui
│  └─user
├─fbase
├─layout
├─lib
├─pages
│  ├─accounts
│  ├─challenge
│  ├─notice
│  ├─post
│  ├─stage
│  └─user
├─store
└─styles
```





## 5. 시연시나리오

> *`README`에서는 주요 기능만을 설명합니다. 자세한 시나리오가 궁금하시다면 [모꼬지 시연시나리오](https://drive.google.com/file/d/1Zw85PwF_qezx5kxNaeFc6XjYAOArsewl/view?usp=sharing)를 클릭해주세요!*

<br/>

#### 1) 메인페이지✔️

![메인페이지](assets/메인페이지.gif)

- 모꼬지에서 진행중인 이벤트, 인기 게시물을 배너를 통해 확인할 수 있습니다.
- 최근 등록된 챌린지들을 확인할 수 있습니다.
- 모꼬지가 추천하는 챌린지를 확인할 수 있습니다.
- 인기 챌린지를 확인할 수 있습니다.
- 인기 포스팅을 확인할 수 있습니다.
- 최근 등록된 포스팅을 한눈에 확인할 수 있습니다.

<br/>

#### 2) 챌린지 및 스테이지✔️

#### **챌린지 생성💫**

![챌린지 생성](https://github.com/junghojin/Moggozi/blob/669af61290ecd7e7f206676a0283489caf54c01f/assets/%EC%B1%8C%EB%A6%B0%EC%A7%80%20%EC%83%9D%EC%84%B1.gif)

- 나만의 챌린지를 생성할 수 있습니다.
- 짧은 소개, 챌린지 난이도, 태그, 제목, 설명등을 첨부할 수 있습니다.

<br/>

#### **스테이지 생성💫**

![스테이지 만들기](https://github.com/junghojin/Moggozi/blob/669af61290ecd7e7f206676a0283489caf54c01f/assets/%EC%8A%A4%ED%85%8C%EC%9D%B4%EC%A7%80%20%EB%A7%8C%EB%93%A4%EA%B8%B0.gif)

- 챌린지에 대한 스테이지를 생성할 수 있습니다.
- 생성된 스테이지에 대한 정보를 입력할 수 있습니다.
- 스테이지의 순서는 드래그 엔 드랍을 이용하여 변경이 가능합니다.\

<br/>

#### **챌린지 상세페이지💫**

![챌린지 디테일](https://github.com/junghojin/Moggozi/blob/669af61290ecd7e7f206676a0283489caf54c01f/assets/%EC%B1%8C%EB%A6%B0%EC%A7%80%20%EB%94%94%ED%85%8C%EC%9D%BC.gif)

- 챌린지에 대한 상세 정보를 확인할 수 있습니다.
- 챌린지에 등록된 스테이지를 확인할 수 있습니다.
- 다른 유저들이 진행한 포스팅을 볼 수 있습니다.

<br/>

#### **한줄평💫**

![한줄평 달기](https://github.com/junghojin/Moggozi/blob/669af61290ecd7e7f206676a0283489caf54c01f/assets/%ED%95%9C%EC%A4%84%ED%8F%89%20%EB%8B%AC%EA%B8%B0.gif)

- 챌린지에 대한 평가를 한줄평을 통해 볼 수 있습니다.
- 별점과 리뷰를 통해 챌린지를 평가할 수 있습니다.

<br/>

#### **챌린지 도전 및 포스팅💫**

![챌린지 디테일](https://github.com/junghojin/Moggozi/blob/669af61290ecd7e7f206676a0283489caf54c01f/assets/%EC%B1%8C%EB%A6%B0%EC%A7%80%20%EB%94%94%ED%85%8C%EC%9D%BC-16610753302267.gif)

- 도전 버튼을 클릭하여 챌린지를 시작할 수 있습니다.
- 포스팅을 통해 스테이지를 해결하고, 스테이지를 모두 마무리한다면 챌린지를 완료할 수 있습니다.

- 다른 유저의 포스팅을 볼 수 있으며, 댓글 또한 남길 수 있습니다.
- 포스팅의 댓글에는 대댓글이 가능합니다.

<br/>

#### **검색💫**

![검색](assets/검색.gif)

- 검색을 통해 `유저`, `챌린지`, `태그`를 검색할 수 있습니다.
- 자동완성 기능을 통해 빠르게 검색 결과를 확인할 수 있습니다.

<br/>

## 6. 개발자

#### 1) Front✔️

| ![](README.assets/2022-08-23-01-36-34-image.png) | ![](README.assets/2022-08-23-01-36-51-image.png) | ![](README.assets/2022-08-23-01-37-23-image.png) |
| :----------------------------------------------: | :----------------------------------------------: | :----------------------------------------------: |
|      [박세호](https://github.com/seho27060)      |       [임윤혁](https://github.com/yunhlim)       |      [허재영](https://github.com/DasisCore)      |
|               실시간 알림, 포스팅                |                 챌린지, 스테이지                 |             웹디자인, 유저관리, CSS              |

#### 2) Back✔️

| ![](README.assets/2022-08-23-01-17-41-image.png) | ![](README.assets/2022-08-23-01-17-54-image.png) | ![](README.assets/2022-08-23-01-18-04-image.png) |
|:------------------------------------------------:|:------------------------------------------------:|:------------------------------------------------:|
| [박상현](https://github.com/nomzaxs)                | [정호진](https://github.com/junghojin)              | [조성민(팀장)](https://github.com/WeedInGist)         |
| 서버, 포스팅 API                                      | 스프링 시큐리티, 유저 API                    | 챌린지 API, 스테이지 API, 알림                            |

---

<br/>

## 7. 기술적 고민

> *우리는 프로젝트 진행을 위해 많은 것을 익혔고, 그리고 다양한 문제들을 만났습니다. 프로젝트 완성 후에도 개선할 수 있는 부분들에 관해 고민하였습니다.*
> *해당 내용을 깃과, 블로그에 정리하였습니다. 링크를 클릭하시면 이동하실 수 있습니다.*

#### [Front Issues](https://github.com/DasisCore/Moggozi/tree/master/Frontend_Trouble_Shooting)
> *더 많은 내용은 블로그에 정리되어 있습니다.*
> - [프로젝트를 진행하며 정리한 내용 - React](https://tarra.tistory.com/category/Develop/react)

---

<br/>

## 8. 기타 자료

> *링크를 누르시면 해당 페이지로 이동합니다.**

#### [모꼬지 요구사항 정의서](https://docs.google.com/document/d/1m0Ws64yskiV_aV0Q_VR1HM2pkPK7ZOOXY_sOWiMyTLo/edit?usp=sharing)

#### [모꼬지 사용자 흐름도](https://docs.google.com/document/d/1U5BoqbgwIo7c3jghJfH3SaOEKPX7v4txefH9_AuvHls/edit?usp=sharing)

#### [모꼬지 와이어프레임](https://www.figma.com/file/bWvgf20dimmd7qJyU9fQcv/Untitled?node-id=0%3A1)

#### [모꼬지 목업](https://www.figma.com/file/hTVofDNOmW8qJBeBOQacnj/moggozi?node-id=275%3A1474)

#### [모꼬지 API 문서](https://junghojin.notion.site/API-40d71786c59c4079916b9f8c5c305423)

#### [모꼬지 포팅 매뉴얼](https://docs.google.com/document/d/1z6tbVxldv0COLLaNV4Lnoa_k6kw80-dn7247fGjbq68/edit?usp=sharing)

#### [모꼬지 시연시나리오](https://drive.google.com/file/d/1Zw85PwF_qezx5kxNaeFc6XjYAOArsewl/view?usp=sharing)

#### [모꼬지 UCC](https://www.youtube.com/watch?v=TBeAkZz2-g0)
