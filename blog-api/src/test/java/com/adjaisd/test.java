package com.adjaisd;

import com.adjaisd.service.ArticleService;
import com.adjaisd.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class test {
    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @Test
    void testQuery(){

    }
}
