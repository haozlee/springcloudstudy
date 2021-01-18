package me.leehao.nacosconsumer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient("nacos-provider")
public interface ProviderClient {
    @RequestMapping(value = "/echo/{string}", method = RequestMethod.GET)
    String echo(@PathVariable String string);
}
