package io.huyvu.reboot.security.model;

import java.util.List;

public record AuthRes(String token,
                      String username,
                      String fullName,
                      String pictureUrl,
                      List<String> roles,
                      String resToken) {
}