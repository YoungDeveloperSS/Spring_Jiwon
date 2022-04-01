package young.board.redis.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.apache.tomcat.jni.Local;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@ToString
@RedisHash("redisCrud")
public class RedisCrudByRepository {
    @Id
    private Long id;
    private String description;
    private LocalDateTime updatedAt;

    @Builder
    public RedisCrudByRepository(Long id, String description, LocalDateTime updatedAt) {
        this.id = id;
        this.description = description;
        this.updatedAt = updatedAt;
    }

    public void update(String description, LocalDateTime updatedAt){
        if(updatedAt.isAfter(this.updatedAt)){
            this.description = description;
            this.updatedAt = updatedAt;
        }
    }
}
