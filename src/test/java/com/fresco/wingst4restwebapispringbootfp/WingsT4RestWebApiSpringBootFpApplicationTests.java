package com.fresco.wingst4restwebapispringbootfp;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.ListIterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fresco.wingst4restwebapispringbootfp.models.*;
import com.fresco.wingst4restwebapispringbootfp.repo.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestMethodOrder(OrderAnnotation.class)
class WingsT4RestWebApiSpringBootFpApplicationTests {
	@Autowired
	CartRepo cartRepo;
	@Autowired
	CategoryRepo categoryRepo;
	@Autowired
	ProductRepo productRepo;
	@Autowired
	UserRepo userRepo;
	@Autowired
	CartProductRepo cartProductRepo;

	static CartProduct cartProduct;

	@Test
	@Order(1)
	public void dbCategoryDefaultData() throws Exception {
		String[] categories = { "Fashion", "Electronics", "Books", "Groceries", "Medicines" };
		for (ListIterator<?> it = ((JSONArray) new JSONParser()
				.parse(new ObjectMapper().writeValueAsString(categoryRepo.findAll()))).listIterator(); it.hasNext();)
			assertEquals(categories[it.nextIndex()], ((JSONObject) it.next()).get("categoryName").toString());
	}

	@Test
	@Order(2)
	public void dbUserDefaultData() throws Exception {
		String[] users = { "jack", "bob", "apple", "glaxo" };
		for (ListIterator<?> it = ((JSONArray) new JSONParser()
				.parse(new ObjectMapper().writeValueAsString(userRepo.findAll()))).listIterator(); it.hasNext();) {
			assertEquals(users[it.nextIndex()], ((JSONObject) it.next()).get("username").toString());
			assertEquals("pass_word", ((JSONObject) it.next()).get("password").toString());
		}
	}
	
	@Test
	@Order(3)
	public void dbProductDefaultData() throws Exception {
		String[] products = { "apple ipad 10.2 8th gen wifi ios tablet", "crocin pain relief tablet" };
		String[] prices = { "29190", "10" };
		for (ListIterator<?> it = ((JSONArray) new JSONParser()
				.parse(new ObjectMapper().writeValueAsString(productRepo.findAll()))).listIterator(); it.hasNext();) {
			assertEquals(products[it.nextIndex()], ((JSONObject) it.next()).get("productName").toString().toLowerCase());
			assertEquals(prices[it.nextIndex()], String.valueOf(Math.round((Double) ((JSONObject) it.next()).get("price"))));
		}
	}
	
	@Test
	@Order(4)
	public void dbCartDefaultData() throws Exception {
		List<Cart> cart = cartRepo.findAll();
		assertEquals(2, cart.size());
		assertEquals("20.0", cart.get(0).getTotalAmount().toString());
		assertEquals("0.0", cart.get(1).getTotalAmount().toString());
		assertEquals("2", cart.get(0).getCartProducts().get(0).getProduct().getProductId().toString());
		assertEquals("2", cart.get(0).getCartProducts().get(0).getQuantity().toString());
		assertEquals("10.0", cart.get(0).getCartProducts().get(0).getProduct().getPrice().toString());
		assertEquals("crocin pain relief tablet",
				cart.get(0).getCartProducts().get(0).getProduct().getProductName().toString());
		assertEquals("5", cart.get(0).getCartProducts().get(0).getProduct().getCategory().getCategoryId().toString());
		assertEquals("Medicines", cart.get(0).getCartProducts().get(0).getProduct().getCategory().getCategoryName());
		assertEquals(0, cart.get(1).getCartProducts().size());
	}

	@Test
	@Order(5)
	public void updateUser() throws Exception {
		User user = userRepo.findById(1).get();
		assertEquals("jack", user.getUsername());
		user.setUsername("jackie");
		userRepo.save((user));

		user = userRepo.findById(3).get();
		assertEquals("apple", user.getUsername());
		user.setUsername("apple inc");
		userRepo.save(user);
	}

