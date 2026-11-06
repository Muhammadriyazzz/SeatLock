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

}
