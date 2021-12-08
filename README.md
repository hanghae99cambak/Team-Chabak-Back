# 
1. PandaN 소개
가장 쉽고 편하게 시작할 수 있는 협업툴

지난 1~2년을 표현할 수 있는 키워드는 '언택트'입니다.
코로나로 인해 늘어나는 비대면 협업 공간의 수요는 점점 늘어나나, 그에 상응하는 공급은 부족합니다.
이미 기존에 협업툴들이 존재하고 있지만 시작하기 어렵다는 공통점들을 발견할 수 있었습니다.

이에 저희 팀 판단은 가장 쉽고 편하게 시작할 수 있는 협업툴, PandaN 을 기획하게 되었습니다.


2. Team PandaN 소개
✔️ 개발 결과물에 대한 책임은 개인이 아닌, 팀 모두에게 있습니다.

✔️ 그 어떤 의사결정도 당연한 것이 없기 때문에 서로에게 끊임없이 되묻습니다.

✔️ PR을 Merge하기 전에 모든 팀원들의 approve가 필요합니다.

✔️ 코드리뷰를 통해 오타, 버그의 조기 발견하며, 더 좋은 해결방안을 찾기 위해 함께 고민합니다.

✔️ 개발 중 드는 의문에 대해서는 이슈로 남겨 함께 토론합니다.

✔️ 토론한 내용을 바탕으로 팀 노션에 문서화하여 기록으로 남깁니다.


강승연	김성경	이태강	최민서
승연	성경	태강	민서
3. 기술 스택
Back-end

Java 8
SpringBoot 2.5.2
Spring Security
Gradle 7.0.2
JPA
QueryDSL
MySQL 8.0
Front-end

React (React Repository이동)
DevOps

Jenkins
Docker
Nginx
AWS EC2 (Centos7)
AWS RDS (MySQL 8.0)
AWS S3

4. 아키텍처 설계



5. 개발 포인트
자세한 내용은 링크를 참고해주세요.

Git Flow를 따르는 협업 프로세스
만장일치 방식의 철저한 코드 리뷰
팀 자체 기술 블로그를 운영하여 문서화
Mysql에서 실행계획 분석 후 쿼리 튜닝
동시성 제어를 통한 DB 무결성 확보
동시 수정 제어 - Lock Manager
동시 이동 제어 - 비즈니스 로직과 격리 수준 차별화
Docker를 이용하여 서버 환경 구축
Jenkins Pipeline을 사용하여 CI/CD, 무중단 배포 환경 구축
Nginx의 Reversed-Proxy를 이용하여 로드밸런싱 및 Blue/Green 방식의 무중단 배포 적용
JMeter를 이용하여 스트레스 테스트

6. 설계
API 설계 - 노션 페이지
ERD - 위키
유저 시나리오 - Whimsical
