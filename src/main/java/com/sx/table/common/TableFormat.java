package com.sx.table.common;


import lombok.ToString;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@ToString
public class TableFormat {

    @Id
    @Column(name = "Field")
    String field;

    @Column(name = "Type")
    String type;

//    @Column(name = "Null")
//    String nullable;

    @Column(name = "Key")
    String key;

//    @Column(name = "Default")
//    String defaulted;

//    @Column(name = "Extra")
//    String extra;

}
