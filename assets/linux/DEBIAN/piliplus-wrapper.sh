#!/usr/bin/env bash
# PiliPlus wrapper - 带日志和错误弹窗
LOG_FILE="/tmp/piliplus-launch.log"
echo "[$(date)] Starting PiliPlus..." > "$LOG_FILE"
echo "Running on: $(uname -m)" >> "$LOG_FILE"
echo "Kernel: $(uname -r)" >> "$LOG_FILE"

# 检查依赖
MISSING=""
for lib in libgtk3 libmpv libwebkit2gtk-4.1 libasound2; do
    if dpkg -l 2>/dev/null | grep -qi "$lib"; then
        echo "OK: $lib found" >> "$LOG_FILE"
    else
        echo "MISSING: $lib not installed!" >> "$LOG_FILE"
        MISSING="$MISSING $lib"
    fi
done

# 设置运行时路径
export LD_LIBRARY_PATH="/opt/PiliPlus/lib:${LD_LIBRARY_PATH}"

# 尝试启动
cd /opt/PiliPlus
./piliplus "$@" 2>> "$LOG_FILE"
EXIT_CODE=$?
echo "[$(date)] Exit code: $EXIT_CODE" >> "$LOG_FILE"

# 启动失败时弹窗
if [ $EXIT_CODE -ne 0 ]; then
    if [ -n "$MISSING" ]; then
        MSG="缺少依赖库：$MISSING\n请运行：sudo apt install$MISSING"
    else
        ERR=$(grep -i 'error\|fatal\|exception\|segmentation\|not found' "$LOG_FILE" 2>/dev/null | tail -3)
        MSG="启动失败（退出码：$EXIT_CODE）\n\n$ERR\n\n完整日志：$LOG_FILE"
    fi

    # 弹窗
    for cmd in "zenity --error --title='PiliPlus 启动失败' --text='$MSG' --width=400" \
               "kdialog --error '$MSG' --title 'PiliPlus 启动失败'" \
               "xmessage -center -title 'PiliPlus 启动失败' '$MSG'"; do
        eval "$cmd" 2>/dev/null && break
    done
fi

exit $EXIT_CODE
