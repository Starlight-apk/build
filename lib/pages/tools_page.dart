import 'package:flutter/material.dart';

class ToolsPage extends StatelessWidget {
  const ToolsPage({super.key});

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    return Scaffold(
      appBar: AppBar(title: const Text('工具箱')),
      body: ListView(
        padding: const EdgeInsets.all(16),
        children: [
          _buildToolCard(theme, Icons.info_outline, '系统信息', '查看 CPU、内存、磁盘等详细信息', Colors.blue, () {}),
          const SizedBox(height: 12),
          _buildToolCard(theme, Icons.speed, '性能监控', '实时监控系统性能（开发中）', Colors.green, () {}),
          const SizedBox(height: 12),
          _buildToolCard(theme, Icons.wifi, '网络工具', '网络状态与诊断（开发中）', Colors.teal, () {}),
          const SizedBox(height: 12),
          _buildToolCard(theme, Icons.storage, '磁盘管理', '磁盘空间分析（开发中）', Colors.orange, () {}),
          const SizedBox(height: 12),
          _buildToolCard(theme, Icons.battery_std, '电池信息', '电池状态与健康（开发中）', Colors.purple, () {}),
        ],
      ),
    );
  }

  Widget _buildToolCard(ThemeData theme, IconData icon, String title, String subtitle, Color color, VoidCallback onTap) {
    return Card(
      child: ListTile(
        leading: CircleAvatar(backgroundColor: color.withOpacity(0.2), child: Icon(icon, color: color)),
        title: Text(title),
        subtitle: Text(subtitle),
        trailing: const Icon(Icons.chevron_right),
        onTap: onTap,
      ),
    );
  }
}
