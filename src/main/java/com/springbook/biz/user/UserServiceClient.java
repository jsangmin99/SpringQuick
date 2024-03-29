package com.springbook.biz.user;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserServiceClient {
    public static void main(String[] args) {
        //1. 스프일 컨테이너 구동
        AbstractApplicationContext container = new GenericXmlApplicationContext("/src/main/web/WEB-INF/applicationContext.xml");
        //2. 스프링 컨테이너로부터 UserServiceImpl 객체를 Lookup 한다.
        UserService userService = (UserService) container.getBean("userService");
        //3. 로그인기능 테스트
        UserVO vo = new UserVO();
        vo.setId("test");
        vo.setPassword("test123");
        UserVO user = userService.getUser(vo);
        if (user != null){
            System.out.println(user.getName() + "님 환영합니다.");
        }else {
            System.out.println("r그인 실패");
        }
        // 4. 스프링 컨테이너를 종료한다.
        container.close();
    }
}
