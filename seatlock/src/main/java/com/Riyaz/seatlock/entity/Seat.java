package com.Riyaz.seatlock.entity;

import com.Riyaz.seatlock.entity.enums.Seatstatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "seats",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_seat_event_number",
                columnNames = {"event_id", "seat_number"}),
        indexes = @Index(
                name = "idx_seat_status_locked_until",
                columnList = "status, locked_until")
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional=false)
    @JoinColumn(name = "event_id", nullable=false)
    private Event event;

    @Column(name = "seat_number", nullable = false, length = 20)
    private String seat_number;

    @Column(nullable= false,precision =10,scale =2)
    private BigDecimal price;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private Seatstatus status = Seatstatus.AVAILABLE;
// continue after this
// Who locked this seat (null when AVAILABLE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locked_by_user_id")
    private User lockedBy;


    // When the 5-minute lock ends (null when AVAILABLE)
    @Column(name = "lock_expires_at")
    private LocalDateTime lockExpiresAt;


    // The booking this seat belongs to (null when AVAILABLE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    // Optimistic locking: Hibernate increases this number on every update
    @Version
    private Long version;
}


