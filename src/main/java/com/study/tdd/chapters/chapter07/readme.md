# 대역
    외부 요인에 의해 테스트 진행이 어려울 때 대체하여 진행할 수 있는 대상.

## 대역의 목적
    테스트 대상이 외부 요인에 의존하여 테스트가 어려울 경우 대역을 써서 테스트 진행

* test double
  * 테스트에서 진짜 대신 사용할 대역을 지칭
  * 영화 촬영 시 배우를 대신하여 위험한 동작을 연기하는 스턴드 더블(Stunt Double)이라는 용어에서 유래됨.
  * 의존 대상으로 인해 테스트가 실패하는 것을 막기 위해 의존 대상 대신 실제 동작하는 것처럼 보이는 별도의 객체.

## 대역의 필요성
1. 외부요인은 테스트 작성을 어렵게 만들고 테스트 결과의 신뢰도도 낮춤.
2. 테스트 환경 제공이 어려워지면서 생기는 대기 시간을 줄여 개발 속도 지연을 막음.
3. 예)
   - 테스트 대상에서 파일시스템을 사용
   - 테스트 대상에서 DB 연동
     - 상황에 맞는 테이블 및 데이터 부재
     - 데이터 조회/데이터 저장
   - 테스트 대상에서 외부 HTTP 통신
     - 외부 테스트 API 서버 미제공
     - 외부 API 서버 일시적 장애

## 대역을 이용한 테스트
1. DB 대신 맵을 이용해서 정보 저장
   - 메모리에만 데이터가 저장되므로 DB와 같은 영속성을 제공하지는 않지만 테스트에 사용할 수 있는 만큼의 기능을 제공
   ```
   public class MemoryUserRepository implements UserRepository {
       private Map<String, User> users = new HashMap<>();
   }
   ```
3. 대역을 사용한 외부상황 흉내와 검증
   - 외부 API 연동
     - 외부 API 상황별 결과를 타입으로 분리하여 확인
     - 예) 외부 업체에서 제공하는 카드 정보 API 연동 없이 올바르게 동작 시 "유효" 리턴
     ```
     public class SpyEmailNotifier implements EmailNotifier {
         private boolean called;
         private String email;

         ...
     
         @Override
         public void sendRegisterEmail(String email) {
             this.called = true;
             this.email = email;
         }
     
         ...
     }
     ```
   - DB
     - DB연동하지 않고 메모리를 사용
     - 실제 DB를 연동하지 않고 데이터가 올바르게 바뀌고 저장되는지 확인
     - 테스트 속도가 굉장히 빠른 이점
        ```
        public class MemoryUserRepository implements UserRepository {
            private Map<String, User> users = new HashMap<>();
            @Override
            public void save(User user) {
                users.put(user.getId(), user);
            }

            ...
        }
       ```

### 대역의 종류
| 대역 종류     | 설명                                                                                                                    |
|-----------|-----------------------------------------------------------------------------------------------------------------------|
| 더미(Dummy) | 인스턴스화된 객체만 필요하고 기능까지는 필요하지 않는 경우 사용. <br/>아무런 동작을 하지 않음.<br/>주로 파라미터 전달을 위해 사용.                                       |
| 스텁(Stub)  | 테스트에 맞게 단순히 원하는 동작을 수행 <br/>특정메서드 호출에 대해 미리 정의된 동작을 반환하는 객체. <br/>테스트에서 사용될 때 실제동작이 아닌 가짜 동작을 수행 <br/>구현을 단순한 것으로 대체. |
| 가짜(Fake)  | 실제 동작하는 구현 제공.<br/>(운영에서는 사용할 수 없는 객체) <br/> 예) DB 대신 메모리를 이용해서 구현                                                    |
| 스파이(Spy)  | 호출된 내역 기록. <br/>기록한 내용은 테스트 결과 검증 시 사용.<br/>실제 객체를 사용하면서 해당 객체의 일부 동작을 감시하고 기록할 수 있는 객체. Stub에 해당                     |
| 모의(Mock)  | 실제-기대 행위 검증. 기대하는 대로 동작하지 않을 시 Exception 발생 가능. <br/>Stub이자 Spy에 해당                                                   |

예) 회원가입
- UserRegister : 회원가입에 대한 핵심 로직 수행 -> 테스트 동작 (Stub)
- WeakPasswordChecker : 암호가 약한지 검사 -> 테스트 동작 (Stub)
- UserRepository : 회원 정보 저장 및 조회 기능 제공 -> 실제 테스트 동작 구현 (Fake)
- EmailNotifier : 이메일 발송여부 확인 -> 동작 행위 기록(Spy)

