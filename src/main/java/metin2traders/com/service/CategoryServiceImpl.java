package metin2traders.com.service;

import metin2traders.com.domain.Category;
import metin2traders.com.exception.ResourceNotFoundException;
import metin2traders.com.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<Category> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long categoryId, Category categoryDetails) {
        return categoryRepository.findById(categoryId).map(category -> {
            category.setName(categoryDetails.getName());
            return categoryRepository.save(category);
        }).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
    }

    @Override
    public ResponseEntity<?> deleteCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).map(category -> {
            categoryRepository.delete(category);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
    }
}
