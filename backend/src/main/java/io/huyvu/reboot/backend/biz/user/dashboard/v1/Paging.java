package io.huyvu.reboot.backend.biz.user.dashboard.v1;

public interface Paging {
  int getLimit();
  int getOffset();
  static Paging of(int page, int size){
      return new Paging() {

          @Override
          public int getLimit() {
              return page * size;
          }

          @Override
          public int getOffset() {
              return size;
          }
      };
  }
}
