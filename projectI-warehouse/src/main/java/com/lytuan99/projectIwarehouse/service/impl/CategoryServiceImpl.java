package com.lytuan99.projectIwarehouse.service.impl;

import com.lytuan99.projectIwarehouse.dto.CategoryDTO;
import com.lytuan99.projectIwarehouse.entity.CategoryEntity;
import com.lytuan99.projectIwarehouse.entity.ProductEntity;
import com.lytuan99.projectIwarehouse.exceptions.productException.EntityNotFoundException;
import com.lytuan99.projectIwarehouse.repository.CategoryRepository;
import com.lytuan99.projectIwarehouse.repository.ProductRepository;
import com.lytuan99.projectIwarehouse.service.CategoryService;
import com.lytuan99.projectIwarehouse.util.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<CategoryDTO> getAllCategory() {
        List<CategoryEntity> listCategory = categoryRepository.findAll();
        List<CategoryDTO> listCategoryDTO = new ArrayList<CategoryDTO>();
        for (CategoryEntity categoryEntity : listCategory){
            CategoryDTO categoryDTO = new CategoryDTO(categoryEntity);
            listCategoryDTO.add(categoryDTO);
        }
        return listCategoryDTO;
    }



    @Override
    public void addCategory(CategoryDTO categoryDTO) {
        long dateEpochTime = DateTime.getDateEpochTime();
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategory(categoryDTO.getName(), dateEpochTime);
        categoryRepository.save(categoryEntity);
        categoryDTO.setId(categoryEntity.getId());
    }

    @Override
    public void addProductToCategory(Long idProduct, Long idCategory) {
        CategoryEntity categoryEntity = getCategoryById(idCategory);
        ProductEntity productEntity = getProductById(idProduct);
        productEntity.setCategory(categoryEntity);
        productEntity.setModifiedDate(DateTime.getDateEpochTime());
        productRepository.save(productEntity);
    }

    private CategoryEntity getCategoryById(Long idCategory){
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(idCategory);
        if(categoryEntity.isEmpty())
            throw new EntityNotFoundException(idCategory, "category");
        return categoryEntity.get();
    }

    private ProductEntity getProductById(Long idProduct){
        Optional<ProductEntity> productEntity = productRepository.findById(idProduct);
        if(productEntity.isEmpty())
            throw new EntityNotFoundException(idProduct, "product");
        return productEntity.get();
    }

    @Override
    public void updateCategory(CategoryDTO categoryDTO, Long idCategory) {
        CategoryEntity categoryEntity = getCategoryById(idCategory);
        categoryEntity.setName(categoryDTO.getName());
        categoryEntity.setModifiedDate(DateTime.getDateEpochTime());
        categoryRepository.save(categoryEntity);
    }

    @Override
    public void deleteCategory(Long idCategory) {
        CategoryEntity categoryEntity = getCategoryById(idCategory);
        categoryRepository.delete(categoryEntity);
    }
}
