package com.study.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
@Getter
@Setter
@Audited
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "deleted", nullable = false, columnDefinition = "bit default false")
    private boolean deleted = false;

    @CreatedBy
    @Column(name = "created_by", nullable = false, columnDefinition = "varchar(" + 255 + ") default '" + "SYSTEM" +"'", updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_at", updatable = false, columnDefinition = "DATETIME(" + 255 + ") DEFAULT CURRENT_TIMESTAMP")
    @CreatedDate
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private Timestamp updatedAt;

}
