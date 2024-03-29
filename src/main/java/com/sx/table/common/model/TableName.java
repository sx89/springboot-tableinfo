package com.sx.table.common.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@ToString
@Getter
@Setter
public class TableName {
    //    @Column(name="Tables_in_teamcore")
    @Id
    @Column(name = "Tables_in_teamcore")
    private String tablename;

}
