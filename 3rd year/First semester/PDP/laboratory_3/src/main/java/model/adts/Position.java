package model.adts;

import lombok.Data;

@Data
public class Position {
    private final int line;
    private final int column;
}
