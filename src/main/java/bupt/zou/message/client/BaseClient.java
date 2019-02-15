package bupt.zou.message.client;


import bupt.zou.message.dto.MessageDto;

public interface BaseClient {

    void sendMessage(String ipAddress, int port, MessageDto dto);

}