## Mockito 의 모의 객체로 스텁(Stub)과 스파이(Spy) 대체
### Mockito
- 모의 객체 생성, 검증, 스텁을 지원하는 Java 오픈 소스 프레임워크
- 실제 객체의 동작을 모방하는 모의 객체(Mock Object)를 생성하여 테스트를 쉽게 만들어 줌.
- 주로 단일 컴포넌트의 동작을 테스트하는 데 사용.
#### Mockito 사용 목적
    모의 객체와 함께 서비스를 호출하여 비즈니스 로직이 올바르게 처리되는지 확인하기 위해 테스트 수행.
    로직 검증, 예외 상황 대처
#### 의존 설정 (gradle)
  ```
  dependencies {
      testImplementation 'org.mockito:mockito-core:4.8.0'
  }
  ```
- JUnit5 확장 설정 가능
- @Mock 어노테이션을 붙인 필드에 자동으로 모의 객체를 생성해줌

#### Mockito 수행과정
    모의 객체 생성 -> 메서드 호출 예상 동작 설정 -> 메서드 호출 검증
- given - when - then 패턴을 따라 다른 예시를 보면 when()-thenReturn()-verify() 조합도 있으나 given-when 의 구분이 모호하여 given() 메서드 등장.
- 두 방식 모두 사용해도 무방.
- 교재에서는 given()-willReturn() 조합을 사용.

1. 모의 객체 생성 : Mock
    - 실제 사용되는 객체 생성을 대체하기 위해 테스트에 사용되는 객체.
    - 일반적으로 null, 0, false 등 기본 타입의 값으로 반환.
2. 메서드 호출 예상 동작 설정 : Stub
    - 모의 객체 메서드 호출에 대핸 '예상동작' 정의
3. 메서드 호출 검증 : Verify
    - 모의 객체에 대한 특정 메서드가 호출되고 예상된 인자와 함께 호출되었는지 검증 메서드 제공

##### mock()
- 특정 타입의 모의 객체 생성
- 클래스, 인터페이스, 추상 클래스에 대해 모의 객체 생성 가능
    
```
GameNumGen genMock = mock(GameNumGen.class);
```

##### given()
- 스텁(Stub) 설정
- 모의 객체 혹은 메서드 호출 전달
```
given(genMock.generate(GameLevel.EASY));
```

##### willReturn()
- 리턴 값 지정 설정
```
given(genMock.generate(GameLevel.EASY)).willReturn("123");
```
##### willThrow()
- 값 지정 대신 익셉션 발생 설정 가능

```
@Test
void weakPassword() {
    BDDMockito.given(mockPasswordChecker.checkPasswordWeak("pw")).willReturn(true);
    assertThrows(WeakPasswordException.class, () -> {
        userRegister.register("id", "pw", "email");
    });
}
```

#### Argument 매칭 처리
    Mockito 는 일치하는 스텁 설정이 없을 경우 자료형의 기본 값을 리턴함
    예) int -> 0, boolean -> false, String -> null, Collection -> null

* ArgumentMatchers 클래스 사용
* Mockito 클래스와 BDDMockito 클래스는 모두 ArgumentMathers 를 상속하고 있음

| ArgumentMatchers 제공 메서드           | 설명                           |
|-----------------------------------|------------------------------|
| anyInt() ... anyFloat()           | 기본데이터 타입에 대한 임의값 일치          |
| anyBoolean()                      | 기본데이터 타입에 대한 임의값 일치          |
| anyString()                       | 문자열에 대한 임의 값 일치              |
| any()                             | 임의 타입에 대한 일치                 | 
| anyList() ... anyCollection()     | 임의 콜렉션에 대한 일치(Mathcher 생성)   |   
| matches(String), matches(Pattern) | 정규표현식을 이용한 String 값 일치 여부 확인 |
| eq(값)                             | 특정 값 일치 여부 확인                |

* null 값 비교 시 eq 가 아닌 isNull 사용 권장.

#### 행위 검증
    모의 객체 호출 여부 검증
##### then()
- 모의 객체를 전달받아 메서드 호출 여부 검증
##### should()
- 모의 객체의 특정 메서드 호출 지정
```
@Test
void checkPassword() {
    userRegister.register("id", "pw", "email");
    BDDMockito.then(mockPasswordChecker).should().checkPasswordWeak(BDDMockito.anyString());
}
```
##### only()
- 한 번만 호출되는 것을 검증
##### times(int)
- 지정한 횟수만큼 호출
##### never()
- 호출하지 않음
##### atLeast(int)
- 최소 지정한 횟수만큼 호출
##### atLeastOnce()
- 최소 한 번은 호출
##### atMost(int)
- 최대 지정한 횟수만큼 호출

