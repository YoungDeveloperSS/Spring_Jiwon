
package young.board.redis.controller.dto;

import lombok.Getter;
import young.board.redis.domain.RedisCrudByRepository;

import java.time.LocalDateTime;

@Getter
public class RedisByRepositoryResponseDto {
    private Long id;
    private String description;
    private LocalDateTime updatedAt;

    public RedisByRepositoryResponseDto(RedisCrudByRepository redisHash) {
        this.id = redisHash.getId();
        this.description = redisHash.getDescription();
        this.updatedAt = redisHash.getUpdatedAt();
    }
}
