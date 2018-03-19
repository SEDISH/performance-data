package org.openmrs.isanteplus.performancedata.generator.util;

public class ChunkKeeper {

    private long amount;

    private long chunkSize;

    private long current;

    public ChunkKeeper(long amount, long size) {
        this.amount = amount;
        this.chunkSize = size;
        this.current = 0;
    }

    public boolean hasNext() {
        return hasFullChunk()|| ((amount - current) > 0L);
    }

    public long getChunk() {
        if (hasFullChunk()) {
            current += chunkSize;
            return chunkSize;
        } else {
            long prevCurrent = current;
            current = amount;
            return amount - prevCurrent;
        }
    }

    private boolean hasFullChunk() {
        return (amount >= (current + chunkSize));
    }
}
