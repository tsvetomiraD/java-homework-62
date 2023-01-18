package spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.core.convert.Property;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

class ConfigClass implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        GenericBeanDefinition bd = new GenericBeanDefinition();
        bd.setBeanClass(MyBeanFactoryPostProcessor.class);
        FileReader reader= null;
        Properties props = null;
        try {
            reader = new FileReader("props.properties");
            props = new Properties();
            props.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Map.Entry<Object, Object> p : props.entrySet()) {
            bd.getPropertyValues().add((String) p.getKey(), p.getValue());
        }

        ((DefaultListableBeanFactory) beanFactory)
                .registerBeanDefinition("myBeanName", bd);
    }
}
