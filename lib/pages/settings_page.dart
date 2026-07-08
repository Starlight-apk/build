import 'package:flutter/material.dart';

class SettingsPage extends StatelessWidget {
  const SettingsPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('设置')),
      body: ListView(
        padding: const EdgeInsets.all(16),
        children: [
          Card(
            child: Column(
              children: [
                SwitchListTile(
                  title: const Text('深色模式'),
                  subtitle: const Text('切换深色/浅色主题'),
                  value: Theme.of(context).brightness == Brightness.dark,
                  onChanged: (_) {},
                ),
              ],
            ),
          ),
          const SizedBox(height: 12),
          Card(
            child: ListTile(
              leading: const Icon(Icons.palette),
              title: const Text('主题颜色'),
              subtitle: const Text('自定义应用主色调'),
              trailing: const Icon(Icons.chevron_right),
              onTap: () {},
            ),
          ),
          const SizedBox(height: 12),
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
