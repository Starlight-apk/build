import 'package:flutter/material.dart';
import '../main.dart';

class SettingsPage extends StatelessWidget {
  const SettingsPage({super.key});

  final List<Color> _themeColors = const [
    Color(0xFF3f51b5),
    Color(0xFF1976d2),
    Color(0xFF00796b),
    Color(0xFF2e7d32),
    Color(0xFFed6c02),
    Color(0xFFd32f2f),
    Color(0xFF9c27b0),
    Color(0xFFe91e63),
  ];

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final settings = settingsService;

    return Scaffold(
      appBar: AppBar(title: const Text('设置')),
      body: ListView(
        padding: const EdgeInsets.all(16),
        children: [
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
                  value: settings.darkMode,
                  onChanged: (v) => settings.setDarkMode(v),
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
                          final isSelected = settings.seedColor.value == color.value;
                          return GestureDetector(
                            onTap: () => settings.setSeedColor(color),
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
                      const SizedBox(height: 16),
                      TextField(
                        decoration: const InputDecoration(
                          labelText: 'HEX 颜色值',
                          hintText: '#6750A4',
                          border: OutlineInputBorder(),
                        ),
                        onSubmitted: (value) {
                          final hex = value.trim();
                          if (hex.startsWith('#') && hex.length == 7) {
                            final color = Color(int.parse(hex.substring(1), radix: 16) + 0xFF000000);
                            settings.setSeedColor(color);
                          }
                        },
                      ),
                    ],
                  ),
                ),
              ],
            ),
          ),
          const SizedBox(height: 12),
          Card(
            child: Column(
              children: [
                ListTile(
                  leading: const Icon(Icons.info_outline),
                  title: const Text('版本'),
                  subtitle: const Text('v0.1.4'),
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
