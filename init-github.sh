#!/bin/bash

# GitHub 仓库初始化脚本
# 使用此脚本快速初始化 Git 仓库并推送到 GitHub

set -e

echo "========================================"
echo "  极光天气 - GitHub 仓库初始化脚本"
echo "========================================"
echo ""

# 检查 Git 是否安装
if ! command -v git &> /dev/null; then
    echo "错误：Git 未安装，请先安装 Git"
    exit 1
fi

# 检查是否在 Git 仓库中
if [ ! -d ".git" ]; then
    echo "正在初始化 Git 仓库..."
    git init
    git branch -M main
fi

# 添加所有文件
echo "添加项目文件..."
git add .

# 创建初始提交
echo "创建初始提交..."
git commit -m "Initial commit: Aurora Weather App

- 基于 Jetpack Compose + Material You 的天气应用
- 支持当前天气、多日预报、天气地图
- 包含自动定位、多城市管理、天气预警功能
- 使用 OpenWeatherMap API 获取天气数据
- 通过 GitHub Actions 自动编译 APK"

# 获取远程仓库 URL
echo ""
echo "请输入你的 GitHub 仓库 URL:"
echo "格式：https://github.com/你的用户名/仓库名.git"
read -p "> " REPO_URL

if [ -z "$REPO_URL" ]; then
    echo "错误：仓库 URL 不能为空"
    exit 1
fi

# 添加远程仓库
echo "添加远程仓库..."
git remote add origin "$REPO_URL"

# 推送到 GitHub
echo "推送到 GitHub..."
git push -u origin main

echo ""
echo "========================================"
echo "  推送成功！"
echo "========================================"
echo ""
echo "接下来："
echo "1. 访问 https://github.com 查看你的仓库"
echo "2. GitHub Actions 将自动开始构建"
echo "3. 在 Actions 页面查看构建状态"
echo "4. 构建完成后在 Artifacts 中下载 APK"
echo ""
echo "如果需要配置 API Key："
echo "1. 在仓库 Settings -> Secrets and variables -> Actions 中添加"
echo "2. 新建 Secret: OPENWEATHER_API_KEY"
echo "3. 填入你的 OpenWeatherMap API Key"
echo ""
