## Activity란?

- Activity는 사용자와 상호작용할 수 있는 사용자 인터페이스(UI)를 포함한 화면을 말한다.

## Activity 생명 주기

Activity 시스템은 stack으로 관리된다.

### Activity Diagram

![Untitled](https://developer.android.com/guide/components/images/activity_lifecycle.png?hl=ko)

- 처음 앱 시작할 때

onCreate → onStart → onResume

- 잠시 앱 자리비울 때

onPause → onStop → (재접속) → onRestart → onResume

- 앱 종료할 때

onPause → onStop → onDestory

3가지 핵심 루프

- Activity의 전체 생명주기는 첫 호출인 onCreate() 와 마지막 호출인 onDestroy() 사이에서 일어난다. Activity는 onCreate()에서 전역 상태를 설정하고, onDestroy()에서 나머지 모든 리소스를 해체한다.
- Activity의 가시적 생명주기는 onStart() 호출과 onStop() 호출 사이에 발생한다. 이 시간 동안 사용자는 화면에서 activity를 볼 수 있지만 foreground에 있지 않고 사용자와 상호 작용할 수 있다. onStart() 와 onStop() 메서드는 Activity가 사용자에게 표시되고 숨겨짐에 따라 여러번 호출될 수 있다.
- Activity의 foreground 생명주기는 onResume()과 onPause()호출 사이에 발생한다. 이 시간 동안 Activity는 사용자가 볼 수 있고, 사용자와 상호작용할 수 있다. Activity는 onResume()과 onPause() 사이를 자주 이동할 수 있다.

Activity의 전체 생명 주기는 다음 Activity 메서드에 의해 정의된다.
