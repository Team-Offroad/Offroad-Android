# 📗 OFFROAD-Android

<img src="https://github.com/user-attachments/assets/3d153cb7-f0e7-410a-baa3-9fb3ca21ae82" width="500">

> 오브, 일상을 모험하는 온오프라인 연계형 어드벤처 게임
</br>

## 💻 IDE

<div><img src="https://img.shields.io/badge/kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white"><img src="https://img.shields.io/badge/android-3DDC84?style=for-the-badge&logo=android&logoColor=white"><img src="https://img.shields.io/badge/Android%20Studio-3E81F2.svg?style=for-the-badge&logo=android-studio&logoColor=white"></div>
</br>

## 📁 Overview

> 오브는 반복되는 일상 속에서 권태를 느끼는 현대인들에게</br>
> 더욱 즐겁고 설레는 일상을 선물하고자 기획되었습니다.</br>

```
📦com.teamoffroad.offroad
├─📂app
├─📂core
│  ├─📂common
│  ├─📂designsystem
│  └─📂navigation
├─📂feature
│  ├─📂auth
│  │  ├─📂data
│  │  ├─📂domain
│  │  ├─📂navigation
│  │  └─📂presentation
│  ├─📂explore
│  │  ├─📂data
│  │  ├─📂domain
│  │  ├─📂navigation
│  │  └─📂presentation
│  ├─📂home
│  │  ├─📂data
│  │  ├─📂domain
│  │  ├─📂navigation
│  │  └─📂presentation
│  ├─📂main
│  │  ├─📂data
│  │  ├─📂domain
│  │  ├─📂navigation
│  │  └─📂presentation
│  ├─📂mypage
│  │  ├─📂data
│  │  ├─📂domain
│  │  ├─📂navigation
│  │  └─📂presentation
│  └─📂mypage
└─📂build-logic
```

</br>

## 🏭 Libraries

- Jetpack Compose: UI 개발의 간소화 및 유지보수성 향상
- Retrofit2: API 통합 간소화
- Naver Map: 위치 기반 서비스 추가
- Google Play Service Auth: 구글 소셜 로그인 추가
- Serialization: 데이터 직렬화
- Okhttp3: 네트워크 호출 성능 개선
- Coroutine: 비동기 처리 용이
- AndroidX: 안드로이드 호환성 보장
- ViewModel: 비즈니스 로직 분리
- Coil: 네트워크 이미지 생성
- Hilt: 아키텍처 의존성 주입
- Data Store: 자동 로그인 및 네트워크 토큰 저장

</br>

## 🥕 Extensions

- BaseResponse: 공용되는 네트워크 응답을 사용하기 위해 만들었습니다.
- Retrofit: 공용되는 네트워크 모듈을 사용하기 위해 만들었습니다.
- 디자인 시스템 및 기타 공용 컴포넌트: 디자인의 일관성 유지 및 유지보수성을 위해 만들었습니다.

</br>

## 🏭 Tech Stacks

- Clean Architecture: 의존성 분리 및 유지보수
- Multi Module: 빌드 속도 개선
- MVVM pattern: 비즈니스 로직 분리
- Hilt: 구글 권장 의존성 주입
- Single Activity Architecture: 제트팩 컴포즈 성능 개선

</br>

## 🏷️ Convention

- Adheres to the [OFFROAD-Android-Convention](https://github.com/Team-Offroad/Offroad-Android).

</br>

## ⭐️ Did you find this repository helpful?

- Don't forget give a star.

</br>

## 🍴 Didn't you?

- Then fork this repo, make it better and don't forget give a STAR.

</br>

## 👨‍👩‍👧‍👦 Contributers

| <img src="https://avatars.githubusercontent.com/u/127238018?v=4" width="128" /> | <img src="https://avatars.githubusercontent.com/u/81751105?v=4" width="128" /> | <img src="https://avatars.githubusercontent.com/u/112953135?v=4" width="128" /> |
|:-------------------------------------------------------------------------------:|:------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------:|
|                      [👑최준서](https://github.com/junseo511)                      |                      [유정현](https://github.com/OliviaYJH)                       |                     [이석찬](https://github.com/leeseokchan00)                     |
|                                 탐험 뷰</br>모듈 분리                                  |                                 홈 뷰</br>디자인 세팅                                 |                          로그인 뷰</br>온보딩 뷰</br>네비게이션 세팅                           |
