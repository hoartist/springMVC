# springMVC
</br>

## :bulb: 01. Spring MVC의 기본 개념

</br></br>

<b> :arrow_forward: MVC 개념 </b>
</br>
<img src="https://user-images.githubusercontent.com/86214493/128589219-90e6c612-2037-40ed-a9b2-a21fadcf0415.png" width="50%" height="50%"/>

+ MVC(Model-View-Controller)는 소프트웨어 엔지니어링에서 사용자 인터페이스와 애플리케이션 로직을 분리하는 데 사용되는 패턴이다. 
+ Model은 비즈니스 계층을 정의하고 Controller는 애플리케이션의 흐름을 관리하며 View는 애플리케이션의 프리젠테이션 계층을 정의한다.
+ <b> Controller </b>
  * Controller 계층은 View와 Model 간의 인터페이스 역할을 한다. View 계층에서 요청을 받고 필요한 유호성 검사를 포함하여 요청을 처리한다.
  * 요청은 데이터 처리를 위해 모델 계층으로 추가 전송되고, 일단 처리되면 데이터는 컨트롤러로 다시 전송된 다음 View에 표시된다. 
+ <b> Model </b>
  * 시스템 비즈니스 로직을 포함하고 애플리케이션의 상태를 나타내는 데이터 계층이다.
  * 프리젠테이션 레어이와 무관하며 컨트롤러는 모델 레이어에서 데이터를 가져와서 뷰 레이어로 보낸다.
+ <b> View </b>
  * 이 계층은 일반적으로 UI 형식의 응용 프로그램 출력을 나타낸다. 프리젠티이션 레이어는 컨트롤러가 가져온 모델 데이터를 표시하는데 사용된다. 

</br></br>
<b> :arrow_forward: Spring MVC의 주요 구성 요소 </b>

<img src="https://user-images.githubusercontent.com/86214493/128589240-bbc8d242-6673-406b-a426-5349d4878a73.png" width="70%" height="50%"/>

</br>

+ <b> DispatcherServlet : </b> 클라이언트의 요청을 전달 받는다.
+ <b> HandlerMapping : </b> 클라이언트의 요청 URL을 어떤 컨트롤러가 처리할지를 결정한다. 
+ <b> HandlerAdapter : </b> 컨트롤러 안에 있는 메소드들 중에 적합한 메소드를 선택해준다.
+ <b> Controller : </b> 클라이언트 요청을 처리한 뒤, 그 결과를 DispatcherServlet에게 알려준다. 
+ <b> ViewResolver : </b> 가장 적합한 View를 찾아준다.
+ <b> View : </b> 컨트롤러의 처리 결과 화면을 생성한다. 
+ <b> ModelAndView : </b> 데이터와 뷰의 이름을 전달하는 객체
+ <b> Model : </b> 뷰에 데이터만을 전달하기 위한 객체


</br></br>

<b> :arrow_forward: Spring MVC의 처리 순서 </b>

1. 클라이언트가 서버에 어떤 요청을 한다면 스프링에서 제공하는 DispatcherServlet이라는 서블릿이 요청을 가로챈다. (web.xml에 설정)
</br></br>
3. 요청을 가로챈 DispatcherServlet은 HandlerMapping에게 어떤 컨트롤러에게 요청을 위임하면 좋을지 물어본다.
(HandlerMapping은 setvlet-context.xml에서 @controller로 등록한 것들을 이미 스캔해서 찾아놨기 때문에 어느 컨트롤러에게 요청을 위임해야할지 알고 있다.)
</br></br>
3. 요청에 매핑된 컨트롤러가 있다면 @RequestMapping을 통하여 요청을 처리할 메서드에게 도달한다.
</br></br>
4. 컨트롤러에서는 해당 요청을 처리할 <b> Service </b>를 <b> 주입(DI) 받아 </b> 비즈니스 로직을 Service에게 위임하게 된다.
</br></br>
5. Service에서는 요청에 필요한 작업 대부분(코딩)을 담당하여 데이터베이스에 접근이 필요하면 <b> DAO </b> 를 주입받아 DB처리는 DAO에게 위임하게 된다.
</br></br>
6.  DAO는 mybatis(또는 hibernate 등) 설정을 이용해서 SQL 쿼리를 날려 DB에 저장되어 있는 정보를 받아 서비스에게 다시 돌려준다.
(이때, 보통 Request와 함께 날라온 DTO 객체로부터 DB 조회에 필요한 데이터를 받아와 쿼리를 만들어 보내고, 결과로 받은 Entity 객체를 가지고 Response에 필요한 DTO 객체로 변환한다.)
</br></br>
7. 모든 비지니스 로직을 끝낸 Service가 결과물을 컨트롤러에게 넘긴다.
</br></br>
8. 결과물을 받은 컨트롤러는 필요에 따라 Model 객체에 결과물을 넣거나, 어떤 View(jsp) 파일을 보여줄 것인지 등의 정보를 담아 DispatcherServlet에게 보낸다.
</br></br>
9. DispatcherServlet은 viewResolver에게 받은 뷰의 대한 정보를 넘긴다.
</br></br>
10. viewResolver는 해당 JSP를 찾아서 (응답할 View를 찾음) Dispatcher에게 알려준다.
(servlet-context.xml에서 suffix, prefix를 통해 /WEB-INF/views/index.jsp 이렇게 만들어주는것도 viewResolver)
</br></br>
11. Dispatcher은 응답할 view에게 Render를 지시하고 View는 응답 로직을 처리한다.
</br></br>
12. 결과적으로 DispatcherServlet이 클라이언트에게 렌더링된 View를 응답한다. 





