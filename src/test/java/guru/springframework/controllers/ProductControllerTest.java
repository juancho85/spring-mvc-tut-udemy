package guru.springframework.controllers;

import guru.springframework.domain.DomainObject;
import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * Created by juancho on 19/01/2017.
 */
public class ProductControllerTest {

    @Mock //Mockito mock object
    private ProductService productService;

    private MockMvc mockMvc;

    @InjectMocks //setup the controller, and inject mock objects into it
    private ProductController productController;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this); // initializes controller and mocks
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testShow() throws Exception {
        Integer id = 1;
        when(productService.getById(id)).thenReturn(new Product());

        mockMvc.perform(get("/product/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/show"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));
    }

    @Test
    public void testEdit() throws Exception {
        Integer id = 1;
        when(productService.getById(id)).thenReturn(new Product());

        mockMvc.perform(get("/product/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/productform"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));
    }

    @Test
    public void testNew() throws Exception {
        Integer id = 1;

        mockMvc.perform(get("/product/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/productform"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));

        verifyZeroInteractions(productService);
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        Integer id = 1;
        String description ="My description";
        BigDecimal price = new BigDecimal("12.00");
        String imageUrl = "example.com";

        Product returnProduct = new Product();
        returnProduct.setId(id);
        returnProduct.setDescription(description);
        returnProduct.setPrice(price);
        returnProduct.setImageUrl(imageUrl);

        when(productService.saveOrUpdate(Mockito.<Product>anyObject())).thenReturn(returnProduct);

        mockMvc.perform(post("/product")
        .param("id", "1")
        .param("description", description)
        .param("price", "12.00")
        .param("imageUrl", imageUrl))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/product/show/1"))
                .andExpect(model().attribute("product", instanceOf(Product.class)))
                .andExpect(model().attribute("product", hasProperty("id", is(id))))
                .andExpect(model().attribute("product", hasProperty("description", is(description))))
                .andExpect(model().attribute("product", hasProperty("price", is(price))))
                .andExpect(model().attribute("product", hasProperty("imageUrl", is(imageUrl))));

        //verify properties of bound object
        ArgumentCaptor<Product> boundProduct = ArgumentCaptor.forClass(Product.class);
        verify(productService).saveOrUpdate(boundProduct.capture());

        assertEquals(id, boundProduct.getValue().getId());
        assertEquals(description, boundProduct.getValue().getDescription());
        assertEquals(price, boundProduct.getValue().getPrice());
        assertEquals(imageUrl, boundProduct.getValue().getImageUrl());
    }

    @Test
    public void testDelete() throws Exception{
        Integer id = 1;

        mockMvc.perform(get("/product/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/product/list"));

        verify(productService, times(1)).delete(id);
    }

}