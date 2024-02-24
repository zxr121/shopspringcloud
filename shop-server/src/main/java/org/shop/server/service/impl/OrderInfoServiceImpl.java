package org.shop.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.shop.server.entity.OrderInfoEntity;
import org.shop.server.mapper.OrderInfoMapper;
import org.shop.server.service.OrderInfoService;
import org.springframework.stereotype.Service;

@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfoEntity> implements OrderInfoService {

}
