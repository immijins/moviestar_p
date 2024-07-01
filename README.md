## 🖥MovieStar 웹사이트 제작
Spring Boot를 활용한 게시판 CRUD로 제작한 영화 리뷰 사이트입니다.

## 📂프로젝트 소개
게시판 글 생성, 수정, 삭제 기능과 댓글 추가, 수정, 삭제를 구현한 웹 사이트로 
영화에 대한 포스터와 제목, 줄거리를 입력하고 해당 글에 대한 평점과 댓글을 남길 수 있는 기능을 추가하였습니다.
- 개발 기간 : 3일(기획, 로고 제작, 디자인, 구현)
- 참여도 : 100%(개인 프로젝트)

## ⚙기술스택
**Tool :** Illustrator, IntelliJ IDEA

**Framework/Library :** Spring Boot, Thymeleaf

**DataBase :** MariraDB

## 📊주요 기능
- 게시판 : 게시글 생성, 읽기, 수정, 삭제 / 이미지 업로드, 이미지 수정 / 게시글 페이징 / 댓글 검색
- 댓글 : 댓글 생성, 읽기, 수정, 삭제 / 댓글 페이징

## 🔗DB

ERD

![db](https://github.com/immijins/moviestar_p/blob/main/db.png)

## 📋API 설계 구조
![api](https://github.com/immijins/moviestar_p/blob/main/api.png)

## 💻구현 화면
- 메인 페이지
![main](https://github.com/immijins/moviestar_p/blob/main/main.jpg)

- 게시글
  - 게시글 추가
  ![add](https://github.com/immijins/moviestar_p/blob/main/add.png)
    
  - 게시글 읽기
  ![read](https://github.com/immijins/moviestar_p/blob/main/read.jpg)
 
  - 게시글 수정
  ![modify](https://github.com/immijins/moviestar_p/blob/main/modify.jpg)
 
  - 게시글 삭제
  ![remove](https://github.com/immijins/moviestar_p/blob/main/remove.png)


- 댓글
  - 댓글 추가
  ![reply_add](https://github.com/immijins/moviestar_p/blob/main/reply_add.jpg)
 
  - 댓글 읽기
  ![reply_read](https://github.com/immijins/moviestar_p/blob/main/reply_list.jpg)
 
  - 댓글 수정
  ![reply_modify](https://github.com/immijins/moviestar_p/blob/main/reply_modify.png)
 
  - 댓글 삭제
  ![reply_remove](https://github.com/immijins/moviestar_p/blob/main/reply_remove.png)


- 검색
![search](https://github.com/immijins/moviestar_p/blob/main/search.png)
  

## 📌회고 및 피드백
게시판의 기본적인 CRUD와 댓글 기능을 통해 다양한 페이지에서 활용해서 사용이 가능한 프로젝트를 만들었습니다.
전체적인 코드 리팩토링을 진행하고, 현재 리뷰를 남길 때 평점을 숫자로 입력하도록 작업되었는데 평점을 별모양으로 제시하고 별 모양을 클릭하여 평점을 입력할 수 있도록 추가작업을 진행할 예정입니다.
