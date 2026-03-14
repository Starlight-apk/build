# 极光天气 (Aurora Weather)

一款采用 Material You 设计风格的现代化 Android 天气应用。

## ✨ 功能特性

- 🌤️ **实时天气** - 显示当前温度、湿度、风速、气压等信息
- 📅 **多日预报** - 提供未来 7 天天气趋势预报
- 🗺️ **天气地图** - 基于 OpenStreetMap 的天气分布图
- 📍 **自动定位** - GPS 自动获取当前位置天气
- 🏙️ **多城市管理** - 添加/切换多个城市
- ⚠️ **天气预警** - 推送恶劣天气预警通知
- 🌙 **深色模式** - 支持自动/手动切换主题
- 🎨 **Material You** - 自适应取色，个性化主题

## 🏗️ 技术栈

- **语言**: Kotlin
- **UI**: Jetpack Compose + Material 3
- **架构**: MVVM + Clean Architecture
- **依赖注入**: Hilt
- **网络**: Retrofit + OkHttp
- **本地存储**: Room + DataStore
- **地图**: OSMDroid (OpenStreetMap)
- **动画**: Lottie + Compose Animation
- **后台任务**: WorkManager

## 📋 系统要求

- Android 12.0+ (API 31)
- 网络权限
- 位置权限（可选）

## 🚀 构建说明

### 前置条件

1. Android Studio Hedgehog (2023.1.1) 或更高版本
2. JDK 17
3. Android SDK (API 34)

### 构建步骤

```bash
# 克隆项目
git clone https://github.com/yourusername/aurora-weather.git
cd aurora-weather

# 使用 Gradle 构建 Debug 版本
./gradlew assembleDebug

# 或使用 Android Studio 打开项目并构建
```

### GitHub Actions 自动构建

推送代码到 GitHub 后，会自动触发构建流程：

1. 访问 [Actions](../../actions) 查看构建状态
2. 在 [Releases](../../releases) 下载 APK

## 📁 项目结构

```
app/
├── src/main/
│   ├── java/com/aurora/weather/
│   │   ├── ui/              # UI 层 (Compose)
│   │   ├── data/            # 数据层 (API, Repository)
│   │   ├── domain/          # 业务逻辑层
│   │   └── util/            # 工具类
│   ├── res/                 # 资源文件
│   └── AndroidManifest.xml
└── build.gradle.kts
```

## 🔑 API 配置

本项目使用 OpenWeatherMap API 获取天气数据。

1. 访问 [OpenWeatherMap](https://openweathermap.org/) 注册账号
2. 创建 API Key
3. 在 `local.properties` 中添加：
   ```properties
   OPENWEATHER_API_KEY=your_api_key_here
   ```

## 📄 开源协议

MIT License

## 🤝 贡献

欢迎提交 Issue 和 Pull Request！

## 📞 联系方式

如有问题或建议，请提交 Issue。
