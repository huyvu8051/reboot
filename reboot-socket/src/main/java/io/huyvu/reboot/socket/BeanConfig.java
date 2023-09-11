/**
 * @Author HuyVu
 * @CreatedDate 2/24/2023 1:41 PM
 */
package io.huyvu.reboot.socket;

import io.socket.engineio.server.EngineIoServer;
import io.socket.engineio.server.EngineIoServerOptions;
import io.socket.socketio.server.SocketIoServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class BeanConfig {

    @Bean
    EngineIoServer engineIoServer() {
        EngineIoServerOptions opt = EngineIoServerOptions.newFromDefault();
        opt.setCorsHandlingDisabled(true);
        EngineIoServer eioServer = new EngineIoServer(opt);
        return eioServer;
    }

    @Bean
    SocketIoServer socketIoServer(EngineIoServer eioServer) {
        SocketIoServer sioServer = new SocketIoServer(eioServer);
        return sioServer;
    }

}
