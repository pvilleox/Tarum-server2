package com.tarum.io.content;

import java.util.HashMap;

public class Cache {

    /**
     * Contains the length(size),
     * offset and count of our
     * active byte chunks.
     *
     * 0 = length
     * 1 = offset
     * 2 = byte_chunk_index
     */
    private HashMap<Long, int[]> byteChunkInfo;

}