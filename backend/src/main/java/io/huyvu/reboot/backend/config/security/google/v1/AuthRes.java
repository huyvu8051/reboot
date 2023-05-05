package io.huyvu.reboot.backend.config.security.google.v1;

import java.util.List;

public record AuthRes(String token,
                      String username,
                      String fullName,
                      String pictureUrl,
                      List<String> roles,
                      String resToken) {
}