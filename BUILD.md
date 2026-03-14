# 极光天气 - 构建说明

## 📋 系统要求

- JDK 17+
- Android SDK (API 34)
- Android Gradle Plugin 8.2.0

## 🚀 构建方式

### 方式一：本地构建（需要 Android SDK）

1. **安装 Android SDK**
   ```bash
   # 使用 Android Studio 安装 SDK
   # 或手动下载 SDK 命令行工具
   ```

2. **配置环境变量**
   ```bash
   export ANDROID_HOME=/path/to/android/sdk
   export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools
   ```

3. **接受 SDK 许可证**
   ```bash
   sdkmanager --licenses
   ```

4. **安装必要的 SDK 组件**
   ```bash
   sdkmanager "platform-tools" "platforms;android-34" "build-tools;34.0.0"
   ```

5. **配置 API Key**
   ```bash
   # 复制配置文件模板
   cp local.properties.template local.properties
   
   # 编辑 local.properties，填入你的 OpenWeatherMap API Key
   # 或者使用 demo 模式（功能受限）
   ```

6. **构建 APK**
   ```bash
   # 构建 Debug 版本
   ./gradlew assembleDebug
   
   # 构建 Release 版本
   ./gradlew assembleRelease
   
   # 清理并构建
   ./gradlew clean assembleDebug
   ```

7. **获取 APK**
   ```
   Debug:  app/build/outputs/apk/debug/app-debug.apk
   Release: app/build/outputs/apk/release/app-release-unsigned.apk
   ```

### 方式二：GitHub Actions 自动构建（推荐）

1. **推送代码到 GitHub**
   ```bash
   git init
   git add .
   git commit -m "Initial commit"
   git branch -M main
   git remote add origin https://github.com/yourusername/aurora-weather.git
   git push -u origin main
   ```

2. **查看构建状态**
   - 访问 https://github.com/yourusername/aurora-weather/actions
   - 等待构建完成

3. **下载 APK**
   - 在 Actions 页面找到最新的构建
   - 下载 Artifact 中的 APK 文件
   - 或者在 Releases 页面下载（如果使用 tag 触发）

### 方式三：使用 Docker 构建

```bash
# 使用 Android SDK Docker 镜像
docker run --rm -v $(pwd):/app -w /app gradle:8.2-jdk17 ./gradlew assembleDebug
```

## ⚙️ 配置说明

### OpenWeatherMap API Key

1. 访问 https://openweathermap.org/api
2. 注册账号
3. 创建 API Key
4. 在 `local.properties` 中配置：
   ```properties
   OPENWEATHER_API_KEY=your_api_key_here
   ```

**注意**：免费 API Key 有 60 次/分钟的调用限制。

### 签名配置（可选）

如果要签名 Release 版本：

1. 创建 keystore 文件
2. 在 `gradle.properties` 中配置：
   ```properties
   RELEASE_STORE_FILE=/path/to/keystore.jks
   RELEASE_STORE_PASSWORD=your_password
   RELEASE_KEY_ALIAS=your_alias
   RELEASE_KEY_PASSWORD=your_key_password
   ```

## 📱 安装 APK

```bash
# 通过 ADB 安装
adb install app/build/outputs/apk/debug/app-debug.apk

# 或直接传输到手机安装
```

## 🐛 常见问题

### 1. 构建失败：SDK 未找到
确保 `ANDROID_HOME` 环境变量正确设置。

### 2. 构建失败：许可证未接受
运行 `sdkmanager --licenses` 接受所有许可证。

### 3. API 调用失败
检查 API Key 是否正确配置，或者网络是否通畅。

### 4. 内存不足
增加 Gradle JVM 参数：
```properties
org.gradle.jvmargs=-Xmx4096m
```

## 📞 获取帮助

如有问题，请提交 Issue 或查看 GitHub Actions 日志。
