package me.webapp.web.open.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@RestController
@ResponseBody
public class TestLogLevelController {

    private static final Logger logger = LoggerFactory.getLogger(TestLogLevelController.class);


    @RequestMapping("/testlog")
    public String testLog() {
        logger.debug("Logger Level ：DEBUG");
        logger.info("Logger Level ：INFO");
        logger.error("Logger Level ：ERROR");
        
        return "testlog";
    }
}
