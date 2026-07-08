import 'package:flutter/material.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    return Scaffold(
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(12),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Card(
              color: theme.colorScheme.surfaceVariant,
              child: Padding(
                padding: const EdgeInsets.all(16),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text('系统概览',
                        style: theme.textTheme.labelLarge
                            ?.copyWith(color: theme.colorScheme.onSurfaceVariant)),
                    const SizedBox(height: 12),
                    _infoRow(theme, '设备名称', 'Loading...'),
                    _infoRow(theme, '操作系统', 'Android'),
                    _infoRow(theme, '架构', 'Unknown'),
                    _infoRow(theme, '计算机名', 'Unknown'),
                  ],
                ),
              ),
            ),
            const SizedBox(height: 8),
            Row(
              children: [
                Expanded(child: _statCard(theme, 'CPU', 'Loading...')),
                const SizedBox(width: 8),
                Expanded(child: _statCard(theme, 'RAM', 'Loading...')),
              ],
            ),
            const SizedBox(height: 8),
            Row(
              children: [
                Expanded(child: _statCard(theme, 'GPU', 'Loading...')),
                const SizedBox(width: 8),
                Expanded(child: _statCard(theme, '存储', 'Loading...')),
              ],
            ),
          ],
        ),
      ),
    );
  }

  Widget _infoRow(ThemeData theme, String label, String value) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 4),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Text(label, style: theme.textTheme.bodyMedium?.copyWith(color: theme.colorScheme.onSurfaceVariant)),
          Text(value, style: theme.textTheme.bodyMedium?.copyWith(color: theme.colorScheme.onSurface)),
        ],
      ),
    );
  }

  Widget _statCard(ThemeData theme, String title, String value) {
    return Card(
      color: theme.colorScheme.surfaceVariant,
      child: Padding(
        padding: const EdgeInsets.all(12),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(title, style: theme.textTheme.titleSmall),
            const SizedBox(height: 8),
            Text(value, style: theme.textTheme.bodySmall?.copyWith(color: theme.colorScheme.onSurfaceVariant)),
          ],
        ),
      ),
    );
  }
}
