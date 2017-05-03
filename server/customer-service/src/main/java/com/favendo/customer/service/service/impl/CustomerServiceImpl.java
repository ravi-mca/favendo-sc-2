package com.favendo.customer.service.service.impl;

import com.favendo.commons.domain.Customer;
import com.favendo.commons.domain.Role;
import com.favendo.commons.domain.User;
import com.favendo.commons.exception.BusinessException;
import com.favendo.commons.exception.StorecastApiException;
import com.favendo.customer.service.dao.CustomerDao;
import com.favendo.customer.service.service.CustomerService;
import com.favendo.user.service.service.RoleService;
import com.favendo.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static com.favendo.commons.enums.RoleEnum.CUSTOMER;
import static com.favendo.commons.exception.ErrorKey.NO_CONTENT;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    public List<Customer> getAll() throws BusinessException {
        List<Customer> customers = customerDao.findAll();
        if (CollectionUtils.isEmpty(customers)) {
            throw new StorecastApiException(NO_CONTENT);
        }
        return customers;
    }

    @Override
    public Customer getById(Long customerId) {
        return customerDao.findById(customerId);
    }

    @Override
    public Customer getByName(String name) {
        return customerDao.findByName(name);
    }

    @Override
    public Customer getByNameAndCustomerId(String name, Long customerId) {
        return customerDao.findByNameAndCustomerId(name, customerId);
    }

    @Override
    @Transactional
    public void save(Customer customer, User user) {
        customer = customerDao.save(customer);
        setRoles(user);
        user.setCustomer(customer);
        userService.save(user);
    }

    @Override
    @Transactional
    public void update(Customer customer, User user) {
        customerDao.save(customer);
        userService.save(user);
    }

    @Override
    @Transactional
    public void delete(Long customerId) {
        customerDao.delete(customerId);
    }

    private User setRoles(User user) {
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.getByName(CUSTOMER.getRole()));
        user.setRoles(roles);
        return user;
    }
}
