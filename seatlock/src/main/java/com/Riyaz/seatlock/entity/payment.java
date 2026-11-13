package com.Riyaz.seatlock.entity;

import com.Riyaz.seatlock.entity.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class payment {
//continue
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "booking_id", nullable = false, unique = true)
    private Booking booking;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentStatus status;

    @Column(name = "transaction_id", nullable = false, length = 64)
    private String transactionId;   // fake ID from the mock payment, e.g. a UUID

    @Column(nullable = false, updatable = false)
    private LocalDateTime paidAt;

    @PrePersist
    void onCreate() {
        this.paidAt = LocalDateTime.now();
    }
}
