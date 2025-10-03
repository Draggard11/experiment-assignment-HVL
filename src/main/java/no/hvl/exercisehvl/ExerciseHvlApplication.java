package no.hvl.exercisehvl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import redis.clients.jedis.UnifiedJedis;

@SpringBootApplication
public class ExerciseHvlApplication {

	public static void main(String[] args) {
        SpringApplication.run(ExerciseHvlApplication.class, args);
        UnifiedJedis jedis = new UnifiedJedis("redis://localhost:6379");
        // instead of using persitence or h2database
        // we can use jedis hashes to retrieve votes on a poll and increment their votecount

        // for the key it is poll:("its id"):votes
        // for the value it has the id for the options with is votes being the value
        // this way the user can send in the pollid and the optionsid and increment it by an amount
        jedis.hset("poll:id:votes", "optionsid", String.valueOf(10));
        jedis.hgetAll("poll:id:votes");
        jedis.hincrBy("poll:id:votes", "optionsid", 1);

        jedis.close();
	}

    @GetMapping("/")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
}
