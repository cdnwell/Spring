package book;

import static org.assertj.core.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookWebProjectApplicationTests {
	@Autowired
	BookMapper mapper;
	
	@BeforeEach
	void beforeTest() {
		String bno = "8912456712";
		String title = "자바 프로그래밍";
		String writer = "홍길동";
		String publisher = "J테스트";
		String wdate = "2020-02-19";
		BookDTO dto= new BookDTO(bno, title, writer, publisher, wdate);
		
		mapper.insertBook(dto);
	}
	
	@Test
	void contextLoads() {
		String title = "자바";
		List<BookDTO> list = mapper.selectBook(title);
		
		if(list.size()==0)
			fail("검색된 데이터가 없습니다.");
	}
	
	@AfterEach
	void afterTest() {
		String bno = "8912456712";
		mapper.deleteBook(bno);
	}
	
}
