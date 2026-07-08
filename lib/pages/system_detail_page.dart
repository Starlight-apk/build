import 'package:flutter/material.dart';
import '../models/system_info.dart';
import '../services/hardware_service.dart';

class SystemDetailPage extends StatefulWidget {
  const SystemDetailPage({super.key});

  @override
  State<SystemDetailPage> createState() => _SystemDetailPageState();
}

class _SystemDetailPageState extends State<SystemDetailPage> {
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
    if (info == null) return const Center(child: CircularProgressIndicator());

    return Scaffold(
      appBar: AppBar(title: const Text('系统信息')),
      body: ListView(
        padding: const EdgeInsets.all(16),
        children: [
          _buildSection(theme, '概览', Icons.dashboard, [
            _buildRow('操作系统', '${info.os.platform} ${info.os.release}'),
            _buildRow('内核', info.os.kernel),
            _buildRow('架构', info.os.arch),
            _buildRow('主机名', info.os.hostname),
          ]),
          const SizedBox(height: 12),
          _buildSection(theme, 'CPU', Icons.memory, [
            _buildRow('型号', info.cpu.model),
            _buildRow('使用率', '${info.cpu.usage.toStringAsFixed(1)}%'),
            _buildRow('当前频率', '${info.cpu.currentFreq.toStringAsFixed(0)} MHz'),
            _buildRow('最大频率', '${info.cpu.maxFreq.toStringAsFixed(0)} MHz'),
            _buildRow('核心数', '${info.cpu.cores} 核'),
          ]),
          const SizedBox(height: 12),
          _buildSection(theme, '内存', Icons.memory, [
            _buildRow('已用', '${info.ram.used.toStringAsFixed(1)} GB'),
            _buildRow('总计', '${info.ram.total.toStringAsFixed(1)} GB'),
            _buildRow('使用率', '${info.ram.usage.toStringAsFixed(1)}%'),
          ]),
          const SizedBox(height: 12),
          ...info.gpus.map((gpu) => _buildSection(theme, 'GPU', Icons.videocam, [
                _buildRow('型号', gpu.model),
                _buildRow('供应商', gpu.vendor),
                _buildRow('显存', '${gpu.memoryUsed.toStringAsFixed(0)} MB / ${gpu.memoryTotal.toStringAsFixed(0)} MB'),
              ])),
          const SizedBox(height: 12),
          ...info.disks.map((disk) => _buildSection(theme, '磁盘 (${disk.mount})', Icons.storage, [
                _buildRow('文件系统', disk.fs),
                _buildRow('已用', '${disk.used.toStringAsFixed(1)} GB'),
                _buildRow('总计', '${disk.total.toStringAsFixed(1)} GB'),
                _buildRow('使用率', '${disk.usage.toStringAsFixed(1)}%'),
              ])),
        ],
      ),
    );
  }

  Widget _buildSection(ThemeData theme, String title, IconData icon, List<Widget> rows) {
    return Card(
      child: Padding(
        padding: const EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Row(
              children: [
                Icon(icon, size: 20, color: theme.colorScheme.primary),
                const SizedBox(width: 8),
                Text(title, style: theme.textTheme.titleMedium?.copyWith(fontWeight: FontWeight.bold)),
              ],
            ),
            const Divider(),
            ...rows,
          ],
        ),
      ),
    );
  }

  Widget _buildRow(String label, String value) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 4),
      child: Row(
        children: [
          Text('$label: ', style: const TextStyle(fontWeight: FontWeight.w500)),
          Expanded(child: Text(value, textAlign: TextAlign.end)),
        ],
      ),
    );
  }
}
