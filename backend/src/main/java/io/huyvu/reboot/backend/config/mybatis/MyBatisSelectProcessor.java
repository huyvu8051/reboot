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
            if (element.getKind() != ElementKind.METHOD) {
                continue;
            }

            ExecutableElement oldMethod = (ExecutableElement) element;

            var returnType = oldMethod.getReturnType();

            String methodName = oldMethod.getSimpleName().toString();

            var args = ParameterSpec.builder(TypeName.get(Map.class), "args").build();

            MethodSpec.Builder newMethodBuilder = MethodSpec.methodBuilder(methodName)
                    .addModifiers(oldMethod.getModifiers())
                   // .addParameters(getParameters(oldMethod));
                    .addParameter(args);

            // copy all annotation and it values
            for (AnnotationMirror annotation : oldMethod.getAnnotationMirrors()) {

                TypeElement annotationTypeElement = (TypeElement) annotation.getAnnotationType().asElement();
                String annotationClassName = annotationTypeElement.getQualifiedName().toString();
                AnnotationSpec.Builder annotationBuilder = AnnotationSpec.builder(Class.forName(annotationClassName));

                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : annotation.getElementValues().entrySet()) {
                    String attributeName = entry.getKey().getSimpleName().toString();
                    AnnotationValue attributeValue = entry.getValue();
                    annotationBuilder.addMember(attributeName, "$L", attributeValue);
                }

                 newMethodBuilder.addAnnotation(annotationBuilder.build());
            }


            TypeElement classElement = (TypeElement) oldMethod.getEnclosingElement();

            TypeSpec.Builder classBuilder = TypeSpec.interfaceBuilder(classElement.getSimpleName().toString() + PAGEABLE)
                    .addAnnotation(Mapper.class)
                    .addModifiers(Modifier.PUBLIC);

            if (returnType instanceof DeclaredType) {
                DeclaredType declaredReturnType = (DeclaredType) returnType;
                TypeElement typeElement = (TypeElement) declaredReturnType.asElement();
                if (!typeElement.getQualifiedName().contentEquals(Page.class.getName())) {
                    log.info("skip: " + typeElement.getQualifiedName() + " " + oldMethod);
                    continue;
                }
                List<? extends TypeMirror> typeArguments = declaredReturnType.getTypeArguments();
                if (typeArguments.size() == 1) {
                    TypeMirror typeArgument = typeArguments.get(0);
                    TypeName typeName = TypeName.get(typeArgument);
                    // Create a ParameterizedTypeName for List<T>
                    TypeName listType = ParameterizedTypeName.get(ClassName.get(List.class), typeName);

                    newMethodBuilder.returns(listType);

                    var newMethod = newMethodBuilder.build();

                    classBuilder.addMethod(newMethod);
                    TypeSpec classSpec = classBuilder.build();
                    log.info("================================Generated================================\n" + classSpec);
                    try {
                        JavaFile.builder(getPackageName(oldMethod), classSpec).build().writeTo(processingEnv.getFiler());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }


        }
        return true;
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
