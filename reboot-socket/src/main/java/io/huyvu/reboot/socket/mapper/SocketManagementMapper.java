package io.huyvu.reboot.socket.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SocketManagementMapper {

    List<String> selectAllJoinedRooms(long uId);

}
