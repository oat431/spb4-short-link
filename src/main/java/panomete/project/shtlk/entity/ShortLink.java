package panomete.project.shtlk.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "tb_short_link")
@EntityListeners(AuditingEntityListener.class)
public class ShortLink {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    UUID id;

    @Column(nullable = false, name = "url_original",columnDefinition = "TEXT")
    String originalUrl;

    @Column(nullable = false, unique = true, name = "url_short")
    String shortUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "link_type")
    LinkType type;

    @CreatedDate
    @Column(nullable = false, updatable = false, name = "created_at")
    Timestamp createdAt;
}
