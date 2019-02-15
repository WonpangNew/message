package bupt.zou.message.client.impl;

import bupt.zou.message.client.BaseClient;
import bupt.zou.message.client.util.ClientUtil;
import bupt.zou.message.dto.MessageDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QQClient implements BaseClient {

    @Override
    public void sendMessage(String ipAddress, int port, MessageDto dto) {
        log.info("send hi message");
        ClientUtil.sendMessageUtil(ipAddress, port, dto);
    }

}
