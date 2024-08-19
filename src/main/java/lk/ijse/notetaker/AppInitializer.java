package lk.ijse.notetaker;

import lk.ijse.notetaker.config.WebAppRootConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {WebAppRootConfig.class};
    }
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {WebAppRootConfig.class};
    }
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
