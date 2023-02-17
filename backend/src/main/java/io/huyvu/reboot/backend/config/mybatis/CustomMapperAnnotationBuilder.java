package io.huyvu.reboot.backend.config.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Case;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Property;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.TypeDiscriminator;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.builder.BuilderException;
import org.apache.ibatis.builder.CacheRefResolver;
import org.apache.ibatis.builder.IncompleteElementException;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.builder.annotation.MapperAnnotationBuilder;
import org.apache.ibatis.builder.annotation.MethodResolver;
import org.apache.ibatis.builder.annotation.ProviderSqlSource;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Discriminator;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultFlag;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.mapping.ResultSetType;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.parsing.PropertyParser;
import org.apache.ibatis.reflection.TypeParameterResolver;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.UnknownTypeHandler;

public class CustomMapperAnnotationBuilder extends MapperAnnotationBuilder {
    private static final Set<Class<? extends Annotation>> statementAnnotationTypes = (Set)Stream.of(Select.class, Update.class, Insert.class, Delete.class, SelectProvider.class, UpdateProvider.class, InsertProvider.class, DeleteProvider.class).collect(Collectors.toSet());
    private final Configuration configuration;
    private final MapperBuilderAssistant assistant;
    private final Class<?> type;

    public CustomMapperAnnotationBuilder(Configuration configuration, Class<?> type) {
        super(configuration,type);
        String resource = type.getName().replace('.', '/') + ".java (best guess)";
        this.assistant = new MapperBuilderAssistant(configuration, resource);
        this.configuration = configuration;
        this.type = type;
    }

    public void parse() {
        String resource = this.type.toString();
        if (!this.configuration.isResourceLoaded(resource)) {
            this.loadXmlResource();
            this.configuration.addLoadedResource(resource);
            this.assistant.setCurrentNamespace(this.type.getName());
            this.parseCache();
            this.parseCacheRef();
            Method[] var2 = this.type.getMethods();
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                Method method = var2[var4];
                if (this.canHaveStatement(method)) {
                    if (this.getAnnotationWrapper(method, false, Select.class, SelectProvider.class).isPresent() && method.getAnnotation(ResultMap.class) == null) {
                        this.parseResultMap(method);
                    }

                    try {
                        this.parseStatement(method);
                    } catch (IncompleteElementException var7) {
                        this.configuration.addIncompleteMethod(new MethodResolver(this, method));
                    }
                }
            }
        }

