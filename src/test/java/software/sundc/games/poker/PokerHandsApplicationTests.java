package software.sundc.games.poker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import software.sundc.games.poker.PokerHandsApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PokerHandsApplication.class)
@WebAppConfiguration
public class PokerHandsApplicationTests {

	@Test
	public void contextLoads() {
	}

}
