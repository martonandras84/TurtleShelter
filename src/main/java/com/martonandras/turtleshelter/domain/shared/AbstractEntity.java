package com.martonandras.turtleshelter.domain.shared;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * Abstract super class of entities containing audit fields
 *
 * @author Andras Marton (martonandras84@gmail.com)
 */
@Getter
@MappedSuperclass
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {

    @CreationTimestamp
    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;

    @UpdateTimestamp
    @Column(name = "LAST_UPDATE_DATE")
    private LocalDateTime lastUpdateDate;

    @Version
    @Column(name = "VERSION")
    private Long version;
}
