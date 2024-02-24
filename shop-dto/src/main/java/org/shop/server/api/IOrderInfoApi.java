package org.shop.server.api;

import org.shop.dto.OrderInfoDto;
import org.shop.dto.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "shop-server")
@RequestMapping("/OrderInfoApi")
public interface IOrderInfoApi {
    @RequestMapping(value = "list", method = RequestMethod.POST)
    ResultData<List<OrderInfoDto>> list();
    @RequestMapping(value = "save", method = RequestMethod.POST)
    ResultData save(@RequestBody OrderInfoDto o);
    @RequestMapping(value = "updateById", method = RequestMethod.POST)
    ResultData updateById(@RequestBody OrderInfoDto o);
    @RequestMapping(value = "removeById", method = RequestMethod.POST)
    ResultData removeById(@RequestParam("id") String id);

}
