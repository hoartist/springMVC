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


</br></br></br>

## :bulb: 02. 프로젝트 셋팅

</br></br>

<b> :arrow_forward: DispatcherServlet </b>

> + Servlet/JSP에서 사용자 요청이 발생하면 이 요청 정보를 해석하고 개발자가 만든 코드를 동작시키는 첫번째 서블릿이다.
> + Spring MVC는 DispatcherServlet을 확대하여 Spring Framework가 가지고 있는 기능을 사용할 수 있도록 이 클래스를 재정의하고 있다.
> + Spring MVC 프로젝트 설정에서 가장 먼저 해야 하는 일은 DispatcherServlet 클래스를 Spring MVC에서 재정의한 클래스로 설정하는 일이다. 

</br>

<b> :arrow_forward: 설정 방식 </b>
> + Spring MVC 프로젝트를 설정하는 방식은 XML을 이용하는 방법과 Java 코드를 활용하는 방법이 있다.

</br>

 #### <b> :eyes: 공통 </b>

 + <b> 프로젝트 생성 </b>
   + File -> New -> Dynamic Web Project 
   + 프로젝트 마우스 오른쪽 버튼 -> Configure -> Convert to Maver Project

</br>

<img src="https://user-images.githubusercontent.com/86214493/128590537-ae98229f-f447-485c-815d-14dde9df1e3f.png" width="40%" height="50%"/>
<img src="https://user-images.githubusercontent.com/86214493/128590543-191c1e10-3ed1-46c0-8122-e593d9677a97.png" width="40%" height="50%"/>
<img src="https://user-images.githubusercontent.com/86214493/128590552-4080ee51-dbf3-42db-8e58-ab8f80b05c84.png" width="40%" height="50%"/>

</br>

 + <b> pom.xml </b>

 <b> &nbsp;  &nbsp; &nbsp; □ servlet-api </b>
 
 </br>
 
<img src="https://user-images.githubusercontent.com/86214493/128590784-efc9dda5-0d56-4419-8cdf-0a98108005d5.png" width="40%" height="50%"/>
    
</br>
    
 ```xml
     <!-- https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api -->
     <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>4.0.1</version>
        <scope>provided</scope>
     </dependency>
 ```
    
 </br></br>
    
 <b> &nbsp; &nbsp;  &nbsp; □ jsp-api </b>
 
 </br>
 
 <img src="https://user-images.githubusercontent.com/86214493/128590847-d80cab62-fd45-40fc-be7d-63313c34ad97.png" width="40%" height="50%"/>
    
 </br>
    
 ```xml

    <!-- https://mvnrepository.com/artifact/javax.servlet.jsp/javax.servlet.jsp-api -->
   <dependency>
       <groupId>javax.servlet.jsp</groupId>
       <artifactId>javax.servlet.jsp-api</artifactId>
       <version>2.3.3</version>
       <scope>provided</scope>
   </dependency>

 ```

  </br></br>

  <b> &nbsp; &nbsp; &nbsp; □  jstl </b>
    
   </br>
   
   <img src="https://user-images.githubusercontent.com/86214493/128590898-891368fe-e419-4394-b4f2-fba42a721988.png" width="40%" height="50%"/>
   
   </br>
   
   ```xml
   
    <!-- https://mvnrepository.com/artifact/javax.servlet/jstl -->
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>jstl</artifactId>
        <version>1.2</version>
    </dependency>
    
   ```
   </br></br>


 <b> &nbsp; &nbsp; □ Spring Web Mvc </b>
    
   </br>
   
  <img src="https://user-images.githubusercontent.com/86214493/128590934-6262e5a8-c327-4155-a982-6f287244f70e.png" width="40%" height="50%"/>
   
   </br>

   ```xml
    
    <!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
   <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-webmvc</artifactId>
       <version>5.2.2.RELEASE</version>
   </dependency>

   ```