#### 인자 캡쳐
    모의 객체 호출 시 사용한 인자 검증
##### ArgumentCaptor
- 메서드 호출 여부 검증 중 실제 호출할 때 전달한 인자 보관
- capture() 로 메서드 호출 시 전달한 인자값 보관
- getValue() 로 보관한 인자값 리턴
```
@Test
void whenRegisterThenSendMail() {
    userRegister.register("id", "pw", "email");
    ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
    BDDMockito.then(mockEmailNotifier).should().sendRegisterEmail(captor.capture());
    String realEmail = captor.getValue();
    assertEquals("email@email.com", realEmail);
}
```

## 의존 도출 및 대역 사용 기준
    📢요구사항이 계속해서 바뀌기 때문에 구현하기 전에 모든 기능을 설계하는 것은 불가능함
    📢단위기능을 구현하기 전에 어떤 구성요소가 필요한지 고민하는 것은 의존 대상을 도출할 때 도움이 됨

- 제어하기 힘든 외부 상황을 별도 타입으로 분리
  - 예) 카드번호 유효 확인 API 호출 성공/실패, 카드 정보 DB 조회 성공/실패 등 
  - 예) 카드번호 유효 확인 API 호출 성공-유효/무효/도난, 카드 정보 DB 조회 성공-데이터 유/무 등 
- 테스트 코드는 별도로 분리한 타입에 따라 대역을 생성
  - 예) 카드번호 유효 확인 API 호출, 카드 정보 DB 조회 등
  - 당장 구현하는 데 시간이 걸리는 로직을 따로 분리해서 테스트 구현 가능
- 생성한 대역을 테스트 대상의 생성자 등을 이용해서 전달
- 대역을 이용하여 상황설정
  - 예) 카드번호 유효/무효/도난 등

## 대역 사용의 장점 : 대기 시간 감소, 개발 속도 증진
- 테스트 환경 마련이 될 때까지 개발이 지연되지 않음
- 선행 기능을 다른 개발자가 개발완료할 때까지 기다릴 필요없이 바로 테스트 가능
- 의존대상에 대하여 실제 구현없이도 실행 결과 확인 가능

## 과도한 대역 사용의 단점
- 테스트 코드 복잡도 증가
  - 메서드 호출 여부 검증 -> 전달 인자 저장 -> 인자 값 유효 검증 -> 기대값-실제값 확인
- 메서드 호출 여부를 검증하는 수단이기 때문에 테스트 결과가 모의 객체 간의 상호 관계 및 작용에 의존됨

# 테스트 가능한 설계
### 테스트가 어려운 이유 : 교체할 수단 부재
#### 하드 코딩된 경로
    하드코딩된 경로, IP주소, 포트 번호 등
#### 의존 대상 직접 생성
    필요한 모든 환경(패키지, 클래스, DB, 데이터) 구성
#### 정적 매서드 사용
#### 실행 시점에 따라 달라지는 결과
    테스트를 실행하는 시점에 따라 테스트 결과가 달라질 시 테스트의 신뢰도 하락함.
#### 역할이 섞여있는 코드
    기능구현이 섞여있어 특정한 부분만 테스트하기 어려움.
#### 그외
- 소켓통신 코드 포함
- 콘솔에서 입력/출력
- 테스트 대상이 사용하는 의존 대상이 final
- 테스트 대상의 소스를 소유하지 않음

### 코드 교체
#### 하드코딩에 대하여 교체 기능 추가
- 생성자나 메서드 파라미터로 받기
- 메서드를 실행할 때 필요값을 인자로 전달받기
#### 의존대상 주입받을 수 있는 수단 제공
- 생성자나 세터 이용
#### 테스트하고 싶은 코드 분리
- 해당 코드를 별도 기능으로 분리하여 테스트 진행
#### 임의 값 생성 기능 분리
- 테스트 대상이 사용하는 시간이나 임의 값을 제공하는 기능 별도로 분리하여 테스트를 원하는 상황으로 쉽게 제어 가능
#### 외부 라이브러리 연동 및 의존 대상이 final인 경우에는 해당 기능을 감싸서 사용
- 외부 라이브러리가 정적 메서드로 기능을 제공한다면 대체가 어려움.
- 외부 라이브러리와 연동하기 위한 타입을 따로 만들어 대역으로 재정의

------
참고 자료
1) <테스트 주도 개발 시작하기> - 최범석
2) https://adjh54.tistory.com/346
3) https://hudi.blog/test-double/