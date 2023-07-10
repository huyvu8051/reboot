package io.huyvu.mybatix;

import com.squareup.javapoet.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@SupportedAnnotationTypes("org.apache.ibatis.annotations.Select")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class MyBatisSelectProcessor extends AbstractProcessor {

    public static final String PAGEABLE_CLASS_PREFIX = "$Pageable";
    public static final String ITEMS_METHOD_NAME_PREFIX = "items$";
    public static final String TOTAL_COUNT_METHOD_NAME_PREFIX = "totalCount$";

    @SneakyThrows
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        log.info("=============================start=================================");
        log.info(roundEnv.getElementsAnnotatedWith(Select.class).toString());
        for (Element element : roundEnv.getElementsAnnotatedWith(Select.class)) {
            analyzeMethod(element);
        }
        return true;
    }

    private void analyzeMethod(Element element) throws ClassNotFoundException {
        ExecutableElement oldMethod = (ExecutableElement) element;
        var returnType = oldMethod.getReturnType();
        if (returnType instanceof DeclaredType) {
            DeclaredType declaredReturnType = (DeclaredType) returnType;
            TypeElement typeElement = (TypeElement) declaredReturnType.asElement();
            if (notReturnPage(typeElement) || notMethod(element)) {
                log.info("skip: " + typeElement.getQualifiedName() + " " + oldMethod);
                return;
            }

            String methodName = oldMethod.getSimpleName().toString();
            var args = ParameterSpec.builder(TypeName.get(Map.class), "args").build();

            MethodSpec.Builder itemsMethodBuilder = MethodSpec.methodBuilder(ITEMS_METHOD_NAME_PREFIX + methodName)
                    .addModifiers(oldMethod.getModifiers())
                    .addParameter(args);


            MethodSpec.Builder totalCountMethodBuilder = MethodSpec.methodBuilder(TOTAL_COUNT_METHOD_NAME_PREFIX + methodName)
                    .addModifiers(oldMethod.getModifiers())
                    .returns(Integer.class)
                    .addParameter(args);


            copyMethodAnnotations(oldMethod, itemsMethodBuilder, totalCountMethodBuilder);


            TypeElement oldClassElement = (TypeElement) oldMethod.getEnclosingElement();

            TypeSpec.Builder newClassBuilder = TypeSpec.interfaceBuilder(oldClassElement.getSimpleName().toString() + PAGEABLE_CLASS_PREFIX)
                    .addAnnotation(Mapper.class)
                    .addModifiers(Modifier.PUBLIC);


            List<? extends TypeMirror> typeArguments = declaredReturnType.getTypeArguments();
            if (typeArguments.size() == 1) {
                TypeMirror typeArgument = typeArguments.get(0);
                TypeName typeName = TypeName.get(typeArgument);
                // Create a ParameterizedTypeName for List<T>
                TypeName listType = ParameterizedTypeName.get(ClassName.get(List.class), typeName);
                itemsMethodBuilder.returns(listType);

                var itemsMethod = itemsMethodBuilder.build();
                var totalCountMethod = totalCountMethodBuilder.build();

                newClassBuilder.addMethod(itemsMethod);
                newClassBuilder.addMethod(totalCountMethod);
                TypeSpec classSpec = newClassBuilder.build();
                log.info("================================Generated================================");
                log.info(classSpec.toString());
                try {
                    JavaFile.builder(getPackageName(oldMethod), classSpec).build().writeTo(processingEnv.getFiler());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void copyMethodAnnotations(ExecutableElement oldMethod, MethodSpec.Builder itemsMethodBuilder, MethodSpec.Builder totalCountMethodBuilder) throws ClassNotFoundException {
        for (AnnotationMirror annotation : oldMethod.getAnnotationMirrors()) {
            TypeElement annotationTypeElement = (TypeElement) annotation.getAnnotationType().asElement();
            String annotationClassName = annotationTypeElement.getQualifiedName().toString();
            Class<?> annotationClass = Class.forName(annotationClassName);
            var elementValues = annotation.getElementValues();

            for (var entry : elementValues.entrySet()) {
                if (annotationClass.equals(Select.class) && entry.getKey().getSimpleName().toString().equals("value")) {
                    AnnotationValue annotationValue = entry.getValue();
                    var values = (List<Object>) annotationValue.getValue();

                    var add = CodeBlock.builder()
                            .add("{")
                            .add(values.get(0).toString())
                            .add(",")
                            .add(values.get(1).toString());


                    if (methodParamsContainPaging(oldMethod)) {
                        add.add(",\" limit #{offset}, #{limit}\"");
                    }

                    CodeBlock cb = add
                            .add("}").build();

                    AnnotationSpec.Builder itemsAnnotationBuilder = AnnotationSpec.builder(annotationClass);
                    String itemsAttributeName = entry.getKey().getSimpleName().toString();
                    itemsAnnotationBuilder.addMember(itemsAttributeName, cb);
                    itemsMethodBuilder.addAnnotation(itemsAnnotationBuilder.build());

                    CodeBlock cb1 = CodeBlock.builder()
                            .add("{")
                            .add("\"select count(*) \"")
                            .add(",")
                            .add(values.get(1).toString())
                            .add("}").build();
                    AnnotationSpec.Builder totalCountAnnotationBuilder = AnnotationSpec.builder(annotationClass);
                    String totalCountAttributeName = entry.getKey().getSimpleName().toString();
                    totalCountAnnotationBuilder.addMember(totalCountAttributeName, cb1);
                    totalCountMethodBuilder.addAnnotation(totalCountAnnotationBuilder.build());

                } else {
                    AnnotationSpec.Builder annotationBuilder = AnnotationSpec.builder(annotationClass);
                    String attributeName = entry.getKey().getSimpleName().toString();
                    AnnotationValue attributeValue = entry.getValue();
                    annotationBuilder.addMember(attributeName, "$L", attributeValue);
                    itemsMethodBuilder.addAnnotation(annotationBuilder.build());
                }
            }

        }
    }

    private static boolean methodParamsContainPaging(ExecutableElement oldMethod) {
        return oldMethod.getParameters().stream().anyMatch(e -> e.asType().toString().equals(Paging.class.getName()));
    }


    private static boolean notMethod(Element element) {
        return element.getKind() != ElementKind.METHOD;
    }

    private static boolean notReturnPage(TypeElement typeElement) {
        return !typeElement.getQualifiedName().contentEquals(Page.class.getName());
    }


    private List<String> getParameterNames(ExecutableElement method) {
        return method.getParameters().stream().map(Element::getSimpleName).map(Name::toString)
                .collect(Collectors.toList());
    }

    private List<ParameterSpec> getParameters(ExecutableElement method) {
        return method.getParameters().stream()
                .map(p -> ParameterSpec.builder(TypeName.get(p.asType()), p.getSimpleName().toString()).build())
                .collect(Collectors.toList());
    }

    private String getPackageName(ExecutableElement method) {
        return processingEnv.getElementUtils().getPackageOf(method).getQualifiedName().toString();
    }
}
