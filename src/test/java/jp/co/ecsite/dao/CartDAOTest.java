package jp.co.ecsite.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.jdbc.Sql;

import jp.co.ecsite.dto.CartDTO;
import jp.co.ecsite.dto.ItemsDTO;

@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CartDAOTest {

	@Autowired
	private CartDAO cartDAO;

	@Sql("/sql/init_data.sql")
	@Test
	void testFindById1() {

		CartDTO cart = cartDAO.findById("user1", 5);

		assertNotNull(cart);
		assertEquals("user1", cart.getUserId());
		assertEquals(5, cart.getItemId());
		assertEquals(LocalDate.of(2026, 6, 1), cart.getBookedDate());

		ItemsDTO item = cart.getItem();
		assertNotNull(item);

		assertEquals(5, item.getItemId());
		assertEquals("野球帽", item.getItemName());
		assertEquals("日本帽子製造", item.getManufacturer());
		assertEquals(2500, item.getPrice());
		assertTrue(item.getRecommended());
		assertEquals("緑色", item.getColor());
		assertEquals(17, item.getStock());
		assertEquals(1, item.getCategoryId());

	}

	@Sql("/sql/init_data.sql")
	@Test
	void testFindById2() {

		CartDTO cart = cartDAO.findById("user1222", 5);

		assertNull(cart);

	}

	@Sql("/sql/init_data.sql")
	@Test
	void testFindByUseId1() {

		List<CartDTO> cartList = cartDAO.findByUserId("user1");

		assertNotNull(cartList);
		assertEquals(3, cartList.size());

		CartDTO cart = cartList.get(0);
		assertEquals(1, cart.getItemId());
		assertEquals(4, cart.getAmount());
		assertEquals(LocalDate.of(2026, 6, 1), cart.getBookedDate());

		cart = cartList.get(1);
		assertEquals(2, cart.getItemId());
		assertEquals(3, cart.getAmount());
		assertEquals(LocalDate.of(2026, 6, 1), cart.getBookedDate());

		cart = cartList.get(2);
		assertEquals(5, cart.getItemId());
		assertEquals(2, cart.getAmount());
		assertEquals(LocalDate.of(2026, 6, 1), cart.getBookedDate());

	}

	@Sql("/sql/init_data.sql")
	@Test
	void testFindByUseId2() {

		List<CartDTO> cartList = cartDAO.findByUserId("user1222");
		assertNotNull(cartList);
		assertEquals(0, cartList.size());

	}

	@Sql("/sql/init_data.sql")
	@Test
	void testInsert() {

		CartDTO cart = new CartDTO();
		cart.setItemId(7);
		cart.setUserId("user1");
		cart.setAmount(4);
		cart.setBookedDate(LocalDate.of(2026, 6, 15));

		int result = cartDAO.insert(cart);
		assertEquals(1, result);

		cart = cartDAO.findById("user1", 7);

		assertNotNull(cart);
		assertEquals("user1", cart.getUserId());
		assertEquals(7, cart.getItemId());
		assertEquals(4, cart.getAmount());
		assertEquals(LocalDate.of(2026, 6, 15), cart.getBookedDate());

	}

	@Sql("/sql/init_data.sql")
	@Test
	void testUpdate() {

		CartDTO cart = cartDAO.findById("user1", 5);

		cart.setAmount(12);
		cart.setBookedDate(LocalDate.of(2026, 7, 23));

		int result = cartDAO.update(cart);
		assertEquals(1, result);

		assertNotNull(cart);
		assertEquals("user1", cart.getUserId());
		assertEquals(5, cart.getItemId());
		assertEquals(12, cart.getAmount());

		assertEquals(LocalDate.of(2026, 7, 23), cart.getBookedDate());

	}

	@Sql("/sql/init_data.sql")
	@Test
	void testDelet() {

		CartDTO cart = cartDAO.findById("user1", 5);

		int result = cartDAO.delete(cart);
		assertEquals(1, result);
		cart = cartDAO.findById("user1", 5);
		assertNull(cart);

	}

}
