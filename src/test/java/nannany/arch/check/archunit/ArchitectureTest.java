package nannany.arch.check.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;


@AnalyzeClasses(packages = "nannany.arch.check.archunit")
public class ArchitectureTest {

    /**
     * serviceパッケージ配下はcontrollerに依存しない
     */
    @ArchTest
    public static final ArchRule servicesCantDependOnController = noClasses()
            .that().resideInAnyPackage("..service..")
            .should().dependOnClassesThat().resideInAPackage("..controller..");

//    @ArchTest
//    public static final ArchRule myRule = noClasses()
//            .that().resideInAnyPackage("..controller..")
//            .should().dependOnClassesThat().resideInAPackage("..service..");

//    /**
//     * RepositoryクラスにはServiceクラスからしかアクセスできない
//     * Loggerの関係で以下のテストは失敗する
//     */
//    @ArchTest
//    public static final ArchRule classDependencies = classes().that().haveNameMatching(".*Repository")
//            .should().onlyBeAccessed().byClassesThat().haveNameMatching(".*Service");


    /**
     * nannany.arch.check.archunit.repository にあるクラスは Repository で終わる命名になっている
     */
    @ArchTest
    public static final ArchRule controllerPackageContainsController =
            classes().that().haveSimpleNameEndingWith("Repository")
                    .should().resideInAPackage("nannany.arch.check.archunit.repository");


    /**
     * layeredArchitectureを使用した
     */
    @ArchTest
    public static final ArchRule layeredArchitecture = layeredArchitecture()
            .layer("Controllers").definedBy("nannany.arch.check.archunit.controller")
            .layer("Services").definedBy("nannany.arch.check.archunit.service")
            .layer("Repositories").definedBy("nannany.arch.check.archunit.repository")

            .whereLayer("Controllers").mayNotBeAccessedByAnyLayer()
            .whereLayer("Services").mayOnlyBeAccessedByLayers("Controllers")
            .whereLayer("Repositories").mayOnlyBeAccessedByLayers("Services");
}