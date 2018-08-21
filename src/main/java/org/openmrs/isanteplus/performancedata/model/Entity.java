package org.openmrs.isanteplus.performancedata.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Entity {

    protected long id;

    protected final String TABLE_NAME = "entity";
    protected final String ID_COLUMN = "id";
    protected final String PREPARED_SQL = "insert into " + TABLE_NAME
            + " (id)"
            + " values (:id,)";

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
