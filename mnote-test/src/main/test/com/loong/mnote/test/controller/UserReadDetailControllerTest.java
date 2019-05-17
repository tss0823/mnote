package com.loong.mnote.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loong.mnote.test.BaseTest;
import com.loong.mnote.web.controller.UserController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class UserReadDetailControllerTest extends BaseTest {

    @Autowired
    private UserController controller;

    @Test
    public void enterRead() throws Exception {

//        UserReadDetailParam param = new UserReadDetailParam();
//        param.setUserId(1L);
//        param.setArticleId(1L);
        ObjectMapper mapper = new ObjectMapper();
//        String content = mapper.writeValueAsString(param);
        String content = mapper.writeValueAsString(null);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/user/readDetail/enter")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
               .andDo(print());

    }

    @Test
    public void exitRead() {
    }

    @Test
    public void getRewards() {
    }
}