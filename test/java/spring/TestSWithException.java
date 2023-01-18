package spring;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class TestSWithException {
    AnnotationConfigApplicationContext context;

    @Test
    public void testWithoutAutowiredInMyApp() {
        context = new AnnotationConfigApplicationContext(Init.class);
        MyApplication app = context.getBean(MyApplication.class);

        Exception e = assertThrows(NullPointerException.class, () -> app.processA("Hi"));

        assertTrue(e.getMessage().contains("Cannot invoke \"spring.A.msg(String)\" because \"this.classA\" is null"));
    }

    @Test
    public void testWithoutBean() {
        Exception e = assertThrows(UnsatisfiedDependencyException.class, () -> context = new AnnotationConfigApplicationContext(Init.class));

        assertTrue(e.getMessage().contains("Error creating bean with name 'myApplication': Unsatisfied dependency expressed through field 'classA':"));
    }

    @Test
    public void testWithoutComponentScan() {
        context = new AnnotationConfigApplicationContext(Init.class);

        Exception e = assertThrows(NoSuchBeanDefinitionException.class, () -> context.getBean(MyApplication.class));

        assertTrue(e.getMessage().contains("No qualifying bean of type 'spring.MyApplication' available"));
    }

    @Test
    public void testWithoutComponent() {
        context = new AnnotationConfigApplicationContext(Init.class);

        Exception e = assertThrows(NoSuchBeanDefinitionException.class, () -> context.getBean(MyApplication.class));

        assertTrue(e.getMessage().contains("No qualifying bean of type 'spring.MyApplication' available"));
    }

    @Test
    public void testWithoutQualifierInInit() {
        Exception e = assertThrows(UnsatisfiedDependencyException.class, () -> context = new AnnotationConfigApplicationContext(Init.class));

        assertTrue(e.getMessage().contains("Error creating bean with name 'myApplication': Unsatisfied dependency expressed through field 'classD':"));
    }

    @Test
    public void testWithoutQualifierInMyApp() {
        Exception e = assertThrows(UnsatisfiedDependencyException.class, () -> context = new AnnotationConfigApplicationContext(Init.class));

        assertTrue(e.getMessage().contains("Error creating bean with name 'myApplication': Unsatisfied dependency expressed through field 'classD':"));
    }

    @Test
    public void testWrongFieldTypeClassD() {
        Exception e = assertThrows(UnsatisfiedDependencyException.class, () -> context = new AnnotationConfigApplicationContext(Init.class));

        assertTrue(e.getMessage().contains("Error creating bean with name 'myApplication': Unsatisfied dependency expressed through field 'classD':"));
    }

    @Test
    public void testPassInterfaceToConstructor() {
        Exception e = assertThrows(BeanCreationException.class, () -> context = new AnnotationConfigApplicationContext(Init.class, AI.class));

        assertTrue(e.getMessage().contains("Error creating bean with name 'AI': Failed to instantiate [spring.AI]: Specified class is an interface"));
    }

    @Test
    public void testWithoutAutowiredInClassDeclaration() {
        context = new AnnotationConfigApplicationContext(Init.class);
        MyApplication app = context.getBean(MyApplication.class);

        A classA = app.classB.aField;
        assertNull(classA);
    }

    @Test
    public void testWithoutAutowiredInClassDeclarationCClass() {
        context = new AnnotationConfigApplicationContext(Init.class);
        MyApplication app = context.getBean(MyApplication.class);

        B classB = app.classB;
        B classBInC = app.classC.bField;

        assertNotNull(classB);
        assertNull(classBInC);
    }
}