</br></br>

 + 정리
 
 ```xml
 
  <!-- 라이브러리 버전관리 -->
	<properties>
   <javax.servlet-version>4.0.1</javax.servlet-version>
   <javax.servlet.jsp-version>2.3.3</javax.servlet.jsp-version>
   <javax.servlet-version>1.2</javax.servlet-version>
   <org.springframework-version>5.2.2.RELEASE</org.springframework-version>
   <!-- <org.springframework-version>4.3.25.RELEASE</org.springframework-version> -->
	</properties>

	<!-- 라이브러리 셋팅 -->
	<dependencies>

		<!-- https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api -->
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>javax.servlet-api</artifactId>
			<version>4.0.1</version>
			<scope>provided</scope>
		</dependency>

		<!-- https://mvnrepository.com/artifact/javax.servlet.jsp/javax.servlet.jsp-api -->
		<dependency>
			<groupId>javax.servlet.jsp</groupId>
			<artifactId>javax.servlet.jsp-api</artifactId>
			<version>${javax.servlet.jsp-version}</version>
			<scope>provided</scope>
		</dependency>

		<!-- https://mvnrepository.com/artifact/javax.servlet/jstl -->
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
			<version>${javax.servlet-version}</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>5.2.2.RELEASE</version>
		</dependency>

	</dependencies>


 ```
 
 </br></br></br>
 
  #### <b> :eyes: XML로 셋팅하기 </b>
 
 </br>
 
&nbsp;  :star: <b> (1) web.xml 설정 </b>

● <b> DispatcherServlet </b> </br>
> &nbsp; □ 웹 애플리케이션에서 최초 사용자 요청이 발생하면 가장 먼저 DispatcherServlet이 사용자의 요청을 받는다. </br>
> &nbsp; □ 따라서 개발자는 DispatcherServlet을 서블릿으로 등록해주는 과정을 설정해주어야 한다. </br>
> &nbsp; □ (따로 정의해주지 않으면 Apache tomcat의 web.xml에서 정의한 default servlet이 실행하게 된다.) 
> &nbsp; □ (각 WAS는 서블릿 매핑에 존재하지 않는 요청을 처리하기 위한 디폴트 서블릿을 제공하고 있음) 

</br>

● <b> ApplicationContext 설정 </b> </br>
> &nbsp; □ Spring MVC로 만든 웹 애플리케이션에서 대한 설정을 하는 파일 </br>

</br>

   <img src="https://user-images.githubusercontent.com/86214493/128593502-5eb392e7-3bb1-490b-9225-edfa387539b3.png" width="50%" height="50%"/>
  

```xml

	<!-- 현재 웹 애플리케이션에서 받아들이는 모든 요청에 대해 appServlet이라는 이름으로 정의되어 있는 서블릿을 사용 -->
	<servlet-mapping>
		<servlet-name>appServlet</servlet-name>
		<url-pattern>/</url-pattern> <!-- 모든 URL에 접속하면 이 DispatcherServlet이 받는다. -->
	</servlet-mapping>

	<!-- 요청 정보를 분석해서 컨트롤러를 선택하는 서블릿을 지정 -->
	<servlet>
		<servlet-name>appServlet</servlet-name>
		<!-- Spring MVC에서 제공하고 있는 기본 서블릿을 지정 -->
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		
		<!-- Spring MVC 설정을 위한 xml 파일을 지정 -->
		<!-- DispatcherServlet을 등록할 때 초기 파라미터로 스프링 설정파일도 같이 설정 -->
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>/WEB-INF/config/servlet-context.xml</param-value>
		</init-param>
		
		<load-on-startup>1</load-on-startup>
		
	</servlet>


```

</br></br>

● <b> RootContext 파일 설정 </b> </br>
> <b> root-context.xml </b>
> > &nbsp; □ 처음에 프로젝트를 생성하면 아무 내용도 없다. RootContext 파일은 공통 빈을 설정하는 곳으로 주로 View 지원을 제외한 Spring MVC 프로젝트 수행 시 사용할 Bean들을 정의한다. </br>
> > &nbsp; □ Service/Repository(DAO)/DB/log 등 비지니스 로직과 관련된 설정을 해준다. 


