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
      - 회원정보 생성 memberRepositoryV0.save()
      - 회원정보 수정 memberRepositoryV0.update()
      - 회원정보 조회 memberRepositoryV0.findById()
      - 회원정보 삭제 memberRepositoryV0.delete()
    - JDBC 공통 메소드 구현 
      - connection 객체 구현 dBConnctionUtil.getConnection()
      - db close 메소드 구현 dBConnctionUtil.close()
    - Test 메소드 구현
      - memberRepositoryV0.crud()
      
  - 0303 코딩내용
    - DataSource 를 활용한 Connetion Pool
      - DataSource 설정 
        - DriverManager 를 이용한 방법 - DriverManagerDataSource(URL, USERNAME, PASSWORD)
        - Hikari CP 를 활용한 커넥션 풀을 이용하는 법 - HikariDataSource()
      - 회원정보 생성 memberRepositoryV1.save()
      - 회원정보 수정 memberRepositoryV1.update()
      - 회원정보 조회 memberRepositoryV1.findById()
      - 회원정보 삭제 memberRepositoryV1.delete()
    - DataSource 공통 메소드 구현 
      - connection 객체 구현 getConnection()
      - db close 메소드 구현 close()
    - Test 메소드 구현
      - memberRepositoryV1.crud()

