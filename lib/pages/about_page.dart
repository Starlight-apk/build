import 'package:flutter/material.dart';

class AboutPage extends StatelessWidget {
  const AboutPage({super.key});

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    return Scaffold(
      appBar: AppBar(title: const Text('关于')),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(24),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text('Startool', style: theme.textTheme.headlineLarge),
            const SizedBox(height: 4),
            Text("StarPak's Not Everything Toolbox",
                style: theme.textTheme.titleMedium?.copyWith(color: theme.colorScheme.primary)),
            const SizedBox(height: 24),
            Text('一个实验性、简约、现代的工具箱', style: theme.textTheme.bodyLarge),
            const Divider(height: 32),
            Text('版本内容', style: theme.textTheme.titleMedium),
            const SizedBox(height: 8),
            Text('分发渠道：Protostomia (Beta)', style: theme.textTheme.bodyMedium),
            Text('版本：v0.1.4', style: theme.textTheme.bodyMedium),
            const Divider(height: 32),
            Text('版权信息', style: theme.textTheme.titleMedium),
            const SizedBox(height: 8),
            Text('版权所有 (C) 2026 StarPak Team。保留所有权利', style: theme.textTheme.bodyMedium),
            Text('项目使用 MIT 协议开源', style: theme.textTheme.bodyMedium),
            const Divider(height: 32),
            Text('外部库使用说明', style: theme.textTheme.titleMedium),
            const SizedBox(height: 12),
            Text('Flutter (跨平台移动框架)', style: theme.textTheme.titleSmall?.copyWith(color: theme.colorScheme.primary)),
            const SizedBox(height: 4),
            Text('BSD-3-Clause 许可证', style: theme.textTheme.bodySmall),
            const SizedBox(height: 12),
            Text('Material Design Icons (图标库)', style: theme.textTheme.titleSmall?.copyWith(color: theme.colorScheme.primary)),
            const SizedBox(height: 4),
            Text('Apache 2.0 许可证', style: theme.textTheme.bodySmall),
            const Divider(height: 32),
            Text('致敬', style: theme.textTheme.titleMedium),
            const SizedBox(height: 8),
            Text('本应用基于 HOE Team 的 NNETB 项目重构', style: theme.textTheme.bodyMedium),
            Text('致敬原作者 HOE Team 的开源精神与卓越工作', style: theme.textTheme.bodySmall),
            const SizedBox(height: 24),
            Row(
              children: [
                const Icon(Icons.email_outlined, size: 16),
                const SizedBox(width: 4),
                Text('falck@foxmail.com', style: theme.textTheme.bodySmall),
              ],
            ),
          ],
        ),
      ),
    );
  }
}
