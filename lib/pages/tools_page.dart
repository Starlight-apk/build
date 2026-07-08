import 'package:flutter/material.dart';
import 'system_detail_page.dart';
import 'network_tools_page.dart';
import '../main.dart';

class ToolsPage extends StatelessWidget {
  const ToolsPage({super.key});

  void _openPage(BuildContext context, Widget page, String title) {
    Navigator.push(
      context,
      PageRouteBuilder(
        pageBuilder: (context, animation, secondaryAnimation) => Scaffold(
          appBar: AppBar(
            title: Text(title),
          ),
          body: page,
        ),
        transitionsBuilder: (context, animation, secondaryAnimation, child) {
          return SlideTransition(
            position: Tween<Offset>(
              begin: const Offset(1, 0),
              end: Offset.zero,
            ).animate(CurvedAnimation(parent: animation, curve: Curves.easeOutCubic)),
            child: child,
          );
        },
        transitionDuration: const Duration(milliseconds: 250),
        reverseTransitionDuration: const Duration(milliseconds: 200),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);

    return Scaffold(
      appBar: AppBar(title: const Text('工具箱')),
      body: ListView(
        padding: const EdgeInsets.all(16),
        children: [
          _buildCard(theme, Icons.info_outline, '系统信息', '查看 CPU、内存、磁盘等详细信息', Colors.blue, () => _openPage(context, const SystemDetailPage(), '系统信息')),
          const SizedBox(height: 12),
          _buildCard(theme, Icons.speed, '性能监控', '实时监控系统性能', Colors.green, null),
          const SizedBox(height: 12),
          _buildCard(theme, Icons.wifi, '网络工具', '网络状态与诊断、延迟折线图', Colors.teal, () => _openPage(context, const NetworkToolsPage(), '网络工具')),
          const SizedBox(height: 12),
          _buildCard(theme, Icons.storage, '磁盘管理', '磁盘空间分析', Colors.orange, null),
          const SizedBox(height: 12),
          _buildCard(theme, Icons.battery_std, '电池信息', '电池状态与健康', Colors.purple, null),
        ],
      ),
    );
  }

  Widget _buildCard(ThemeData theme, IconData icon, String title, String subtitle, Color color, VoidCallback? onTap) {
    return Card(
      child: ListTile(
        leading: CircleAvatar(backgroundColor: color.withOpacity(0.2), child: Icon(icon, color: color)),
        title: Text(title),
        subtitle: Text(subtitle),
        trailing: const Icon(Icons.chevron_right),
        onTap: () {
          if (onTap != null) {
            onTap();
          } else {
            notificationService.show('$title 功能开发中，敬请期待！');
          }
        },
      ),
    );
  }
}
