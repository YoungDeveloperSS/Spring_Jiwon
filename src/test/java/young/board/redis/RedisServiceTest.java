package young.board.redis;

import org.junit.Test;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisServiceTest {

    @Autowired
    private RedisService redisService;

    @Resource(name = "redisTemplate")
    private ZSetOperations<String, String> zSetOperations;

    private static final String KEY = "player";

    public RedisServiceTest() {
    }

    @Test
    public void add() {
        redisService.add();
        assertTrue(zSetOperations.zCard(KEY) > 9_000_000);
    }

    @Test
    @RepeatedTest(3)
    public void getPlayers() {
        long start = System.currentTimeMillis();
        List<String> players = redisService.getPlayers();
        long end = System.currentTimeMillis();
        System.out.println("프로그램 수행 시간: " + (double)(end - start)/1000);

        //assertTrue(players.size() == 50_000);
    }

}