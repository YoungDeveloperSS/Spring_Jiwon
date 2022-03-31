package young.board.redis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import young.board.redis.controller.dto.RedisByTemplateResponseDto;
import young.board.redis.controller.dto.RedisCrudSaveRequestDto;
import young.board.redis.controller.dto.RedisByRepositoryResponseDto;
import young.board.redis.domain.RedisCrudByRepository;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class RedisCrudService {
    //방법1.
    private final young.board.redis.domain.repository.RedisCrudRepository redisCrudRepository;
    //방법2.
    private final StringRedisTemplate redisTemplate;
    private ZSetOperations<String, String> zSetOps;

    @Transactional
    public Long saveByRedisRepository(RedisCrudSaveRequestDto requestDto){
        return redisCrudRepository.save(requestDto.toRedisHash()).getId();
    }

    public RedisByRepositoryResponseDto getByRedisRepository(Long id){
        RedisCrudByRepository redisCrud = redisCrudRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Nothing saved. id=" + id));
        return new RedisByRepositoryResponseDto(redisCrud);
    }


    @PostConstruct
    public void init(){
        zSetOps = redisTemplate.opsForZSet();
    }

    @Transactional
    public Boolean saveByRedisTemplate(String name, double count){
        Boolean aBoolean = zSetOps.add("redisCrudTemplate", name, count);
        return aBoolean;
    }

    public Set<RedisByTemplateResponseDto> get1To9ByRedisTemplate(){
        Set<String> redisCrudTemplate = zSetOps.reverseRange("redisCrudTemplate", 0, 8);
        Set<RedisByTemplateResponseDto> redisByTemplateResponseDtoSet = new HashSet<>();
        Iterator<String> iterator = redisCrudTemplate.stream().iterator();
        int i=1;
        while(iterator.hasNext()){
            redisByTemplateResponseDtoSet.add(RedisByTemplateResponseDto.of(i++,iterator.next()));
        }
        return redisByTemplateResponseDtoSet;
    }

}
