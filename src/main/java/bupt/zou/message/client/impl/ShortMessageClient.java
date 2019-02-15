package bupt.zou.message.client.impl;


import bupt.zou.message.client.BaseClient;
import bupt.zou.message.client.util.ClientUtil;
import bupt.zou.message.dto.MessageDto;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @Description 短信客户端
 *
 * @Author zoujian
 * @Date 2019-01-15
 */
@Slf4j
public class ShortMessageClient implements BaseClient {

    @Override
    public void sendMessage(String ipAddress, int port, MessageDto dto) {
        log.info("short message");
        ClientUtil.sendMessageUtil(ipAddress, port, dto);
    }

}
