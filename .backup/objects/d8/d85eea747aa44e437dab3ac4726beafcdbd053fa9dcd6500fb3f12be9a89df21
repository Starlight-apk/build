import 'package:flutter/material.dart';

class SettingsScreen extends StatelessWidget {
  const SettingsScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    return Scaffold(
      body: Padding(
        padding: const EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text('程序外观', style: theme.textTheme.titleMedium),
            const SizedBox(height: 16),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Text('深色主题', style: theme.textTheme.bodyLarge),
                Switch(value: true, onChanged: (_) {}),
              ],
            ),
            const Divider(),
            const SizedBox(height: 8),
            Text('颜色主题 (HEX)', style: theme.textTheme.bodyLarge),
            const SizedBox(height: 4),
            Text('输入 6 位 HEX，例如：#6750A4',
                style: theme.textTheme.bodySmall?.copyWith(
                    color: theme.colorScheme.onSurfaceVariant)),
          ],
        ),
      ),
    );
  }
}
