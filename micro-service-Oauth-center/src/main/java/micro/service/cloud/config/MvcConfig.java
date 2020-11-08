package micro.service.cloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/09/12 16:35
 * @version: V1.0
 */
//@Configuration
//@EnableConfigurationProperties({WebMvcProperties.class})
public class MvcConfig implements WebMvcConfigurer {
    //装载WebMvcProperties 属性
    @Autowired
    WebMvcProperties webMvcProperties;

    @Bean
    public LocaleResolver  localeResolver(){
        MessagesLocalResolver localResolver = new MessagesLocalResolver();
        localResolver.setDefaultLocale(webMvcProperties.getLocale());
        return localResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor()).addPathPatterns("/**");;
    }

    static class MessagesLocalResolver implements LocaleResolver {

        @Nullable
        private Locale defaultLocale;

        public void setDefaultLocale(@Nullable Locale defaultLocale) {
            this.defaultLocale = defaultLocale;
        }

        @Nullable
        public Locale getDefaultLocale() {
            return this.defaultLocale;
        }

        @Override
        public Locale resolveLocale(HttpServletRequest request) {
            Locale defaultLocale = this.getDefaultLocale();
            if(defaultLocale != null && request.getHeader("Accept-Language") == null) {
                return defaultLocale;
            } else {
                Locale requestLocale = request.getLocale();
                String localeFlag = request.getParameter("lang");
                if (!StringUtils.isEmpty(localeFlag)) {
                    String[] split = localeFlag.split("_");
                    requestLocale = new Locale(split[0], split[1]);
                }
                return requestLocale;
            }
        }

        @Override
        public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

        }
    }
}

