package spring;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.management.MemoryType;

import static org.junit.Assert.*;

public class AppTest {
    AnnotationConfigApplicationContext context;
    final String PROP_STRING = "my string property";

    @Before
    public void setUp() {
        context = new AnnotationConfigApplicationContext(Init.class);
    }

    @Test
    public void testGetBeanNotNull() {
        MyApplication app = context.getBean(MyApplication.class);

        assertNotNull(app);
    }

    @Test
    public void testTwoInstancesMyApplication() {
        MyApplication app = context.getBean(MyApplication.class);
        MyApplication app2 = context.getBean(MyApplication.class);

        assertNotNull(app);
        assertEquals(app, app2);
    }

    @Test
    public void testTwoInstancesAClass() {
        MyApplication app = context.getBean(MyApplication.class);
        A a1 = app.classA;
        MyApplication app2 = context.getBean(MyApplication.class);
        A a2 = app2.classA;

        assertNotNull(a1);
        assertEquals(a1, a2);
    }

    @Test
    public void testQualifier() {
        MyApplication app = context.getBean(MyApplication.class);
        DI d = app.classD;
        DI e = app.classE;

        assertNotNull(d);
        assertNotNull(e);
        assertNotEquals(d, e);
    }

    @Test
    public void testAfieldInBClassNotNull() {
        MyApplication app = context.getBean(MyApplication.class);
        A classAInB = app.classB.aField;

        assertNotNull(classAInB);
    }

    @Test
    public void testAfieldInBClassEqualsWithAClass() {
        MyApplication app = context.getBean(MyApplication.class);
        A classA = app.classA;
        A classAInB = app.classB.aField;

        assertNotNull(classAInB);
        assertEquals(classA, classAInB);
    }

    @Test
    public void testClassFieldClassWithFieldClass() {
        MyApplication app = context.getBean(MyApplication.class);
        C classC = app.classC;
        A classA = app.classA;
        A classAInBInC = classC.bField.aField;

        assertNotNull(classAInBInC);
        assertEquals(classA, classAInBInC);
    }

    @Test
    public void testScope() {
        MyApplication app = context.getBean(MyApplication.class);
        Prototype p = app.prototype;
        C c = app.classC;
        assertNotEquals(p, c.p);
    }

    @Test
    public void testBeanFactoryPostProcessor() {
        MyBeanFactoryPostProcessor m = context.getBean(MyBeanFactoryPostProcessor.class);
        assertEquals("Do something " + PROP_STRING, m.doSomething());
    }

    @Test
    public void testShutdownHook() {
        MyApplication app = context.getBean(MyApplication.class);
        ShutdownHook p = app.shutdownHook;
        context.registerShutdownHook();
        assertTrue(p.destroyMethod());
    }
}
