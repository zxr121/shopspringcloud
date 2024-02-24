package org.shop.api.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.shop.server.api.IOrderInfoApi;
import org.shop.dto.OrderInfoDto;
import org.shop.dto.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/order")
@Api(tags = "订单管理")
public class OrderController {
    @Autowired
    private IOrderInfoApi orderInfoApi;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ApiOperation(value = "返回订单列表")
    public ResultData<List<OrderInfoDto>> readOrderList() {
        String key = "1";
        if (redisTemplate.hasKey(key)) {
            Object o = redisTemplate.opsForValue().get(key);
            if (!StrUtil.isEmptyIfStr(o)) {
                List<OrderInfoDto> list = JSONObject.parseArray(String.valueOf(o), OrderInfoDto.class);
                return new ResultData<>(0, "缓存中读取的数据", list);
            }
        }
        ResultData<List<OrderInfoDto>> res = orderInfoApi.list();
        res.setMessage("数据库中读取的数据");
        List<OrderInfoDto> list = res.getData();
        String l = JSONObject.toJSONString(list);
        redisTemplate.opsForValue().set(key, l,10, TimeUnit.SECONDS);
        return res;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ApiOperation(value = "新增订单")
    public ResultData addOrderInfo(@RequestBody OrderInfoDto order) {
        return orderInfoApi.save(order);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ApiOperation(value = "更新订单")
    public ResultData updateOrderInfo(@RequestBody OrderInfoDto order) {
        return orderInfoApi.updateById(order);
    }

    @RequestMapping(value = "del", method = RequestMethod.POST)
    @ApiOperation(value = "删除订单")
    public ResultData delOrderInfo(String id) {
        return orderInfoApi.removeById(id);
    }
}
