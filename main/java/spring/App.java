package spring;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

interface AI {
    String msg(String msg);
}
class A implements AI {
    public String msg(String msg) {
        return "Message send for A class: " + msg;
    }
}

class B implements AI{
    @Autowired
    public A aField;
    public String msg(String msg) {
        return "Message send for B class: " + msg;
    }
}

class C {
    @Autowired
    B bField;

    @Autowired
    Prototype p;
}

interface DI {
}

class D implements DI {
}

class E implements DI {
}

class Prototype {

}

class MyBeanFactoryPostProcessor {
    private String strProp;

    public void setStrProp (String strProp) {
        this.strProp = strProp;
    }

    public String doSomething () {
       return "Do something " + strProp;
    }
}

class ShutdownHook {
    @PreDestroy
    public boolean destroyMethod(){
        System.out.println("Calling destroyMethod");
        return true;
    }
}