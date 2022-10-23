package ru.otus.spring.spring15.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.spring.spring15.enums.CaterpillarTypeEnum;

@Data
@AllArgsConstructor
public class Butterfly {
    private String name;
    private CaterpillarTypeEnum type;
}
