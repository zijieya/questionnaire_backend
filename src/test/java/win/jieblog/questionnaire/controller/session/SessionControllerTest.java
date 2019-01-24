package win.jieblog.questionnaire.controller.session;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SessionControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate template;
    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void getUserByLogin() throws Exception {
        String content = "{\"password\":\"123456\",\"username\":\"zijieya@jieblog.win\"}";
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/v1/session")
                //请求编码和数据格式为json和UTF8
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                //请求的参数，为json的格式
                .content(content))
                //期望的返回值 或者返回状态码
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                //返回请求的字符串信息
                .andReturn().getResponse().getContentAsString();
    }
    @Test
    public void getUserWithNotExistAccountByLogin() throws Exception {
        String content = "{\"password\":\"123456\",\"username\":\"zijieyaa@jieblog.win\"}";
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/v1/session")
                //请求编码和数据格式为json和UTF8
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                //请求的参数，为json的格式
                .content(content))
                //期望的返回值 或者返回状态码
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                //返回请求的字符串信息
                .andReturn().getResponse().getContentAsString();
    }
    @Test
    public void getUserInfoWithWrongToken() throws Exception {
        String content = "{\"token\":\"ztest\"}";
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/v1/token/user")
                //请求编码和数据格式为json和UTF8
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                //请求的参数，为json的格式
                .content(content))
                //期望的返回值 或者返回状态码
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                //返回请求的字符串信息
                .andReturn().getResponse().getContentAsString();
    }
//    @Test
//    public void getUserInfoWithRightToken() throws Exception {
//        String content = "{\"token\":\"ztest\"}";
//        String result = mockMvc.perform(MockMvcRequestBuilders.post("/v1/token/user")
//                //请求编码和数据格式为json和UTF8
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                //请求的参数，为json的格式
//                .content(content))
//                //期望的返回值 或者返回状态码
//                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
//                //返回请求的字符串信息
//                .andReturn().getResponse().getContentAsString();
//    }
}