package bupt.zou.message.cache.util;


import bupt.zou.message.cache.MessageCache;
import bupt.zou.message.dto.MessageDto;

/**
 * @Description 缓存工具类
 * @Author Zou
 * @Date 2019-01-15
 */
public class CacheUtil {

    /**
     * 添加消息dto到缓存中
     * @param dto 消息实体dto
     */
    public static void addMessage2Cache(MessageDto dto) {
        try {
            MessageCache.getCache().put(dto.getMessageEntity());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