        this.parsePendingMethods();
    }

    private boolean canHaveStatement(Method method) {
        return !method.isBridge() && !method.isDefault();
    }

    private void parsePendingMethods() {
        Collection<MethodResolver> incompleteMethods = this.configuration.getIncompleteMethods();
        synchronized(incompleteMethods) {
            Iterator<MethodResolver> iter = incompleteMethods.iterator();

            while(iter.hasNext()) {
                try {
                    ((MethodResolver)iter.next()).resolve();
                    iter.remove();
                } catch (IncompleteElementException var6) {
                }
            }

        }
    }

    private void loadXmlResource() {
        if (!this.configuration.isResourceLoaded("namespace:" + this.type.getName())) {
            String xmlResource = this.type.getName().replace('.', '/') + ".xml";
            InputStream inputStream = this.type.getResourceAsStream("/" + xmlResource);
            if (inputStream == null) {
                try {
                    inputStream = Resources.getResourceAsStream(this.type.getClassLoader(), xmlResource);
                } catch (IOException var4) {
                }
            }

            if (inputStream != null) {
                XMLMapperBuilder xmlParser = new XMLMapperBuilder(inputStream, this.assistant.getConfiguration(), xmlResource, this.configuration.getSqlFragments(), this.type.getName());
                xmlParser.parse();
            }
        }

    }

    private void parseCache() {
        CacheNamespace cacheDomain = (CacheNamespace)this.type.getAnnotation(CacheNamespace.class);
        if (cacheDomain != null) {
            Integer size = cacheDomain.size() == 0 ? null : cacheDomain.size();
            Long flushInterval = cacheDomain.flushInterval() == 0L ? null : cacheDomain.flushInterval();
            Properties props = this.convertToProperties(cacheDomain.properties());
            this.assistant.useNewCache(cacheDomain.implementation(), cacheDomain.eviction(), flushInterval, size, cacheDomain.readWrite(), cacheDomain.blocking(), props);
        }

    }

    private Properties convertToProperties(Property[] properties) {
        if (properties.length == 0) {
            return null;
        } else {
            Properties props = new Properties();
            Property[] var3 = properties;
            int var4 = properties.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Property property = var3[var5];
                props.setProperty(property.name(), PropertyParser.parse(property.value(), this.configuration.getVariables()));
            }

            return props;
        }
    }

    private void parseCacheRef() {
        CacheNamespaceRef cacheDomainRef = (CacheNamespaceRef)this.type.getAnnotation(CacheNamespaceRef.class);
        if (cacheDomainRef != null) {
            Class<?> refType = cacheDomainRef.value();
            String refName = cacheDomainRef.name();
            if (refType == Void.TYPE && refName.isEmpty()) {
                throw new BuilderException("Should be specified either value() or name() attribute in the @CacheNamespaceRef");
            }

            if (refType != Void.TYPE && !refName.isEmpty()) {
                throw new BuilderException("Cannot use both value() and name() attribute in the @CacheNamespaceRef");
            }

            String namespace = refType != Void.TYPE ? refType.getName() : refName;

            try {
                this.assistant.useCacheRef(namespace);
            } catch (IncompleteElementException var6) {
                this.configuration.addIncompleteCacheRef(new CacheRefResolver(this.assistant, namespace));
            }
        }

    }

    private String parseResultMap(Method method) {
        Class<?> returnType = this.getReturnType(method);
        Arg[] args = (Arg[])method.getAnnotationsByType(Arg.class);
        Result[] results = (Result[])method.getAnnotationsByType(Result.class);
        TypeDiscriminator typeDiscriminator = (TypeDiscriminator)method.getAnnotation(TypeDiscriminator.class);
        String resultMapId = this.generateResultMapName(method);
        this.applyResultMap(resultMapId, returnType, args, results, typeDiscriminator);
        return resultMapId;
    }

    private String generateResultMapName(Method method) {
        Results results = (Results)method.getAnnotation(Results.class);
        if (results != null && !results.id().isEmpty()) {
            return this.type.getName() + "." + results.id();
        } else {
            StringBuilder suffix = new StringBuilder();
            Class[] var4 = method.getParameterTypes();
            int var5 = var4.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                Class<?> c = var4[var6];
                suffix.append("-");
                suffix.append(c.getSimpleName());
            }

            if (suffix.length() < 1) {
                suffix.append("-void");
            }

            return this.type.getName() + "." + method.getName() + suffix;
        }
    }

    private void applyResultMap(String resultMapId, Class<?> returnType, Arg[] args, Result[] results, TypeDiscriminator discriminator) {
        List<ResultMapping> resultMappings = new ArrayList();
        this.applyConstructorArgs(args, returnType, resultMappings);
        this.applyResults(results, returnType, resultMappings);
        Discriminator disc = this.applyDiscriminator(resultMapId, returnType, discriminator);
        this.assistant.addResultMap(resultMapId, returnType, (String)null, disc, resultMappings, (Boolean)null);
        this.createDiscriminatorResultMaps(resultMapId, returnType, discriminator);
    }

    private void createDiscriminatorResultMaps(String resultMapId, Class<?> resultType, TypeDiscriminator discriminator) {
        if (discriminator != null) {
            Case[] var4 = discriminator.cases();
            int var5 = var4.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                Case c = var4[var6];
                String caseResultMapId = resultMapId + "-" + c.value();
                List<ResultMapping> resultMappings = new ArrayList();
                this.applyConstructorArgs(c.constructArgs(), resultType, resultMappings);
                this.applyResults(c.results(), resultType, resultMappings);
                this.assistant.addResultMap(caseResultMapId, c.type(), resultMapId, (Discriminator)null, resultMappings, (Boolean)null);
            }
        }

    }

    private Discriminator applyDiscriminator(String resultMapId, Class<?> resultType, TypeDiscriminator discriminator) {
        if (discriminator == null) {
            return null;
        } else {
            String column = discriminator.column();
            Class<?> javaType = discriminator.javaType() == Void.TYPE ? String.class : discriminator.javaType();
            JdbcType jdbcType = discriminator.jdbcType() == JdbcType.UNDEFINED ? null : discriminator.jdbcType();
            Class<? extends TypeHandler<?>> typeHandler = discriminator.typeHandler() == UnknownTypeHandler.class ? null : (Class<? extends TypeHandler<?>>) discriminator.typeHandler();
            Case[] cases = discriminator.cases();
            Map<String, String> discriminatorMap = new HashMap();
            Case[] var10 = cases;
            int var11 = cases.length;

            for(int var12 = 0; var12 < var11; ++var12) {
                Case c = var10[var12];
                String value = c.value();
                String caseResultMapId = resultMapId + "-" + value;
                discriminatorMap.put(value, caseResultMapId);
            }

            return this.assistant.buildDiscriminator(resultType, column, javaType, jdbcType, typeHandler, discriminatorMap);
        }
    }

    void parseStatement(Method method) {
        Class<?> parameterTypeClass = this.getParameterType(method);
        LanguageDriver languageDriver = this.getLanguageDriver(method);
        this.getAnnotationWrapper(method, true, (Collection)statementAnnotationTypes).ifPresent((stmAnno) -> {
            AnnotationWrapper statementAnnotation = (AnnotationWrapper) stmAnno;
            SqlSource sqlSource = this.buildSqlSource(statementAnnotation.getAnnotation(), parameterTypeClass, languageDriver, method);
            SqlCommandType sqlCommandType = statementAnnotation.getSqlCommandType();
            Options options = (Options)this.getAnnotationWrapper(method, false, Options.class).map((x) -> {
                return (Options)x.getAnnotation();
            }).orElse((Options) null);
            String mappedStatementId = this.type.getName() + "." + method.getName();
            String keyProperty = null;
            String keyColumn = null;
            Object keyGenerator;
            if (!SqlCommandType.INSERT.equals(sqlCommandType) && !SqlCommandType.UPDATE.equals(sqlCommandType)) {
                keyGenerator = NoKeyGenerator.INSTANCE;
            } else {
                SelectKey selectKey = (SelectKey)this.getAnnotationWrapper(method, false, SelectKey.class).map((x) -> {
                    return (SelectKey)x.getAnnotation();
                }).orElse((SelectKey) null);
                if (selectKey != null) {
                    keyGenerator = this.handleSelectKeyAnnotation(selectKey, mappedStatementId, this.getParameterType(method), languageDriver);
                    keyProperty = selectKey.keyProperty();
                } else if (options == null) {
                    keyGenerator = this.configuration.isUseGeneratedKeys() ? Jdbc3KeyGenerator.INSTANCE : NoKeyGenerator.INSTANCE;
                } else {
                    keyGenerator = options.useGeneratedKeys() ? Jdbc3KeyGenerator.INSTANCE : NoKeyGenerator.INSTANCE;
                    keyProperty = options.keyProperty();
                    keyColumn = options.keyColumn();
                }
            }

            Integer fetchSize = null;
            Integer timeout = null;
            StatementType statementType = StatementType.PREPARED;
            ResultSetType resultSetType = this.configuration.getDefaultResultSetType();
            boolean isSelect = sqlCommandType == SqlCommandType.SELECT;
            boolean flushCache = !isSelect;
            boolean useCache = isSelect;
            if (options != null) {
                if (FlushCachePolicy.TRUE.equals(options.flushCache())) {
                    flushCache = true;
                } else if (FlushCachePolicy.FALSE.equals(options.flushCache())) {
                    flushCache = false;
                }

                useCache = options.useCache();
                fetchSize = options.fetchSize() <= -1 && options.fetchSize() != Integer.MIN_VALUE ? null : options.fetchSize();
                timeout = options.timeout() > -1 ? options.timeout() : null;
                statementType = options.statementType();
                if (options.resultSetType() != ResultSetType.DEFAULT) {
                    resultSetType = options.resultSetType();
                }
            }

            String resultMapId = null;
            if (isSelect) {
                ResultMap resultMapAnnotation = (ResultMap)method.getAnnotation(ResultMap.class);
                if (resultMapAnnotation != null) {
                    resultMapId = String.join(",", resultMapAnnotation.value());
                } else {
                    resultMapId = this.generateResultMapName(method);
                }
            }

            this.assistant.addMappedStatement(mappedStatementId, sqlSource, statementType, sqlCommandType, fetchSize, timeout, (String)null, parameterTypeClass, resultMapId, this.getReturnType(method), resultSetType, flushCache, useCache, false, (KeyGenerator)keyGenerator, keyProperty, keyColumn, statementAnnotation.getDatabaseId(), languageDriver, options != null ? this.nullOrEmpty(options.resultSets()) : null);
        });
    }

    private LanguageDriver getLanguageDriver(Method method) {
        Lang lang = (Lang)method.getAnnotation(Lang.class);
        Class<? extends LanguageDriver> langClass = null;
        if (lang != null) {
            langClass = lang.value();
        }

        return this.configuration.getLanguageDriver(langClass);
    }

    private Class<?> getParameterType(Method method) {
        Class<?> parameterType = null;
        Class<?>[] parameterTypes = method.getParameterTypes();
        Class[] var4 = parameterTypes;
        int var5 = parameterTypes.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Class<?> currentParameterType = var4[var6];
            if (!RowBounds.class.isAssignableFrom(currentParameterType) && !ResultHandler.class.isAssignableFrom(currentParameterType)) {
                if (parameterType == null) {
                    parameterType = currentParameterType;
                } else {
                    parameterType = MapperMethod.ParamMap.class;
                }
            }
        }

        return parameterType;
    }

    private Class<?> getReturnType(Method method) {
        Class<?> returnType = method.getReturnType();
        Type resolvedReturnType = TypeParameterResolver.resolveReturnType(method, this.type);
        if (resolvedReturnType instanceof Class) {
            returnType = (Class)resolvedReturnType;
            if (returnType.isArray()) {
                returnType = returnType.getComponentType();
            }

            if (Void.TYPE.equals(returnType)) {
                ResultType rt = (ResultType)method.getAnnotation(ResultType.class);
                if (rt != null) {
                    returnType = rt.value();
                }
            }
        } else if (resolvedReturnType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType)resolvedReturnType;
            Class<?> rawType = (Class)parameterizedType.getRawType();
            Type[] actualTypeArguments;
            Type returnTypeParameter;
            if (!Collection.class.isAssignableFrom(rawType) && !Cursor.class.isAssignableFrom(rawType)) {
                if (method.isAnnotationPresent(MapKey.class) && Map.class.isAssignableFrom(rawType)) {
                    actualTypeArguments = parameterizedType.getActualTypeArguments();
                    if (actualTypeArguments != null && actualTypeArguments.length == 2) {
                        returnTypeParameter = actualTypeArguments[1];
                        if (returnTypeParameter instanceof Class) {
                            returnType = (Class)returnTypeParameter;
                        } else if (returnTypeParameter instanceof ParameterizedType) {
                            returnType = (Class)((ParameterizedType)returnTypeParameter).getRawType();
                        }
                    }
                } else if (Optional.class.equals(rawType)) {
                    actualTypeArguments = parameterizedType.getActualTypeArguments();
                    returnTypeParameter = actualTypeArguments[0];
                    if (returnTypeParameter instanceof Class) {
                        returnType = (Class)returnTypeParameter;
                    }
                }
            } else {
                actualTypeArguments = parameterizedType.getActualTypeArguments();
                if (actualTypeArguments != null && actualTypeArguments.length == 1) {
                    returnTypeParameter = actualTypeArguments[0];
                    if (returnTypeParameter instanceof Class) {
                        returnType = (Class)returnTypeParameter;
                    } else if (returnTypeParameter instanceof ParameterizedType) {
                        returnType = (Class)((ParameterizedType)returnTypeParameter).getRawType();
                    } else if (returnTypeParameter instanceof GenericArrayType) {
                        Class<?> componentType = (Class)((GenericArrayType)returnTypeParameter).getGenericComponentType();
                        returnType = Array.newInstance(componentType, 0).getClass();
                    }
                }
            }
        }

        return returnType;
    }

    private void applyResults(Result[] results, Class<?> resultType, List<ResultMapping> resultMappings) {
        Result[] var4 = results;
        int var5 = results.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Result result = var4[var6];
            List<ResultFlag> flags = new ArrayList();
            if (result.id()) {
                flags.add(ResultFlag.ID);
            }

            Class<? extends TypeHandler<?>> typeHandler = result.typeHandler() == UnknownTypeHandler.class ? null : (Class<? extends TypeHandler<?>>) result.typeHandler();
            boolean hasNestedResultMap = this.hasNestedResultMap(result);
            ResultMapping resultMapping = this.assistant.buildResultMapping(resultType, this.nullOrEmpty(result.property()), this.nullOrEmpty(result.column()), result.javaType() == Void.TYPE ? null : result.javaType(), result.jdbcType() == JdbcType.UNDEFINED ? null : result.jdbcType(), this.hasNestedSelect(result) ? this.nestedSelectId(result) : null, hasNestedResultMap ? this.nestedResultMapId(result) : null, (String)null, hasNestedResultMap ? this.findColumnPrefix(result) : null, typeHandler, flags, (String)null, (String)null, this.isLazy(result));
            resultMappings.add(resultMapping);
        }

    }

    private String findColumnPrefix(Result result) {
        String columnPrefix = result.one().columnPrefix();
        if (columnPrefix.length() < 1) {
            columnPrefix = result.many().columnPrefix();
        }

        return columnPrefix;
    }

    private String nestedResultMapId(Result result) {
        String resultMapId = result.one().resultMap();
        if (resultMapId.length() < 1) {
            resultMapId = result.many().resultMap();
        }

        if (!resultMapId.contains(".")) {
            resultMapId = this.type.getName() + "." + resultMapId;
        }

        return resultMapId;
    }

    private boolean hasNestedResultMap(Result result) {
        if (result.one().resultMap().length() > 0 && result.many().resultMap().length() > 0) {
            throw new BuilderException("Cannot use both @One and @Many annotations in the same @Result");
        } else {
            return result.one().resultMap().length() > 0 || result.many().resultMap().length() > 0;
        }
    }

    private String nestedSelectId(Result result) {
        String nestedSelect = result.one().select();
        if (nestedSelect.length() < 1) {
            nestedSelect = result.many().select();
        }

        if (!nestedSelect.contains(".")) {
            nestedSelect = this.type.getName() + "." + nestedSelect;
        }

        return nestedSelect;
    }

    private boolean isLazy(Result result) {
        boolean isLazy = this.configuration.isLazyLoadingEnabled();
        if (result.one().select().length() > 0 && FetchType.DEFAULT != result.one().fetchType()) {
            isLazy = result.one().fetchType() == FetchType.LAZY;
        } else if (result.many().select().length() > 0 && FetchType.DEFAULT != result.many().fetchType()) {
            isLazy = result.many().fetchType() == FetchType.LAZY;
        }

        return isLazy;
    }

    private boolean hasNestedSelect(Result result) {
        if (result.one().select().length() > 0 && result.many().select().length() > 0) {
            throw new BuilderException("Cannot use both @One and @Many annotations in the same @Result");
        } else {
            return result.one().select().length() > 0 || result.many().select().length() > 0;
        }
    }

    private void applyConstructorArgs(Arg[] args, Class<?> resultType, List<ResultMapping> resultMappings) {
        Arg[] var4 = args;
        int var5 = args.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Arg arg = var4[var6];
            List<ResultFlag> flags = new ArrayList();
            flags.add(ResultFlag.CONSTRUCTOR);
            if (arg.id()) {
                flags.add(ResultFlag.ID);
            }

            Class<? extends TypeHandler<?>> typeHandler = arg.typeHandler() == UnknownTypeHandler.class ? null : (Class<? extends TypeHandler<?>>) arg.typeHandler();
            ResultMapping resultMapping = this.assistant.buildResultMapping(resultType, this.nullOrEmpty(arg.name()), this.nullOrEmpty(arg.column()), arg.javaType() == Void.TYPE ? null : arg.javaType(), arg.jdbcType() == JdbcType.UNDEFINED ? null : arg.jdbcType(), this.nullOrEmpty(arg.select()), this.nullOrEmpty(arg.resultMap()), (String)null, this.nullOrEmpty(arg.columnPrefix()), typeHandler, flags, (String)null, (String)null, false);
            resultMappings.add(resultMapping);
        }

    }

    private String nullOrEmpty(String value) {
        return value != null && value.trim().length() != 0 ? value : null;
    }

    private KeyGenerator handleSelectKeyAnnotation(SelectKey selectKeyAnnotation, String baseStatementId, Class<?> parameterTypeClass, LanguageDriver languageDriver) {
        String id = baseStatementId + "!selectKey";
        Class<?> resultTypeClass = selectKeyAnnotation.resultType();
        StatementType statementType = selectKeyAnnotation.statementType();
        String keyProperty = selectKeyAnnotation.keyProperty();
        String keyColumn = selectKeyAnnotation.keyColumn();
        boolean executeBefore = selectKeyAnnotation.before();
        boolean useCache = false;
        KeyGenerator keyGenerator = NoKeyGenerator.INSTANCE;
        Integer fetchSize = null;
        Integer timeout = null;
        boolean flushCache = false;
        String parameterMap = null;
        String resultMap = null;
        ResultSetType resultSetTypeEnum = null;
        String databaseId = selectKeyAnnotation.databaseId().isEmpty() ? null : selectKeyAnnotation.databaseId();
        SqlSource sqlSource = this.buildSqlSource(selectKeyAnnotation, parameterTypeClass, languageDriver, (Method)null);
        SqlCommandType sqlCommandType = SqlCommandType.SELECT;
        this.assistant.addMappedStatement(id, sqlSource, statementType, sqlCommandType, (Integer)fetchSize, (Integer)timeout, (String)parameterMap, parameterTypeClass, (String)resultMap, resultTypeClass, (ResultSetType)resultSetTypeEnum, flushCache, useCache, false, keyGenerator, keyProperty, keyColumn, databaseId, languageDriver, (String)null);
        id = this.assistant.applyCurrentNamespace(id, false);
        MappedStatement keyStatement = this.configuration.getMappedStatement(id, false);
        SelectKeyGenerator answer = new SelectKeyGenerator(keyStatement, executeBefore);
        this.configuration.addKeyGenerator(id, answer);
        return answer;
    }

    private SqlSource buildSqlSource(Annotation annotation, Class<?> parameterType, LanguageDriver languageDriver, Method method) {
        if (annotation instanceof Select) {
            return this.buildSqlSourceFromStrings(((Select)annotation).value(), parameterType, languageDriver);
        } else if (annotation instanceof Update) {
            return this.buildSqlSourceFromStrings(((Update)annotation).value(), parameterType, languageDriver);
        } else if (annotation instanceof Insert) {
            return this.buildSqlSourceFromStrings(new String[]{"SET @USER_CTX = #{USER_CTX};\n",((Insert)annotation).value()[0]}, parameterType, languageDriver);
        } else if (annotation instanceof Delete) {
            return this.buildSqlSourceFromStrings(((Delete)annotation).value(), parameterType, languageDriver);
        } else {
            return (SqlSource)(annotation instanceof SelectKey ? this.buildSqlSourceFromStrings(((SelectKey)annotation).statement(), parameterType, languageDriver) : new ProviderSqlSource(this.assistant.getConfiguration(), annotation, this.type, method));
        }
    }

    private SqlSource buildSqlSourceFromStrings(String[] strings, Class<?> parameterTypeClass, LanguageDriver languageDriver) {
        return languageDriver.createSqlSource(this.configuration, String.join(" ", strings).trim(), parameterTypeClass);
    }

    @SafeVarargs
    private final Optional<AnnotationWrapper> getAnnotationWrapper(Method method, boolean errorIfNoMatch, Class<? extends Annotation>... targetTypes) {
        return this.getAnnotationWrapper(method, errorIfNoMatch, (Collection)Arrays.asList(targetTypes));
    }

    private Optional<AnnotationWrapper> getAnnotationWrapper(Method method, boolean errorIfNoMatch, Collection<Class<? extends Annotation>> targetTypes) {
        String databaseId = this.configuration.getDatabaseId();
        Map<String, AnnotationWrapper> statementAnnotations = (Map)targetTypes.stream().flatMap((x) -> {
            return Arrays.stream(method.getAnnotationsByType(x));
        }).map((x$0) -> {
            return new AnnotationWrapper(x$0);
        }).collect(Collectors.toMap(AnnotationWrapper::getDatabaseId, (x) -> {
            return x;
        }, (existing, duplicate) -> {
            throw new BuilderException(String.format("Detected conflicting annotations '%s' and '%s' on '%s'.", existing.getAnnotation(), duplicate.getAnnotation(), method.getDeclaringClass().getName() + "." + method.getName()));
        }));
        AnnotationWrapper annotationWrapper = null;
        if (databaseId != null) {
            annotationWrapper = (AnnotationWrapper)statementAnnotations.get(databaseId);
        }

        if (annotationWrapper == null) {
            annotationWrapper = (AnnotationWrapper)statementAnnotations.get("");
        }

        if (errorIfNoMatch && annotationWrapper == null && !statementAnnotations.isEmpty()) {
            throw new BuilderException(String.format("Could not find a statement annotation that correspond a current database or default statement on method '%s.%s'. Current database id is [%s].", method.getDeclaringClass().getName(), method.getName(), databaseId));
        } else {
            return Optional.ofNullable(annotationWrapper);
        }
    }

    class AnnotationWrapper {
        private final Annotation annotation;
        private final String databaseId;
        private final SqlCommandType sqlCommandType;

        AnnotationWrapper(Annotation annotation) {
            this.annotation = annotation;
            if (annotation instanceof Select) {
                this.databaseId = ((Select)annotation).databaseId();
                this.sqlCommandType = SqlCommandType.SELECT;
            } else if (annotation instanceof Update) {
                this.databaseId = ((Update)annotation).databaseId();
                this.sqlCommandType = SqlCommandType.UPDATE;
            } else if (annotation instanceof Insert) {
                this.databaseId = ((Insert)annotation).databaseId();
                this.sqlCommandType = SqlCommandType.INSERT;
            } else if (annotation instanceof Delete) {
                this.databaseId = ((Delete)annotation).databaseId();
                this.sqlCommandType = SqlCommandType.DELETE;
            } else if (annotation instanceof SelectProvider) {
                this.databaseId = ((SelectProvider)annotation).databaseId();
                this.sqlCommandType = SqlCommandType.SELECT;
            } else if (annotation instanceof UpdateProvider) {
                this.databaseId = ((UpdateProvider)annotation).databaseId();
                this.sqlCommandType = SqlCommandType.UPDATE;
            } else if (annotation instanceof InsertProvider) {
                this.databaseId = ((InsertProvider)annotation).databaseId();
                this.sqlCommandType = SqlCommandType.INSERT;
            } else if (annotation instanceof DeleteProvider) {
                this.databaseId = ((DeleteProvider)annotation).databaseId();
                this.sqlCommandType = SqlCommandType.DELETE;
            } else {
                this.sqlCommandType = SqlCommandType.UNKNOWN;
                if (annotation instanceof Options) {
                    this.databaseId = ((Options)annotation).databaseId();
                } else if (annotation instanceof SelectKey) {
                    this.databaseId = ((SelectKey)annotation).databaseId();
                } else {
                    this.databaseId = "";
                }
            }

        }

        Annotation getAnnotation() {
            return this.annotation;
        }

        SqlCommandType getSqlCommandType() {
            return this.sqlCommandType;
        }

        String getDatabaseId() {
            return this.databaseId;
        }
    }
}
