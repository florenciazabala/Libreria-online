package com.egg.library.domain.repository;

import com.egg.library.domain.CustomerVO;
import com.egg.library.domain.PictureVO;
import com.egg.library.domain.UserVO;

import java.util.List;
import java.util.Optional;


public interface CustomerVORepository {
    CustomerVO createCustomer(CustomerVO customerVO);
    void updateCustomer(CustomerVO customerVO);
    void updateFoto(PictureVO pictureVO, Integer id);
    Optional<CustomerVO> getById(Integer id);
    Optional<CustomerVO> getByDocument(Long document);
    Optional<CustomerVO> getByUserMail(String mail);
    List<CustomerVO> getAllCustomers();
    void addFavorite(Integer idCustomer,Long isbn);
    void removeFavorite(Integer idCustomer,Long isbn);
    List<Long> findFavoritesBooks(Integer idCustomer);
    Boolean existsRelationFavoriteBook(Integer idCustomer, Long isbn);
}
