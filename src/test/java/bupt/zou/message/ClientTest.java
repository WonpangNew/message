package bupt.zou.message;

import bupt.zou.message.client.BaseClient;
import bupt.zou.message.client.impl.QQClient;
import bupt.zou.message.client.impl.ShortMessageClient;
import bupt.zou.message.client.impl.WeChatClient;
import bupt.zou.message.dto.MessageDto;
import bupt.zou.message.enums.MessageResources;
import bupt.zou.message.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;


/**
 * @Description client测试类
 * @Author zoujian
 * @Date 2019-01-16
 */
@Slf4j
public class ClientTest {

    private static String IP_ADDRESS = Utils.getStringValue("server.ip.address");
    private static int SERVER_IP_PORT = Utils.getIntValue("server.ip.port");

    @Test
    public void testShortMessageClient() {
        MessageDto dto = new MessageDto();
        dto.setMessageResources(MessageResources.QQ)
                .setMessageEntity("short msg");
        BaseClient client = new ShortMessageClient();
        client.sendMessage(IP_ADDRESS, SERVER_IP_PORT, dto);
    }

    @Test
    public void testQQClient() {
        MessageDto dto = new MessageDto();
        dto.setMessageResources(MessageResources.WECHAT)
                .setMessageEntity("qq msg");
        BaseClient client = new QQClient();
        client.sendMessage(IP_ADDRESS, SERVER_IP_PORT, dto);
    }

    @Test
    public void testWeChatClient() {
        MessageDto dto = new MessageDto();
        dto.setMessageResources(MessageResources.SHORT_MESSAGE)
                .setMessageEntity("vx message");
        BaseClient client = new WeChatClient();
        client.sendMessage(IP_ADDRESS, SERVER_IP_PORT, dto);
    }

}