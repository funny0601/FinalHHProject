안드로이드 9.0, SDK=28

현재 apk 작동하는 안드 기기 리스트
: Samsung Galaxy S8, S8+, 노트8, LG G8 ThinQ
<br><br>
tab1.java <br>
: 해당 공공데이터 제공해주는 홈페이지에 들어가서 본인 서비스키를 신청해서 공백란에 넣으면 실행<br>
카카오 로그인<br>
: 카카오 api, 해시키 모두 필요 string.xml 수정해야함<br>
구글맵<br>
: SHA1 키 값을 개인 노트북마다 직접 발급받아야 함

**parseXML 파일 안에 해시키 주석처리 했으니까 매번 본인껄로 바꿔야 함**

커밋/푸시 로그
- 2019.05.27 프사 없을 경우 대체 이미지로 처리, tab5.xml 레이아웃 개요 작성
- 2919.05.31 tab1에 listView에 대해 검색하는 기능 xml 레이아웃 개요 작성 
- 2019.05.31 xml 파일에 맞게 tab1.java에 검색 기능 추가 (이제 검색 되는데 검색 결과 없는 것에 대한 처리 아직 못함)
- 2019.06.01 xml 파싱하는 클래스, 파싱된 데이터 mountainTable 클래스화 (tab1.java 기능별로 분할한거임), 데이터를 여러 탭 간의 공유 가능(tab2.java 코드 보면 확인 가능)





==========================
이거 이렇게 쓰는거 맞나유?

ListViewAdapter.java & listview_item.xml-  Adapter에 추가된 데이터를 저장하기 위한 
산관련 정보들 리스트로 보여줌
tab2.java- 버튼누르면 산정보 불러와서 리스트랑 연결되도록 
tab2.xml- 전체 체크박스(스피너 , 체크박스, 스피너)
MountainItem-지금은 "산정보 부제" 정보 하나만 받는경우를 가정하고 데이터를 한개만 받음


====하다가 오류난 부분
 btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

 //String strUrl=serviceUrl+"?mntnInfoAraCd="+"01"+"?mntnHght="+"1561"+"?mntnInfoThmCd="+"02"+"&serviceKey="+serviceKey;

"?mntnInfoAraCd="+이뒤에 숫자!!!=>
첫번째 스피너를 클릭할때 해당 arrayindex번호를 불러와서 이어줄려고
 if arrayList.get(int index) ....했는데 안되고

"?mntnHght="+이뒤에?=> 여기 상중하랑 연결시켜야 되는데 그걸 if문으로 처리해야되나

"?mntnInfoThmCd="+이뒤에 숫자!!!=>
두번째 스피너를 클릭할때 해당 arrayindex번호를 불러와서 이어줄려고
 if arrayList2.get(int index) ....


        }
        });


