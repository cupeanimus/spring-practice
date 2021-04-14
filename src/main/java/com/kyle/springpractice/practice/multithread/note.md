멀티쓰레드로 인한 db 동시성 제어 학습

참고 블로그 : https://4whomtbts.tistory.com/118

1. 일반적인 상황에서 멀티쓰레드로 동시에 같은 데이터를 업데이트를 하게되면 어떻게 되나?
-> 중첩 발생
2. 참고 페이지 해결 방법
 - @Version 사용, 조회시 Lock 모드를 PESSIMISTIC_FORCE_INCREMENT 설정
 ? 블로그를 통해 습득한 지식으로는 @Version 을 통해 낙관적 락을 설정한다 이 설정과 조회에서는 Lock 모드에서 비관적 락 옵션으로?
   -> @Version을 삭제하였을때 조회가 되지 않은 것으로 보아 @Version의 default가 낙관적 락으로 이해된다.
 - accountId를 @Id로 설정하였기에 findByAccountId와 findById 모두 동일하게 검색 될 것으로 예상하였으나 id 컬럼이 추가되었다 <- 실수
 - version은 찾을때와 수정할때, 즉 db에 접근할때마다 1씩 증가. 때문에 10씩 100번 추가하여서 최종 200
 
 
 @GeneratedValue(strategy = GenerationType.AUTO) vs @GeneratedValue(strategy = GenerationType.IDENTITY) 차이
 
default인 GenerationType.AUTO는 hibernate_sequence table에서 시퀀스를 가져와서 업데이트한다. 즉 모든 테이블에서 적용
A,B,C 테이블 모두 GenerationType.AUTO 이고 하나씩 Insert를한다면 각 테이블의 id 는 1,1,1이 아닌 1,2,3이 되는 것이다.

@GeneratedValue(strategy = GenerationType.IDENTITY) 는 데이터 베이스의 auto_increment와 같다.
따라서 각 테이블마다 독립적이고 순차적으로 증가한다.
 
 
