package young.board.redis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import young.board.redis.controller.dto.RedisByTemplateResponseDto;
import young.board.redis.controller.dto.RedisByRepositoryResponseDto;
import young.board.redis.controller.dto.RedisCrudSaveRequestDto;
import young.board.redis.service.RedisCrudService;

import java.util.Arrays;
import java.util.Set;

@RequiredArgsConstructor
@RestController
public class RedisController {
    private final RedisCrudService redisCrudService;
    private final StringRedisTemplate redisTemplate;

    @GetMapping("/")
    public String ok(){
        return "ok~!";
    }

    @GetMapping("/keys")
    public String keys(){
        //Set<String> keys = redisTemplate.opsForZSet().reverseRange("movie",0,5);
        Set<String> keys = redisTemplate.opsForSet().members("redisCrud");
        //SetOperations<String,String> stringValueOperations = redisTemplate.opsForSet();
        //System.out.println(stringValueOperations.members("movie"));
        assert keys != null;
        return Arrays.toString(keys.toArray());
    }

    @PostMapping("/save")
    public Long saveByRedisRepository(@RequestBody RedisCrudSaveRequestDto requestDto){
        return redisCrudService.saveByRedisRepository(requestDto);
    }

    @GetMapping("/get/{id}")
    public RedisByRepositoryResponseDto getByRedisRepository(@PathVariable Long id){
        return redisCrudService.getByRedisRepository(id);
    }

    @PostMapping("/save/template")
    public Boolean saveByRedisTemplate(@RequestParam String name, @RequestParam double count){
        return redisCrudService.saveByRedisTemplate(name,count);
    }

    @GetMapping("/get1To9/template")
    public Set<RedisByTemplateResponseDto> get1To9ByRedisTemplate(){
        return redisCrudService.get1To9ByRedisTemplate();
    }

}