## Configuration

### 1. Gradle Configuration
- **`settings.gradle.kts`**: Set `rootProject.name` to your project's name.
- **`gradle.properties`**: Update properties such as `mod_id`, `version`, and `group` to match your mod's details.

### 2. Package & Directory Refactoring
1.  **Rename Directories**: In `common`, `fabric`, and `neoforge` source sets, refactor the package directories to match the `group` defined in `gradle.properties`.
2.  **Refactor Main Class**: Perform a project-wide find and replace for the placeholder package `com.example.examplemod.ExampleModKt` with your actual main class path.

### 3. Service Loader Configuration
For both `fabric` and `neoforge` source sets, navigate to `src/main/resources/META-INF/services/`:
1.  **Rename Service File**: Rename the service file (e.g., `com.example.examplemod.platform.services.PlatformHelper`) to use your mod's package structure (e.g., `xyz.milosworks.klib.platform.services.PlatformHelper`).
2.  **Update Service Content**: Open the file and change its content to point to your implementation class (e.g., change `com.example.examplemod.platform.FabricPlatformHelper` to `xyz.milosworks.klib.platform.FabricPlatformHelper`).

### 4. Optional: Removing Mixins
If your mod does not require mixins, follow these steps to remove them:

#### 1. Delete Files
Remove the following files and directories:
- `common/src/main/java/`
- `common/src/main/resources/examplemod.mixins.json`
- `fabric/src/main/java/`
- `fabric/src/main/resources/examplemod.fabric.mixins.json`

#### 2. Update Mod Metadata
- **`fabric.mod.json`**: Remove the `mixins` array.
- **`neoforge.mods.toml`**: Remove the two `[[mixins]]` tables.