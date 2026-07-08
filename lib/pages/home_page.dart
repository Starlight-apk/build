import 'package:flutter/material.dart';
import '../models/system_info.dart';
import '../services/hardware_service.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  SystemInfo? _info;

  @override
  void initState() {
    super.initState();
    _info = HardwareService.getSystemInfo();
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final info = _info;
    if (info == null) {
      return const Scaffold(body: Center(child: CircularProgressIndicator()));
    }

    return Scaffold(
      appBar: AppBar(title: const Text('StarTool')),
      body: ListView(
        padding: const EdgeInsets.all(16),
        children: [
          _buildOverviewCard(theme, info.os),
          const SizedBox(height: 12),
          _buildProgressCard(
            theme: theme,
            title: 'CPU',
            value: info.cpu.usage,
            subtitle: info.cpu.model,
            detail: '${info.cpu.currentFreq.toStringAsFixed(0)} MHz / ${info.cpu.maxFreq.toStringAsFixed(0)} MHz · ${info.cpu.cores} 核',
            color: Colors.blue,
          ),
          const SizedBox(height: 12),
          _buildProgressCard(
            theme: theme,
            title: '内存',
            value: info.ram.usage,
            subtitle: '${info.ram.used.toStringAsFixed(1)} GB / ${info.ram.total.toStringAsFixed(1)} GB',
            detail: '使用率 ${info.ram.usage.toStringAsFixed(1)}%',
            color: Colors.green,
          ),
          const SizedBox(height: 12),
          ...info.disks.map((disk) => _buildDiskCard(theme, disk)),
          const SizedBox(height: 12),
          ...info.gpus.map((gpu) => _buildGpuCard(theme, gpu)),
        ],
      ),
    );
  }

  Widget _buildOverviewCard(ThemeData theme, OsInfo os) {
    return Card(
      color: theme.colorScheme.surfaceVariant,
      child: Padding(
        padding: const EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text('系统概览', style: theme.textTheme.labelLarge?.copyWith(color: theme.colorScheme.onSurfaceVariant)),
            const SizedBox(height: 12),
            _overviewRow(theme, '设备名称', os.hostname),
            _overviewRow(theme, '操作系统', os.distro),
            _overviewRow(theme, '架构', os.arch),
            _overviewRow(theme, '内核', os.kernel),
          ],
        ),
      ),
    );
  }

  Widget _overviewRow(ThemeData theme, String label, String value) {
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

  Widget _buildProgressCard({
    required ThemeData theme,
    required String title,
    required double value,
    required String subtitle,
    required String detail,
    required Color color,
  }) {
    return Card(
      color: theme.colorScheme.surfaceVariant,
      child: Padding(
        padding: const EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(title, style: theme.textTheme.titleSmall),
            const SizedBox(height: 8),
            LinearProgressIndicator(value: value / 100, backgroundColor: color.withOpacity(0.15), valueColor: AlwaysStoppedAnimation(color)),
            const SizedBox(height: 8),
            Text(subtitle, style: theme.textTheme.bodyMedium),
            if (detail.isNotEmpty) Text(detail, style: theme.textTheme.bodySmall?.copyWith(color: theme.colorScheme.onSurfaceVariant)),
          ],
        ),
      ),
    );
  }

  Widget _buildDiskCard(ThemeData theme, DiskInfo disk) {
    return Card(
      color: theme.colorScheme.surfaceVariant,
      child: Padding(
        padding: const EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text('存储 (${disk.mount})', style: theme.textTheme.titleSmall),
            const SizedBox(height: 8),
            LinearProgressIndicator(value: disk.usage / 100, backgroundColor: Colors.orange.withOpacity(0.15), valueColor: const AlwaysStoppedAnimation(Colors.orange)),
            const SizedBox(height: 8),
            Text('${disk.used.toStringAsFixed(1)} GB / ${disk.total.toStringAsFixed(1)} GB', style: theme.textTheme.bodyMedium),
            Text('文件系统: ${disk.fs}', style: theme.textTheme.bodySmall?.copyWith(color: theme.colorScheme.onSurfaceVariant)),
          ],
        ),
      ),
    );
  }

  Widget _buildGpuCard(ThemeData theme, GpuInfo gpu) {
    return Card(
      color: theme.colorScheme.surfaceVariant,
      child: Padding(
        padding: const EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text('GPU', style: theme.textTheme.titleSmall),
            const SizedBox(height: 8),
            Text(gpu.model, style: theme.textTheme.bodyMedium),
            Text('${gpu.vendor} · 显存 ${gpu.memoryUsed.toStringAsFixed(0)} MB / ${gpu.memoryTotal.toStringAsFixed(0)} MB',
                style: theme.textTheme.bodySmall?.copyWith(color: theme.colorScheme.onSurfaceVariant)),
          ],
        ),
      ),
    );
  }
}
