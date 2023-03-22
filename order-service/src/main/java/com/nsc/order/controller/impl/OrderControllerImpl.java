package com.nsc.order.controller.impl;

import com.nsc.order.configuration.ws.WebApplicationContextLocator;
import com.nsc.order.controller.IOrderController;
import com.nsc.order.enums.response.ResponseCode;
import com.nsc.order.enums.response.ResponseMessage;
import com.nsc.order.model.CreateOrderRequest;
import com.nsc.order.model.CreateOrderResponse;
import com.nsc.order.model.GetOrderListResponse;
import com.nsc.order.model.Order;
import com.nsc.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.web.context.WebApplicationContext;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "com.nsc.order.controller.IOrderController")
public class OrderControllerImpl implements IOrderController {

    @Autowired
    private IOrderService orderService;

    public OrderControllerImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        CreateOrderResponse createOrderResponse;
        try {
            orderService.createOrder(request);
            createOrderResponse = new CreateOrderResponse(ResponseCode.OK.getValue(), ResponseMessage.OK.getValue());
        } catch (Exception ex){
            createOrderResponse = new CreateOrderResponse(ResponseCode.GENERAL_ERROR.getValue(), ResponseMessage.GENERAL_ERROR.getValue());
        }
        return createOrderResponse;
    }

    @Override
    public GetOrderListResponse getOrderList(Long customerId) {
        List<Order> orders = orderService.getCustomerOrders(customerId);
        return new GetOrderListResponse(orders);
    }
}
