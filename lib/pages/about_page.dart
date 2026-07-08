import 'package:flutter/material.dart';

class AboutPage extends StatelessWidget {
  const AboutPage({super.key});

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    return Scaffold(
      appBar: AppBar(title: const Text('关于')),
      body: Center(
        child: Padding(
          padding: const EdgeInsets.all(32),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Container(
                width: 100,
                height: 100,
                decoration: BoxDecoration(
                  shape: BoxShape.circle,
                  gradient: LinearGradient(
                    colors: [Colors.purple, Colors.blue],
                  ),
                ),
                child: const Icon(Icons.auto_awesome, size: 50, color: Colors.white),
              ),
              const SizedBox(height: 24),
              Text('StarTool', style: theme.textTheme.headlineMedium?.copyWith(fontWeight: FontWeight.bold)),
              const SizedBox(height: 8),
              Text('星空工具箱，万物皆可测', style: theme.textTheme.bodyLarge?.copyWith(color: theme.colorScheme.primary)),
              const SizedBox(height: 32),
              Text('版权所有 © 2026 starpak team', style: theme.textTheme.bodySmall),
              const SizedBox(height: 4),
              Text('基于 MIT 许可证开源', style: theme.textTheme.bodySmall),
              const SizedBox(height: 24),
              Text('致敬', style: theme.textTheme.titleMedium?.copyWith(fontWeight: FontWeight.bold)),
              const SizedBox(height: 8),
              Text('本应用基于 HOE Team 的 NNETB 项目重构', style: theme.textTheme.bodySmall),
              Text('致敬原作者 HOE Team 的开源精神与卓越工作', style: theme.textTheme.bodySmall),
              const SizedBox(height: 24),
              const Icon(Icons.email_outlined, size: 16),
              const SizedBox(width: 4),
              Text('falck@foxmail.com', style: theme.textTheme.bodySmall),
            ],
          ),
        ),
      ),
    );
  }
}