<img src="https://user-images.githubusercontent.com/86214493/128593922-73944b0d-6bcf-4eb3-9a6c-e6ca473cfa4d.png" width="35%" height="50%"/>


</br>


```xml


	<!-- Bean을 정의할 xml 파일을 지정한다. -->
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>/WEB-INF/config/root-context.xml</param-value>
	</context-param>

	<!-- 리스너설정 -->
	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>

```

</br></br>


● <b> 파라미터 필터 설정 </b> </br>
> &nbsp; □ 파라미터에 한글이 있을 경우를 위해 인코딩을 설정한다. </br>

```xml
	
<!-- 파라미터 인코딩 필터 설정 -->
	<filter>
		<filter-name>encodingFilter</filter-name>
		<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
		<init-param>
			<param-name>encoding</param-name>
			<param-value>UTF-8</param-value>
		</init-param>
		<init-param>
			<param-name>forceEncoding</param-name>
			<param-value>true</param-value>
		</init-param>
	</filter>

	<filter-mapping>
		<filter-name>encodingFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>

```

</br> </br> 

&nbsp;  :star: <b> (2) servlet-context.xml (스프링 설정파일) </b>
+ 스프링 설정 파일은 클래스로부터 객체(bean)를 생성하고 조립하는 역할을 한다.
+ DispatcherServlet을 등록할 때 contextConfigLocation 이름으로 초기화 파라미터를 servlet-context.xml로 지정하고 있는데 이때 지정된 servlet-context.xml 파일이 스프링 설정의 역할을 하는 파일이다. 

</br>

