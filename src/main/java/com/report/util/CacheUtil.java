package com.report.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Representing header token in local session cookie.
 */
public class CacheUtil {



    public static Cache<String,String> cachedAuthToken = CacheBuilder.newBuilder().maximumSize(10)
                                                                                  .expireAfterWrite(10, TimeUnit.MINUTES)
                                                                                  .build();

    public void clearAll() {
        if(cachedAuthToken != null)
            cachedAuthToken.invalidateAll();
    }
}
