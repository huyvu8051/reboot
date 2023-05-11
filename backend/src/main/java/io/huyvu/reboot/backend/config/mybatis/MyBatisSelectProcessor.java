package io.huyvu.reboot.backend.config.mybatis;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@AutoService(Processor.class)
@SupportedAnnotationTypes("org.apache.ibatis.annotations.Select")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class MyBatisSelectProcessor extends AbstractProcessor {

    public static final String PAGEABLE = "$Pageable";

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

            MethodSpec.Builder newMethodBuilder = MethodSpec.methodBuilder(methodName)
                    .addModifiers(oldMethod.getModifiers())
                    // .addParameters(getParameters(oldMethod));
                    .addParameter(args);

            // copy all annotation and it values
            copyAnnotations(oldMethod, newMethodBuilder);


            TypeElement oldClassElement = (TypeElement) oldMethod.getEnclosingElement();

            TypeSpec.Builder newClassBuilder = TypeSpec.interfaceBuilder(oldClassElement.getSimpleName().toString() + PAGEABLE)
                    .addAnnotation(Mapper.class)
                    .addModifiers(Modifier.PUBLIC);


            List<? extends TypeMirror> typeArguments = declaredReturnType.getTypeArguments();
            if (typeArguments.size() == 1) {
                TypeMirror typeArgument = typeArguments.get(0);
                TypeName typeName = TypeName.get(typeArgument);
                // Create a ParameterizedTypeName for List<T>
                TypeName listType = ParameterizedTypeName.get(ClassName.get(List.class), typeName);
                newMethodBuilder.returns(listType);

                var newMethod = newMethodBuilder.build();

                newClassBuilder.addMethod(newMethod);
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

    private static boolean notMethod(Element element) {
        return element.getKind() != ElementKind.METHOD;
    }

    private static boolean notReturnPage(TypeElement typeElement) {
        return !typeElement.getQualifiedName().contentEquals(Page.class.getName());
    }

    private static void copyAnnotations(ExecutableElement oldMethod, MethodSpec.Builder newMethodBuilder) throws ClassNotFoundException {
        for (AnnotationMirror annotation : oldMethod.getAnnotationMirrors()) {

            TypeElement annotationTypeElement = (TypeElement) annotation.getAnnotationType().asElement();
            String annotationClassName = annotationTypeElement.getQualifiedName().toString();
            Class<?> annotationClass = Class.forName(annotationClassName);
            AnnotationSpec.Builder annotationBuilder = AnnotationSpec.builder(annotationClass);
            var elementValues = annotation.getElementValues();

            for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : elementValues.entrySet()) {
                String attributeName = entry.getKey().getSimpleName().toString();
                AnnotationValue attributeValue = entry.getValue();
                annotationBuilder.addMember(attributeName, "$L", attributeValue);
            }

            newMethodBuilder.addAnnotation(annotationBuilder.build());
        }
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
