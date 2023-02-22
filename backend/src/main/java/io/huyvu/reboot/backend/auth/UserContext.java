/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 1:00 PM
 */
package io.huyvu.reboot.backend.auth;

/** Context only when user logged
 * @param id user id
 * @param username username
 */
public record UserContext(long id, String username) {
}
