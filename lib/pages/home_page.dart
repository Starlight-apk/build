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
          _buildProgressCard(
            theme: theme,
            icon: Icons.memory,
            title: 'CPU',
            value: info.cpu.usage,
            subtitle: info.cpu.model,
            detail: '${info.cpu.currentFreq.toStringAsFixed(0)} MHz / ${info.cpu.maxFreq.toStringAsFixed(0)} MHz · ${info.cpu.cores} 核',
            color: Colors.blue,
          ),
          const SizedBox(height: 12),
          _buildProgressCard(
            theme: theme,
            icon: Icons.memory,
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

  Widget _buildProgressCard({
    required ThemeData theme,
    required IconData icon,
    required String title,
    required double value,
    required String subtitle,
    required String detail,
    required Color color,
  }) {
    return Card(
      child: Padding(
        padding: const EdgeInsets.all(16),
        child: Row(
          children: [
            SizedBox(
              width: 72,
              height: 72,
              child: Stack(
                alignment: Alignment.center,
                children: [
                  SizedBox(
                    width: 64,
                    height: 64,
                    child: CircularProgressIndicator(
                      value: value / 100,
                      strokeWidth: 6,
                      backgroundColor: color.withOpacity(0.15),
                      valueColor: AlwaysStoppedAnimation<Color>(
                        value < 50 ? color : (value < 80 ? Colors.orange : Colors.red),
                      ),
                    ),
                  ),
                  Text('${value.toStringAsFixed(0)}%',
                      style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold, color: color)),
                ],
              ),
            ),
            const SizedBox(width: 16),
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(title, style: theme.textTheme.titleMedium?.copyWith(fontWeight: FontWeight.bold)),
                  const SizedBox(height: 4),
                  Text(subtitle, style: theme.textTheme.bodyMedium),
                  if (detail.isNotEmpty) ...[
                    const SizedBox(height: 2),
                    Text(detail,
                        style: theme.textTheme.bodySmall
                            ?.copyWith(color: theme.colorScheme.onSurfaceVariant)),
                  ],
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildDiskCard(ThemeData theme, DiskInfo disk) {
    return Card(
      child: Padding(
        padding: const EdgeInsets.all(16),
        child: Row(
          children: [
            SizedBox(
              width: 72,
              height: 72,
              child: Stack(
                alignment: Alignment.center,
                children: [
                  SizedBox(
                    width: 64,
                    height: 64,
                    child: CircularProgressIndicator(
                      value: disk.usage / 100,
                      strokeWidth: 6,
                      backgroundColor: Colors.orange.withOpacity(0.15),
                      valueColor: AlwaysStoppedAnimation<Color>(
                        disk.usage < 50 ? Colors.orange : (disk.usage < 80 ? Colors.orange : Colors.red),
                      ),
                    ),
                  ),
                  Text('${disk.usage.toStringAsFixed(0)}%',
                      style: const TextStyle(fontSize: 16, fontWeight: FontWeight.bold, color: Colors.orange)),
                ],
              ),
            ),
            const SizedBox(width: 16),
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text('磁盘 (${disk.mount})',
                      style: theme.textTheme.titleMedium?.copyWith(fontWeight: FontWeight.bold)),
                  const SizedBox(height: 4),
                  Text('${disk.used.toStringAsFixed(1)} GB / ${disk.total.toStringAsFixed(1)} GB',
                      style: theme.textTheme.bodyMedium),
                  const SizedBox(height: 2),
                  Text('文件系统: ${disk.fs}',
                      style: theme.textTheme.bodySmall
                          ?.copyWith(color: theme.colorScheme.onSurfaceVariant)),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildGpuCard(ThemeData theme, GpuInfo gpu) {
    return Card(
      child: Padding(
        padding: const EdgeInsets.all(16),
        child: Row(
          children: [
            Container(
              width: 64,
              height: 64,
              decoration: BoxDecoration(
                color: Colors.purple.withOpacity(0.15),
                borderRadius: BorderRadius.circular(16),
              ),
              child: const Icon(Icons.videocam, size: 32, color: Colors.purple),
            ),
            const SizedBox(width: 16),
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text('GPU', style: theme.textTheme.titleMedium?.copyWith(fontWeight: FontWeight.bold)),
                  const SizedBox(height: 4),
                  Text(gpu.model, style: theme.textTheme.bodyMedium),
                  const SizedBox(height: 2),
                  Text('${gpu.vendor} · 显存 ${gpu.memoryUsed.toStringAsFixed(0)} MB / ${gpu.memoryTotal.toStringAsFixed(0)} MB',
                      style: theme.textTheme.bodySmall
                          ?.copyWith(color: theme.colorScheme.onSurfaceVariant)),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
