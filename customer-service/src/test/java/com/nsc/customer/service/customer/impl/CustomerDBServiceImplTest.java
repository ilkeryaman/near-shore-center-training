package com.nsc.customer.service.customer.impl;

import com.nsc.customer.exception.CustomerNotFoundException;
import com.nsc.customer.helper.CustomerDataHelper;
import com.nsc.customer.model.customer.Customer;
import com.nsc.customer.model.messaging.EventMessage;
import com.nsc.customer.repository.ICustomerRepository;
import com.nsc.customer.service.messaging.IMessageService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CustomerDBServiceImplTest {

    @InjectMocks
    private CustomerDBServiceImpl customerDBServiceImpl;

    @Mock
    private ICustomerRepository customerRepositoryMock;

    @Mock
    private IMessageService messageServiceMock;

    private List<Customer> listOfCustomer;

    @Before
    public void initData(){
        listOfCustomer = CustomerDataHelper.getListOfCustomer();
    }

    @Test
    public void getCustomerListTest(){
        Mockito.when(customerRepositoryMock.findAll()).thenReturn(listOfCustomer);
        List<Customer> listOfCustomerActual = customerDBServiceImpl.getCustomerList();

        Assert.assertEquals(listOfCustomer.size(), listOfCustomerActual.size());
        Assert.assertEquals(listOfCustomer.get(0).getId(), listOfCustomerActual.get(0).getId());
        Assert.assertEquals(listOfCustomer.get(0).getCustomerNo(), listOfCustomerActual.get(0).getCustomerNo());
        Assert.assertEquals(listOfCustomer.get(0).getName(), listOfCustomerActual.get(0).getName());
        Assert.assertEquals(listOfCustomer.get(0).getSurname(), listOfCustomerActual.get(0).getSurname());
        Assert.assertEquals(listOfCustomer.get(0).getSegment(), listOfCustomerActual.get(0).getSegment());
        Assert.assertNotNull(listOfCustomerActual.get(0).getAddress());
        Assert.assertEquals(listOfCustomer.get(0).getAddress().getCity(), listOfCustomerActual.get(0).getAddress().getCity());
        Assert.assertEquals(listOfCustomer.get(0).getAddress().getDistrict(), listOfCustomerActual.get(0).getAddress().getDistrict());

        Assert.assertEquals(listOfCustomer.get(1).getId(), listOfCustomerActual.get(1).getId());
        Assert.assertEquals(listOfCustomer.get(1).getCustomerNo(), listOfCustomerActual.get(1).getCustomerNo());
        Assert.assertEquals(listOfCustomer.get(1).getName(), listOfCustomerActual.get(1).getName());
        Assert.assertEquals(listOfCustomer.get(1).getSurname(), listOfCustomerActual.get(1).getSurname());
        Assert.assertEquals(listOfCustomer.get(1).getSegment(), listOfCustomerActual.get(1).getSegment());
        Assert.assertNotNull(listOfCustomerActual.get(1).getAddress());
        Assert.assertEquals(listOfCustomer.get(1).getAddress().getCity(), listOfCustomerActual.get(1).getAddress().getCity());
        Assert.assertEquals(listOfCustomer.get(1).getAddress().getDistrict(), listOfCustomerActual.get(1).getAddress().getDistrict());
    }

    @Test
    public void findCustomerByIdTest(){
        Customer customer = listOfCustomer.get(0);
        Mockito.when(customerRepositoryMock.findById(3001L)).thenReturn(customer);
        Customer customerActual = customerDBServiceImpl.findCustomerById(3001);

        Assert.assertEquals(customer.getId(), customerActual.getId());
        Assert.assertEquals(customer.getCustomerNo(), customerActual.getCustomerNo());
        Assert.assertEquals(customer.getName(), customerActual.getName());
        Assert.assertEquals(customer.getSurname(), customerActual.getSurname());
        Assert.assertEquals(customer.getSegment(), customerActual.getSegment());
        Assert.assertNotNull(customerActual.getAddress());
        Assert.assertEquals(customer.getAddress().getCity(), customerActual.getAddress().getCity());
        Assert.assertEquals(customer.getAddress().getDistrict(), customerActual.getAddress().getDistrict());
    }

    @Test(expected = CustomerNotFoundException.class)
    public void findCustomerByIdThrowExceptionTest(){
        long id = 1111111L;
        Mockito.when(customerRepositoryMock.findById(id)).thenThrow(CustomerNotFoundException.class);
        customerDBServiceImpl.findCustomerById(id);
    }

    @Test
    public void findCustomerByIdWithNullTest(){
        Customer customerActual = customerDBServiceImpl.findCustomerById(1);
        Assert.assertNull(customerActual);
    }

    @Test
    public void addCustomerTest(){
        Customer customer = listOfCustomer.get(0);
        Mockito.when(customerRepositoryMock.create(Mockito.any(Customer.class))).thenReturn(customer);
        Mockito.doNothing().when(messageServiceMock).sendMessage(Mockito.any(EventMessage.class));
        boolean responseActual = customerDBServiceImpl.addCustomer(customer);
        Assert.assertTrue(responseActual);
        Mockito.verify(messageServiceMock, Mockito.atLeastOnce()).sendMessage(Mockito.any(EventMessage.class));
    }

    @Test
    public void deleteCustomerTest(){
        Mockito.doNothing().when(customerRepositoryMock).delete(Mockito.anyLong());
        boolean responseActual = customerDBServiceImpl.deleteCustomer(1919);
        Assert.assertTrue(responseActual);
    }
}
