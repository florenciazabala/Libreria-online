package com.egg.library.perisitence.repository;

import com.egg.library.domain.CustomerVO;
import com.egg.library.domain.PictureVO;
import com.egg.library.domain.UserVO;
import com.egg.library.domain.repository.CustomerVORepository;
import com.egg.library.perisitence.DAO.ClienteDAO;
import com.egg.library.perisitence.entity.Cliente;
import com.egg.library.perisitence.entity.Foto;
import com.egg.library.perisitence.mapper.CustomerMapper;
import com.egg.library.perisitence.mapper.PictureMapper;
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

    @Autowired
    private PictureMapper pictureMapper;

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
    public void updateFoto(PictureVO pictureVO, Integer id) {
        Foto foto = pictureMapper.toFoto(pictureVO);
        clienteDAO.updateFoto(foto,id);
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
    public Optional<CustomerVO> getByUserMail(String mail) {
        return clienteDAO.findByUserMail(mail).map(cliente -> customerMapper.toCustomerVO(cliente));
    }

    @Override
    public List<CustomerVO> getAllCustomers() {
        return customerMapper.toCustomerVO(clienteDAO.findAll());
    }

    @Override
    public void addFavorite(Integer idCustomer, Long isbn) {
        clienteDAO.addFavorite(idCustomer,isbn);
    }

    @Override
    public void removeFavorite(Integer idCustomer, Long isbn) {
        clienteDAO.removeFavorite(idCustomer,isbn);
    }

    @Override
    public List<Long> findFavoritesBooks(Integer idCustomer) {
        return clienteDAO.getFavoriteBooksByClient(idCustomer);
    }

    @Override
    public Boolean existsRelationFavoriteBook(Integer idCustomer, Long isbn) {
       if(clienteDAO.existsRelation(idCustomer,isbn)>0){
           return true;
       }else {
           return false;
       }
    }
}
