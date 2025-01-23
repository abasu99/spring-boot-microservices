package com.revision.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Accounts extends BaseEntity{

    private Long customerId;
    @Id
    private Long accountNo;
    private String accountType;
    private String branchAddress;
}

