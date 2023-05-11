package io.huyvu.mybatix;

public interface Paging {
    int getLimit();

    int getOffset();

    static Paging unPage(){
        return of(0, Integer.MAX_VALUE);
    }

    static Paging of(int page, int size) {
        return new Paging() {

            @Override
            public int getLimit() {
                return size;
            }

            @Override
            public int getOffset() {
                return page * size;
            }
        };
    }
}
