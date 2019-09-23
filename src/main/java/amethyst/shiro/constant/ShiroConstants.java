package amethyst.shiro.constant;

import java.io.Serializable;

public interface ShiroConstants {

    //过期时间 单秒是秒
    Long SESSION_TIMEOUT = 60 * 30L;
    //session前辍
    Serializable SHIRO_SESSION_PREFIX = "shiro-session-";
    //所有session的key
    String SHIRO_SESSION_PREFIX_KYES="shiro-session-*";

    //过期时间 单秒是秒
    Long CACHE_TIMEOUT = 60 * 30L;
    //cache前辍
    String SHIRO_CACHE_PREFIX = "shiro-cache-";
    //所有cache的key
    String SHIRO_CACHE_PREFIX_KYES = "shiro-cache-*";
}
