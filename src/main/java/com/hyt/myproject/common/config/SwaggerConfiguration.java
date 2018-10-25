

package com.hyt.myproject.common.config;

import com.google.common.base.Predicates;
import java.util.ArrayList;
import java.util.List;

import com.hyt.myproject.common.dto.PcdToken;
import com.hyt.myproject.jwt.JwtManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@EnableWebMvc
@ConditionalOnExpression("${swagger.enable:false}")
public class SwaggerConfiguration extends WebMvcConfigurerAdapter {
    private static final Logger log = LoggerFactory.getLogger(SwaggerConfiguration.class);
    public static final String SWAGGER_EXCLUDE_PATH = "/swagger-ui.html;/webjars/springfox-swagger-ui/**;/swagger-resources/**;/v2/api-docs/**";
    @Value("${swagger.desc:Swagger RESTful API Description}")
    private String desc;
    @Value("${swagger.header:token@Authorization}")
    private String swaggerHeader;
    @Value("${swagger.token.userid:1}")
    private String swaggerUserId;
    @Value("${swagger.token.expire:7200}")
    private Long swaggerExpireSecond;
    @Value("${swagger.token.secret:}")
    private String swaggerSecretKey;
    @Autowired
    private JwtManager jwtManager;

    public SwaggerConfiguration() {
    }

    @Bean
    public Docket api() {
        return (new Docket(DocumentationType.SWAGGER_2)).apiInfo(this.apiInfo()).select().paths(Predicates.and(PathSelectors.ant("/**"), Predicates.not(PathSelectors.ant("/error")))).build().ignoredParameterTypes(new Class[]{ApiIgnore.class}).globalOperationParameters(this.pars()).enableUrlTemplating(true);
    }

    private ApiInfo apiInfo() {
        return (new ApiInfoBuilder()).title("PayCloudx 接口文档").description(this.desc).contact(new Contact("Chad Wong", "http:/test-url.com", "chadwong2008@gmail.com")).license("Apache 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html").version("1.0.0").build();
    }

    private List<Parameter> pars() {
        List<Parameter> pars = new ArrayList();
        if (StringUtils.isNotBlank(this.swaggerHeader)) {
            String[] var2 = this.swaggerHeader.split(",");
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String header = var2[var4];
                String[] vars = header.split("@");
                if (vars != null && vars.length >= 2) {
                    SwaggerConfiguration.HeadType type = SwaggerConfiguration.HeadType.getFromName(vars[0]);
                    if (type != null) {
                        String value = vars[1];
                        switch(type) {
                            case token:
                                if (StringUtils.isBlank(this.swaggerSecretKey)) {
                                    log.error("SwaggerHeader swaggerSecretKey is null");
                                }

                                String token = "Bearer " + this.jwtManager.createJwtToken((new PcdToken()).uid(this.swaggerUserId).secretKey(this.swaggerSecretKey).subject("{\"puid\":\"" + this.swaggerUserId + "\", \"loginIp\":\"127.0.0.1\"}").startTime(System.currentTimeMillis()).issuer("swagger").loginIp("127.0.0.1").expiredTime(this.swaggerExpireSecond * 1000L));
                                pars.add((new ParameterBuilder()).name(value).defaultValue(token).description("JWT token").modelRef(new ModelRef("string")).parameterType("header").required(false).build());
                                break;
                            case time:
                                pars.add((new ParameterBuilder()).name(value).defaultValue(System.currentTimeMillis() + "").description("timestamp(currentTimeMillis)").modelRef(new ModelRef("string")).parameterType("header").required(false).build());
                                break;
                            case ip:
                                pars.add((new ParameterBuilder()).name(value).defaultValue("127.0.0.1").description("ip").modelRef(new ModelRef("string")).parameterType("header").required(false).build());
                                break;
                            default:
                                log.error("SwaggerHeader type error:{}", type);
                        }
                    }
                }
            }
        }

        return pars;
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(new String[]{"swagger-ui.html"}).addResourceLocations(new String[]{"classpath:/META-INF/resources/"});
        registry.addResourceHandler(new String[]{"/webjars/**"}).addResourceLocations(new String[]{"classpath:/META-INF/resources/webjars/"});
    }

    static enum HeadType {
        token,
        time,
        ip;

        private HeadType() {
        }

        public static SwaggerConfiguration.HeadType getFromName(String name) {
            SwaggerConfiguration.HeadType[] var1 = values();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                SwaggerConfiguration.HeadType type = var1[var3];
                if (type.name().equals(name)) {
                    return type;
                }
            }

            return null;
        }
    }
}
