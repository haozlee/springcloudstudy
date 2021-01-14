package me.leehao.nacosconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RefreshScope
@RestController
public class ConfigController {
    private static final Logger logger = LoggerFactory.getLogger(ConfigController.class);

    @Value("${user:leo}")
    private String user;

    @RequestMapping(value = "/config", method = RequestMethod.GET)
    public String index() {
        logger.info("get user config value from nacos: {}", user);
        return user;
    }
}
