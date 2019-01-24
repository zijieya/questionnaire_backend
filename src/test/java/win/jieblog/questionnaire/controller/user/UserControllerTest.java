package win.jieblog.questionnaire.controller.user;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import win.jieblog.questionnaire.dao.UserMapper;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    @Autowired
    private UserMapper userMapper;
    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /**
     * 已存在该用户
     */
    @Test
    public void regitserWithExistAccount() throws Exception {
        String content = "{\"email\":\"zijieya@jieblog.win\",\"password\":\"123456\",\"username\":\"zijieya@jieblog.win\"}";
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/v1/user")
                //请求编码和数据格式为json和UTF8
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                //请求的参数，为json的格式
                .content(content))
                //期望的返回值 或者返回状态码
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                //返回请求的字符串信息
                .andReturn().getResponse().getContentAsString();
    }
    /**
     * 不存在该用户
     */
    @Test
    public void regitserWithNotExistAccount() throws Exception {
        String content = "{\"email\":\"zijieyaa@jieblog.win\",\"password\":\"123456\",\"username\":\"zijieyaa@jieblog.win\"}";
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/v1/user")
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
    public void resetPassword() throws Exception {
        String content = "{\"password\":\"123456\",\"userserialid\":\"727ad0d8-f136-43a1-b463-13753428b4cc\"}";
        String result = mockMvc.perform(MockMvcRequestBuilders.put("/v1/user/password")
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
    public void uploadAvatar() {
    }

    /**
     * 清除测试数据
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
            userMapper.deleteByEmail("zijieyaa@jieblog.win");
    }
}