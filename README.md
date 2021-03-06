# 맛동산
> <strong>'맛집, 동네날씨, 산'</strong>이라는 의미로 <br>맛동산처럼 남녀노소 즐겨찾을 수 있는 앱을 의미합니다. 


<img src="./app/src/main/res/drawable/mainlogo.png" width="350" height="500"></img><br>
Copyright 2019. [@suyn_dec2](https://www.instagram.com/suyn_dec2/) all rights reserved.

## [어플리케이션 시연 영상](https://youtu.be/V3lpmFCzQi4)

![타이틀](https://user-images.githubusercontent.com/38810970/59881144-bc75a380-93e9-11e9-89b7-9c8227d5e4a3.png)
![개요](https://user-images.githubusercontent.com/38810970/59881142-bb447680-93e9-11e9-97e5-7d6e749a870e.png)

안드로이드 9.0, SDK=28

현재 apk 작동하는 안드 기기 리스트
: Samsung Galaxy S8, S8+, 노트8, LG G8 ThinQ
<br><br>
tab1.java, tab2.java <br>
: 해당 공공데이터 제공해주는 홈페이지에 들어가서 본인 서비스키를 신청해서 공백란에 넣으면 실행<br>
카카오 로그인<br>
: 카카오 api, 해시키 모두 필요 string.xml 수정해야함<br>
구글맵<br>
: SHA1 키 값을 개인 노트북마다 직접 발급받아야 함

**parseXML 파일 안에 해시키 주석처리 했으니까 매번 본인것으로 바꿔야 함**

커밋/푸시 로그
- 세영: 2019.05.27 프사 없을 경우 대체 이미지로 처리, tab5.xml 레이아웃 개요 작성
- 세영: 2919.05.31 tab1에 listView에 대해 검색하는 기능 xml 레이아웃 개요 작성 
- 세영: 2019.05.31 xml 파일에 맞게 tab1.java에 검색 기능 추가 (이제 검색 되는데 검색 결과 없는 것에 대한 처리 아직 못함)
- 세영: 2019.06.01 xml 파싱하는 클래스, 파싱된 데이터 mountainTable 클래스화 (tab1.java 기능별로 분할한거임), 데이터를 여러 탭 간의 공유 가능(tab2.java 코드 보면 확인 가능)
- 윤진: 2019.06.02 ListViewAdapter.java & listview_item.xml-  Adapter에 추가된 데이터를 저장하기 위한 산관련 정보들 리스트로 보여줌, tab2.java- 버튼누르면 산정보 불러와서 리스트랑 연결되도록, tab2.xml- 전체 체크박스(스피너 , 체크박스, 스피너), MountainItem-지금은 "산정보 부제" 정보 하나만 받는경우를 가정하고 데이터를 한개만 받음
- 세영: 2019.06.02 tab2.java 버튼 누르면 정보 못 가져온 것 수정 완료 
- 윤진: 2019.06.03 산정보지역, 산정보주제 스피너 가능! 앞으로 추가할것 -> 산정보계절코드 스피너추가하고 스피너 xml추가할것
그리고 다이얼로그로 구글맵까지 뜨게하기
- 윤진: 2019.06.19 tab2 - 산정보계절코드 스피너추가하고 체크박스 선택안하면 그냥 키값을 ""(null)인 상태로 되도록함.
    버튼 누르면 산정보를 구글맵으로 뜨게하기 / tab4 - xml 완성 item리스트뷰 밑에 보이게 함
- 윤진: 2019.06.20 tab3 - 날씨 xml완성/ 음악 java,xml완성 -> 실행 오류나면 gradle들어가서 버전 22,28계속 바꿔가면서 실행하다보면됨..
- 세영: 2019.06.20 tab2 - 레이아웃 조정, 초기화 버튼 추가 
- 윤진: 2019.06.20 tab3 - 음악 없어도 화면 안꺼지도록 수정
- 세영: 2019.06.20 tab5 다이얼로그 띄우고 다이어리 리스트뷰 동적 추가  
- 세영: 2019.06.20 tab2 산 후보군 많을 때 랜덤 선택 
- 세영: 2019.06.20 tab1 데이터 파싱 100개 속도 올려서 완성 !!!!!
- 세영: 2019.06.20 tab5 산 이름까지 유저가 등록하게 함, tab5 완성!!!!
- 세영: 2019.06.20 tab5 저장된 파일 중복으로 읽히는 것 처리, 삭제 기능 없앰 
- 윤진: 2019.06.20 tab3, tab4 완성/ tab3은 스피너로 처리
- 윤진: 2019.06.20 tab3, tab4 커밋
- 세영: 2019.06.21 tab2 수정, 이미지들 추가 
- 세영: 2019.06.21 tab3 날씨 여러번 검색 되도록 수정 
- 윤진: 2019.06.21 tab2 토스트부분 
- 세영: 2019.06.21 tab2, tab3 drawable 추가, 이미지 버튼별 selector 설정 
- 윤진: 2019.06.21 tab2 체크박스 해제시 enable, clickable="false"설정, 세 조건모두를 충족시키지 않아도 결과나오도록 수정
- 세영: 2019.06.21 tab4 레이아웃 수정 완료, tab5 날짜 선택 안 할 경우 오늘 날짜로 자동 설정  
- 윤진: 2019.06.21 tab3 음악기능완성
- 윤진: 2019.06.21 주석수정
- 세영: 2019.06.21 tab2 스피너 선택되지 않을 경우 toast 수정, 구글맵 띄우기, tab3 날씨 날짜 현재 날짜로 수정 
