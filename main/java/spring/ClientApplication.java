package spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ClientApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Init.class);
        //MyApplication app = context.getBean(MyApplication.class);

        /*System.out.println(app.processA("Hi"));
        app.processB("Hi");
        System.out.println(app.classD);
        System.out.println(app.classC.bField);*/
    }
}