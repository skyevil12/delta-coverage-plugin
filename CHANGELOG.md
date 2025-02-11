# Delta-Coverage Gradle plugin Changelog

## 2.0.1
### Fixed
- Fixed build failure when coverage rules are satisfied and coverage engine is INTELLIJ.


## 2.0.0
### Added
- Added support of [Intellij coverage](https://github.com/JetBrains/intellij-coverage).

### Breaking changes
- Base report directory is changed from `build/reports/jacoco/deltaCoverage` to `build/reports/coverage-reports`.
- `jacocoExecFiles` property of `DeltaCoverageConfiguration` extension is renamed to `coverageBinaryFiles`.
- Now auto-applying of JaCoCo(or Kover) plugin is configured via the Delta Coverage plugin extension:
  ```kts
  // kotlin gradle dsl 
  configure<DeltaCoverageConfiguration> {
    coverage {
        engine = CoverageEngine.JACOCO // Default is 'JACOCO'.
        autoApplyPlugin = true // Default is 'true'.
    } 
  }
  ```
  Auto-applying via a project property `io.github.surpsg.delta-coverage.auto-apply-jacoco` is removed.
- Removed deprecated properties from `DeltaCoverageConfiguration` extension:
  - `minLines`
  - `minBranches`
  - `minInstructions`
  See README for the new API.

### Changed
- Min supported Gradle version is **5.6**.
- CSV report generation is deprecated and will be removed in the next major release.

### Dependency updates
- Official support of Gradle **8.5**.


## 1.3.0
### Dependency updates
- Official support of Gradle 8.4
- Updated JaCoCo dependency to [0.8.11](https://github.com/jacoco/jacoco/releases/tag/v0.8.11)


## 1.2.0
- Support java records
### Dependency updates
- Official support of Gradle 8.3


## 1.1.0
### Added
- Ignore coverage violation if a coverage entity count is less than threshold
  <details>
    <summary>Usage example</summary>
        
    ```kts
    // kotlin gradle dsl 
    configure<io.github.surpsg.deltacoverage.gradle.DeltaCoverageConfiguration> {
      violationRules {
         
          rule(io.github.surpsg.deltacoverage.gradle.CoverageEntity.INSTRUCTION) {
              minCoverageRatio.set(0.9)
              entityCountThreshold.set(1234)
          }
          rule(io.github.surpsg.deltacoverage.gradle.CoverageEntity.LINE) {
              // ... 
          }
          rule(io.github.surpsg.deltacoverage.gradle.CoverageEntity.BRANCH) {
              // ...
          }
  
          // OR configure altogether
          all {
              minCoverageRatio.set(0.6d)
              entityCountThreshold.set(456)
          }  
      }
    }
        
    ```
    </details>
### Dependency updates
- Updated JaCoCo dependency to [0.8.10](https://github.com/jacoco/jacoco/releases/tag/v0.8.10)
- Official support Gradle `7.2.+`.


## 1.0.0
### Added
- `Delta Coverage` plugin applies JaCoCo plugin to a project and all it's subprojects
  - could be disabled by adding property to `gradle.properties`:
  ```
  io.github.surpsg.delta-coverage.auto-apply-jacoco=false
  ```
- Support of Gradle configuration cache
- Support of classes exclusion #69

### Changed
- Official support Gradle `8.1.+`.
- Updated JaCoCo dependency to [0.8.9](https://github.com/jacoco/jacoco/releases/tag/v0.8.9)
- Min supported Gradle is `5.1`.
- `compareWith` and `failIfCoverageLessThan` are changed to infix function: 
  - <details>
    <summary>Usage example</summary>
    
     ```kotlin
    // kotlin gradle dsl 
    configure<io.github.surpsg.deltacoverage.gradle.DeltaCoverageConfiguration> {
        // both are correct
        diffSource.git compareWith "HEAD"
        diffSource.git.compareWith("HEAD")
        
        // both are correct
        violationRules failIfCoverageLessThan 0.7
        violationRules.failIfCoverageLessThan(0.7)
    }
    
    ```
    </details>
- `deltaCoverage` task depends on `classes` tasks.
- Replaced the plugin extension values with [properties](https://docs.gradle.org/current/userguide/lazy_configuration.html)
