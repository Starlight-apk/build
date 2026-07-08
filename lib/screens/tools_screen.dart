import 'package:flutter/material.dart';

class ToolsScreen extends StatefulWidget {
  const ToolsScreen({super.key});

  @override
  State<ToolsScreen> createState() => _ToolsScreenState();
}

class _ToolsScreenState extends State<ToolsScreen> {
  int _selectedTab = 0;

  final _tabs = [
    '系统信息与硬件监控工具',
    '驱动程序管理工具',
    '媒体工具',
    '文件工具',
    '开发人员工具',
    '其它工具',
  ];

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    return Scaffold(
      body: Column(
        children: [
          SizedBox(
            height: 48,
            child: ListView(
              scrollDirection: Axis.horizontal,
              children: List.generate(_tabs.length, (i) {
                return Padding(
                  padding: const EdgeInsets.symmetric(horizontal: 4),
                  child: ChoiceChip(
                    label: Text(_tabs[i], style: const TextStyle(fontSize: 12)),
                    selected: _selectedTab == i,
                    onSelected: (_) => setState(() => _selectedTab = i),
                  ),
                );
              }),
            ),
          ),
          const Divider(height: 1),
          Expanded(
            child: ListView(
              padding: const EdgeInsets.all(12),
              children: [
                Text(
                  _tabs[_selectedTab],
                  style: theme.textTheme.titleSmall?.copyWith(
                    color: theme.colorScheme.onSurfaceVariant,
                  ),
                ),
                const SizedBox(height: 12),
                ...List.generate(5, (i) => Padding(
                  padding: const EdgeInsets.only(bottom: 8),
                  child: Card(
                    color: theme.colorScheme.surfaceVariant,
                    child: ListTile(
                      title: Text('工具名称', style: theme.textTheme.titleSmall),
                      subtitle: Text('工具描述', style: theme.textTheme.bodySmall),
                    ),
                  ),
                )),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
