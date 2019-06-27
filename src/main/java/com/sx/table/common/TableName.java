package com.sx.table.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@ToString
@Getter
@Setter
public class TableName {
//    @Column(name="Tables_in_teamcore")
    @Id
    private String Tables_in_teamcore;

}
