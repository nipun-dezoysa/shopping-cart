package com.naprojects.shoppingcart.service.category;

import com.naprojects.shoppingcart.exceptions.AlreadyExistsException;
import com.naprojects.shoppingcart.exceptions.ResourceNotFoundException;
import com.naprojects.shoppingcart.model.Category;
import com.naprojects.shoppingcart.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category not fount!"));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return Optional.of(category)
                .filter(c-> !categoryRepository.existsByName(category.getName()))
                .map(categoryRepository::save)
                .orElseThrow(()-> new AlreadyExistsException(category.getName()+" already exists"));
    }

    @Override
    public Category updateCategory(Category category,Long id) {
        return Optional.ofNullable(getCategoryById(id))
                .map(old -> {
                    old.setName(category.getName());
                    return categoryRepository.save(old);
                }).orElseThrow(()-> new ResourceNotFoundException("Category not fount!"));
    }

    @Override
    public void deleteCategoryById(Long Id) {
        categoryRepository.findById(Id).ifPresentOrElse(categoryRepository::delete,()->new ResourceNotFoundException("Category not fount!"));
    }
}
