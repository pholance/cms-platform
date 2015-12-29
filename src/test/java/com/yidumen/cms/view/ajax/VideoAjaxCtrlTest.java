package com.yidumen.cms.view.ajax;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @author 蔡迪旻
 *         2015年12月29日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test.xml")
@WebAppConfiguration
@ImportResource("classpath:messages_zh_CN.properties")
public class VideoAjaxCtrlTest extends TestCase {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).addFilter(new CharacterEncodingFilter("utf-8", true), "/api/*").build();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCreate() throws Exception {
        mockMvc.perform(post("/api/video/create").servletPath("/api")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")).andDo(print());
    }
}