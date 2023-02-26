/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 1:00 PM
 */
package io.huyvu.reboot.backend.config.security;

/** Context only when user logged
 * @param id user id
 * @param username username
 */
public record UserContextVo(long id, String username) {
}
