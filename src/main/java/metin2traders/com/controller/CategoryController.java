package metin2traders.com.controller;


import metin2traders.com.domain.Category;
import metin2traders.com.resource.SaveCategoryResource;
import metin2traders.com.resource.CategoryResource;
import metin2traders.com.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public Page<CategoryResource> getAllCategories(Pageable pageable) {
        List<CategoryResource> categories = categoryService.getAllCategories(pageable)
                .getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        int categoriesCount = categories.size();
        return new PageImpl<>(categories, pageable, categoriesCount);
    }
    @PostMapping("/categories")
    public CategoryResource createCategory(@Valid @RequestBody SaveCategoryResource resource) {
        return convertToResource(categoryService.createCategory(convertToEntity(resource)));
    }
    @PutMapping("/categories/{id}")
    public CategoryResource updateCategory ( @PathVariable(name = "id") Long categoryId, @Valid @RequestBody SaveCategoryResource resource) {
        return convertToResource(categoryService.updateCategory(categoryId,convertToEntity(resource)));
    }
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> deleteTag (@PathVariable(name = "id") Long categoryId)
    {
        return categoryService.deleteCategory(categoryId);
    }
    private Category convertToEntity(SaveCategoryResource resource) {
        return mapper.map(resource, Category.class);
    }
    private CategoryResource convertToResource(Category entity) {

        return mapper.map(entity, CategoryResource.class);
    }
}
