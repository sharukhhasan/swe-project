package models.forms;

import models.Product;
import play.data.format.Formats;
import play.data.validation.Constraints;

import java.util.Date;

/**
 * Created by Sharukh on 3/21/16.
 */
public class ProductForm {
    @Constraints.Required
    public String productName;
    @Constraints.Required
    public String productDescription;
    @Constraints.Required
    public int quantity;
    @Constraints.Required
    public String manufacturer;
    @Constraints.Required
    public int price;
    @Formats.DateTime(pattern = "yyyy-MM-dd hh:mm:ss")
    public Date datePosted;

    public Product formToProduct(ProductForm productForm)
    {
        Product product = new Product();

        product.productName = productForm.productName;
        product.productDescription = productForm.productDescription;
        product.quantity = productForm.quantity;
        product.manufacturer = productForm.manufacturer;
        product.price = productForm.price;
        product.datePosted = productForm.datePosted;
        return product;
    }
}
