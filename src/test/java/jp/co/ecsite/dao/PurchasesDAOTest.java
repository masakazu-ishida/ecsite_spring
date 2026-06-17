package jp.co.ecsite.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.jdbc.Sql;

import jp.co.ecsite.dto.ItemsDTO;
import jp.co.ecsite.dto.PurchasesDTO;
import jp.co.ecsite.dto.PurchasesDetailsDTO;

@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PurchasesDAOTest {

	@Autowired
	private PurchasesDAO purchasesDAO;

	@Sql("/sql/init_data.sql")
	@Test
	void testInsert() {

		PurchasesDTO dto = new PurchasesDTO();

		dto.setPurchasedUser("user1");
		dto.setPurchasedDate(LocalDate.of(2026, 06, 21));
		dto.setDestination("");
		dto.setCancel(false);

		int result = purchasesDAO.insert(dto);

		assertEquals(1, result);
		assertEquals(4, dto.getPurchaseId());

		dto = purchasesDAO.findById(4);

		assertNotNull(dto);
		assertEquals(4, dto.getPurchaseId());
		assertEquals("user1", dto.getPurchasedUser());
		assertEquals(null, dto.getDestination());
		assertEquals(LocalDate.of(2026, 6, 21), dto.getPurchasedDate());
		assertEquals(false, dto.isCancel());

	}

	@Sql("/sql/init_data.sql")
	//@Test
	void testFindById() {

		PurchasesDTO dto = purchasesDAO.findById(1);
		assertNotNull(dto);
		assertEquals(1, dto.getPurchaseId());
		assertEquals("user1", dto.getPurchasedUser());
		assertEquals(null, dto.getDestination());
		assertEquals(LocalDate.of(2026, 6, 15), dto.getPurchasedDate());
		assertEquals(false, dto.isCancel());

		PurchasesDetailsDTO pdDTO = dto.getPurchaseDatails().get(0);

		assertEquals(3, dto.getPurchaseDatails().size());
		assertEquals(1, pdDTO.getPurchaseDetailId());
		assertEquals(4, pdDTO.getAmount());
		assertEquals(7, pdDTO.getItemId());

		ItemsDTO item = pdDTO.getItem();
		assertNotNull(item);
		assertEquals(7, item.getItemId());
		assertEquals("ハンチング帽", item.getItemName());
		assertEquals("日本帽子製造", item.getManufacturer());
		assertEquals("黄色", item.getColor());

		assertEquals(1980, item.getPrice());

	}
}
