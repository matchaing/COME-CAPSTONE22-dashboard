# 한밭대학교 컴퓨터공학과 불사조 팀

## **데이터 모니터링이 가능한 대시보드 개발 (Develop dashboard for data monitoring)**

**팀 구성**

- 20191743 이민주
- 20191775 백아름
- 20191777 송민지

## 불사조 팀 Project Background

- ### 필요성

  - 기업 측에 연구실 장비에서 얻은 수치데이터를 그래프로 파악하고자 한다는 요청이 있었다. 
  - 장비의 데이터들을 분석하여 이상치를 탐지할 수 있는 방법이 필요했다.
  - 다른 DB를 연결해도 확인이 가능한 대시보드 오픈소스를 만들고자 한다.

- ### 기존 해결책의 문제점
  - 서버 및 DB연결이 필요한 경우 번거로운 코드 개발을 대비해 오픈소스화 된 코드가 필요했다.
  - 데이터 이상치 현황을 사람이 계속 모니터링 해야 했다.

## System Design

<div>
<img src="https://img.shields.io/badge/JAVA-007396?style=for-the-badge&logo=java&logoColor=white">
<img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white">
<img src="https://img.shields.io/badge/apache tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=white">
<img src="https://img.shields.io/badge/mariaDB-003545?style=for-the-badge&logo=mariaDB&logoColor=white">
<img src="https://img.shields.io/badge/html-E34F26?style=for-the-badge&logo=html5&logoColor=white">
<img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white">
<img src="https://img.shields.io/badge/Javascript-F7DF1E?style=for-the-badge&logo=Javascript&logoColor=white">
<img src="https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=React&logoColor=white">
<img src="https://img.shields.io/badge/Python-3776AB?style=for-the-badge&logo=Python&logoColor=white">
<img src="https://img.shields.io/badge/Flask-000000?style=for-the-badge&logo=Flask&logoColor=white">
</div>

- ### System Requirements

  - 대시보드와 통신하는 API 서버 -> eGovFrame 사용
  - 이상 데이터를 탐지하는 Auto Encoder -> Flask 사용
  - 대시보드 웹 페이지 -> React 사용

## Development Environment
 - ### Spring API Server
   - java 16
   - jdk 16.0.2
   - IDE : EclipseIDE(eGovFrame)
   - Framework : egovFrame4.0
   - Database : MariaDB 
   
- ### Auto Encoder Server
   - python 3.10.1
   - IDE : Visual Studio Code
   - Framework : Flask
   - Database : MariaDB 
   - 라이브러리 : pandas 1.5.1, numpy 1.23.5, tensorflow 2.11.0, pymysql 1,0,2, sklearn 0.0.post1, sqlalchemy 1.4.44

- ### Dashboard
   - Javascript
   - npm: 8.15.0
   - node: 16.17.0
   - IDE : Visual Studio Code
   - Framework : React  


## Installing and Running

- ### Spring API Server

  `Dashboard 프로젝트 우클릭 -> Run as -> Run on Server -> 서버 선택 후 실행`

- ### Auto Encoder Server

  `Visual Studio Code 프로젝트 생성 -> 실행 버튼 클릭 또는 ctrl + F5`

- ### Dashboard
  `Visual Studio Code 프로젝트 생성 -> 터미널에 ‘npm run start’ 입력`
  
## Mentoring
### 멘토링 진행
   - 2022.10.12 기업체 멘토 분과 멘토링 진행
   - 백엔드, 프론트엔드, 오토인코더 각각 질의응답을 통해 개발 방향을 설정함
  
 <img src="https://user-images.githubusercontent.com/97873594/206093424-6b84135d-1f6d-4503-a9e0-f7b34e7a182f.PNG" width="500" height="300"/>

## Conclusion

- ### 데이터 확인 가능
![image](https://user-images.githubusercontent.com/69198709/205867843-c337b8d1-9699-432b-9336-930648cb2336.png)

![IMG_3994](https://user-images.githubusercontent.com/69198709/206104379-d124fac7-20fa-443a-a740-5e63e29957aa.GIF)

- ### 이상 데이터 그래프
  ![image](https://user-images.githubusercontent.com/69198709/205867904-d6b54a4d-4b70-4830-8c86-c195942fcc3d.png)
  
  ![IMG_3996](https://user-images.githubusercontent.com/69198709/206104374-b4fdce15-6277-4682-998f-8acac650ba50.GIF)
  
- ### 활용방안
  - 현재 기업 데이터에 맞추어 개발을 했지만, 해당 데이터 외에 다른 데이터를 연결하여 사용할 수 있다. 백엔드, 프론트엔드 사이의 API 통신을 구축해놓아 일부 코드만 수정한다면 사용자가 원하는 방식으로 활용할 수 있을 것이다.
  - 서버 및 DB연결이 필요한 경우 자잘한 코드 개발 시간을 줄일 수 있어 효율적이다.
  - 오토인코더를 사용하였기 때문에 이상치 탐지가 필요한 곳에 실용적으로 쓰일 수 있다. 이상치를 탐지할 뿐만 아니라 결과도 대시보드로 확인할 수 있어 데이터의 변동을 확인할 수 있다. 이를 통해 연결된 장치 혹은 데이터의 이상을 파악할 수 있다.
  
## 캡스톤 디자인 작품전시회 참가
  ### Poster
   
  <img src="https://user-images.githubusercontent.com/97873594/206093492-adff7dff-16fa-4709-9b61-aaa3442a59a9.jpg" width="600" height="900"/>
  
  
  
  
  
