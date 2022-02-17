package ru.kosenko.springshoplesson_9.converter;


import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import ru.kosenko.springshoplesson_9.dto.CartItem;
import ru.kosenko.springshoplesson_9.dto.ProductDto;
import ru.kosenko.springshoplesson_9.dto.ProductShortDto;
import ru.kosenko.springshoplesson_9.model.Category;
import ru.kosenko.springshoplesson_9.model.Product;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2022-02-12T15:06:08+0500",
        comments = "version: 1.2.0.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl extends ProductMapper {

    @Override
    public Product productShortDtoToProduct(ProductShortDto productShortDto) {
        if ( productShortDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setCategory( categoryTitleToCategory( productShortDto.getCategory() ) );
        product.setTitle( productShortDto.getTitle() );
        product.setPrice( productShortDto.getPrice() );

        return product;
    }

    @Override
    public CartItem productToCartItem(Product product) {
        if ( product == null ) {
            return null;
        }

        CartItem cartItem = new CartItem();

        cartItem.setProductId( product.getId() );
        cartItem.setPricePerOne( product.getPrice() );
        cartItem.setTitle( product.getTitle() );
        cartItem.setPrice( product.getPrice() );

        return cartItem;
    }

    @Override
    public ProductDto productToProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        String title = productCategoryTitle( product );
        if ( title != null ) {
            productDto.setCategory( title );
        }
        productDto.setId( product.getId() );
        productDto.setTitle( product.getTitle() );
        if ( product.getPrice() != null ) {
            productDto.setPrice( String.valueOf( product.getPrice() ) );
        }

        return productDto;
    }

    private String productCategoryTitle(Product product) {
        if ( product == null ) {
            return null;
        }
        Category category = product.getCategory();
        if ( category == null ) {
            return null;
        }
        String title = category.getTitle();
        if ( title == null ) {
            return null;
        }
        return title;
    }
}
