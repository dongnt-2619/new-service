package com.tannv.system.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author nguyen.van.tan (David Black)
 * @project IntelliJ IDEA
 * @created 07/06/2021 - 15:42
 */
@Document(value = "news")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class News {
    @Id
    private String id;
    private String title;
    private String tags;
    private String image;
    private Integer status;
    private String description;
    private String author;
    private Date created_date;
    private Date modified_date;
}
