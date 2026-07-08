import 'package:flutter/material.dart';
import 'system_detail_page.dart';
import 'network_tools_page.dart';
import '../main.dart';

class ToolsPage extends StatefulWidget {
  const ToolsPage({super.key});

  @override
  State<ToolsPage> createState() => _ToolsPageState();
}

class _ToolsPageState extends State<ToolsPage> {
  String? _currentPage;

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);

    if (_currentPage == 'system') {
      return const SystemDetailPage();
    }
    if (_currentPage == 'network') {
      return const NetworkToolsPage();
    }

    return Scaffold(
      appBar: AppBar(title: const Text('工具箱')),
      body: ListView(
        padding: const EdgeInsets.all(16),
        children: [
          _buildCard(theme, Icons.info_outline, '系统信息', '查看 CPU、内存、磁盘等详细信息', Colors.blue, 'system'),
          const SizedBox(height: 12),
          _buildCard(theme, Icons.speed, '性能监控', '实时监控系统性能', Colors.green, null),
          const SizedBox(height: 12),
          _buildCard(theme, Icons.wifi, '网络工具', '网络状态与诊断、延迟折线图', Colors.teal, 'network'),
          const SizedBox(height: 12),
          _buildCard(theme, Icons.storage, '磁盘管理', '磁盘空间分析', Colors.orange, null),
          const SizedBox(height: 12),
          _buildCard(theme, Icons.battery_std, '电池信息', '电池状态与健康', Colors.purple, null),
        ],
      ),
    );
  }

  Widget _buildCard(ThemeData theme, IconData icon, String title, String subtitle, Color color, String? page) {
    return Card(
      child: ListTile(
        leading: CircleAvatar(backgroundColor: color.withOpacity(0.2), child: Icon(icon, color: color)),
        title: Text(title),
        subtitle: Text(subtitle),
        trailing: const Icon(Icons.chevron_right),
        onTap: () {
          if (page != null) {
            setState(() => _currentPage = page);
          } else {
            notificationService.show('$title 功能开发中，敬请期待！');
          }
        },
      ),
    );
  }
}
