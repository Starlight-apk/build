import 'package:flutter/material.dart';

class SettingsPage extends StatefulWidget {
  const SettingsPage({super.key});

  @override
  State<SettingsPage> createState() => _SettingsPageState();
}

class _SettingsPageState extends State<SettingsPage> {
  bool _darkMode = true;
  Color _seedColor = const Color(0xFF3f51b5);

  final List<Color> _themeColors = [
    const Color(0xFF3f51b5), // 靛蓝
    const Color(0xFF1976d2), // 蓝色
    const Color(0xFF00796b), // 青色
    const Color(0xFF2e7d32), // 绿色
    const Color(0xFFed6c02), // 橙色
    const Color(0xFFd32f2f), // 红色
    const Color(0xFF9c27b0), // 紫色
    const Color(0xFFe91e63), // 粉色
  ];

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    return Scaffold(
      appBar: AppBar(title: const Text('设置')),
      body: ListView(
        padding: const EdgeInsets.all(16),
        children: [
          // 外观
          Card(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Padding(
                  padding: const EdgeInsets.fromLTRB(16, 16, 16, 8),
                  child: Row(
                    children: [
                      Icon(Icons.palette, size: 20, color: theme.colorScheme.primary),
                      const SizedBox(width: 8),
                      Text('外观', style: theme.textTheme.titleMedium?.copyWith(fontWeight: FontWeight.bold)),
                    ],
                  ),
                ),
                SwitchListTile(
                  title: const Text('深色模式'),
                  subtitle: const Text('切换深色/浅色主题'),
                  value: _darkMode,
                  onChanged: (v) => setState(() => _darkMode = v),
                ),
                const Divider(height: 1),
                Padding(
                  padding: const EdgeInsets.all(16),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      const Text('主题颜色', style: TextStyle(fontWeight: FontWeight.w500)),
                      const SizedBox(height: 12),
                      Wrap(
                        spacing: 12,
                        runSpacing: 12,
                        children: _themeColors.map((color) {
                          final isSelected = _seedColor.value == color.value;
                          return GestureDetector(
                            onTap: () => setState(() => _seedColor = color),
                            child: Container(
                              width: 36,
                              height: 36,
                              decoration: BoxDecoration(
                                color: color,
                                shape: BoxShape.circle,
                                border: isSelected ? Border.all(color: Colors.white, width: 3) : null,
                                boxShadow: isSelected ? [BoxShadow(color: color.withOpacity(0.5), blurRadius: 8)] : null,
                              ),
                              child: isSelected ? const Icon(Icons.check, color: Colors.white, size: 18) : null,
                            ),
                          );
                        }).toList(),
                      ),
                    ],
                  ),
                ),
              ],
            ),
          ),
          const SizedBox(height: 12),

          // 关于
          Card(
            child: Column(
              children: [
                ListTile(
                  leading: const Icon(Icons.info_outline),
                  title: const Text('版本'),
                  subtitle: const Text('v0.1.3'),
                ),
                const Divider(height: 1),
                ListTile(
                  leading: const Icon(Icons.code),
                  title: const Text('技术栈'),
                  subtitle: const Text('Flutter + Material Design 3'),
                ),
                const Divider(height: 1),
                ListTile(
                  leading: const Icon(Icons.group),
                  title: const Text('开发者'),
                  subtitle: const Text('starpak team'),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
