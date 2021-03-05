package metin2traders.com.service;

import metin2traders.com.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    Page<Category> getAllCategories(Pageable pageable);
    Category createCategory(Category category);
    Category updateCategory(Long categoryId, Category categoryDetails);
    ResponseEntity<?> deleteCategory(Long categoryId);
}
