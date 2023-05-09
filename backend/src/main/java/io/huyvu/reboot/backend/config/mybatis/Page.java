/**
 * @Author HuyVu
 * @CreatedDate 5/9/2023 11:06 AM
 */
package io.huyvu.reboot.backend.config.mybatis;

import java.util.List;

public interface Page<T> {
    int getTotalCount();

    List<T> getItems();
}
