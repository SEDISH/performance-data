package org.openmrs.isanteplus.performancedata.generator.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChunkKeeperTest {

    @Test
    public void shouldReturn40Iterations() {
        ChunkKeeper chunkKeeper = new ChunkKeeper(2000, 50);
        int iteration = 0;
        while (chunkKeeper.hasNext()) {
            iteration++;
            chunkKeeper.getChunk();
        }

        assertEquals(40, iteration);
        assertEquals(0, chunkKeeper.getChunk());
    }

    @Test
    public void shouldReturn1619Iterations() {
        ChunkKeeper chunkKeeper = new ChunkKeeper(59874, 37);
        int iteration = 0;
        while (chunkKeeper.hasNext()) {
            iteration++;
            chunkKeeper.getChunk();
        }

        assertEquals(1619, iteration);
        assertEquals(0, chunkKeeper.getChunk());
    }

    @Test
    public void shouldReturnLastChunk8() {
        ChunkKeeper chunkKeeper = new ChunkKeeper(59874, 37);
        int iteration = 0;
        long chunk = 0L;
        while (chunkKeeper.hasNext()) {
            iteration++;
            chunk = chunkKeeper.getChunk();
        }

        assertEquals(1619, iteration);
        assertEquals(0, chunkKeeper.getChunk());
        assertEquals(8, chunk);
    }

    @Test
    public void shouldReturnWhenAmountIsSmallerThanChunkSize() {
        ChunkKeeper chunkKeeper = new ChunkKeeper(312, 400);
        int iteration = 0;
        long chunk = 0L;
        while (chunkKeeper.hasNext()) {
            iteration++;
            chunk = chunkKeeper.getChunk();
        }

        assertEquals(1, iteration);
        assertEquals(0, chunkKeeper.getChunk());
        assertEquals(312, chunk);
    }
}
