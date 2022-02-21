# Team Chabak 백엔드
## 차박 소개🚗  



<img src="https://user-images.githubusercontent.com/70641418/145668920-6e7a9392-1559-4621-8be8-286200580497.JPG" style="width: 1000px;">

차박(차에서 숙박) 할 꿀 장소들을 공유하는 SNS입니다.  
다양한 차박 장소들을 사진, 지역과 글을 통해 공유할 수 있습니다👍


팀 노션 링크 =>
<a href="https://www.notion.so/4-6-10-6d4bde31359447f282101443beab8fe7">클릭</a>

<br/>
<br/>

## 개발 기간📆

2021.12.06 ~ 2021.12.11


<br/>
<br/>

## Team Chabak Rule 🤝
✔️ 프론트, 백엔드로 진행되는 첫 프로젝트이기 때문에 대화를 통해 문제점을 개선해 나갑니다.

✔️ 그 어떤 의사결정도 당연한 것이 없기 때문에 서로에게 끊임없이 되묻습니다.

✔️ PR 후 Merge는 팀원이 알 수 있도록 공유 후에 진행합니다.

✔️ 막히는 부분은 공유하고, 가능하면 같이 해결해 나갑니다.

✔️ 개발 중 드는 의문에 대해서는 노션에 남겨 토론을 진행합니다.

✔️ 토론한 내용을 바탕으로 팀 노션에 문서화하여 기록으로 남깁니다.

<br/>



## UI 디자인

![피그마](https://user-images.githubusercontent.com/70641418/145669186-173be214-9e25-49c1-bbd7-38487ac7906b.JPG)

피그마(Figma)툴을 사용해서 UI를 디자인했습니다.  

피그마 URL로 이동 =>
<a href="https://www.figma.com/file/z3liAITH2IJig8fkeEZat2/Untitled">클릭</a>


<br/>

## Team Chabak Frontend
- 최수인 => 로그인, 회원가입 기능 구현
- 박재우 => 메인,상세 게시판,게시글삭제 기능 구현
- 정민수 => 게시글 등록,수정 기능 구현

## Team Chabak Backend
- 김우진 => 로그인, 회원가입, 이미지  기능 구현  
- 전종민 => 게시판 CRUD 기능 구현  
- 임전혁 => S3 이미지 업로드 기능 구현  

<br/>

## 기술 스택 🧰
### Back-end
- Java 8
- SpringBoot 2.5.3  
- Spring Security  
- Gradle  
- JPA  
- MySQL 8.0  

### Front-end
- React
- Redux
- JavaScript
- HTML, CSS

<br/>

### DevOps
- AWS EC2 (Centos7)
- AWS RDS (MySQL 8.0)
- AWS S3

<br/>

## 개발 포인트 🔍

- Git을 활용한 협업 진행
- 노션을 통한 진행상황 공유
- 팀 프로젝트 중 개선할 점 혹은 피드백 노션에 정리
- AWS를 활용한 배포
- DB 설계, 와이어프레임 결과물, 툴을 사용해서 프론트, 백엔드 공유 => ex) 피그마, ERDcloud

<br/>

## 설계 ✏
- API 설계 - 노션 페이지, Swagger API 문서 자동화
- ERD 설계 - ERDcloud
- 와이어프레임 - 피그마(Figma)

<br/>

## ERD Cloud

<img src="https://user-images.githubusercontent.com/70641418/145669235-290d39b2-62b4-405d-84e6-90fcc735264c.JPG" style="width: 1000px;">

백엔드 팀원과 함께 ERD Cloud 사이트에서 DB 설계 했습니다.  
ERD Cloud 링크로 이동 =>
<a href="https://www.erdcloud.com/d/8YAbsi25XhvDwxo7R">클릭</a>

<br/>

---
# 프로젝트 후기


<code>정민수</code>
- 내용

<code>최수인</code>
- 회원가입 시 유효성 검사를 진행하는 이유에 대해서 조금 더 확실하게 알 수 있는 시간이었습니다.   
  처음으로 소셜 로그인 기능을 구현해 보면서 토큰을 주고받는 방식을 익힐 수 있는 한주가 된 거 같습니다 :)  

<code>박재우</code>
- 프론트엔드와 백엔드가 나눠서 처음 협업해 볼 수 있는 귀중한 시간이었습니다. 
  협업하는 경험을 통해 서로 맞추어가는 과정을 많이 배우고 고쳐나가야 할 부분을 알게 되었습니다.
- axios를 통해 서버로부터 데이터를 받아오는 과정을 익힐 수 있었고, 리덕스의 특징에 대해 더 자세히 아는 기회가 됐습니다.


<code>김우진</code>
- 첫 프론트와 협업을 시작으로 가장 중요한 키를 뽑자면 커뮤니케이션 같습니다.  
- 원하는 정보를 팀원에게 알아듣기 쉽게, 명료하게 전달해야 프로젝트 도중에 꼬이는 일이 발생하지 않을 것 같습니다.  
- 혼자 하던 프로젝트와, 팀원들과 함께 구현하는 프로젝트 차이는 문제사항에 대한 지속적인 소통,  
  피드백을 해야한다는 점 인거 같습니다.  
- 특정 부분이 뒤틀린다면 저만의 피해가아니라 팀원들의 시간과 비용을 몇배로 소진시키는 일이기 때문에   
  앞으로는 더 많은 소통 시도를 해야할 것 같습니다.  
- 이번 첫 협업은 앞으로 다시 협업을 할 때 어떻게하면 더 효율적으로 Collaboration in progress을   
  해야할지에 대한 좋은 경험이었습니다.  


<code>전종민</code>
- 이 프로젝트는 프론트와 백엔드를 나눠서 합치는 작업을 해보는 첫 프로젝트였습니다.  
  프로젝트가 시작될 때 기본 설계를 자세하게 해야한다는 것을 체감하게 되었습니다.  
  또한 Open API를 사용하게 된다면 적용할 기능에 대해 자세히 알아보고 사용해야 된다는 점을 배웠습니다.  
  마지막으로 앉아있는 시간이 긴 만큼 체력적으로 힘든 경우가 많아지고 있습니다.  
  건강 챙겨가면서 코딩하시고 이번주도 고생 많으셨습니다.  

<code>임전혁</code>
- 프론트는 리액트 백엔드는 자바 스프링으로 팀 프로젝트를 처음 경험해봤습니다.    
  협업 프로젝트를 진행하면서 실력도 실력이지만, 의사소통의 중요성을 알게되었습니다.    
  멘토분들에게도 협업에대한 조언을 듣고 다양한 문제를 경험해서 상당히 유익한 시간이였습니다.     
  다음 주에 클론코딩을 협업으로 진행하는데, 이번 프로젝트에서 문제점을 개선하면서 성장해 나갈 제 자신에게 기대가 됩니다.  
  이번 프로젝트 고생한 팀원분들에게 감사드립니다.  
