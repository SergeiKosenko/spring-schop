package ru.kosenko.springshoplesson_9.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kosenko.springshoplesson_9.converter.ProductMapper;
import ru.kosenko.springshoplesson_9.dto.ProductDto;
import ru.kosenko.springshoplesson_9.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {

  private final ProductService productService;
  private final ProductMapper productMapper;

  public ProductController(ProductService productService, ProductMapper productMapper) {
    this.productService = productService;
    this.productMapper = productMapper;
  }

  @GetMapping
  public String getAllStudents(Model model) {
    List<ProductDto> students =  productService.getAll().stream()
        .map(productMapper::productToProductDto).collect(Collectors.toList());
    model.addAttribute("products", students);
    return "product_list";
  }

  @GetMapping("/info/{id}")
  public String getStudentInfo(@PathVariable Long id, Model model) {
    model.addAttribute("product", productMapper.productToProductDto(productService.findById(id).orElse(null)));
    return "product_info";
  }
}
