import GeneratorDefaults.DEFAULT_CLASS_NAME
import GeneratorDefaults.DEFAULT_PACKAGE_NAME
import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputDirectory
import java.io.File

abstract class AbstractGenerator : DefaultTask() {
    @get:Input
    @get:Optional
    abstract val packageName: Property<String>

    @get:Input
    @get:Optional
    abstract val className: Property<String>

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @get:Internal
    protected val outputDirWithPackage get() = File(outputDir.get().asFile, pkg.replace(".", "/"))

    @get:Internal
    protected val clazz get() = className.orNull ?: DEFAULT_CLASS_NAME

    @get:Internal
    protected val pkg get() = packageName.orNull ?: DEFAULT_PACKAGE_NAME
}