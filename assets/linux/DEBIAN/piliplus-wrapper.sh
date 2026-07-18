#!/usr/bin/env bash
# PiliPlus wrapper - 带日志输出，方便排查启动问题
LOG_FILE="/tmp/piliplus-launch.log"
echo "[$(date)] Starting PiliPlus..." > "$LOG_FILE"
echo "Running on: $(uname -m)" >> "$LOG_FILE"
echo "Kernel: $(uname -r)" >> "$LOG_FILE"

# 检查依赖
for lib in libgtk-3 libmpv libwebkit2gtk-4.1 libasound2; do
    dpkg -l | grep -q "$lib" && echo "OK: $lib found" >> "$LOG_FILE" || echo "MISSING: $lib not installed!" >> "$LOG_FILE"
done

# 设置运行时路径
export LD_LIBRARY_PATH="/opt/PiliPlus/lib:${LD_LIBRARY_PATH}"

# 尝试启动，捕获错误
cd /opt/PiliPlus
./piliplus "$@" 2>> "$LOG_FILE"
EXIT_CODE=$?
echo "[$(date)] Exit code: $EXIT_CODE" >> "$LOG_FILE"
exit $EXIT_CODE