	@Test
	@Order(6)
	public void checkUpdatedUser() throws Exception {
		String[] users = { "jackie", "bob", "apple inc", "glaxo" };
		for (ListIterator<?> it = ((JSONArray) new JSONParser()
				.parse(new ObjectMapper().writeValueAsString(productRepo.findAll()))).listIterator(); it.hasNext();) {
			assertEquals(users[it.nextIndex()], ((JSONObject) it.next()).get("username").toString());
			assertEquals("pass_word", ((JSONObject) it.next()).get("password").toString());
		}
	}
	
	@Test
	@Order(7)
	public void updateProduct() throws Exception {
		Product product = productRepo.findById(1).get();
		assertEquals("apple ipad 10.2 8th gen wifi ios tablet", product.getProductName());
		assertEquals("29190", product.getPrice().toString());

		product.setProductName("apple iphone");
		product.setPrice(100000.0);
		productRepo.save(product);
	}
	
	@Test
	@Order(8)
	public void checkUpdatedProduct() throws Exception {
		String[] products = { "apple iphone", "crocin pain relief tablet" };
		String[] prices = { "100000", "10" };
		for (ListIterator<?> it = ((JSONArray) new JSONParser()
				.parse(new ObjectMapper().writeValueAsString(productRepo.findAll()))).listIterator(); it.hasNext();) {
			assertEquals(products[it.nextIndex()], ((JSONObject) it.next()).get("productName").toString().toLowerCase());
			assertEquals(prices[it.nextIndex()], String.valueOf(Math.round((Double) ((JSONObject) it.next()).get("price"))));
		}	
	}

	@Test
	@Order(9)
	public void compareUserAndCartOwner() throws Exception {
		Cart cart = cartRepo.findById(1).get();
		User user = userRepo.findById(1).get();

		assertEquals(user.getUsername(), cart.getUser().getUsername());
		assert (user.getRoles().toString().contains("CONSUMER"));
		assert (user.getRoles().toString().contains("SELLER"));
	}


	@Test
	@Order(10)
	public void removeProductFromCart() throws Exception {
		Cart cart = cartRepo.findById(1).get();
		assertEquals(1, cart.getCartProducts().size());

		cart.getCartProducts().remove(0);
		cartRepo.save(cart);

		cartProduct = cartProductRepo.findById(1).get();
		cartProductRepo.deleteById(1);
	}

	@Test
	@Order(11)
	public void chaeckProductRemovedFromCart() throws Exception {
		Cart cart = cartRepo.findById(1).get();
		assertEquals(0, cart.getCartProducts().size());
	}

	@Test
	@Order(12)
	public void addCartProduct() throws Exception {
		assertEquals("crocin pain relief tablet", cartProduct.getProduct().getProductName());
	}

	@Test
	@Order(13)
	public void addNewProduct() {
		cartProduct.setProduct(productRepo.findById(1).get());
		cartProductRepo.save(cartProduct);

		List<CartProduct> cartProducts = cartProductRepo.findAll();
		assertEquals(1, cartProducts.size());
	}

	@Test
	@Order(14)
	public void checkUserCart() throws Exception {
		Cart cart = cartRepo.findById(1).get();

		assertEquals("1", cart.getUser().getUserId().toString());
		assertEquals("jackie", cart.getUser().getUsername());
	}

	@Test
	@Order(15)
	public void checkUserCartProduct() throws Exception {
		Cart cart = cartRepo.findById(1).get();

		assertEquals(1, cart.getCartProducts().size());
		assertEquals("2", cart.getCartProducts().get(0).getQuantity().toString());
		assertEquals("1", cart.getCartProducts().get(0).getProduct().getProductId().toString());
		assertEquals("apple iphone", cart.getCartProducts().get(0).getProduct().getProductName());
	}
}
