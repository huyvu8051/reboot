package io.huyvu.reboot.backend.config.mybatis

import org.apache.ibatis.binding.BindingException
import org.apache.ibatis.binding.MapperProxyFactory
import org.apache.ibatis.binding.MapperRegistry
import org.apache.ibatis.builder.annotation.MapperAnnotationBuilder
import org.apache.ibatis.io.ResolverUtil
import org.apache.ibatis.session.Configuration
import org.apache.ibatis.session.SqlSession

import java.util.*

public class CustomMapperRegistry extends MapperRegistry {
    private final Configuration config
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap()
    public CustomMapperRegistry(Configuration config) {
        super(config)
        this.config = config
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory)this.knownMappers.get(type)
        if (mapperProxyFactory == null) {
            throw new BindingException("Type " + type + " is not known to the MapperRegistry.")
        } else {
            try {
                return mapperProxyFactory.newInstance(sqlSession)
            } catch (Exception var5) {
                throw new BindingException("Error getting mapper instance. Cause: " + var5, var5)
            }
        }
    }

    public <T> boolean hasMapper(Class<T> type) {
        return this.knownMappers.containsKey(type)
    }

    public <T> void addMapper(Class<T> type) {
        if (type.isInterface()) {
            if (this.hasMapper(type)) {
                throw new BindingException("Type " + type + " is already known to the MapperRegistry.")
            }

            boolean loadCompleted = false

            try {
                this.knownMappers.put(type, new MapperProxyFactory(type))
                MapperAnnotationBuilder parser = new CustomMapperAnnotationBuilder(this.config, type)
                parser.parse()
                loadCompleted = true
            } finally {
                if (!loadCompleted) {
                    this.knownMappers.remove(type)
                }

            }
        }

    }

    public Collection<Class<?>> getMappers() {
        return Collections.unmodifiableCollection(this.knownMappers.keySet())
    }

    public void addMappers(String packageName, Class<?> superType) {
        ResolverUtil<Class<?>> resolverUtil = new ResolverUtil()
        resolverUtil.find(new ResolverUtil.IsA(superType), packageName)
        Set<Class<? extends Class<?>>> mapperSet = resolverUtil.getClasses()
        Iterator var5 = mapperSet.iterator()

        while(var5.hasNext()) {
            Class<?> mapperClass = (Class)var5.next()
            this.addMapper(mapperClass)
        }

    }

    public void addMappers(String packageName) {
        this.addMappers(packageName, Object.class)
    }
}
