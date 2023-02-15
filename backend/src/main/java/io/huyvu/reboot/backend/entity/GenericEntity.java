package io.huyvu.reboot.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class GenericEntity {
    private String createdBy;

    private Long createdDate;

    private String modifiedBy;

    private Long modifiedDate;

    private boolean isDeleted;
}