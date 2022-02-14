package ru.kosenko.springshoplesson_9.controller.rest;

import org.springframework.web.bind.annotation.*;
import ru.kosenko.springshoplesson_9.dto.Cart;
import ru.kosenko.springshoplesson_9.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartRestController {

  private CartService cartService;

  public CartRestController(CartService cartService) {
    this.cartService = cartService;
  }

  @GetMapping// GET cart
  public Cart getCart() {
    return cartService.getCartForCurrentUser();
  }

  @PostMapping("/product/{id}") // POST cart/product/1
  public Cart addProduct(@PathVariable Long id) {
    return cartService.addProductById(id);
  }

  @DeleteMapping("/product/{id}") // DELETE cart/product/1
  public Cart deleteProduct(@PathVariable Long id) {
    return cartService.removeProductById(id);
  }
}
