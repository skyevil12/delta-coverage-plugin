rootProject.name = "delta-coverage-gradle-plugin"
include("jacoco-filtering-extension")
include("delta-coverage")

dependencyResolutionManagement {
    versionCatalogs {
        create("deps") {
            from(files("gradle/deps.versions.toml"))
        }
    }
}

