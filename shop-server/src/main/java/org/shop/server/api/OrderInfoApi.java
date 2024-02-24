package org.shop.server.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.shop.dto.OrderInfoDto;
import org.shop.dto.ResultData;
import org.shop.server.entity.OrderInfoEntity;
import org.shop.server.service.OrderInfoService;
import org.shop.utils.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/OrderInfoApi")
public class OrderInfoApi implements IOrderInfoApi {

    @Autowired
    private OrderInfoService orderInfoService;

    @Override
    public ResultData<List<OrderInfoDto>> list() {
        List<OrderInfoDto> list = new ArrayList<>();
        List<OrderInfoEntity> list1 = orderInfoService.list();
        for (int i = 0; i < list1.size(); i++) {
            OrderInfoEntity o = list1.get(i);
            OrderInfoDto d = new OrderInfoDto();
            BeanUtil.copyProperties(o,d);
            list.add(d);
        }
        return new ResultData<>(list);
    }

    @Override
    public ResultData save(@RequestBody OrderInfoDto o) {
        OrderInfoEntity d = new OrderInfoEntity();
        BeanUtil.copyProperties(o,d);
        boolean isSave = orderInfoService.save(d);
        if (isSave) {
            return new ResultData(0, "创建成功");
        }
        return new ResultData(1, "创建失败");
    }
    @Override
    public ResultData updateById(@RequestBody OrderInfoDto o){
        OrderInfoEntity d = new OrderInfoEntity();
        BeanUtil.copyProperties(o,d);
        boolean isUpDate = orderInfoService.updateById(d);
        if (isUpDate) {
            return new ResultData<>(0, "更新成功");
        }
        return new ResultData(1, "更新失败");
    }
    @Override
    public ResultData removeById(@RequestParam("id") String id){
        boolean isDel = orderInfoService.removeById(id);
        if (isDel) {
            return new ResultData(0, "删除成功");
        }
        return new ResultData(1, "删除失败");
    }

}
