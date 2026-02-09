# 프로젝트 개요
* 개발 기간 : 2025.09.25 ~ 2025.10.01
* 개발 인원 : 4인
* 핵심역할 : 상점 및 음식 정보

# Tech Stack
* Backend
    * Java 17(IntelliJ)
    * Spring Boot
    * Spring Data JPA
    * H2 Database (또는 MySQL 설정 지원)
    * Maven

* Frontend
    * HTML5
    * CSS3
    * JavaScript
    * OpenLayers (지도 라이브러리)
    * Chart.js (데이터 시각화)
    * Thymeleaf (템플릿 엔진)

# 핵심 기능
* 지도 기반 식당 조회
    * OpenLayers API를 활용한 지도 인터페이스 제공
    * 학교 및 주변 식당의 위치를 마커로 표시
    * 마커 클릭 시 식당의 상세 정보(메뉴, 위치 등) 확인 가능

* 식사 기록 관리 (Lunch Log)
    * 학생들이 이용한 식당과 메뉴를 기록
    * 식당별 방문 횟수 및 메뉴 데이터를 데이터베이스에 저장

* 데이터 시각화 및 통계 (Chart)
    * Chart.js를 활용하여 식사 데이터를 다양한 차트로 제공
    * 식당별 선호도, 메뉴 분포 등을 시각적으로 분석
    * 관리자용 전체 통계 및 개별 식당 통계 페이지 지원

* 식당 및 메뉴 관리
    * 식당 정보(이름, 좌표, 연락처 등) 등록 및 수정
    * 식당별 메뉴 리스트 관리 기능
