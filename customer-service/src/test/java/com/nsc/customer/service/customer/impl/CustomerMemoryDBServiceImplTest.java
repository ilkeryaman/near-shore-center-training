package com.nsc.customer.service.customer.impl;

import com.nsc.customer.enums.address.City;
import com.nsc.customer.enums.cache.CacheKey;
import com.nsc.customer.exception.AddressNotFoundException;
import com.nsc.customer.helper.AddressDataHelper;
import com.nsc.customer.helper.CustomerDataHelper;
import com.nsc.customer.model.customer.Address;
import com.nsc.customer.model.customer.Customer;
import com.nsc.customer.model.messaging.EventMessage;
import com.nsc.customer.service.address.IAddressService;
import com.nsc.customer.service.cache.ICacheService;
import com.nsc.customer.service.messaging.IMessageService;
import com.nsc.customer.testconfiguration.CacheServiceTestConfiguration;
import io.vavr.CheckedFunction0;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Import(CacheServiceTestConfiguration.class)
public class CustomerMemoryDBServiceImplTest {

    @Autowired
    private CustomerMemoryDBServiceImpl customerMemoryDBServiceImpl;

    @MockBean
    private IMessageService messageServiceMock;

    @SpyBean
    ICacheService cacheServiceMock;

    @MockBean
    private IAddressService addressServiceMock;

    private List<Customer> listOfCustomer;
    private List<Address> listOfAddress;

    @Before
    public void initData(){
        listOfAddress = AddressDataHelper.getListOfAddresses();
        listOfCustomer = CustomerDataHelper.getListOfCustomer();
    }

    @Test
    public void getCustomerListTest(){
        List<Customer> listOfCustomerActual = customerMemoryDBServiceImpl.getCustomerList();

        Assert.assertEquals(2, listOfCustomerActual.size());
        Assert.assertEquals(1, listOfCustomerActual.get(0).getId());
        Assert.assertEquals("1001", listOfCustomerActual.get(0).getCustomerNo());
        Assert.assertEquals("Ilker", listOfCustomerActual.get(0).getName());
        Assert.assertEquals("Yaman", listOfCustomerActual.get(0).getSurname());
        Assert.assertNull(listOfCustomerActual.get(0).getSegment());
        Assert.assertNotNull(listOfCustomerActual.get(0).getAddress());
        Assert.assertEquals(City.ISTANBUL.getValue(), listOfCustomerActual.get(0).getAddress().getCity());
        Assert.assertEquals("Avcılar", listOfCustomerActual.get(0).getAddress().getDistrict());

        Assert.assertEquals(2, listOfCustomerActual.get(1).getId());
        Assert.assertEquals("1002", listOfCustomerActual.get(1).getCustomerNo());
        Assert.assertEquals("Sinan", listOfCustomerActual.get(1).getName());
        Assert.assertEquals("Bulubay", listOfCustomerActual.get(1).getSurname());
        Assert.assertNull(listOfCustomerActual.get(1).getSegment());
        Assert.assertNotNull(listOfCustomerActual.get(1).getAddress());
        Assert.assertEquals(City.ANKARA.getValue(), listOfCustomerActual.get(1).getAddress().getCity());
        Assert.assertEquals("Çankaya", listOfCustomerActual.get(1).getAddress().getDistrict());
    }

    @Test
    public void findCustomerByIdTest(){
        Customer customerActual = customerMemoryDBServiceImpl.findCustomerById(1);

        Assert.assertEquals(1, customerActual.getId());
        Assert.assertEquals("1001", customerActual.getCustomerNo());
        Assert.assertEquals("Ilker", customerActual.getName());
        Assert.assertEquals("Yaman", customerActual.getSurname());
        Assert.assertNull(customerActual.getSegment());
        Assert.assertNotNull(customerActual.getAddress());
        Assert.assertEquals(City.ISTANBUL.getValue(), customerActual.getAddress().getCity());
        Assert.assertEquals("Avcılar", customerActual.getAddress().getDistrict());
    }

    @Test
    public void findCustomerByIdWithNullTest(){
        Customer customerActual = customerMemoryDBServiceImpl.findCustomerById(100);
        Assert.assertNull(customerActual);
    }

    @Test
    public void addCustomerTest(){
        Customer customer = listOfCustomer.stream().filter(c -> c.getAddress().getDistrict().equals("Avcılar")).findFirst().get();
        Mockito.doNothing().when(messageServiceMock).sendMessage(Mockito.any(EventMessage.class));
        boolean responseActual = customerMemoryDBServiceImpl.addCustomer(customer);

        Assert.assertTrue(responseActual);
    }

    @Test
    public void addCustomerWithNullCacheTest(){
        Customer customer = listOfCustomer.stream().filter(c -> c.getAddress().getDistrict().equals("Avcılar")).findFirst().get();
        Mockito.when(cacheServiceMock.getCache(CacheKey.ADDRESS_LIST.getValue(), List.class)).thenReturn(null);
        Mockito.when(addressServiceMock.getListOfAddresses()).thenReturn(listOfAddress);
        boolean responseActual = customerMemoryDBServiceImpl.addCustomer(customer);

        Assert.assertTrue(responseActual);
    }

    @Test(expected = AddressNotFoundException.class)
    public void addCustomerWithNullCacheAddressNotFoundTest(){
        Customer customer = listOfCustomer.stream().filter(c -> c.getAddress().getDistrict().equals("Çeşme")).findFirst().get();
        Mockito.when(cacheServiceMock.getCache(CacheKey.ADDRESS_LIST.getValue(), List.class)).thenReturn(null);
        Mockito.when(addressServiceMock.getListOfAddresses()).thenReturn(listOfAddress);
        customerMemoryDBServiceImpl.addCustomer(customer);
    }

    @Test(expected = AddressNotFoundException.class)
    public void addCustomerAddressNotFoundTest(){
        Customer customer = listOfCustomer.stream().filter(c -> c.getAddress().getDistrict().equals("Çeşme")).findFirst().get();
        customerMemoryDBServiceImpl.addCustomer(customer);
    }

    @Test
    public void getCheckedFunctionTest(){
        CheckedFunction0<List<Address>> checkedFunction0 = customerMemoryDBServiceImpl.getCheckedFunction();
        Assert.assertNotNull(checkedFunction0);
    }

    @Test
    public void deleteCustomerFalseTest(){
        boolean responseActual = customerMemoryDBServiceImpl.deleteCustomer(12123);
        Assert.assertFalse(responseActual);
    }

    @Test
    public void deleteCustomerTest(){
        Customer customer = listOfCustomer.stream().filter(c -> c.getId() == 3001).findFirst().get();
        customerMemoryDBServiceImpl.getCustomerList().add(customer);
        boolean responseActual = customerMemoryDBServiceImpl.deleteCustomer(3001);
        Assert.assertTrue(responseActual);

    }

}