![image](https://user-images.githubusercontent.com/86214493/128594208-db3a6e7a-2575-4f0c-9d63-316598328267.png)

</br>

+  ``` <annotation-driven/> ``` </br>
	+ Spring MVC 컴포넌트들을 디폴트 설정을 통해 활성화한다. </br>
	+ @Controller에 요청을 보내기 위해 필요한 HandlerMapping과 HandlerAdapter를 Bean으로 등록한다. </br>

</br>

+ ``` <context:component-scan base-package=""/> ``` </br>
	+ 스캔할 bean들이 모여있는 패키지를 지정한다. </br>

</br>

 + <b> viewResolver </b> </br>
	+ Controller의 메서드에서 반환하는 문자열 앞 뒤에 붙힐 경로 정보를 셋팅한다. 
 ![image](https://user-images.githubusercontent.com/86214493/128594674-029d237e-ab36-4be4-8891-9cccb84d0cc9.png)

``` xml

	<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<beans:property name="prefix" value="/WEB-INF/views/"/>
		<beans:property name="suffix" value=".jsp"/>
	</beans:bean>
	

```

</br>

 + <b> 정적파일 설정
 	+ 정적파일(이미지, 사운드, 동영상, JS, CSS 등등) 경로 셋팅  
 
 ![image](https://user-images.githubusercontent.com/86214493/128594776-8f32fc72-4128-475d-9869-bdcc5f101d23.png)

 </br></br></br>
 
  #### <b> :eyes: Java로 셋팅하기 </b>

</br>

&nbsp;  :star: <b> (1) web.xml 설정 -> 방법 1 : WebApplicationInitializer 인터페이스 구현 </b>
+ onStartUp 메서드를 구현 

</br>

+ String MVC 프로젝트 설정을 위해 작성하는 클래스의 객체를 생성

```java

	AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
	servletAppContext.register(ServletAppContext.class);	

```

</br>

+ 요청 발생 시 요청을 처리하는 서블릿을 DispatcherServlet으로 설정한다. 


```java
	
	DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);
	ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", dispatcherServlet); 
	
	// 부가 설정
	servlet.setLoadOnStartup(1); //가장 먼저 로드하겠다.
	servlet.addMapping("/"); // url 매핑시 '/*'로 지정하면 안된다.
	
```

+ bean을 정의하는 클래스를 지정 (root-context.xml) 

```java

	AnnotationConfigWebApplicationContext rootAppContext = new AnnotationConfigWebApplicationContext();
	rootAppContext.register(RootAppContext.class);

	// 리스터 설정
	ContextLoaderListener listener = new ContextLoaderListener(rootAppContext);
	servletContext.addListener(listener);
	
```

</br>

+ 파라미터 인코딩 설정

```java

	FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
	filter.setInitParameter("encoding", "UTF-8");
	filter.addMappingForServletNames(null, false, "dispatcher");
	
```

</br>
</br>

&nbsp;  :star: <b> (2) web.xml 설정 -> 방법 2 : AbstractAnnotationConfigDispatcherServletInitializer 상속 </b>
+ Spring 3.2부터 추가된 클래스 
+ WebApplicationInitializer 보다 사용하기 쉽지만 이미 구현된 것을 가져다 쓰는 것이기 때문에 새로운 내용을 추가로 수정해서 사용하기가 어려움

```java
	
	
public class SpringConfigClass extends AbstractAnnotationConfigDispatcherServletInitializer{

	//DispatcherServlet에 매핑할 요청 주소를 셋팅한다.
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	//Spring MVC 프로젝트 설정을 위한 클래스를 지정한다. 
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {ServletAppContext.class};
	}
	
	//프로젝트에서 사용할 Bean들을 정의하기 위한 클래스를 지정한다.
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootAppContext.class};
	}
	
	//파라미터 인코딩 필터 설정
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		return new Filter[] {encodingFilter};
	}
	
}


```

</br>
</br>

&nbsp;  :star: <b> (3) servlet-context.xml -> WebMvcConfigurer 인터페이스 구현 </b>

+ @Configuration
	+ 어노테이션기반 환경구성을 돕는다.
	+ 이 어노테이션을 구현함으로써 클래스가 하나 이상의 @Bean 메소드를 제공하고 스프링 컨테이너가 Bean 정의를 생성하고 런타임시 그 Bean들이 요청들을 처리할 것을 선언하게 된다.

+ @EnableWebMvc
	+ @EnableWebMvc 어노테이션을 사용하면 Spring Framework에서 여러 Config 값을 알아서 셋팅해준다.
	
+ @ComponentScan
	+ 스캔할 패키지를 지정 

</br>

```java

//Spring MVC 프로젝트에 관련된 설정을 하는 클래스
@Configuration
@EnableWebMvc // <- Controller 어노테이션이 셋팅되어 있는 클래스를 Controller로 등록한다.
@ComponentScan("chap02.controller") // <- 스캔할 패키지를 지정
public class ServletAppContext implements WebMvcConfigurer{
	
	//Controller의 메서드가 반환하는 jsp의 이름 앞뒤에 경로와 확장자를 붙여주도록 설정한다.
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		WebMvcConfigurer.super.configureViewResolvers(registry);
		registry.jsp("/WEB-INF/views/",".jsp");
	}
	
	//정적파일의 경로를 매핑한다.
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/**").addResourceLocations("/resources/");
	}
	
}

```

</br>
</br>

&nbsp;  :star: <b> (4) root-context.xml -> 상속없음 </b>

```java
	//프로젝트 작업시 사용할 bean을 정의하는 클래스
	@Configuration
	public class RootAppContext {

	}

```

</br>
</br>
</br>

</b>

## :bulb: 03. URL Mapping

&nbsp;  :star: <b> (1) Servlet/JSP URL 주소 </b>
+ <b> 사용자가 서버에 접속해서 서비스를 받기 위해 입력하는 주소를 URL이라고 부른다. </b>
+ <b> URL 주소는 여러 의미를 가지고 있는 값들로 구성된다. </b>
+ <b> 프로토콜://도메인주소(IP):포트번호/경로1/경로2/경로3/... </b> </br>
+ <b> 프로토콜 </b> 
	+ 서버와 클라이언트간의 통신을 위한 약속 (생략시 http) </br>
+ <b> 도메인 주소(IP 주소) </b>
	+ IP 주소는 같은 네트워크 망에서 컴퓨터를 구분하기 위해 제공되는 숫자로 구성된 고유 주소이다. 
	+ 인터넷 망에 연결된 컴퓨터는 전 세계에서 유일한 주소를 할당 받고 공유기 등에 연결된 컴퓨터는 공유기 안에서 유일한 주소를 할당 받는다.
	+ 그러나 숫자는 사람이 외우기 어려워 도메인 주소라는 것을 만들어 제공한다. 
	+ 도메인 주소는 IP 주소로 변환되어 컴퓨터를 찾을 수 있도록 한다. </br>
+ <b> 포트번호 </b>
	+ 1부터 65535번까지로 구성된 숫자로 컴퓨터 내에서 프로그램을 구분하기 위해 사용된다. </br>
	+ 그 이후 경로는 하위 경로가 된다. 
	
	</br>
	
+ <b> ContextPath </b>
	+ 하나의 서버에서 각 웹 어플리케이션을 구분하기 위채 지정되는 이름이며 폴더의 이름이 Context Path가 된다. 
	![image](https://user-images.githubusercontent.com/86214493/128625681-981fe9e2-e497-4703-9037-1de197393bdd.png)
	+ Servlet/JSP에서는 첫번째 경로는 Context Path라고 부른다.
	+ 이클립스에서 프로젝트를 생성하면, 자동으로 server.xml에 추가된다.
	![image](https://user-images.githubusercontent.com/86214493/128625644-f417baf1-b69f-4ba9-8d1b-de3792561db3.png)

</br>

+ <b> SpringMVC에서의 주소 </b>
	+ Spring MVC에서는 Context Path 다음에 나오는 주소는 실제 물리적인 경로와 다르게 지정할 수 있다. 
	
	```java
		
		@RequestMapping("/test1") //ContextPath 다음에 나오는 주소
		public String test1() {
			return "/chap03/test1"; //실제 물리적인 경로
		}
	
	```

</br>
 
+ <b> 공통된 하위경로를 통합하는 것이 가능하다.  </b>

```java
	
	@Controller
	@RequestMapping("/sub2") //공통부분
	public class Sub2Controller {

		@RequestMapping("/test5") /* http://localhost:8080/[ContextPath]/sub2/test5 */
		public String sub2Test5() {
			return "/chap03/sub2/test5";
		}

		@RequestMapping("/test6") /* http://localhost:8080/[ContextPath]/sub2/test6 */
		public String sub2Test6() { 
			return "/chap03/sub2/test6";
		}

	}	
	
```

</br>
</br>
</br>


## :bulb: 04. HTTP 메소드 / 요청 방식
&nbsp;  :star: <b> (1) 요청 방식 지정하기 </b>
+ <b> GET </b> 
	- 요청 받은 URI의 정보를 검색하여 응답한다. 
+ <b> HEAD </b>
	- GET 방식과 동일하지만, 응답에 BODY가 없고 응답코드와 HEAD만 응답한다.
+ <b> POST </b>
	- 요청된 자원을 생성한다. 
+ <b> PUT </b>
	- 요청된 자원을 수정한다. 
+ <b> PATCH </b>
	- 요청된 자원을 수정한다. PUT의 경우 전체를 갱신하지만 Patch는 자원의 일부를 수정할 때 사용한다.
+ <b> DELETE </b>
	- 요청된 자원을 삭제한다. 


&nbsp; <b> (2) 요청 방식 지정하기 </b>
+ Spring MVC는 요청 주소별로 메서드를 정의할 수도 있지만 같은 요청 주소에서 요청 방식에 따라 메서드를 정의할 수도 있다.
+ GET, POST, PUT, DELETE, PATCH에 대해 처리할 수 있다. 


&nbsp; <b> (3) @RequestMapping  </b>
+ RequestMapping 어노테이션은 요청 주소 셋팅 뿐만 아니라 요청 방식도 설정할 수 있다. 

```java

	@RequestMapping(value="/chap04/test1", method= RequestMethod.GET)
	public String test1_get() {
		return "/chap04/test1";
	}
	
	
	@RequestMapping(value="/chap04/test2",method=RequestMethod.POST)
	public String test2_post() {
		return "/chap04/test2";
	}

```

+ Get과 Post를 동시에 설정하는 것도 가능하다. 

```java
	
	@RequestMapping(value="/chap04/test7",method= {RequestMethod.GET, RequestMethod.POST})
	public String test7() {
		return "/chap04/test7";
	}

```

</br>
</br>

&nbsp; <b> (4) @GetMapping / @PostMapping  </b>
+ RequestMapping 대신 요청별로 제공되는 어노테이션을 사용할 수 있다.
+ Spring 4.3 버전에 추가된 내용 

```java
	
	@GetMapping("/chap04/test6")
	public String test6_get() {
		return "/chap04/test6_get";
	}
	
	@PostMapping("/chap04/test6")
	public String test6_post() {
		return "/chap04/test6_post";
	}

```

</br>

+ 동시에 처리하기


```java

	/*
	-> 오류
	@GetMapping("/chap04/test8")
	public String test8_get() {
		return "/chap04/test8";
	}
	
	@GetMapping("/chap04/test8")
	public String test8_post() {
		return "/chap04/test8";
	}
	
	*/
	
	/* ↓ 나중에 파라미터를 주입받을 때 불편할 수 있음 */ 
	@GetMapping("/chap04/test8")
	public String test8_get() {
		return test8_post();
	}
	
	@PostMapping("/chap04/test8")
	public String test8_post() {
		return "/chap04/test8";
	}


```

</br>
</br>
</br>

## :bulb: 05. 파라미터 추출하기

&nbsp; :star: <b> (1) HttpServletRequest 사용 </b>

+ 모든 파라미터는 HttpServletRequest에 담긴다.
+ Spring MVC는 필요한 객체나 데이터를 주입받아 사용할 수 있음 
+ Servlet / JSP 에서는 파라미터 데이터를 추출할 때 HttpServletRequest 객체를 통하게 되는데 Spring MVC 에서는 이 객체를 주입 받아 사용할 수 있음
+ 파라미터 추출 뿐만 아니라 HttpServlertRequest 객체가 필요한 경우 주입받아 사용할 수 있음 

</br>

```html
	<h4><a href="chap05/test1?data1=100&data2=200&data3=300&data3=400">test1</a></h4>
```

```java

	@GetMapping("/chap05/test1")
	public String test1(HttpServletRequest request) {
		
		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");
		String[] data3 = request.getParameterValues("data3");
		
		System.out.println("data1 : " + data1);
		System.out.println("data2 : " + data2);
		
		for(String str : data3) {
			System.out.println("data3 : "+str);
		}
		
		return "/chap05/result";
	}
	

```

<img src="https://user-images.githubusercontent.com/86214493/129195020-2cf91336-7e71-4dde-a181-65877573aad8.png" width="45%" height="45%">


</br>

&nbsp; :star: <b> (2) WebRequest 사용 </b>
+ WebRequest는 HttpServletRequest 클래스를 확장한 클래스이다.

```html	
	<h4><a href="chap05/test2?data1=10&data2=20&data3=30&data3=40">test2</a></h4>
```

```java

	@GetMapping("/chap05/test2")
	public String test2(WebRequest request) {
		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");
		String[] data3 = request.getParameterValues("data3");
		
		System.out.println("data1 : " + data1);
		System.out.println("data2 : " + data2);
		
		for(String str : data3) {
			System.out.println("data3 : "+str);
		}
		return "/chap05/result";
	}
	
```

<img src="https://user-images.githubusercontent.com/86214493/129196309-565a7e96-0b25-4a0d-8d27-21075ff3e679.png" width="45%" height="45%">

</br>

&nbsp; :star: <b> (3) @PathVariable </b>
+ 데이터가 요청 주소에 있을 경우 값을 주입 받을 수 있는 어노테이션
+ Request API 서버 프로그래밍시 많이 사용하는 방식
+ 요청주소/값1/값2/값3
+ 자동형변환 가능 (원래 모든 파라미터는 String 형태로 넘어옴)

```html
	<h4><a href="chap05/test3/1000/2000/3000/4000">test3</a></h4>
```

```java
	
	@GetMapping("chap05/test3/{data1}/{data2}/{data3}/{data4}")
	public String test3(@PathVariable int data1, @PathVariable int data2, 
			    @PathVariable String data3, @PathVariable String data4) {
	
		int sum = data1 + data2;
		
		System.out.println("data1 : "+data1);
		System.out.println("data2 : "+data2);
		System.out.println("sum : " + sum);
		System.out.println("data3 : "+data3);
		System.out.println("data4 : "+data4);
		
		return "/chap05/result";
	}
	
```

<img src="https://user-images.githubusercontent.com/86214493/129197974-d805fb4c-1d60-4ad8-ba97-698b15bcbfe1.png" width="45%" height="45%">


</br>

&nbsp; :star: <b> (4) @RequestParam </b>
+ 파라미터 데이터를 직접 주입받을 수 있음 
+ 지정된 변수의 이름과 파라미터의 이름이 같을 경우 값을 주입받는다.
+ 가능한 경우 형 변환도 처리해준다.

</br>

```html
	<h4><a href="chap05/test4?data1=100&data2=200&data3=300">test4</a></h4>
```

```java
	
	@GetMapping("chap05/test4")
	public String test4(@RequestParam int data1, @RequestParam int data2,
			    @RequestParam int data3) {
			    
		int add = data1 + data2 + data3;
		System.out.println("add : "+add);
		
		return "/chap05/result";
	}
```

<img src="https://user-images.githubusercontent.com/86214493/129199644-6366c622-0506-4455-8acc-95975dae4b71.png" width="45%" height="45%">


</br>

+ <b> value 속성 : </b> 파라미터의 이름과 변수의 이름이 다를 경우 파라미터 이름을 지정한다. 

```html
	<h4><a href="chap05/test4?data1=10&data2=20&data3=30">test5</a></h4>
```

```java
	
	@GetMapping("chap05/test5")
	public String test5(@RequestParam(value="data1") int value1, @RequestParam(value="data2") int value2,
			    @RequestParam(value="data3") int value3) {
		
		int add = value1 + value2 + value3;
		System.out.println("add : "+add);
		
		return "/chap05/result";
	}
	
```

<img src="https://user-images.githubusercontent.com/86214493/129200493-19c344ba-c6df-4db0-9cf1-9fb6977092dc.png" width="45%" height="45%">

+ 만약 넘어오지 않은 데이터를 받는다면 오류가 발생한다.

```html
	<h4><a href="chap05/test6">test6</a></h4>
```

```java
	@GetMapping("chap05/test6")
	public String test6(@RequestParam String data1) {
		
		if(data1 != null) {
			System.out.println("data1");
		}
		
		return "/chap05/result";
	}
```

<img src="https://user-images.githubusercontent.com/86214493/129201440-c5a540a6-f147-4d1b-bc0f-0f021e38935a.png" width="45%" height="45%">

+  <b> required 속성 : false를 설정하면 지정된 이름의 파라미터가 없을 경우 null 주입된다. </b>

```java
	
	@GetMapping("chap05/test6")
	public String test7(@RequestParam(required = false) String data1) {
		
		System.out.println("data1 : "+data1);
		
		return "/chap05/result";
	}
	

```

<img src="https://user-images.githubusercontent.com/86214493/129202306-c798ad93-5550-4c76-b5bc-69d8882b142f.png" width="45%" height="45%">


+  <b> defaultValue 속성 : 넘어온 데이터가 없을 때 기본으로 주입할 데이터를 설정 </b>

```java
	
	@GetMapping("chap05/test6")
	public String test6(@RequestParam(defaultValue = "700") int data1 ) {
		System.out.println("data1 : "+data1);
		return "/chap05/result";
	}
	
```

<img src="https://user-images.githubusercontent.com/86214493/129202910-d591bb1a-ddab-479e-be47-d3c87393a881.png" width="45%" height="45%">

</br>
</br>
</br>

## :bulb: 06. 객체로 파라미터 주입받기

&nbsp; :star: <b> (1) Map으로 주입받기 </b>
+ 클라이언트가 전달하는 모든 파라미터 데이터를 한번에 Map으로 받을 수 있다.
+ <b> 단 동일 명으로 전달되는 2개 이상의 파라미터는 하나만 담기게 된다. </b>

```html
	<h4><a href="chap06/test1?data1=100&data2=200&data3=300&data3=400">test1 get</a></h4>
```

```java
	@GetMapping("/chap06/test1")
	public String test1(@RequestParam Map<String,String> map) {
		String data1 = map.get("data1");
		String data2 = map.get("data2");
		String data3 = map.get("data3");
		
		System.out.println("data1 : "+data1); //100
		System.out.println("data2 : "+data2); //200
		System.out.println("data3 : "+data3); // 300
	
		return "chap06/result";
	}
```

<img src="https://user-images.githubusercontent.com/86214493/129303178-2e5f6f31-f4c3-44a9-aee3-81dc42fc6929.png" width="45%" height="45%">

</br>

+ <b> 동일 명으로 전달되는 파라미터가 2개 이상이라면 List로 주입받아야 한다. </b>

```java
		
	@GetMapping("/chap06/test1")
	public String test1(@RequestParam Map<String,String> map, @RequestParam List<String> data3) {
		String data1 = map.get("data1");
		String data2 = map.get("data2");
		
		System.out.println("data1 : "+data1);
		System.out.println("data2 : "+data2);
		
		for(String str : data3) {
			System.out.println("data3 : "+str);
		}
	
		return "chap06/result";
	}
```

<img src="https://user-images.githubusercontent.com/86214493/129303347-f6a3f4dd-a6c8-45ee-9f18-8870a437e015.png" width="45%" height="45%">


</br>

+ <b> Map과 List 사용시 모든 value는 String 형태로 담기게 된다. (자동 형변환 X) </b>

```java
	
	//↓ 오류 
	@GetMapping("/chap06/test1")
	public String test1(@RequestParam Map<String,Integer> map, @RequestParam List<Integer> data3) {
		
		int data1 = map.get("data1");
		int data2 = map.get("data2");
		
		System.out.println("data1 : "+data1);
		System.out.println("data2 : "+data2);
		
		for(int number : data3) {
			System.out.println("data3 : " + number);
		}
	
		return "chap06/result";
	}

```

<img src="https://user-images.githubusercontent.com/86214493/129303619-e1d63fbc-b6e9-432f-88e6-6dbfd5e682ed.png" width="45%" height="45%">

</br>

&nbsp; :star: <b> (2) @ModelAttribute </b>
+ ModelAttribute 어노테이션을 사용하면 파라미터를 객체로 주입받을 수 있다. 
+ 전달되는 파라미터의 이름과 동일한 프로퍼티에 자동으로 주입된다. ( getter, setter가 존재해야 됨)
+ 이 어노테이션은 생략이 가능함
+ <b> 커맨드 객체 (Command Object) : 클라이언트가 전달해주는 파라미터 데이터를 주입 받기 위해 사용되는 객체</b>
+ 자동으로 형변환이 이루어진다.
+ 같은 파라미터를 여러 곳에서 주입받을 수 있음 

```html
	<h4><a href="chap06/test2?data1=10&data2=20&data3=30&data3=40">test2 get</a></h4>
```

```java 
	
	@GetMapping("/chap06/test2")
	public String test2(@ModelAttribute DataBean bean1, DataBean2 bean2) {
		
		System.out.println("data1 : "+bean1.getData1());
		System.out.println("data2 : "+bean1.getData2());
		
		for(int number : bean1.getData3()) {
			System.out.println("data3 : "+number);
		}
		
		System.out.println("====================================");
		
		System.out.println("bean2.data1 : "+bean2.getData1());
		System.out.println("bean2.data2 : "+bean2.getData2());
		return "chap06/result";
	}

```

<img src="https://user-images.githubusercontent.com/86214493/129304660-62a4c09a-1548-4638-9064-f4d8891aca43.png" width="45%" height="45%">




