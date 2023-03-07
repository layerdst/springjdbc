## Spring JDBC 강의(230301~)
  - 230301 코딩내용
    - SpringBoot Setting
      - SpringBoot Version 2.7.9
      - SpringBoot starter jdbc
      - mysql-connector j
      - lombok
      
  - 230302 코딩내용
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
  - 0304 코딩내용
    - Transaction 이해와 적용
      - Transaction 적용
        - Transaction 시작 - setAutocommit(false)
        - Transaction 실패 - rollack()
        - Transaction 성공 - commit()
        - Transaction 종료 - setAutocommit(true) / close()
      - 회원간의 계좌이체 비즈니스 로직 - memberServiceV2.accountTransfer(fromId, toId, money)
  - 0305 코딩내용
    - Transaction 개선
      - TransactionManager을 주입하여 TransactionTempate 을 활용  
      - 트랜잭션 템플릿을 활용한 코드개선 - MemberServiceV3_2.accountTransfer()
      - 기존에 setAutoCommit과 close를 코드의 반복을 줄일 수 있음
  - 0306 코딩내용
    - 비즈니스 로직 내 트랜잭션 처리를 코드 내용을 Transaction AOP로 대체하여, 가독성을 높임
    - 또한 비즈니스 로직을 완벽히 분리할수 있었음
    - Transaction AOP - (선언적 트랜잭션) 해당 로직 메소드에 @Transacion 을 삽입
      - Transaction 시작 
        - transactionManager.getTransaction()
        - dataSource.getConnection()
        - conn.setAutoCommit(false)
      - Transaction 동기화 매니저
        - 보관된 커넥션을 활용해 동기화 커넥션을 획득
        - 비즈니스 로직 수행
      - Transaction 종료
        - setAutoCommit(auto);
        - connection 반환
    - 0307 코딩내용
      - Checked 예외 
        - 컴파일러가 체크하는 예외 -> 비즈니스 로직상 필요한 부분만 작성
        - CheckedTest
        - 예외 발생하면 Controller, Service 에서의 상위 계층으로 전달되는데, 유지보수 관점에서 예외처리 과정이 종속적일 수 있다.
        - 예외 발생시 Controller 계층까지 이동할 경우 예외의 노출로 보안이슈가 발생할 수 있다.
        - 따라서 Unchecked 예외를 기본으로 하고, 별도의 비즈니스 로직에서 커스텀 예외는 Checked 예외로 
      - UnChecked 예외 
        - 런타임 예외 -> 기본 예외처리는 이것으로 한다
        - UnCheckedTest
       

      
