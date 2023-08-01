/*
 * package com.itwill.myleaves.repository.cart;
 * 
 * import java.time.LocalDateTime;
 * 
 * import org.springframework.data.annotation.CreatedDate;
 * 
 * import jakarta.persistence.Column; import jakarta.persistence.EmbeddedId;
 * import jakarta.persistence.Entity; import jakarta.persistence.Table; import
 * lombok.AllArgsConstructor; import lombok.Builder; import lombok.Getter;
 * import lombok.NoArgsConstructor; import lombok.ToString;
 * 
 * @NoArgsConstructor
 * 
 * @AllArgsConstructor
 * 
 * @Builder
 * 
 * @Getter
 * 
 * @ToString
 * 
 * @Entity
 * 
 * @Table(name = "CART") public class Cart {
 * 
 * @EmbeddedId private CartId cartId;
 * 
 * @Column(nullable = true) private long cnt;
 * 
 * @CreatedDate private LocalDateTime addDate;
 * 
 * }
 */