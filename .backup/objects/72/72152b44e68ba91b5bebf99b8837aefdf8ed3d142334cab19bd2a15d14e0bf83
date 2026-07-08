import 'package:flutter/material.dart';

class AboutScreen extends StatelessWidget {
  const AboutScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    return Scaffold(
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(24),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text('Startool', style: theme.textTheme.headlineLarge),
            const SizedBox(height: 4),
            Text('StarPak\'s Not Everything Toolbox',
                style: theme.textTheme.titleMedium?.copyWith(
                    color: theme.colorScheme.primary)),
            const SizedBox(height: 24),
            Text('一个实验性、简约、现代的工具箱',
                style: theme.textTheme.bodyLarge),
            const Divider(height: 32),
            Text('版本内容', style: theme.textTheme.titleMedium),
            const SizedBox(height: 8),
            Text('版本：v0.1.4', style: theme.textTheme.bodyMedium),
            const Divider(height: 32),
            Text('版权信息', style: theme.textTheme.titleMedium),
            const SizedBox(height: 8),
            Text('版权所有 (C) 2026 StarPak Team。保留所有权利',
                style: theme.textTheme.bodyMedium),
            Text('项目使用 MIT 协议开源', style: theme.textTheme.bodyMedium),
          ],
        ),
      ),
    );
  }
}
