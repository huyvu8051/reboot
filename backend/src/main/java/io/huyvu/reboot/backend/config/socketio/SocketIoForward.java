package io.huyvu.reboot.backend.config.socketio;

import io.socket.engineio.server.EngineIoServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class SocketIoForward extends OncePerRequestFilter {

    private final EngineIoServer eioServer;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain)
            throws IOException {
        eioServer.handleRequest(req, resp);
    }

}