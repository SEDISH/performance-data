package org.openmrs.isanteplus.performancedata.generator.util;

import lombok.Getter;
import org.openmrs.isanteplus.performancedata.model.Entity;

import java.util.List;

public class ChunkKeeper {

    private long amount;

    @Getter
    private long chunkSize;

    @Getter
    private long current;

    @Getter
    private long lastChunkSize;

    public ChunkKeeper(long amount, long size) {
        this.amount = amount;
        this.chunkSize = size;
        this.current = 0;
        this.lastChunkSize = 0;
    }

    public boolean hasNext() {
        return hasFullChunk() || ((amount - current) > 0L);
    }

    public long getChunk() {
        if (hasFullChunk()) {
            current += chunkSize;
            lastChunkSize = chunkSize;
        } else {
            long prevCurrent = current;
            current = amount;
            lastChunkSize = amount - prevCurrent;
        }
        return lastChunkSize;
    }

    public List<Entity> getChunkFromList(List<Entity> entities) {
        int start = (int)(current);
        getChunk();
        int end = (int)(current);

        return entities.subList(start, end);
    }

    private boolean hasFullChunk() {
        return (amount >= (current + chunkSize));
    }
}
