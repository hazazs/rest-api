package hu.hazazs.rest.api.entity.mongodb;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Winner {

    @Id
    private String id;
    private String name;
    private int age;

}