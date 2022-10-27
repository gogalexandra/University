package Model.Types;

import Model.Values.Value;

public interface Type {
    Value defaultValue();
    Type deepCopy();
    boolean equals(Object another);
}
