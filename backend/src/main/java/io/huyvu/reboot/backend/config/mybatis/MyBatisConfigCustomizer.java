package io.huyvu.reboot.backend.config.mybatis;

import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;

public class MyBatisConfigCustomizer extends Configuration {
    private final CustomMapperRegistry customMapperRegistry;

    public MyBatisConfigCustomizer(){
        customMapperRegistry = new CustomMapperRegistry(this);
    }

    @Override
    public MapperRegistry getMapperRegistry() {
        return customMapperRegistry;
    }

    @Override
    public void addMappers(String packageName, Class<?> superType) {
        customMapperRegistry.addMappers(packageName, superType);
    }

    @Override
    public void addMappers(String packageName) {
        customMapperRegistry.addMappers(packageName);
    }

    @Override
    public <T> void addMapper(Class<T> type) {
        customMapperRegistry.addMapper(type);
    }

    @Override
    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return customMapperRegistry.getMapper(type, sqlSession);
    }

    @Override
    public boolean hasMapper(Class<?> type) {
        return customMapperRegistry.hasMapper(type);
    }

    public boolean hasStatement(String statementName) {
        return this.hasStatement(statementName, true);
    }

    public boolean hasStatement(String statementName, boolean validateIncompleteStatements) {
        if (validateIncompleteStatements) {
            this.buildAllStatements();
        }

        return this.mappedStatements.containsKey(statementName);
    }
}
