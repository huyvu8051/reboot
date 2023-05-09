package io.huyvu.reboot.backend.config.mybatis;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.*;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AutoService(Processor.class)
@SupportedAnnotationTypes("io.huyvu.reboot.backend.config.mybatis.MyBatisSelect")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class MyBatisSelectProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(MyBatisSelect.class)) {
            if (element.getKind() != ElementKind.METHOD) {
                continue;
            }

            ExecutableElement method = (ExecutableElement) element;
            String methodName = method.getSimpleName().toString();
            String newMethodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);

            MethodSpec.Builder builder = MethodSpec.methodBuilder(newMethodName)
                    .addModifiers(Modifier.PUBLIC)
                    .returns(TypeName.get(method.getReturnType()))
                    .addParameters(getParameters(method))
                    .addStatement("return $L($L)", methodName, getParameterNames(method));


            MethodSpec newMethod = builder.build();

            TypeElement classElement = (TypeElement) method.getEnclosingElement();

            TypeSpec.Builder classBuilder = TypeSpec.classBuilder(classElement.getSimpleName().toString() + "Dick");

            classBuilder.addMethod(newMethod);

            TypeSpec classSpec = classBuilder.build();

            try {
                JavaFile.builder(getPackageName(method), classSpec).build().writeTo(processingEnv.getFiler());
            } catch (IOException e) {
                e.printStackTrace();
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
