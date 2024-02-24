package org.shop.utils;

import org.shop.dto.OrderInfoDto;
import org.shop.server.entity.OrderInfoEntity;

public class OrderUtil {

    public static OrderInfoEntity getOrderInfoEntity(OrderInfoDto o){
        OrderInfoEntity d = new OrderInfoEntity();
        d.setOrderId(o.getOrderId());
        d.setOrderName(o.getOrderName());
        d.setOrderPrice(o.getOrderPrice());
        d.setOrderNum(o.getOrderNum());
        d.setOrderUser(o.getOrderUser());
        d.setUserName(o.getUserName());
        d.setOrderDate(o.getOrderDate());
        d.setUserPayNum(o.getUserPayNum());
        d.setUserPayDate(o.getUserPayDate());
        d.setUpdateDate(o.getUpdateDate());
        d.setCreateDate(o.getCreateDate());
        d.setUserUpdate(o.getUserUpdate());
        d.setUserCreate(o.getUserCreate());
        return d;
    }

}
