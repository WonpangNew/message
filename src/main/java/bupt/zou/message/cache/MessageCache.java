package bupt.zou.message.cache;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.concurrent.LinkedBlockingQueue;


/**
 *
 * @Description 存放邮件消息的缓存
 *
 * @Author Zou
 * @Date 2019-01-11
 */
@Accessors(chain = true)
public class MessageCache {

    @Getter
    @Setter
    private static LinkedBlockingQueue<String> cache = new LinkedBlockingQueue<>();

}
