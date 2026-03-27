package edson.springframework.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edson.springframework.commands.ProductForm;
import edson.springframework.converters.ProductToProductForm;
import edson.springframework.domain.Product;
import edson.springframework.services.ProductService;

import jakarta.validation.Valid;

/**
 * @author edson 16/01/2019
 */
@Controller
public class ProductController {

    private static final Logger log = LogManager.getLogger();

    private ProductService productService;

    private ProductToProductForm productToProductForm;

    @Autowired
    public void setProductToProductForm(ProductToProductForm productToProductForm) {
        this.productToProductForm = productToProductForm;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/")
    public String redirToList() {
        return "redirect:/product/list";
    }

    @RequestMapping({"/product/list", "/product"})
    public String listProducts(Model model) {
        model.addAttribute("products", productService.listAll());
        return "product/list";
    }

    @RequestMapping("/product/show/{id}")
    public String getProduct(@PathVariable String id, Model model) {
        model.addAttribute("product", productService.getById(Long.valueOf(id)));
        return "product/show";
    }

    @RequestMapping("product/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Product product = productService.getById(Long.valueOf(id));
        ProductForm productForm = productToProductForm.convert(product);
        model.addAttribute("productForm", productForm);
        return "product/productform";
    }

    @RequestMapping("/product/new")
    public String newProduct(Model model) {
        model.addAttribute("productForm", new ProductForm());
        return "product/productform";
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String saveOrUpdateProduct(@Valid ProductForm productForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product/productform";
        }
        Product savedProduct = productService.saveOrUpdateProductForm(productForm);
        return "redirect:/product/show/" + savedProduct.getId();
    }

    @RequestMapping("/product/delete/{id}")
    public String delete(@PathVariable String id) {
        productService.delete(Long.valueOf(id));
        return "redirect:/product/list";
    }

    @RequestMapping("/product/sendMessage/{id}")
    public String indexProduct(@PathVariable String id) {
        productService.sendMessage(id);
        return "redirect:/product/show/" + id;
    }

    // ------------------------------------------------------------------------
    // Helper methods for sending product messages from other components
    // ------------------------------------------------------------------------

    /**
     * Trigger a product message without returning any view.
     *
     * @param id the product identifier as String
     */
    public void sendMessage(String id) {
        productService.sendMessage(id);
    }

    /**
     * Trigger a product message without returning any view.
     *
     * @param id the product identifier as Long
     */
    public void sendMessage(Long id) {
        productService.sendMessage(String.valueOf(id));
    }

    /**
     * Trigger a product message optionally performing a redirect.
     *
     * @param id       the product identifier as String
     * @param redirect flag indicating whether a redirect should be performed
     */
    public void sendMessage(String id, boolean redirect) {
        productService.sendMessage(id);
        // Redirect handling, if needed, is performed by the caller.
    }

    /**
     * Trigger a product message optionally performing a redirect.
     *
     * @param id       the product identifier as Long
     * @param redirect flag indicating whether a redirect should be performed
     */
    public void sendMessage(Long id, boolean redirect) {
        productService.sendMessage(String.valueOf(id));
        // Redirect handling, if needed, is performed by the caller.
    }
}