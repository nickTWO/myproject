package com.hyt.myproject;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.cli.CliDocumentation;
import org.springframework.restdocs.http.HttpDocumentation;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.restdocs.snippet.Snippet;
import org.springframework.restdocs.templates.TemplateFormats;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

/**
 * com.btcpay.wallet
 *
 * @author ayalamih.
 * @date 11:17 AM, 31/01/2018
 * @contact 31007354@qq.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyProjectApplication.class)
@ComponentScan(excludeFilters = {})
public class SpringRestDocApplicationTests {

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/snippets");

    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        //默认生成的文档片段
        Snippet[] defaultSnippets = new Snippet[]{ CliDocumentation.curlRequest(),
                CliDocumentation.httpieRequest(),
                HttpDocumentation.httpRequest(),
                HttpDocumentation.httpResponse()};
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation)
                        //此处也支持生成markdown文档片段，但不能生成html
                        .snippets().withTemplateFormat(TemplateFormats.asciidoctor()).withEncoding("UTF-8")
//                        .withDefaults(defaultSnippets)
                        .and()
                        .uris().withScheme("http").withHost("wallet.isbatestpay.com").withPort(80)
                        .and()
                )
                .build();
    }
}
