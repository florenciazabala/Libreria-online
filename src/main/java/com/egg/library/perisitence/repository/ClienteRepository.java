package com.egg.library.perisitence.repository;

import com.egg.library.domain.CustomerVO;
import com.egg.library.domain.repository.CustomerVORepository;
import com.egg.library.perisitence.DAO.ClienteDAO;
import com.egg.library.perisitence.entity.Cliente;
import com.egg.library.perisitence.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepository implements CustomerVORepository {

    @Autowired
    private ClienteDAO clienteDAO;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public CustomerVO createCustomer(CustomerVO customerVO) {
        return customerMapper.toCustomerVO(clienteDAO.save(customerMapper.toCliente(customerVO)));
    }

    @Override
    public void updateCustomer(CustomerVO customerVO) {
        Cliente cliente = customerMapper.toCliente(customerVO);
        clienteDAO.update(cliente.getDocumento(),cliente.getNombre(),cliente.getApellido(),cliente.getMail(),cliente.getTelefono(),
                cliente.getAlta(),cliente.getUsuario(),cliente.getId());
    }

    @Override
    public Optional<CustomerVO> getById(Integer id) {
        return clienteDAO.findById(id).map(cliente -> customerMapper.toCustomerVO(cliente));
    }

    @Override
    public Optional<CustomerVO> getByDocument(Long document) {
        return clienteDAO.findByDocumento(document).map(cliente -> customerMapper.toCustomerVO(cliente));
    }

    @Override
    public List<CustomerVO> getAllCustomers() {
        return customerMapper.toCustomerVO(clienteDAO.findAll());
    }
}
