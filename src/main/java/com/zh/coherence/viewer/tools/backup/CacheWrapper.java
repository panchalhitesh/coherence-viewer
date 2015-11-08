package com.zh.coherence.viewer.tools.backup;

import java.io.File;

import com.tangosol.net.NamedCache;

public class CacheWrapper {
    public static final String FILE_EXT = ".zhcb";

    public NamedCache cache;
    public File cacheFile;
    public CacheInfo info;

    public CacheWrapper(NamedCache cache, CacheInfo info) {
        this(cache, info, null);
    }

    public CacheWrapper(NamedCache cache, CacheInfo info, File cacheFile) {
        this.cache = cache;
        this.cacheFile = cacheFile;
        this.info = info;
    }
}
