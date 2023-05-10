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
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@AutoService(Processor.class)
@SupportedAnnotationTypes("io.huyvu.reboot.backend.config.mybatis.MyBatisSelect")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class MyBatisSelectProcessor extends AbstractProcessor {

    @SneakyThrows
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(MyBatisSelect.class)) {
            if (element.getKind() != ElementKind.METHOD) {
                continue;
            }

            ExecutableElement oldMethod = (ExecutableElement) element;

            var returnType = oldMethod.getReturnType();
            if (returnType instanceof DeclaredType) {
                DeclaredType declaredReturnType = (DeclaredType) returnType;
                TypeElement typeElement = (TypeElement) declaredReturnType.asElement();
                if (typeElement.getQualifiedName().contentEquals(List.class.getName())) {
                    System.out.println("skip " + oldMethod);
                    continue;
                }
            }



            String methodName = oldMethod.getSimpleName().toString();

            MethodSpec.Builder newMethodBuilder = MethodSpec.methodBuilder(methodName)
                    .addModifiers(oldMethod.getModifiers())
                    .addParameters(getParameters(oldMethod));

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

              //  newMethodBuilder.addAnnotation(annotationBuilder.build());
            }


            TypeElement classElement = (TypeElement) oldMethod.getEnclosingElement();

            TypeSpec.Builder classBuilder = TypeSpec.interfaceBuilder(classElement.getSimpleName().toString() + "Pageable")
                    .addAnnotation(Mapper.class)
                    .addModifiers(Modifier.PUBLIC);

            if (returnType instanceof DeclaredType) {
                DeclaredType declaredReturnType = (DeclaredType) returnType;
                List<? extends TypeMirror> typeArguments = declaredReturnType.getTypeArguments();
                if (typeArguments.size() == 1) {
                    TypeMirror typeArgument = typeArguments.get(0);
                    TypeName typeName = TypeName.get(typeArgument);
                    // Create a ParameterizedTypeName for List<T>
                    TypeName listType = ParameterizedTypeName.get(ClassName.get(List.class), typeName);

                    newMethodBuilder.returns(listType);

                    var newMethod = newMethodBuilder.build();

                    log.info("================================Generated================================" +  newMethod);
                    classBuilder.addMethod(newMethod);
                    TypeSpec classSpec = classBuilder.build();
                    JavaFile.builder(getPackageName(oldMethod), classSpec).build().writeTo(processingEnv.getFiler());
                }
                log.info("================================Generated2================================");

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
