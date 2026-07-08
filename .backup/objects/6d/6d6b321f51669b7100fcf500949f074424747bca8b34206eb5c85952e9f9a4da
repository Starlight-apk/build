import 'package:flutter/material.dart';

class HomePage extends StatelessWidget {
  const HomePage({super.key});

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    return Scaffold(
      appBar: AppBar(title: const Text('StarTool')),
      body: GridView.count(
        padding: const EdgeInsets.all(16),
        crossAxisCount: 2,
        mainAxisSpacing: 16,
        crossAxisSpacing: 16,
        childAspectRatio: 1.2,
        children: [
          _buildCard(theme, Icons.memory, 'CPU', '0%', Colors.blue),
          _buildCard(theme, Icons.memory, 'RAM', '0 GB', Colors.green),
          _buildCard(theme, Icons.storage, '磁盘', '0 GB', Colors.orange),
          _buildCard(theme, Icons.videocam, 'GPU', 'N/A', Colors.purple),
        ],
      ),
    );
  }

  Widget _buildCard(ThemeData theme, IconData icon, String title, String value, Color color) {
    return Card(
      child: Padding(
        padding: const EdgeInsets.all(16),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Icon(icon, size: 40, color: color),
            const SizedBox(height: 8),
            Text(title, style: theme.textTheme.titleMedium),
            Text(value, style: theme.textTheme.headlineSmall?.copyWith(color: color, fontWeight: FontWeight.bold)),
          ],
        ),
      ),
    );
  }
}
