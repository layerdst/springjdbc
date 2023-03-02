## Spring JDBC 강의(230301~)
  - 230301 코딩내용
    - SpringBoot Setting
      - SpringBoot Version 2.7.9
      - SpringBoot starter jdbc
      - mysql-connector j
      - lombok
      
  - 0302 코딩내용
    - JDBC 를 활용한 회원정보 CRUD
      - MYSQL 테이블 생성
      - 회원정보 생성 memberRepository.save()
      - 회원정보 수정 memberRepository.update()
      - 회원정보 조회 memberRepository.findById()
      - 회원정보 삭제 memberRepository.delete()
    - JDBC 공통 메소드 구현 
      - connection 객체 구현 dBConnctionUtil.getConnection()
      - db close 메소드 구현 dBConnctionUtil.close()
    - Test 메소드 구현
      - memberRepository.crud()
