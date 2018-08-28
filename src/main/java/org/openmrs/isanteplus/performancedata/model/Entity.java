package org.openmrs.isanteplus.performancedata.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class Entity {

    protected long id;
    protected long creator;
    protected LocalDateTime dateCreated;
    protected long voided;
    protected Long voidedBy;
    protected LocalDateTime dateVoided;
    protected String voidReason;
    
    protected final String TABLE_NAME = "entity";
    protected final String ID_COLUMN = "id";
    protected final String CREATOR_COLUMN = "creator";
    protected final String DATE_CREATED_COLUMN = "date_created";
    protected final String VOIDED_COLUMN = "voided";
    protected final String VOIDED_BY_COLUMN = "voided_by";
    protected final String DATE_VOIDED_COLUMN = "date_voided";
    protected final String VOID_REASON_COLUMN = "void_reason";
    protected final String PREPARED_SQL = "insert into " + TABLE_NAME
            + " (" + ID_COLUMN + ", " + CREATOR_COLUMN + ", " + DATE_CREATED_COLUMN + ", " + VOIDED_COLUMN + ", "
            + VOIDED_BY_COLUMN + ", " + DATE_VOIDED_COLUMN + ", " + VOID_REASON_COLUMN + ")"
            + " values (:id, " +
            ":creator, :dateCreated, :voided, :voidedBy, :dateVoided, :voidReason, :changedBy, :dateChanged, :uuid)";

    public final String COUNT_ALIAS = "size";

    private final String SELECT_COUNT = "SELECT COUNT(*) as " + COUNT_ALIAS + " FROM ";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss[.S]"); //2012-01-12 22:38:19.0

    public Entity(long id) {
        this.id = id;
    }

    public Entity(long id, long creator, LocalDateTime dateCreated, long voided, Long voidedBy, LocalDateTime dateVoided,
                  String voidReason) {
        this.id = id;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.voided = voided;
        this.voidedBy = voidedBy;
        this.dateVoided = dateVoided;
        this.voidReason = voidReason;
    }

    public String getSelect(long limit, long offset) {
        return getSelect(ID_COLUMN, TABLE_NAME, limit, offset);
    }
    
    protected String getSelect(String entityIdName, String tableName, long limit, long offset) {
        return "SELECT "+ entityIdName + " as " +
                ID_COLUMN + ", " +
                CREATOR_COLUMN + ", " +
                DATE_CREATED_COLUMN + ", " +
                VOIDED_COLUMN + ", " +
                VOIDED_BY_COLUMN + ", " +
                DATE_VOIDED_COLUMN + ", " +
                VOID_REASON_COLUMN +
                " FROM " + tableName + " LIMIT "
                + limit + " OFFSET " + offset;
    }

    public String getCount() {
        return getCount(TABLE_NAME);
    }

    protected String getCount(String tableName) {
        return SELECT_COUNT + tableName;
    }

    public LocalDateTime parseDateTimeFromDatabase(String dateTime) {
        return dateTime == null ? null : LocalDateTime.parse(dateTime, formatter);
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
